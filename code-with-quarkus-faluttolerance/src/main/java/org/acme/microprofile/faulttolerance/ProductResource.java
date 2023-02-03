package org.acme.microprofile.faulttolerance;

import org.acme.microprofile.faulttolerance.entity.Product;
import org.eclipse.microprofile.faulttolerance.*;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

@Path("/api/products")
public class ProductResource {
    @Inject
    ProductService productService;

    private AtomicLong counter = new AtomicLong(0);

    @GET
    @Retry(maxRetries = 2)
    @Fallback(fallbackMethod = "retryFallbackProducts")
    public List<Product> products() {
        //Simulate errors and use falut management api
        final Long invocationNumber = counter.getAndIncrement();
        //call failure simulator api
        maybeFail(String.format("ProductResource#products invocation #%d failed", invocationNumber));
        return productService.getAllProducts();
    }

    public List<Product> retryFallbackProducts() {
        return List.of(new Product(0, "FallbackProduct", "Fallback Country", 34));
    }

    //timeout
    @GET
    @Path("/{id}/recommendations")
    @Timeout(250)
    @Fallback(fallbackMethod = "fallbackRecommendations")
    public List<Product> recommendations(@PathParam("id") int id) {
        //time calculation
        long started = System.currentTimeMillis();
        final long invocationNumber = counter.getAndIncrement();
        //simulate delay
        try {
            randomDelay();
            System.out.println(String.format("ProductResource#Recommendations invocation #%d returning successfully", invocationNumber));
            return productService.getRecommendations(id);
        } catch (InterruptedException e) {
            System.out.println(String.format("ProductResource#Recommendations invocation" +
                            " #%d timedout after %d ms", invocationNumber,
                    System.currentTimeMillis() - started));

            return null;

        }
    }

    //fallback method for timeout exception:

    public List<Product> fallbackRecommendations(int id) {
        return Collections.singletonList(productService.getProductById(1));
    }

    public List<Product> fallbackCb() {
        return List.of(new Product(0, "fallbackCb", "Fallbackcb", 34));
    }

    private void randomDelay() throws InterruptedException {
        Thread.sleep(new Random().nextInt(500));
    }

    private void maybeFail(String errorMessage) {
        if (new Random().nextBoolean()) {
            System.out.println(errorMessage);
            throw new RuntimeException("Resource Failure");
        }
    }

    //cb:
    @GET
    @Path("/cb")
    @Fallback(fallbackMethod = "fallbackCb")
    @CircuitBreaker(successThreshold = 10, requestVolumeThreshold = 4, failureRatio = 0.75, delay = 1000)
    public List<Product> productswithcb() {
        maybeFailcb();
        return productService.getAllProducts();
    }

    @GET
    @Path("/bulk")
    @Bulkhead(value = 5)
    @Fallback(fallbackMethod = "bulkheadfallback")
    public List<Product> productsWithbulkHead() {
        final long invocationNumber = counter.getAndIncrement();
        System.out.println(String.format("ProductResource#productsWithbulkHead invocation #%d returning successfully", invocationNumber));
        return productService.getAllProducts();
    }

    public List<Product> bulkheadfallback() {
          return List.of(new Product(0, "fallbackbulkhead", "fallbackbulkhead", 34));

    }

    private void maybeFailcb() {
        final Long invocationNumber = counter.getAndIncrement();

        //failure Ratio : in realtime failure ratio is calculted in % , if service fails
        //60%,start apply ciruitbreaker, cb stops further invocation if failures are dedecuted
        if (invocationNumber % 4 > 1) {
            throw new RuntimeException("Service failed");
        }
    }
}

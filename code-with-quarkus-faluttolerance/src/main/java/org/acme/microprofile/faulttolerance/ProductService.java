package org.acme.microprofile.faulttolerance;

import org.acme.microprofile.faulttolerance.entity.Product;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.util.*;
import java.util.stream.Collectors;

@ApplicationScoped
public class ProductService {
    //create list of products
    private Map<Integer, Product> productList = new HashMap<>();

    //init method : which populates some sample data
    @PostConstruct
    public void init() {
        productList.put(1, new Product(1, "Coffee", "Srilanka", 23));
        productList.put(2, new Product(2, "Tea", "China", 290));
        productList.put(3, new Product(3, "Rubber", "India", 450));
    }

    //biz api
    public List<Product> getAllProducts() {
        return new ArrayList<>(productList.values());
    }

    //product by id
    public Product getProductById(Integer id) {
        return productList.get(id);
    }

    //product Reviews/Recommendations
    public List<Product> getRecommendations(Integer id) {
        if (id == null) {
            return Collections.emptyList();
        }
        return productList.values()
                .stream()
                .filter(product -> !id.equals(product.id))
                .limit(2)
                .collect(Collectors.toList());
    }
}

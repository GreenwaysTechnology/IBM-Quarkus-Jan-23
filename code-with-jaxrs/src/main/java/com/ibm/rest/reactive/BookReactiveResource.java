package com.ibm.rest.reactive;

import com.ibm.rest.enity.Book;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Uni;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.time.Duration;
import java.util.List;

@Path("/api/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookReactiveResource {

    @GET
    @Path("/nonreactive")
    public List<Book> listBooks() {
        System.out.println("Current Thread - listBooks " + Thread.currentThread().getName());
        return List.of(new Book("1222", "Quarkus in Action", "Subramanian"));
    }

    @GET
    @Path("/reactive")
    public Uni<List<Book>> getBooks() {
        System.out.println("Current Thread - getBooks " + Thread.currentThread().getName());
        List books = List.of(new Book("1222", "Quarkus in Action", "Subramanian"));
        return Uni.createFrom().item(books);
    }

    @GET
    @Path("/dontblockeventloop")
    @Blocking //safely delegate the work to worker thread...
    public Uni<List<Book>> blockBooks() throws InterruptedException {
        System.out.println("Current Thread - getBooks " + Thread.currentThread().getName());
        //Thread.sleep(2000);
        Thread.sleep(5000);
        List books = List.of(new Book("1222", "Quarkus in Action", "Subramanian"));
        return Uni.createFrom().item(books);
    }

    @GET
    @Path("/nonblockingdelay")
    public Uni delayBooks() throws InterruptedException {
        System.out.println("Current Thread - getBooks " + Thread.currentThread().getName());
        List books = List.of(new Book("1222", "Quarkus in Action", "Subramanian"));
        return Uni.createFrom().item(books).onItem().delayIt().by(Duration.ofSeconds(2));
    }
}

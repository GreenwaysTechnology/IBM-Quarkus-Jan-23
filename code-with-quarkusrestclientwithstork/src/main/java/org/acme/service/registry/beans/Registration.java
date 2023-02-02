package org.acme.service.registry.beans;


import io.quarkus.runtime.StartupEvent;
import io.vertx.ext.consul.ConsulClientOptions;
import io.vertx.ext.consul.ServiceOptions;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.ext.consul.ConsulClient;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

@ApplicationScoped
public class Registration {

    @ConfigProperty(name = "consul.host")
    String host;
    @ConfigProperty(name = "consul.port")
    int port;

    @ConfigProperty(name = "hello-service-port", defaultValue = "8080")
    int hello;

    public void init(@Observes StartupEvent ev, Vertx vertx) {

        ConsulClient client = ConsulClient.create(vertx, new ConsulClientOptions()
                .setHost(host).setPort(port));

        client.registerServiceAndAwait(
                new ServiceOptions().setPort(hello).setAddress("localhost")
                        .setName("hello-service").setId("hello"));

    }
}
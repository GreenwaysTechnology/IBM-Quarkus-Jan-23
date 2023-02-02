    package org.acme;


    import io.quarkus.vertx.ConsumeEvent;
    import io.vertx.mutiny.core.eventbus.Message;

    import javax.enterprise.context.ApplicationScoped;

    //Listener

    //Every service is identified by address,
    //Every service has unique address, if you dont provide explicit address,  quarkus takes fully
    //qualified name of the bean like. eg org.acme.GreetingService


    @ApplicationScoped
    public class GreetingService {

        //biz logic
        //@ConsumeEvent("org.acme.GreetingService")
        @ConsumeEvent // default address  is name of the bean with package name
        public String sayHello(String name) {
            return "Hello Event Bus";
        }

        @ConsumeEvent("greeting")
        public void sayGreet(Message<String> msg) {
            //get the Message and process
            System.out.println(msg.body());
            System.out.println(msg.address());
            msg.reply("done");
        }


        //pub sub - many subscribers /consumers
        @ConsumeEvent("notification")
        public void notifyOne(Message<String> msg) {
            //get the Message and process
            System.out.println(msg.body());
            System.out.println(msg.address());
        }

        @ConsumeEvent("notification")
        public void notifyTwo(Message<String> msg) {
            //get the Message and process
            System.out.println(msg.body());
            System.out.println(msg.address());
        }
    }

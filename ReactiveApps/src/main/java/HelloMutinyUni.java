import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.groups.UniSubscribe;

public class HelloMutinyUni {
    public static void main(String[] args) {
        //create publisher
        /**
         * Uni is interface
         * createFrom is factory api to create Uni implementation Object
         * item is method(operator) , to emit event called "onitem"
         * subcribe method will return "Subscriber Object"
         */
//        UniSubscribe<String> hello = Uni.createFrom().item("Hello").subscribe();
//        //listen for events
//        hello.with(s -> System.out.println(s),err->System.out.println(err));
//
        Uni.createFrom().item("Hello").subscribe().with(s -> System.out.println(s),err->System.out.println(err));
    }
}

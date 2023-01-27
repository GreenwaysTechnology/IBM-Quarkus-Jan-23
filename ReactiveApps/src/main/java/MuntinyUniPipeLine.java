import io.smallrye.mutiny.Uni;

import java.time.Duration;

public class MuntinyUniPipeLine {
    public static void main(String[] args) {

        System.out.println("Start");
        Uni.createFrom()
                .item("hello")
                //data pipe lines
                .onItem().transform(item-> {
                    return item.toUpperCase();
                })
                //delay; subscribe after some time
                .onItem().delayIt().by(Duration.ofMillis(100))
                .subscribe().with(i->System.out.println(i));
        System.out.println("end");

    }

}

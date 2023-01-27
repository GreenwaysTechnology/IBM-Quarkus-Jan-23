import io.smallrye.mutiny.Multi;

public class MultiProcessing {
    public static void main(String[] args) {
        Multi.createFrom()
                .items(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .onItem().transform(i -> i * 2) //double it
                .select().first(3) // get first 3 items
                .filter(item -> item < 5) //filter item less than 5
                .subscribe()
                .with(item -> System.out.println(item));

    }
}

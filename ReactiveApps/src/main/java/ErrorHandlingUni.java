import io.smallrye.mutiny.Uni;

public class ErrorHandlingUni {
    public static void main(String[] args) {
//        Uni.createFrom()
//                .failure(new RuntimeException("something went wrong"))
//                .subscribe().with(System.out::println,System.out::println);
        Object fallback;
        Uni.createFrom()
                .failure(new RuntimeException("something went wrong"))
                .onFailure()
                .recoverWithItem("Fallback Response")
                .onItem().transform(item->item.toString().toUpperCase())
                .subscribe().with(System.out::println);
    }
}

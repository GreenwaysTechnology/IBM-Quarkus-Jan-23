import io.smallrye.mutiny.Uni;

public class ErrorMuntinyUni {
    public static void main(String[] args) {
        Uni.createFrom()
               .failure(new RuntimeException("something went wrong"))
                .subscribe().with(System.out::println,System.out::println);
    }
}

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.stream.IntStream;

public class Task1 {
    public void main(String[] args) throws ExecutionException, InterruptedException {
        int n, m1, m2;
        Scanner scanner = new Scanner(System.in);

        System.out.print("Массив 1: ");
        n = scanner.nextInt();
        m1 = scanner.nextInt();

        List<Integer> list1 = IntStream.rangeClosed(1, n)
                .boxed()
                .toList();

        System.out.print("Массив 2: ");
        n = scanner.nextInt();
        m2 = scanner.nextInt();

        List<Integer> list2 = IntStream.rangeClosed(1, n)
                .boxed()
                .toList();

        FutureTask<StringBuilder> future1 = new FutureTask<>(() -> (circle(list1, m1)));
        FutureTask<StringBuilder> future2 = new FutureTask<>(() -> (circle(list2, m2)));

        new Thread(future1).start();
        new Thread(future2).start();

        StringBuilder result1 = future1.get();
        StringBuilder result2 = future2.get();

        System.out.print(result1.toString() + result2.toString());

    }

    private StringBuilder circle(List<Integer> list, int m){
        StringBuilder result = new StringBuilder();
        result.append(list.getFirst());
        int i = m - 1;
        if(i > list.size() - 1){
            i %= list.size();
        }
        while(i != 0){
            result.append(list.get(i));
            i += m - 1;
            if(i > list.size() - 1){
                i %= list.size();
            }
        }
        return result;
    }
}

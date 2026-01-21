import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Task4 {

    public void main(String[] args) throws FileNotFoundException {

        if(args.length < 1) {
            System.err.println("Укажите путь к файлу как аргумент");
            return;
        }

        String filePath = args[0];

        File file = new File(filePath);
        List<Integer> nums = new ArrayList<>();
        int steps = 0;

        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.isEmpty()) continue;

            try {
                int number = Integer.parseInt(line);
                nums.add(number);
            } catch (NumberFormatException e) {
                System.err.println("Некорректное число в строке: '" + line + "'");
            }
        }

        if (nums.isEmpty()) {
            System.err.println("В файле нет корректных чисел");
            return;
        }

        Collections.sort(nums);

        for(int num : nums){
            if(steps > 20){
                System.out.println("20 ходов недостаточно для приведения всех элементов массива к одному числу");
                return;
            }
            steps += Math.abs(num - nums.get(nums.size()/2));
        }

        System.out.println(steps);
    }
}

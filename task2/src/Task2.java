import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Task2 {
    private int centerX, centerY;
    private int semiaxesA,  semiaxesB;
    private static final double EPSILON = 1e-10;

    public void main(String[] args) throws FileNotFoundException {

        if (args.length < 2) {
            System.err.println("Укажите пути к файлам как аргументы командной строки");
            return;
        }
        String ellipseFilePath = args[0];
        String pointsFilePath = args[1];

        Scanner scanner = new Scanner(new File(ellipseFilePath));

        String line = scanner.nextLine();
        String[] parts = line.split(" ");
        if (parts.length < 2) {
            System.err.println("Некорректный формат строки: " + line);
            return;
        }
        try {
            centerX = Integer.parseInt(parts[0]);
            centerY = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            System.err.println("Нечисловое значение в файле: " + line);
            return;
        }

        line = scanner.nextLine();
        parts = line.split(" ");
        if (parts.length < 2) {
            System.err.println("Некорректный формат строки: " + line);
            return;
        }
        try{
            semiaxesA = Integer.parseInt(parts[0]);
            semiaxesB = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e){
            System.err.println("Нечисловое значение в файле: " + line);
            return;
        }


        scanner = new Scanner(new File(pointsFilePath));
        while(scanner.hasNextLine()){
            line = scanner.nextLine();
            parts = line.split(" ");
            if (parts.length < 2) {
                System.err.println("Некорректный формат строки: " + line);
                return;
            }

            try{
                int x = Integer.parseInt(parts[0]);
                int y = Integer.parseInt(parts[1]);

                System.out.println(checkPointLocation(x,y));
            } catch (NumberFormatException e){
                System.err.println("Нечисловое значение в файле: " + line);
                return;
            }
        }
    }

    private int checkPointLocation(int x, int y){
        double s = Math.pow((x - centerX), 2) / Math.pow(semiaxesA, 2) + Math.pow((y - centerY), 2) / Math.pow(semiaxesB, 2);

        if (Math.abs(s - 1.0) < EPSILON) {
            return 0;
        } else if (s < 1.0) {
            return 1;
        } else {
            return 2;
        }
    }
}

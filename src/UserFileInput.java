
import java.util.Scanner;

public class UserFileInput {
    private String sourceFile;

    protected void readPathToTheFile() {

        boolean isCorrect = false; // Флаг проверки корректности входа

        while (!isCorrect) {
            Scanner user = new Scanner(System.in);
            System.out.println("Введите txt файл с номерами документов:");
            if (user.hasNextLine()) {
                sourceFile = user.nextLine();
                isCorrect = true;
            } else {
                System.out.println("Вы ввели не корректное имя файла!");
            }
        }
    }

    public String getSourceFile() {
        return sourceFile;
    }
}


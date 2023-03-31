
import java.util.Scanner;

public class UserInput {
    private boolean isCorrect = false; // Флаг проверки корректности входа
    protected String nameFile;

    protected String thePathToTheFile() {
        while (!isCorrect) {
            Scanner user = new Scanner(System.in);
            System.out.println("Введите txt файл с номерами документов:");
            if (user.hasNextLine()) {
                nameFile = user.nextLine();
                isCorrect = true;
            } else {
                System.out.println("Вы ввели не корректное имя файла!");
            }
        }
        return nameFile;
    }
}


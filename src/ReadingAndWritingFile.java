
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ReadingAndWritingFile extends UserInput {

    private List<String> file = new LinkedList<>();
    private List<String> validFile = new LinkedList<>();

    private List<String> noValidFile = new LinkedList<>();

    protected List<String> readingFile() {

        try (FileInputStream nameFile = new FileInputStream(thePathToTheFile())) {

            String text = "";
            String[] result;
            int i = -1;

            while ((i = nameFile.read()) != -1) {
                text = text + (char) i;
            }
            result = text.split("\r\n");
            file = Arrays.asList(result);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return file;
    }

    protected void sortingFile() {
        for (String number : this.file) {
            if (!((number.startsWith("docnum") && number.length() == 15) ||
                    (number.startsWith("contract") && number.length() == 15))) {
                noValidFile.add(number);
            } else {
                validFile.add(number);
            }
        }
    }

    protected void writingValidFile() {

        FileOutputStream outputValidFile = null;

        try {
            outputValidFile = new FileOutputStream("D:/Document valid file.txt");
            outputValidFile.write(validFile.toString().getBytes());

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                outputValidFile.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    protected void writingNoValidFile() {

        FileOutputStream outputNoValidFile = null;

        try {
            outputNoValidFile = new FileOutputStream("D:/Document novalid file.txt");

            int index = 0;
            for (String number : this.noValidFile) {
                if ((number.length() != 15)&&!(number.startsWith("docnum") || number.startsWith("contract"))) {
                    StringBuilder result = new StringBuilder("");
                    result.append(number).append(" -файл не начинается с последовательности docnum или contract и " +
                            "длина файла не равна 15 символам;");
                    result.append("\n");
                    String resultFinal = result.toString();
                    noValidFile.set(index, resultFinal);
                } else if (!(number.startsWith("docnum") || number.startsWith("contract"))&&(number.length() == 15)) {
                    StringBuilder result = new StringBuilder("");
                    result.append(number).append(" -файл не начинается с последовательности docnum или contract;");
                    result.append("\n");
                    String resultFinal = result.toString();
                    noValidFile.set(index, resultFinal);
                } else {
                    StringBuilder result = new StringBuilder("");
                    result.append(number).append(" -длина файла не равна 15 символам;");
                    result.append("\n");
                    String resultFinal = result.toString();
                    noValidFile.set(index, resultFinal);
                }
                index++;
            }
            outputNoValidFile.write(noValidFile.toString().getBytes());

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                outputNoValidFile.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void displayInfo() {
        System.out.println(file);
    }

    public void noValidFileInfo() {
        System.out.println(noValidFile);

    }

    public void validFileInfo() {
        System.out.println(validFile);
    }

}

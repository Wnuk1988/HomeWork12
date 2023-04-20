
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ReadingAndWritingFile extends UserFileInput {

    private List<String> listDocuments = new LinkedList<>();
    private List<String> validDocuments = new LinkedList<>();

    private List<String> noValidDocuments = new LinkedList<>();
    private boolean isRead = false;
    private boolean isAnalyze = false;

    private void readFile() {

        readPathToTheFile();

        try (FileInputStream nameFile = new FileInputStream(new String(getSourceFile()))) {

            String text = "";
            String[] result;
            int i = -1;

            while ((i = nameFile.read()) != -1) {
                text = text + (char) i;
            }
            result = text.split("\r\n");
            listDocuments = Arrays.asList(result);

            displayInfo();
            isRead = true;

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected List<String> getListDocuments() {
        return listDocuments;
    }

    protected List<String> getValidDocuments() {
        if (!isAnalyze) {
            analyzeFile();
        }
        validDocumentsInfo();
        return validDocuments;
    }

    protected List<String> getNoValidDocuments() {
        if (!isAnalyze) {
            analyzeFile();
        }
        noValidDocumentsInfo();
        return noValidDocuments;
    }

    private void analyzeFile() {
        if (!isRead) {
            readFile();
        }
        for (String number : this.listDocuments) {
            if (!((number.startsWith("docnum") && number.length() == 15) ||
                    (number.startsWith("contract") && number.length() == 15))) {
                noValidDocuments.add(number);
            } else {
                validDocuments.add(number);
            }
        }
        isAnalyze = true;
    }

    protected void writingValidDocuments() {

        FileOutputStream outputValidFile = null;

        try {
            outputValidFile = new FileOutputStream("D:/Document valid file.txt");
            outputValidFile.write(validDocuments.toString().getBytes());

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

    protected void writingNoValidDocuments() {

        FileOutputStream outputNoValidFile = null;

        try {
            outputNoValidFile = new FileOutputStream("D:/Document novalid file.txt");

            int index = 0;
            for (String number : this.noValidDocuments) {
                if ((number.length() != 15) && !(number.startsWith("docnum") || number.startsWith("contract"))) {
                    StringBuilder result = new StringBuilder("");
                    result.append(number).append(" -файл не начинается с последовательности docnum или contract и " +
                            "длина файла не равна 15 символам;");
                    result.append("\n");
                    String resultFinal = result.toString();
                    noValidDocuments.set(index, resultFinal);
                } else if (!(number.startsWith("docnum") || number.startsWith("contract")) && (number.length() == 15)) {
                    StringBuilder result = new StringBuilder("");
                    result.append(number).append(" -файл не начинается с последовательности docnum или contract;");
                    result.append("\n");
                    String resultFinal = result.toString();
                    noValidDocuments.set(index, resultFinal);
                } else {
                    StringBuilder result = new StringBuilder("");
                    result.append(number).append(" -длина файла не равна 15 символам;");
                    result.append("\n");
                    String resultFinal = result.toString();
                    noValidDocuments.set(index, resultFinal);
                }
                index++;
            }
            outputNoValidFile.write(noValidDocuments.toString().getBytes());

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

    private void displayInfo() {
        System.out.println(listDocuments);
    }

    private void noValidDocumentsInfo() {
        System.out.println(noValidDocuments);

    }

    private void validDocumentsInfo() {
        System.out.println(validDocuments);
    }

}

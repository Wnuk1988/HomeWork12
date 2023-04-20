
public class Program {
    public static void main(String[] args) {

        ReadingAndWritingFile file = new ReadingAndWritingFile();

        file.getListDocuments();
        file.getValidDocuments();
        file.getNoValidDocuments();

        file.writingValidDocuments();
        file.writingNoValidDocuments();
    }
}

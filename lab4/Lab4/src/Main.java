import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) {
        Document cv = new Document();
        cv.setTitle("Jana Kowalski - CV");
        cv.setPhoto("proba.png");
        Section s1 = new Section("Wykształcenie");
        s1.addParagraph("2000-2005 Przedszkole im. Królewny Snieżki w ...");
        s1.addParagraph("2006-2012 SP7 im Ronalda Regana w ...");
        s1.addParagraph("...");
        cv.addSection(s1);
        Section s2 = new Section("Umiejętności");
        cv.addSection(s2);
        ParagraphWithList pwl1 = new ParagraphWithList();
        pwl1.setContent("Umiejętności");
        s2.addParagraph(pwl1);
        pwl1.addItem("C");
        pwl1.addItem("C++");
        pwl1.addItem("Java");

        cv.writeHTML(System.out);
        try {
            cv.writeHTML(new PrintStream("cv.html", StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

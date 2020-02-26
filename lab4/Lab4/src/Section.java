import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Section {
    String title;
    List<Paragraph> paragraphs = new ArrayList<>();

    Section(String title) {
        this.title = title;
    }

    void setTitle(String title) {
        this.title = title;
    }

    void addParagraph(String paragraphText) {
        Paragraph p = new Paragraph(paragraphText);
        paragraphs.add(p);
    }

    void addParagraph(Paragraph p) {
        paragraphs.add(p);
    }

    void writeHTML(PrintStream out) {
        out.printf("<div><h1>%s</h1>\n", this.title);
        for (int i = 0; i < paragraphs.size(); i++) {
            paragraphs.get(i).writeHTML(out);
        }
        out.print("</div>\n");
    }
}

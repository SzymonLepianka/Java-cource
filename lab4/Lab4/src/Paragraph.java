import java.io.PrintStream;

public class Paragraph {
    String text;

    Paragraph() {
    }

    Paragraph(String text) {
        this.text = text;
    }

    void setContent(String text) {
        this.text = text;
    }

    void writeHTML(PrintStream out) {
        out.printf("<p> %s </p> \n", text);
    }
}

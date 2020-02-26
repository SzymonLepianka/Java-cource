import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class ParagraphWithList extends Paragraph {
    UnorderedList list = new UnorderedList();

    ParagraphWithList() {
    }
    ParagraphWithList(String text) {
        super(text);
    }


    void setContent(String text) {
        super.setContent(text);
    }

    void addItem(ListItem item) {
        list.addItem(item);
    }

    void addItem(String item) {
        list.addItem(item);
    }

    void writeHTML(PrintStream out) {
        super.writeHTML(out);
        list.writeHTML(out);
    }
}

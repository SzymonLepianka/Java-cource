import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class UnorderedList {
    List<ListItem> items = new ArrayList<>();

    UnorderedList(){};
    UnorderedList(String text){
        items.add(new ListItem(text));
    }

    void addItem(String ItemText) {
        ListItem i = new ListItem(ItemText);
        items.add(i);
    }

    void addItem(ListItem i) {
        items.add(i);
    }

    public void writeHTML(PrintStream out) {
        out.print("<ul>\n");
        for (ListItem item : items) {
            item.writeHTML(out);
        }
        out.print("\n</ul>");
    }
}

import java.io.PrintStream;

public class ListItem {
    String text;
    ListItem(String text){
        this.text=text;
    }
    void setContent(String text){
        this.text=text;
    }
    void writeHTML(PrintStream out){
        out.printf("<li> %s </li> \n", text);
    }
}

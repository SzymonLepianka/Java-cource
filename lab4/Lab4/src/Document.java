import java.util.ArrayList;
import java.util.List;
import java.io.PrintStream;

public class Document {
    String title;
    Photo photo;
    List<Section> sections = new ArrayList<>();
    Document(){}
    Document(String title){
        this.title = title;
    }
    void setTitle(String title) {
        this.title = title;
    }

    void setPhoto(String photoUrl) {
        this.photo = new Photo(photoUrl);
    }

    void addSection(String sectionTitle) {
        Section s = new Section(sectionTitle);
        sections.add(s);
    }

    void addSection(Section s) {
        sections.add(s);
    }

    void writeHTML(PrintStream out) {
        out.print("<!DOCTYPE html>\n");
        out.print("<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang = \"en\" lang = \"en\">\n");
        out.print("<head>\n");
        out.print("<meta charset=\"UTF-8\">\n");
        out.printf("<title>%s</title>\n", title);
        out.print("</head>\n");
        out.print("<body>\n");
        out.printf("<h1>%s</h1>\n", title);
        this.photo.writeHTML(out);
        for (int i = 0; i < sections.size(); i++) {
            sections.get(i).writeHTML(out);
        }
        out.print("</body>\n");
        out.print("</html>");
    }
}

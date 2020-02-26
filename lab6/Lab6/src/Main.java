import java.io.IOException;
import java.io.PrintStream;
import java.io.Reader;
import java.io.StringReader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Main {
    public static void main(String[] args) throws IOException {
        /*CSVReader reader = new CSVReader("missing-values.csv",";",true);
        while(reader.next()){
            int id = reader.getInt("id");
            String name = reader.get("name");
            String surname = reader.get("nazwisko");
            String street = reader.get("ulica");
            double h_number = reader.getDouble("nrdomu");
            double a_number = reader.getDouble("nrmieszkania");
            System.out.printf(Locale.US,"%d %s %s %s %f %f\n",id, name, surname, street, h_number, a_number);
        }*/


        /*CSVReader reader = new CSVReader("missing-values.csv", ";", true);

        while (reader.next()) {
            int id = reader.getInt(0);
            int parent = reader.getInt(1);
            String name = reader.get(2);
            String surname = reader.get(3);
            String street = reader.get(4);
            double h_number = reader.getDouble(5);
            double a_number = reader.getDouble(6);
            System.out.printf(Locale.US, "%d %d %s %s %s %f %f\n", id, parent, name, surname, street, h_number, a_number);
        }*/


/*
        System.out.println("xxxxxxxxxxxxxxxxxxx");
        String text = "a,b,c\n123.4,567.8,91011.12";
        reader = new CSVReader(new StringReader(text), ",", true);
        while (reader.next()) {
            String name = reader.get(0);
            String surname = reader.get(1);
            String street = reader.get(2);
            System.out.printf(Locale.US, "%s %s %s\n", name, surname, street);
        }

        System.out.println("xxxxxxxxxxxxxxxxxxx");
        LocalTime time = LocalTime.parse("12:55", DateTimeFormatter.ofPattern("HH:mm"));
        System.out.println(time);
        time = LocalTime.parse("12:55:23",DateTimeFormatter.ofPattern("HH:mm:ss"));
        System.out.println(time);

        LocalDate date = LocalDate.parse("2017-11-30", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        System.out.println(date);
        date = LocalDate.parse("23.11.2017", DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        System.out.println(date);
    }
    */
       /*CSVReader reader = new CSVReader("admin-units.csv", ",", true);
        for (int i = 0; i < 100 && reader.next(); i++) {
            String id = reader.get(0);
            String parent = reader.get(1);
            String name = reader.get(2);
            String admin_level = reader.get(3);
            String population = reader.get(4);
            String area = reader.get(5);
            String density = reader.get(6);
            System.out.printf(Locale.US, "%s %s %s %s %s %s\n", id, parent, name, admin_level, population, area, density);
        }*/
        AdminUnitList ad = new AdminUnitList();
        ad.read("admin-units.csv");
        ad.list(System.out);


        BoundingBox box = new BoundingBox();
        box.xmin = box.xmax = box.ymin = box.ymax = Double.NaN;
        box.addPoint(10, 10);
        System.out.println(box);

        System.out.println("xxxxxxxxxxxxxx");

        /////ad.filter((a->(a.adminlevel == 6 && a.parent.name.equals("województwo podkarpackie"))).list(out));

        AdminUnitQuery query = new AdminUnitQuery()
                .selectFrom(ad)
                .where(a->a.area>1000)
                .or(a->a.name.startsWith("Sz"))
                .sort((a,b)->Double.compare(a.area,b.area))
                .limit(100);
        query.execute().list(System.out);
    }
}


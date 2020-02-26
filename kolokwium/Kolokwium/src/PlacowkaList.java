import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlacowkaList {

    List<Placowka> pracownicy = new ArrayList<>();
    int wszyscy=0;

    public void read(String Javalename) throws IOException {
        CSVReader reader = new CSVReader(Javalename, ";", true);

        while (reader.next()) {
            Placowka pr = new Placowka();
            pr.REGON = reader.get(0,"");
            pr.RSPO = reader.get(1,"");
            pr.nazwa = reader.get(2,"");
            pr.rodzaj = reader.get(3,"");
            pr.dzielnica = reader.get(4,"");
            pr.adres = reader.get(5,"");
            pr.kod = reader.get(6,"");
            pr.tel = reader.get(7,"");
            pr.mail = reader.get(8,"");
            pr.dyrektor = reader.get(9,"");
            pr.organ = reader.get(10,"");
            pracownicy.add(pr);
            //wszyscy+=1;
            System.out.println(pr.toString());
        }
    }
    void list() {
        for (Placowka un : this.pracownicy ){
            System.out.println(un.toString());
        }
    }
    PlacowkaList bezuprawnien (){
        PlacowkaList wynik = new PlacowkaList();
        for (Placowka pr: pracownicy){
            if (pr.rodzaj.contains("bez upr.publ.")){
                wynik.pracownicy.add(pr);
            }
        }
        return wynik;
    }
    PlacowkaList dyrektorniekobieta (){
        PlacowkaList wynik = new PlacowkaList();
        for (Placowka un : pracownicy ){
            String []dyr = un.dyrektor.split(" ");
            if (!dyr[0].endsWith("a") && !dyr[0].isEmpty()){
                wynik.pracownicy.add(un);
            }
        }

        return wynik;
    }
    PlacowkaList przedszkola (){
        PlacowkaList wynik = new PlacowkaList();
        for (Placowka pr: pracownicy){
            if (pr.nazwa.contains("Przedszkole")){
                wynik.pracownicy.add(pr);
                //System.out.println(pr.nazwa);
            }
        }
        return wynik;
    }
    PlacowkaList sortuj (){
        PlacowkaList wynik = new PlacowkaList();
        for (Placowka pr: pracownicy) {
            wynik.pracownicy.add(pr);
            break;
        }
        for (Placowka pr: pracownicy){
            String []dyr = pr.dyrektor.split(" ");
            //for (String s: dyr) { System.out.println(s); }
            int i=0;
            int k=0;
            for (Placowka prrr: wynik.pracownicy) {
                String []dyr2 = prrr.dyrektor.split(" ");
                if (!dyr[1].isEmpty() && dyr[1].compareTo(dyr2[1]) < 0 ){//NIE DO KOŃCAmaster
                    System.out.println(dyr[1] +", "+dyr2[1]+", "+dyr[1].compareTo(dyr2[1]));
                    wynik.pracownicy.add(i,pr);
                    k=1;
                }
            }
            if (k==0)
                wynik.pracownicy.add(pr);
            i++;
        }
        return wynik;
    }

    public static void main(String[] args) throws IOException {
        PlacowkaList lista = new PlacowkaList();
        lista.read("placowki.csv");
        //lista.ile();


        PlacowkaList bezupr = lista.bezuprawnien();
        System.out.println("Placówki bez uprawnień: ");
        bezupr.list();

        PlacowkaList dyrfacet = lista.dyrektorniekobieta();
        System.out.println("Dykertor facet: ");
        //dyrfacet.list();
        for (Placowka p: dyrfacet.pracownicy
        ) {
            String []dyr = p.dyrektor.split(" ");
            System.out.print(dyr[1]+", ");
        }

        PlacowkaList przed = lista.przedszkola();
        System.out.println("\nPrzedszkola: ");
        //przed.list();
        for (Placowka p: przed.pracownicy
        ) {
            System.out.println(p.nazwa);
        }

        PlacowkaList sorttt = lista.sortuj();
        System.out.println("Posortowane: ");
        //sorttt.list();
        for (Placowka p: sorttt.pracownicy
        ) {
            String []dyr = p.dyrektor.split(" ");
            System.out.println(dyr[1]+", "+p.mail+", "+p.tel);
        }

    }
}

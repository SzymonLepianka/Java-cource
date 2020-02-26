public class Placowka {
    String REGON;
    String RSPO;
    String nazwa;
    String rodzaj;
    String dzielnica;
    String adres;
    String kod;
    String tel;
    String mail;
    String dyrektor;
    String organ;


    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(
                " " + REGON +
                        " " + RSPO +
                        " " + nazwa +
                        " " + rodzaj +
                        " " + dzielnica +
                        " " + adres +
                        " " + kod +
                        " " + tel +
                        " " + mail +
                        " " + dyrektor
                        + " " + organ);
        return sb.toString();
    }

}
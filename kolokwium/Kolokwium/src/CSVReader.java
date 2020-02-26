import java.io.*;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVReader {
    BufferedReader reader;
    String delimiter;
    boolean hasHeader;
    String[] current;
    int currentLength;


    // nazwy kolumn w takiej kolejności, jak w pliku
    List<String> columnLabels = new ArrayList<>();
    // odwzorowanie: nazwa kolumny -> numer kolumny
    Map<String, Integer> columnLabelsToInt = new HashMap<>();

    public CSVReader(String filename) throws IOException {
        this(new FileReader(filename), ",", true);
    }

    public CSVReader(String filename, String delimiter) throws IOException {
        this(new FileReader(filename), delimiter, true);
    }

    public CSVReader(String filename, String delimiter, boolean hasHeader) throws IOException {
        this(new FileReader(filename), delimiter, hasHeader);
    }

    public CSVReader(Reader reader, String delimiter, boolean hasHeader) throws IOException {
        this.reader = new BufferedReader(reader);
        this.delimiter = delimiter;
        this.hasHeader = hasHeader;
        if (hasHeader) {
            parseHeader();
        }
    }

    void parseHeader() throws IOException {
        String line = reader.readLine();
        if (line == null) {
            return;
        }

        // podziel na pola
        String[] header = line.split(delimiter);
        // przetwarzaj dane w wierszu
        for (int i = 0; i < header.length; i++) {
            // dodaj nazwy kolumn do columnLabels i numery do columnLabelsToInt
            this.columnLabels.add(header[i]);
            this.columnLabelsToInt.put(header[i], i);
        }
        //
        // System.out.println(columnLabelsToInt.toString());
    }

    public boolean next() throws IOException {
        String line = reader.readLine();
        if (line == null) {
            return false;
        }
        //this.current = line.split(delimiter);
        //this.current = line.split(delimiter + "(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
        this.current = line.split(delimiter + "(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");//maciek
        currentLength = line.length();
        /*System.out.println("xxxxxxxxxxxxxxxxx");
        for (String s:current
             ) {
            System.out.println(s);

        }
        System.out.println("xxxxxxxxxxxxxxxxxx");*/
        //System.out.println(current.toString());
        return true;
    }

    public String get(int index) {
        /*if (index < 0 || isMissing(index)) {
            return "";
        } else {
            return current[index];
        }*/
        return current[index];
    }

    public String get(String column) {
        if (isMissing(column)) {
            return "";
        } else {
            return current[this.columnLabelsToInt.get(column)];
        }
    }
    public String get(int index, String defaultValue) throws IndexOutOfBoundsException {
        if (isMissing(index)) {
            return defaultValue;
        }

        return current[index];
    }
    public String get(String label, String defaultValue) throws IllegalArgumentException {
        return get(this.columnLabelsToInt.get(label), defaultValue);
    }

    public int getInt(int index) {
        if (!isMissing(index)) {
            String s = current[index];
            return Integer.parseInt(s);
        } else {
            return -1;
        }
    }

    public int getInt(String column) {
        if (!isMissing(column)) {
            String s = current[this.columnLabelsToInt.get(column)];
            return Integer.parseInt(s);
        } else {
            return -1;
        }
    }

    public double getDouble(int index) {
        if (!isMissing(index)) {
            String s = current[index];
            return Double.parseDouble(s);
        } else {
            return -1;
        }
    }

    public double getDouble(String column) {
        if (!isMissing(column)) {
            String s = current[this.columnLabelsToInt.get(column)];
            return Double.parseDouble(s);
        } else {
            return -1;
        }
    }

    public double getDouble(int index, double defaultt) {
        if (!isMissing(index)) {
            String s = current[index];
            return Double.parseDouble(s);
        } else {
            return defaultt;
        }
    }

    public double getDouble(String column, double defaultt) {
        if (!isMissing(column)) {
            String s = current[this.columnLabelsToInt.get(column)];
            return Double.parseDouble(s);
        } else {
            return defaultt;
        }
    }
    public int getInt(int index, int defaultt) {
        if (!isMissing(index)) {
            String s = current[index];
            return Integer.parseInt(s);
        } else {
            return defaultt;
        }
    }

    public int getInt(String column, int defaultt) {
        if (!isMissing(column)) {
            String s = current[this.columnLabelsToInt.get(column)];
            return Integer.parseInt(s);
        } else {
            return defaultt;
        }
    }

    public long getLong(int index) {
        if (!isMissing(index)) {
            String s = current[index];
            return Long.parseLong(s);
        } else {
            return -1;
        }
    }

    public long getLong(String column) {
        if (!isMissing(column)) {
            String s = current[this.columnLabelsToInt.get(column)];
            return Long.parseLong(s);
        } else {
            return -1;
        }
    }


    public List<String> getColumnLabels() {
        return new ArrayList<>(columnLabelsToInt.keySet());
    }

    public int getRecordLength() {
        return current.length;
        //currentLength;
    }


    /*public boolean isMissing(int index) {
        //System.out.println(index);
        //System.out.println(getRecordLength());
        //System.out.println(current[index].isEmpty());
        return index >= current.length || index < 0;//current[index].isEmpty();
    }*/
    public boolean isMissing(int index) throws IndexOutOfBoundsException {
        if (index >= current.length || index < 0) {
            return true;
        }
        return get(index).equals("");
    }

    public boolean isMissing(String column) {
        return isMissing(this.columnLabelsToInt.get(column));
    }

    LocalTime getTime(String colname) {
        return getTime(columnLabelsToInt.get(colname));
    }

    LocalTime getTime(int columnIndex) {
        return LocalTime.parse(current[columnIndex], DateTimeFormatter.ofPattern("HH:mm"));
    }

    LocalDate getDate(String colname) {
        return getDate(columnLabelsToInt.get(colname));
    }

    LocalDate getDate(int columnIndex) {
        return LocalDate.parse(current[columnIndex], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
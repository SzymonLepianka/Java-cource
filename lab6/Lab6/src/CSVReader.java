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

    }

    public boolean next() throws IOException {
        String line = reader.readLine();
        if (line == null) {
            return false;
        }
        //this.current = line.split(delimiter);
        this.current = line.split(delimiter + "(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
        currentLength = line.length();
        return true;
    }

    public String get(int index) {

        if (isMissing(index)) {
            return "";
        } else {
            return current[index];
        }
    }

    public String get(String column) {
        if (isMissing(column)) {
            return "";
        } else {
            return current[this.columnLabelsToInt.get(column)];
        }
    }

    public int getInt(int index) {
        try {
            String s = current[index];
            return Integer.parseInt(s);
        } catch (Exception e) {
            return -1;
        }
    }

    public int getInt(String column) {
        try {
            String s = current[this.columnLabelsToInt.get(column)];
            return Integer.parseInt(s);
        } catch (Exception e) {
            return -1;
        }
    }

    public double getDouble(int index) {
        try {
            String s = current[index];
            return Double.parseDouble(s);
        } catch (Exception e) {
            return -1;
        }
    }

    public double getDouble(String column) {
        try {
            String s = current[this.columnLabelsToInt.get(column)];
            return Double.parseDouble(s);
        } catch (Exception e) {
            return -1;
        }
    }
    public double getDouble(int index, double defaultt) {
        try {
            String s = current[index];
            return Double.parseDouble(s);
        } catch (Exception e) {
            return defaultt;
        }
    }

    public double getDouble(String column, double defaultt) {
        try {
            String s = current[this.columnLabelsToInt.get(column)];
            return Double.parseDouble(s);
        } catch (Exception e) {
            return defaultt;
        }
    }

    public long getLong(int index) {
        try {

            String s = current[index];
            return Long.parseLong(s);
        } catch (Exception e) {
            return -1;
        }
    }

    public long getLong(String column) {
        try {

            String s = current[this.columnLabelsToInt.get(column)];
            return Long.parseLong(s);
        } catch (Exception e) {
            return -1;
        }
    }


    public List<String> getColumnLabels() {
        return new ArrayList<>(columnLabelsToInt.keySet());
    }

    public int getRecordLength() {
        return currentLength;
    }


    //nie sprawdza wszystkich warunków, zrobić zamiast wyjątków coś
    public boolean isMissing(int index) {
        return current[index].isEmpty();
        //return get(index).isEmpty();
    }

    public boolean isMissing(String column) {
        return current[this.columnLabelsToInt.get(column)].isEmpty();
        //return get(column).isEmpty();
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
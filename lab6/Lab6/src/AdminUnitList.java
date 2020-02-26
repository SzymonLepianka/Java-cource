import java.io.IOException;
import java.io.PrintStream;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AdminUnitList {
    List<AdminUnit> units = new ArrayList<>();
    Map<Long, AdminUnit> IDtoAU = new HashMap<>();
    Map<AdminUnit, Long> AUtoParent = new HashMap<>();
    Map<Long, List<AdminUnit>> parentid2child = new HashMap<>();

    AdminUnitList(List<AdminUnit> units) {
        this.units = units;
    }

    public AdminUnitList() {
    }

    public void read(String filename) throws IOException {
        CSVReader reader = new CSVReader(filename, ",", true);
        for (int i = 0; i < 500 && reader.next(); i++) { //while (reader.next()) {
            AdminUnit unit = new AdminUnit();
            BoundingBox bbox = new BoundingBox();
            unit.name = reader.get("name");
            unit.adminLevel = reader.getInt("admin_level");
            unit.population = reader.getDouble("population");
            unit.area = reader.getDouble("area");
            unit.density = reader.getDouble("density");
            double x1 = reader.getDouble("x1", Double.NaN);
            double x2 = reader.getDouble("x2", Double.NaN);
            double x3 = reader.getDouble("x3", Double.NaN);
            double x4 = reader.getDouble("x4", Double.NaN);
            double y1 = reader.getDouble("y1", Double.NaN);
            double y2 = reader.getDouble("y2", Double.NaN);
            double y3 = reader.getDouble("y3", Double.NaN);
            double y4 = reader.getDouble("y4", Double.NaN);
            bbox.xmin = Math.min(x1, Math.min(x2, Math.min(x3, x4)));
            bbox.xmax = Math.max(x1, Math.max(x2, Math.max(x3, x4)));
            bbox.ymin = Math.min(y1, Math.min(y2, Math.min(y3, y4)));
            bbox.ymax = Math.max(y1, Math.max(y2, Math.max(y3, y4)));
            unit.bbox = bbox;
            units.add(unit);
            long parentID = reader.getLong("id");
            IDtoAU.put(parentID, unit);
            AUtoParent.put(unit, reader.getLong("parent"));
            if (parentID != -1) {
                if (parentid2child.containsKey(parentID)) {
                    parentid2child.get(parentID).add(unit);
                } else {
                    List<AdminUnit> children = new ArrayList<>();
                    children.add(unit);
                    parentid2child.put(parentID, children);
                }
            }
        }

        for (int i = 0; i < units.size(); i++) {
            long parentid = AUtoParent.get(units.get(i));
            units.get(i).parent = IDtoAU.getOrDefault(parentid, null);
            if (units.get(i).parent != null) {
                units.get(i).parent.children = parentid2child.getOrDefault(parentid, new ArrayList<>());
            }
        }
        this.fixMissingValues();


    }

    /**
     * Wypisuje zawartość korzystając z AdminUnit.toString()
     *
     * @param out
     */
    void list(PrintStream out) {
        for (int i = 0; i < units.size(); i++) {
            out.append(units.get(i).toString());
        }
    }

    /**
     * Wypisuje co najwyżej limit elementów począwszy od elementu o indeksie offset
     *
     * @param out    - strumień wyjsciowy
     * @param offset - od którego elementu rozpocząć wypisywanie (numerowanie od 0)
     * @param limit  - ile (maksymalnie) elementów wypisać
     */
    void list(PrintStream out, int offset, int limit) {
        if (limit + offset > units.size()) {
            limit = units.size();
            for (int i = offset; i < limit; i++) {
                out.println(units.get(i).toString());
            }
        }

    }

    /**
     * Zwraca nową listę zawierającą te obiekty AdminUnit, których nazwa pasuje do wzorca
     *
     * @param pattern - wzorzec dla nazwy
     * @param regex   - jeśli regex=true, użyj finkcji String matches(); jeśli false użyj funkcji contains()
     * @return podzbiór elementów, których nazwy spełniają kryterium wyboru
     */
    AdminUnitList selectByName(String pattern, boolean regex) {
        AdminUnitList ret = new AdminUnitList();
        // przeiteruj po zawartości units
        for (int i = 0; i < units.size(); i++) {
            if (regex) {
                if (units.get(i).name.matches(pattern)) {
                    ret.units.add(this.units.get(i));
                }
            } else {
                if (units.get(i).name.contains(pattern)) {
                    ret.units.add(this.units.get(i));
                }
            }
        }
        // jeżeli nazwa jednostki pasuje do wzorca dodaj do ret
        return ret;
    }

    void fixMissingValues() {
        for (int i = 0; i < units.size(); i++) {
            units.get(i).fixMissingValues();
        }
    }

    AdminUnitList getNeighbors(AdminUnit unit, double maxdistance) {
        /*if(unit.bbox.isEmpty()){
            throw new RuntimeException("Not implemented");
        }
        AdminUnitList neighboors = new AdminUnitList();
        if (unit.adminLevel==8){
            for (int i = 0; i < units.size(); i++) {
                if (unit.bbox.distanceTo(units.get(i).bbox) <= maxdistance){
                    neighboors.units.add(units.get(i));
                }
                else if (unit.bbox.intersects(units.get(i).bbox)){
                    neighboors.units.add(units.get(i));
                }
            }
            return neighboors;
        }
        for (int i = 0; i < units.size(); i++) {
            if (unit.bbox.intersects(units.get(i).bbox)){
                neighboors.units.add(units.get(i));
            }
        }
        return neighboors;*/
        List<AdminUnit> neighbors = new ArrayList<>();
        if (unit.parent == null || unit.parent.parent == null) {
            for (AdminUnit au : this.units)
                if (au.adminLevel == unit.adminLevel && !au.name.equals(unit.name) && unit.bbox.intersects(au.bbox))
                    neighbors.add(au);
        } else if (unit.adminLevel == 7) {
            for (AdminUnit au : unit.parent.parent.children)
                for (AdminUnit au1 : au.children)
                    if (au1.adminLevel == unit.adminLevel && !au1.name.equals(unit.name) && unit.bbox.intersects(au1.bbox))
                        neighbors.add(au1);
        } else {
            for (AdminUnit au : unit.parent.parent.children)
                for (AdminUnit au1 : au.children)
                    if (au1.adminLevel == 8 && !au1.name.equals(unit.name) && (unit.bbox.intersects(au1.bbox) || unit.bbox.distanceTo(au1.bbox) < maxdistance))
                        neighbors.add(au1);
        }
        return new AdminUnitList(neighbors);
    }

    /**
     * Sortuje daną listę jednostek (in place = w miejscu)
     *
     * @return this
     */

    AdminUnitList sortInplaceByName() {
        class Inside implements Comparator<AdminUnit> {
            public int compare(AdminUnit t, AdminUnit t1) {
                return t.name.compareTo(t1.name);
            }
        }
        units.sort(new Inside());
        return this;
    }

    /**
     * Sortuje daną listę jednostek (in place = w miejscu)
     *
     * @return this
     */
    AdminUnitList sortInplaceByArea() {

        units.sort(new Comparator<AdminUnit>() {
            @Override
            public int compare(AdminUnit adminUnit, AdminUnit t1) {
                return Double.compare(adminUnit.area, t1.area);
            }
        });
        return this;
    }

    /**
     * Sortuje daną listę jednostek (in place = w miejscu)
     *
     * @return this
     */
    AdminUnitList sortInplaceByPopulation() {
        units.sort((p, p1) -> Double.compare(p.population, p1.population));
        return this;
    }

    AdminUnitList sortInplace(Comparator<AdminUnit> cmp) {
        units.sort(cmp);
        return this;
    }

    AdminUnitList sort(Comparator<AdminUnit> cmp) {
        AdminUnitList tmp = new AdminUnitList();
        tmp.units.addAll(this.units);
        tmp.sortInplace(cmp);
        return tmp;
    }

    AdminUnitList filter(Predicate<AdminUnit> pred) {
        AdminUnitList tmp = new AdminUnitList();
        for (AdminUnit au : this.units)
            if (pred.test(au))
                tmp.units.add(au);
        // tmp.units.removeIf(pred.negate());
        return tmp;
    }

    /**
     * Zwraca co najwyżej limit elementów spełniających pred
     *
     * @param pred  - predykat
     * @param limit - maksymalna liczba elementów
     * @return nową listę
     */
    AdminUnitList filter(Predicate<AdminUnit> pred, int limit) {
        return new AdminUnitList(this.filter(pred).units.subList(0, limit));
    }

    /**
     * Zwraca co najwyżej limit elementów spełniających pred począwszy od offset
     * Offest jest obliczany po przefiltrowaniu
     *
     * @param pred  - predykat
     * @param -     od którego elementu
     * @param limit - maksymalna liczba elementów
     * @return nową listę
     */
    AdminUnitList filter(Predicate<AdminUnit> pred, int offset, int limit) {
        AdminUnitList result = new AdminUnitList();
        result = this.filter(pred);
        System.out.println(result.units.size());
        if (result.units.size() > limit + offset) {
            result.units.subList(offset, limit + offset);
        } else
            result.units.subList(offset, result.units.size());
        return result;
    }

}


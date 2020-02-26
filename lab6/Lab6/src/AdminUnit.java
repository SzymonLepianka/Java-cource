import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminUnit {
    String name;
    int adminLevel;
    double population;
    double area;
    double density;
    AdminUnit parent;
    BoundingBox bbox = new BoundingBox();
    List<AdminUnit> children;

    AdminUnit() {
    }

    AdminUnit(String name, int adminLevel, double population, double area, double density, double[] points){
        this.name = name;
        this.adminLevel = adminLevel;
        this.population = population;
        this.area = area;
        this.density = density;
        //this.bbox = new BoundingBox(points[0], points[1], points[2], points[3]);
    }
    public String toString() {
        if (parent != null) {
            return "AdminUnit{"
                    + "name='" + name + '\''
                    + ", adminLevel="
                    + adminLevel
                    + ", population="
                    + population
                    + ", area="
                    + area
                    + ", density="
                    + density
                    + ", parent="
                    + parent.name
                    + ", bbox="
                    + bbox
                    + "}\n";
        } else {
            return "AdminUnit{"
                    + "name='" + name + '\''
                    + ", adminLevel="
                    + adminLevel
                    + ", population="
                    + population
                    + ", area="
                    + area
                    + ", density="
                    + density
                    + ", parent="
                    + parent
                    + ", bbox="
                    + bbox
                    + "}\n";
        }

    }

    void fixMissingValues() {
        if (density != -1 && population != -1) {
            return;
        }
        AdminUnit parentUnit = this.parent;
        while (parentUnit != null) {
            if (parentUnit.density == -1 || parentUnit.population == -1) {
                parentUnit = parentUnit.parent;
            } else {
                break;
            }
        }
        if (parentUnit == null) {
            return;
        }

        this.density = parentUnit.density;
        this.population = this.area * this.density;
    }
}

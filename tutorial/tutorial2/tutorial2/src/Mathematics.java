import com.sun.jdi.VoidType;

public class Mathematics {
    public static void main(String[] args){
        Mathematics m = new Mathematics();
        m.className();
        System.out.println(m.sqrt(5));
    }
    public void className(){
        System.out.println("Mathematics");
    }
    public int sqrt(int a){
        return a*a;
    }
    public double sqrt(double a){
        return a*a;
    }
}

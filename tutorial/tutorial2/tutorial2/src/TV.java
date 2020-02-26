public class TV {
    private int size;
    private String name;
    private boolean onoff;
    //public TV(){}
    public TV(int aSize, String name){
        this.size=aSize;
        this.name=name;
    }
    public int getSize(){
        return size;
    }
    public String getName(){
        return name;
    }
    public void setOnoff(boolean a){
        onoff = a;
    }
    public void getOnoff(){
        if (onoff){
            System.out.println("włączony");
        }else{
            System.out.println("wyłączony");
        }
    }
}

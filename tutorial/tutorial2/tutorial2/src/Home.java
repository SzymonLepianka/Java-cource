public class Home {
    public static void main(String[] args) {
        TV sony = new TV(45, "sony123445");
        //sony.size = 32;
        TV sama = new TV(32, "sama12345");
        System.out.println(sama.getName() + " " + sama.getSize());
        System.out.println(sony.getName() + " " + sony.getSize());

        sama.getOnoff();
        sama.setOnoff(true);
        sama.getOnoff();

        Dog rex = new Dog();
        rex.speak();
        rex.setSize(15);
        System.out.println(rex.getSize());
    }
}

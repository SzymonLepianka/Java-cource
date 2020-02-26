public class Dog extends Animal{//pies rozszerza zwierzę

    @Override
    public void speak() {
        //super.speak();//wywołanie metody z nadrzędnej
        System.out.println("hau");
    }
}

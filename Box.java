package Lesson_1;

import java.util.ArrayList;

public class Box  <T extends Fruit>{
    private T a;
    private float wightBox;

    Fruit fruit = new Fruit();

    ArrayList <Fruit> list = new ArrayList<Fruit>();

    public ArrayList<Fruit> getList() {
        return list;
    }

    public float getWight(){
        return list.get(0).getWight()*list.size();
    }

    public boolean compareTo(Box <? extends Fruit> boxAnother) {
        return Math.abs(this.wightBox - boxAnother.wightBox) < 0.0001;
    }

    public void newBox(Box <? extends Fruit> box){
        box.getList().clone();
    }

    public void putInTheBox(Fruit fruit){
        list.add(fruit);
    }
}

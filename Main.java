package Lesson_1;

import java.util.ArrayList;

/**
 * Написать метод, который меняет два элемента массива местами (массив может быть любого ссылочного типа);
 *
 * Написать метод, который преобразует массив в ArrayList;
 *
 * Задача:
 * Даны классы Fruit, Apple extends Fruit, Orange extends Fruit;
 *
 * Класс Box, в который можно складывать фрукты. Коробки условно сортируются по типу фрукта, поэтому в
 * одну коробку нельзя сложить и яблоки, и апельсины;
 *
 * Для хранения фруктов внутри коробки можно использовать ArrayList;
 *
 * Сделать метод getWeight(), который высчитывает вес коробки. Задать вес одного фрукта и
 * их количество: вес яблока – 1.0f, апельсина – 1.5f (единицы измерения не важны);
 *
 * Внутри класса Box сделать метод compare(), который позволяет сравнить текущую коробку с
 * той, которую подадут в compare() в качестве параметра. true – если их массы равны, false
 * в противоположном случае. Можно сравнивать коробки с яблоками и апельсинами;
 *
 * Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую.
 * Помним про сортировку фруктов: нельзя яблоки высыпать в коробку с апельсинами. Соответственно,
 * в текущей коробке фруктов не остается, а в другую перекидываются объекты, которые были в первой;
 *
 * Не забываем про метод добавления фрукта в коробку.
 * */

public class Main {

    private static Object Apple;

    public static void main(String[] args) {
//        Integer[] arr1 = new Integer[]{1, 2, 3, 4};
//        changePlace(arr1, 0, 1);
//        System.out.println(Arrays.toString(arr1));
//        System.out.println(makeArrayList(arr1));

        Box <Apple> boxA = new Box<>();
        Box <Orange> boxO = new Box<>();

        boxA.compareTo(boxO);
        System.out.println(boxA.getWight());
        boxA.putInTheBox((Fruit) Apple);
        boxA.putInTheBox((Fruit) Apple);
        boxA.putInTheBox((Fruit) Apple);
        System.out.println(boxA.getWight());
        System.out.println(boxA.compareTo(boxO));
        Box<Apple> boxX = new Box<>();
        boxA.newBox(boxX);
    }

    public static void changePlace(Integer arr[], int index1, int index2) {
            int a = arr[index1];
            arr[index1] = arr[index2];
            arr[index2] = a;

    }

    public static ArrayList<Integer> makeArrayList(Integer arr []){
        ArrayList<Integer> al = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            al.add(arr[i]);
        }
        return al;
    }
}

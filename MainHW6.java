import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

/**
 * Домашнее задание к 6 уроку
 * Оксана Марковская, 23.02.2020
 *
 * 2. Написать метод, которому в качестве аргумента передается не пустой одномерный целочисленный массив. Метод должен
 * вернуть новый массив, который получен путем вытаскивания из исходного массива элементов, идущих после последней
 * четверки. Входной массив должен содержать хотя бы одну четверку, иначе в методе необходимо выбросить RuntimeException.
 * Написать набор тестов для этого метода (по 3-4 варианта входных данных). Вх: [ 1 2 4 4 2 3 4 1 7 ] -> вых: [ 1 7 ].
 *
 * 3. Написать метод, который проверяет состав массива из чисел 1 и 4. Если в нем нет хоть одной четверки или единицы,
 * то метод вернет false; Написать набор тестов для этого метода (по 3-4 варианта входных данных).
 * */



public class MainHW6 {
    public static void main(String[] args) {

        int [] brr = new int []{0, 2, 3, 5, 8, 4, 6, 7,};
        System.out.println(Arrays.toString(method1(brr)));
        //System.out.println(method2(brr));


    }

    public static int [] method1(int... arr) {
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] == 4) {
                return Arrays.copyOfRange(arr, i + 1, arr.length);
            }
        }
        throw new RuntimeException("4 не найдено");
    }

    public static boolean method2(int... arr) {
       boolean int1 = false;
       boolean int4 = false;
        for (int i = 0; i < arr.length ; i++) {
            if(arr[i] == 1){
                int1 = true;
            } else if (arr[i] == 4){
                int4 = true;
            } else {
                return false;
            }
        }
        return int1 & int4;
    }
}
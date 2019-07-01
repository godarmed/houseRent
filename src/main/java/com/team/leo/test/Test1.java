package com.team.leo.test;

import org.junit.Test;

import java.sql.SQLOutput;

public class Test1 {
    private int i;
    private String str;

    public Test1() {
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    @FunctionalInterface
    public interface Supplier<T> {
        T get();
    }

    //Supplier是jdk1.8的接口，这里和lamda一起使用了
    public static Test1 create(final Supplier<Test1> supplier) {
        return supplier.get();
    }

    /*public static void main(String[] args) {
        Test1 test = Test1.create(Test1::new);

        System.out.println(test.getI());
    }*/

    /**
     * 获取rows阶的杨辉三角
     */
    @Test
    public void getYanghuiTriangle() {
        int rows = 10;
        //行
        for (int i = 1; i <= rows; i++) {
            //首项为1
            int num = 1;
            //打印空字符串
            System.out.format("%" + (rows - i + 1) + "s", " ");
            //列
            for (int j = 1; j <= i; j++) {
                System.out.println(num);
                num = num * (i - j) / j;
            }
            System.out.println();
        }
    }

    /**
     * 正向冒泡排序
     */
    @Test
    public void getSortResult() {
        //要排序的数组
        int[] arr = {1, 2, 3, 1, 5};

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    arr[j] = arr[j] + arr[j + 1];
                    arr[j + 1] = arr[j] - arr[j + 1];
                    arr[j] = arr[j] - arr[j + 1];
                }
            }
        }
        for (int i : arr) {
            System.out.println(i);
        }
    }

    @Test
    public void getFibonacci() {
        int arr[] = new int[10];
        arr[0] = 1;
        arr[1] = 1;

        for (int j = 2; j < arr.length; j++) {
            arr[j] = arr[j - 1] + arr[j - 2];
        }

        for (int i1 : arr) {
            System.out.println(i1);
        }
    }

    /**
     * 非递归获取第斐波那契数列的第i个数
     * @param i
     * @return
     */
    public static int getFibonacci1(int i) {
        int num1 = 1;
        int num2 = 1;

        if(i == 1 || i == 2){
            return 1;
        }

        int tmp;
        for (int j = 2; j < i; j++) {
            tmp = num2;
            num2 = num1 + num2;
            num1 = tmp;
        }
        return num2;
    }

    /**
     * 递归获取第斐波那契数列的第i个数
     * @param i
     * @return
     */
    public static int getFibonacci2(int i) {
        if(i==1||i==2){
            return 1;
        }

        return getFibonacci2(i - 1) + getFibonacci2(i - 2);
    }



    public static void main(String[] args) {
        System.out.println(Test1.getFibonacci1(5));
    }

}
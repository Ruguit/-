package test_2;
import java.util.Random;
import java.util.Scanner;
public class TestDamo_2 {
    public static void main(String[] args) {
        Random s=new Random();
        int n=s.nextInt(100);
        int a=s.nextInt(10);
        TestDamo3.show(a,n);
    }
}

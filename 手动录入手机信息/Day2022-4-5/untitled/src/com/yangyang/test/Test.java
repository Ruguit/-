package com.yangyang.test;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
//手机类
public class Test {
    public static void main(String[] args) {
        Scanner sc=new Scanner (System.in);
        ArrayList<phone> phones=new ArrayList<>();
        phone h;
        for(int i=1;;i++){
            System.out.println("请输入 <输入>或<结束>");
           String str= sc.next();
           if(str.equals("输入")){
               System.out.println("品牌");
               String n=sc.next();
               System.out.println("颜色");
               String c=sc.next();
               System.out.println("价格");
               Double p=sc.nextDouble();
               h=new phone(n,c,p);
               phones.add(h);
           }else if(str.equals("结束"))break;
        }

        System.out.println("已经录入以下数据");
        for (int i = 0; i < phones.size(); i++) {
            phone d = phones.get(i);
            System.out.println(d.getName()+ " "+ d.getColor()+" " + d.getPrice());
        }

    }
}

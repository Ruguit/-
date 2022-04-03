package com.yangyang.test;

import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;
//案例三：模拟双色球系统
/*要求：6个红球 1-33  1个蓝球  1-16 （不重复）
1：随机一组中将号码
2：用户输入一组号码
3：比较判断中奖情况
* */
public class Test {

    public static void main(String[] args) {
        int []SetNumber=SetNumber();
        System.out.println("你的号码是：");
        show(SetNumber);
        System.out.println();//换行
        int []GetNumber=GetNumber();
        System.out.println("以下是开奖的号码");
        show(GetNumber);
        System.out.println();//换行
        Choose(SetNumber,GetNumber);//判断

    }
    //4：比较判断中奖情况
    public static void Choose(int []SetNumber,int []GetNumber){
        //判断红球
        int ans=0;
        for(int i=0;i<GetNumber.length-1;i++){
            //将自己写的于系统给的逐一比较
            for(int j=0;j<GetNumber.length-1;j++){
                if(SetNumber[i]==GetNumber[j])ans++;//如果相同就记录一次
            }

        }
        //判断蓝球
        int anss=2;//不相等是2
        if(GetNumber[GetNumber.length - 1]==SetNumber[SetNumber.length - 1])anss=3;//相等是3
        System.out.println("红球中了"+ans);
        if(anss==2)System.out.println("蓝球没中");
        if(anss==3)System.out.println("蓝球中了");
    }

    //3：遍历
    public static void show(int []a){
        for(int i:a){
            System.out.print(i+" ");
        }
    }
    //1：随机一组中将号码
    public static int[]GetNumber(){
        Random s=new Random();
        int []ball=new int[7];
        ball[6]=s.nextInt(16)+1;//蓝球 1-16 随机
        for(int i=0;i<ball.length-1;i++){//红球 遍历 1-33 随机
            //建立死循环
           while(true){
               // 1-33的随机数
               int num=s.nextInt(33)+1;
               boolean flog=true;
               for(int j=0;j<i;j++){
                   //如果输入的数字重复就再输一次
                   if(num==ball[j]){
                       flog=false;
                       break;
                   }
               }
               //不重复就跳出死循环
               if(flog){
                   ball[i]=num;
                   break;
               }
           }
        }
        return ball;
    }


    //2：用户输入一组号码
    public static int[] SetNumber() {
        Scanner sc = new Scanner(System.in);
        int[] a = new int[7];
        boolean flog = true;
        for (int i = 0; i < a.length - 1; i++) {
            //建立死循环
            while(true){
                flog = true;
                System.out.println("请输入你要买的第" + (i + 1) + "个红球号码");
                a[i] = sc.nextInt();
                //超出范围就报错
                if (a[i] < 1 || a[i] > 33) {
                    System.out.println("出现错误！");
                    flog = false;
                }
                //判断重复与否
                if(flog){
                    for(int j=0;j<i;j++){
                        //重复就报错
                        if(a[i]==a[j]){
                            System.out.println("出现错误！重复了");
                            flog=false;
                            break;
                        }
                    }
                }
                //没有问题就跳出循环
                if(flog)break;
            }
        }
        //建立死循环
           while(true){
               flog=true;
               System.out.println("请输入蓝球号码");
               a[a.length - 1] = sc.nextInt();
               //超出范围就报错
               if (a[a.length - 1] < 1 || a[a.length - 1] > 15) {
                   System.out.println("出现错误,请重新输入");
                   flog=false;
               }
               //没有问题就跳出循环
               if(flog)break;
           }
      return a;
    }

}

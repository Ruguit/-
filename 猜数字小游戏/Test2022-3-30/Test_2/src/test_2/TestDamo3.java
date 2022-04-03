package test_2;
import java.util.Scanner;
public class TestDamo3 {

    public static void show(int a,int n){
        int num1=-1,num2=100,num3=2,num4=1,num5=1;
        Scanner sc=new Scanner(System.in);

        for(int i=1;;i++){
            System.out.println("请输入一个在"+num1+"到"+num2+"之间，但不包括"+num1+"和"+num2+"的整数");
            int number=sc.nextInt();

            if(number==num1||number==num2){
                if(num5==3){
                    System.out.println("拜拜！");
                    break;
                }
                System.out.println("请不要重复输入！");
                num5++;
            }

            if(number>num2||number<num1) {
                if(num3==3){
                    System.out.println("TMD! 你就是个废物，老子不跟你玩了！");
                    break;
                }
                if(num3==2) {
                    System.out.println("你是傻逼吗？叫你居然按了"+number);
                    num3++;
                }
            }

            else if(number<num2&&number>num1){
                num4++;
                if(num4==a){
                    System.out.println("拜拜了！废物！");
                    break;
                }else if(num4==a-1){
                    System.out.println("卧槽了！不是吧！还找不到，再给你一次机会，找不到就别玩了");


                } else if(number==n) {
                    System.out.println("恭喜您答对了！");
                    break;
                }else if(number<n) {
                    num1 = number;
                    System.out.println("猜小了，再来一次吧！");
                }else if(number>n){
                    num2 = number;
                    System.out.println("猜大了，再来一次吧！");
                }
            }
        }
    }
}

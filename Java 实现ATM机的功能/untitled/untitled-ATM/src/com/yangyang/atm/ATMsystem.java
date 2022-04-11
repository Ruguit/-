package com.yangyang.atm;
import com.yangyang.atm.Accout;
/**
 * ATM系统的入口类
 */
import javax.swing.*;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
public class ATMsystem {
    public static void main(String[] args){
        //需要一个集合存储账户对象，进行相关操作
        ArrayList<Accout> accouts=new ArrayList();
        Scanner sc=new Scanner(System.in);

        while (true) {
            System.out.println("================ATM==================");
            System.out.println("1:账户登录");
            System.out.println("2:账户注册");
            System.out.println("请选择你的操作：");
            int command=sc.nextInt();
            switch(command){
                case 1:
                    //用户登录
                    login(accouts,sc);
                    break;
                case 2:
                    //用户开户
                    regist(accouts,sc);
                    break;
                default:System.out.println("你输入的命令不存在！");
            }
        }



    }

    /**
     * 用户登录的实现
     * @param accouts 接受账户集合
     * @param sc 扫描器
     */
    private static void login(ArrayList<Accout> accouts,Scanner sc) {
        System.out.println("====================用户登录====================");
        //判断账户集合中是否有账户存在，如果不存在，登录功能不实现
        if(accouts.size()==0){
            System.out.println("当前系统中无账户存在，请先开户。");
            return;
        }
        //2:正式进入登录操作
        while (true) {
            System.out.println("请输入卡号");
            String cardId = sc.next();
            //判断卡号是否存在，根据卡号在集合中找对象
            Accout acc=getAccoutByCardId(cardId,accouts);
            if(acc!=null){
                //存在
                while (true) {
                    //输入密码 判断
                    System.out.println("请输入密码");
                    String password = sc.next();
                    if(acc.getPassWord().equals(password)){
                        //成功
                        System.out.println("恭喜你登陆成功"+acc.getUserName()+"先生/女士");
                        //其他功能
                        showUseCommand(acc,sc,accouts);//当前登录的账户
                        return;//干掉登陆方法
                    }else{
                        //失败
                        System.out.println("您输入的密码有误");
                    }
                }
            }else{
                System.out.println("当前系统中无无改卡号存在");
            }
        }


    }

    /**
     *登录后功能的实现， 查询账户信息 存款 转账 取款 改密码 退出 注销
     * @param acc 接收的账户
     * @param sc 扫描仪
     */
    private static void showUseCommand(Accout acc,Scanner sc,ArrayList<Accout>accouts){
        while (true) {
            System.out.println("====================用户操作界面====================");
            System.out.println("欢迎！请选择你的操作：");
            System.out.println("1:查询账户信息");
            System.out.println("2：存款");
            System.out.println("3：取款");
            System.out.println("4：转账");
            System.out.println("5：改密码");
            System.out.println("6：退出");
            System.out.println("7：注销账户");
            int number =sc.nextInt();
            switch(number){
                case 1://查询账户信息(展示当前登录的账户)
                    showAccout(acc);
                    break;
                case 2://存款
                    depositMoney(acc,sc);
                    break;
                case 3://取款
                    drawMoney(acc,sc);
                    break;
                case 4://转账
                    transferMoney(sc,acc,accouts);
                    break;
                case 5://改密码
                    updatePassWord(sc,acc);
                    return;//停止执行当前方法 在登录界面验证新密码
                case 6://退出
                    System.out.println("退出成功，欢迎下次光临！");
                    return;//停止执行当前方法
                case 7://注销账户
                    delateAccout(sc,acc,accouts);
                    return;//停止执行当前方法
                default :
                    System.out.println("对不起， 您输入的指令有误！");
            }
        }
    }

    /**
     * 注销账户
     * @param sc 扫描仪
     * @param acc 自己的账户
     * @param accouts 账户集合
     */
    private static void delateAccout(Scanner sc, Accout acc, ArrayList<Accout> accouts) {
        System.out.println("=================用户销户界面==================");
        System.out.println("确定要销户？y/n");
        String choose= sc.next();
        switch(choose){
            case "y":
                if(acc.getMoney()>0){
                    System.out.println("账户尚有余额，不可以注销！");
                }else{
                    accouts.remove(acc);
                    System.out.println("销户成功！");
                }
                break;
            default:
                System.out.println("好的，账户继续保留。");
        }
    }

    /**
     * 修改密码
     * @param sc 扫描仪
     * @param acc 账户对象
     */
    private static void updatePassWord(Scanner sc, Accout acc) {
        System.out.println("=================用户密码修改==================");
        while (true) {
            System.out.println("请输入旧密码");
            String password= sc.next();
            //判断密码
            if(password.equals(acc.getPassWord())){
                //对的
                while(true){
                    System.out.println("请输入新密码");
                    String newpassword= sc.next();
                    System.out.println("请确认新密码");
                    String oknewpassword= sc.next();
                    if(newpassword.equals(oknewpassword)){
                        acc.setPassWord(newpassword);
                        System.out.println("恭喜你，密码修改成功");
                        return;//结束
                    }else {
                        System.out.println("密码错误");
                    }
                }
            }else{
                //错的
                System.out.println("抱歉，密码错误！");
            }
        }
    }

    /**
     * 转账
     * @param sc 扫描仪
     * @param acc 自己的账户
     * @param accouts 账户集合
     */
    private static void transferMoney(Scanner sc, Accout acc, ArrayList<Accout> accouts) {
        System.out.println("=================用户转账操作==================");
        //判断是否有两个账户
        if(accouts.size()<2){
            System.out.println("对不起,账户数量不足，请先开户~");
            return;
        }
        //判断自己是否有钱
        while (true) {
            if(acc.getMoney()==0){
                System.out.println("抱歉，账户余额不足");
                return;
            }
            //真正开始转账
            System.out.println("请输入对方的卡号");
            String cardId=sc.next();
            if(cardId.equals(acc.getCardId())){
                System.out.println("对不起，您不可以输入自己的卡号");
                continue;
            }
            //通过卡号判断账户是否存在
            Accout accout=getAccoutByCardId(cardId,accouts);
            if(accout==null){
                System.out.println("账户不存在");
            }else{
                //姓氏认证
                String  userName=accout.getUserName();
                String tip="*"+userName.substring(1);//截取姓氏之后的
                System.out.println("请输入["+tip+"]的姓氏");
                String prename=sc.next();
                // 确认
                if(userName.startsWith(prename)){
                    //通过
                    System.out.println("请输入转账金额");
                    double money=sc.nextDouble();
                    if(money>acc.getMoney()){
                        System.out.println("抱歉，余额不足。");
                    }else{
                        acc.setMoney(acc.getMoney()-money);
                        accout.setMoney(accout.getMoney()+money);
                        showAccout(acc);
                        showAccout(accout);
                        return;//结束
                    }
                }else{
                    System.out.println("对不起，您的输入有误！");
                }
            }
        }
    }

    /**
     * 取钱
     * @param acc
     * @param sc
     */
    private static void drawMoney(Accout acc, Scanner sc) {
        System.out.println("=================用户取钱操作==================");
        if(acc.getMoney()<100){
            System.out.println("抱歉，账户不够100元，无法取钱");
            return ;
        }
        System.out.println("请输入取款金额");
        double money=sc.nextDouble();
        //限额
        if(money> acc.getQuotaMoney()){
            System.out.println("抱歉，超出单次取款限额");
        }else {
            //余额
            if(money>acc.getMoney()){
                System.out.println("余额不足！");
            }else{
                acc.setMoney(acc.getMoney()-money);
                System.out.println("取款成功"+money+"元");
                showAccout(acc);
                return;//结束方法
            }
        }

    }

    /**
     * 存钱
     * @param acc 当前账户对象
     * @param sc 扫描仪
     */
    private static void depositMoney(Accout acc,Scanner sc) {
        System.out.println("=================用户存钱操作==================");
        System.out.println("请输入存款的金额");
        double money=sc.nextDouble();
        acc.setMoney(acc.getMoney()+money);
        System.out.println("存款成功！信息如下");
        while (true) {
            showAccout(acc);
            System.out.println("输入1以退出");
            Scanner ss=new Scanner(System.in);
            int s=ss.nextInt();
            if(s==1)return;
        }
    }

    /**
     * 展示账户信息
     * @param acc 被展示账户
     */
    private static void showAccout(Accout acc) {
        while (true) {
            System.out.println("=============================当前账户信息如下=============================");
            System.out.println("卡号："+acc.getCardId());
            System.out.println("户主："+acc.getUserName());
            System.out.println("余额："+acc.getMoney());
            System.out.println("限额："+acc.getQuotaMoney());
            System.out.println("输入1以退出");
            Scanner ss=new Scanner(System.in);
            int s=ss.nextInt();
            if(s==1)break;
        }
    }

    /**
     *  用户开户功能的实现
     * @param accouts 接收的账户集合
     */
    private static void regist(ArrayList<Accout> accouts,Scanner sc) {
        System.out.println("====================用户开户====================");
        //账户类
        Accout accout=new Accout();
        //录入账户与密码
        System.out.println("请输入你的用户名");
        String userName=sc.next();
        accout.setUserName(userName);
        while (true) {
            System.out.println("请输入你的用户密码");
            String password=sc.next();
            System.out.println("请输入你的确认密码");
            String okpassword=sc.next();
            //密码查验
            if(password.equals(okpassword)) {
                accout.setPassWord(password);
                break;
            }else System.out.println("密码不一致");
        }
        //限额
        System.out.println("请输入你的限额");
        double quotamoney=sc.nextDouble();
        accout.setQuotaMoney(quotamoney);
        //为账户随机一个8位且与其他账户不重复的密码
        String cardId=getRandomCardId(accouts);
        accout.setCardId(cardId);
        //将账户对象添加到账户集合中去
        accouts.add(accout);
        System.out.println("恭喜您，"+userName+"先生/女士，您的卡号是"+cardId);
    }

    /**
     * 为账户随机一个8位且与其他账户不重复的密码
     * @return
     */
    private static String getRandomCardId(ArrayList<Accout>accouts) {
        //生成8位数字
        Random d=new Random();
        while (true) {
            String cardId="";
            for (int i = 0; i <8 ; i++) {
                cardId+=d.nextInt(10);
            }
            //判断是否重复
            Accout acc=getAccoutByCardId(cardId,accouts);
            if(acc==null) return cardId;//无账户 可以用
        }
    }

    /**
     * 根据卡号查账户
     * @param cardId 卡号
     * @param accouts 全都账户集合
     * @return 账户对象/null
     */
    private static Accout getAccoutByCardId(String cardId,ArrayList<Accout>accouts){
       for(int i=0;i< accouts.size();i++){
           Accout acc=accouts.get(i);
           if(acc.getCardId().equals(cardId))return acc;
       }
       return null;
    }

}

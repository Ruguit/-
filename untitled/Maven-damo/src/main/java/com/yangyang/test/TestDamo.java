package com.yangyang.test;

import com.yangyang.mapper.UserMapper;
import com.yangyang.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class TestDamo {
    static Scanner SC=new Scanner(System.in);
    public static void main(String[] args) throws IOException {
        while (true) {
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            System.out.println("===============ATM=====================");
            System.out.println("请选择你的操作:");
            System.out.println("1:登录");
            System.out.println("2:注册");
            int num1=SC.nextInt();
            switch (num1){
                case 1:
                    login(sqlSessionFactory);//登录方法
                    break;
                case 2:
                    write(sqlSessionFactory);//注册方法
                    break;
                default:
                    System.out.println("输入有误！重新输入");
            }
        }
    }

    /***
     * 注册方法
     */
    private static void write(SqlSessionFactory sqlSessionFactory) {
        while (true) {
            System.out.println("请输入名字");
            String name=SC.next();
            System.out.println("请输入密码");
            String password=SC.next();
            System.out.println("请输入用户名");
            String userName=SC.next();
            String cardId = getCardid(sqlSessionFactory);
            double money=0.0;
            User user=new User(name,cardId,password,userName,money);
            SqlSession sqlSession=sqlSessionFactory.openSession(false);
            UserMapper usersmapper=sqlSession.getMapper(UserMapper.class);
            usersmapper.add(user);
            System.out.println("您的卡号是+"+cardId);
            System.out.println("确定注册吗？  yes/no");
            String s=SC.next();
            if(s.equals("yes")){
                sqlSession.commit();
                break;
            }else{
                System.out.println("您要推出吗？yes/no");
                String ss=SC.next();
                if(ss.equals("yes")){
                    return;
                }
            }
        }
        System.out.println("注册成功！");
    }

    /***
     * 随机生成11位数的卡号
     * @return
     */
    private static String getCardid(SqlSessionFactory sqlSessionFactory) {
        Random r=new Random();
        while (true) {
            String cardId="";
            for (int i = 0; i < 11; i++) {
                cardId+=r.nextInt(10);//0-9
            }
            //判断数据库中是否以及存在
            List<User> list=chechCardid(cardId,sqlSessionFactory);
            if(list.size()==0){
                return cardId;
            }
        }
    }

    /***
     * 通过卡号判断数据库中是否以及存在
     * @param cardId
     * @param sqlSessionFactory
     * @return
     */
    private static List<User> chechCardid(String cardId,SqlSessionFactory sqlSessionFactory){
        SqlSession sqlSession=sqlSessionFactory.openSession();
        UserMapper usersmapper=sqlSession.getMapper(UserMapper.class);
        List<User> list=usersmapper.selectByCardId(cardId);
        sqlSession.close();
        return list;
    }

    /***
     * 登录方法
     */
    private static void login(SqlSessionFactory sqlSessionFactory) {
        while (true) {
            System.out.println("请输入卡号");
            String cardId=SC.next();
            System.out.println("请输入密码");
            String password=SC.next();
            if (chechCardid(cardId,sqlSessionFactory).size()!=0) {
                List<User> list = chechCardid(cardId, sqlSessionFactory);
                if (list.get(0).getPassword().equals(password)) {
                    System.out.println("登录成功");
                    function(sqlSessionFactory,list.get(0));
                    return;
                }else {
                    System.out.println("登录失败");
                }
            }else{
                System.out.println("该账号不存在，建议先注册账号");
                System.out.println("您要退出吗？ y/n");
                String s= SC.next();
                if (s.equals("y")) {
                    return;
                }
            }
        }
    }

    /***
     * 用户功能展示界面
     */
    private static void function(SqlSessionFactory sqlSessionFactory,User user) {
        while (true) {
            System.out.println("===========用户功能界面==============");
            System.out.println("1:查询");
            System.out.println("2:存款");
            System.out.println("3:取款");
            System.out.println("4:转账");
            System.out.println("5:推出");
            int s= SC.nextInt();
            switch (s){
                case 1:
                    selectAll(user);
                    break;
                case 2:
                    addMoney(user,sqlSessionFactory);
                    break;
                case 3:
                    getMoney(user,sqlSessionFactory);
                    break;
                case 4:
                    moveMoney(user,sqlSessionFactory);
                    break;
                case 5:
                    return ;
                default:
                    System.out.println("输入有误");
            }
        }
    }

    /***
     * 转账
     */
    private static void moveMoney(User user,SqlSessionFactory sqlSessionFactory) {
        while (true) {
            System.out.println("输入对方的卡号");
            String str=SC.next();
            List<User> list = chechCardid(str, sqlSessionFactory);
            System.out.println("输入您的转账金额");
            double d=SC.nextDouble();
            double money = chechCardid(user.getCardId(), sqlSessionFactory).get(0).getMoney();
            if(money>d) {
                double dd = money - d;
                double cc=list.get(0).getMoney()+d;
                String cardId = user.getCardId();
                SqlSession sqlSession = sqlSessionFactory.openSession(true);
                UserMapper usersmapper = sqlSession.getMapper(UserMapper.class);
                usersmapper.updateMoneyByCardId(cardId, dd);
                usersmapper.updateMoneyByCardId(str, cc);
                System.out.println("您要退出吗？ y/n");
                String s= SC.next();
                if (s.equals("y")) {
                    sqlSession.close();
                    return;
                }
            }else {
                System.out.println("抱歉，余额不足,无法转账");
                System.out.println("您要退出吗？ y/n");
                String s= SC.next();
                if (s.equals("y")) {
                    return;
                }
            }
        }
    }

    /***
     * 取款
     */
    private static void getMoney(User user,SqlSessionFactory sqlSessionFactory) {
        while (true) {
            System.out.println("输入您的取款金额");
            double d=SC.nextDouble();
            double money = chechCardid(user.getCardId(), sqlSessionFactory).get(0).getMoney();
            if(money>d) {
                double dd = money - d;
                String cardId = user.getCardId();
                SqlSession sqlSession = sqlSessionFactory.openSession(true);
                UserMapper usersmapper = sqlSession.getMapper(UserMapper.class);
                usersmapper.updateMoneyByCardId(cardId, dd);
                System.out.println("您要退出吗？ y/n");
                String s= SC.next();
                if (s.equals("y")) {
                    sqlSession.close();
                    return;
                }
            }else {
                System.out.println("抱歉，余额不足");
                System.out.println("您要退出吗？ y/n");
                String s= SC.next();
                if (s.equals("y")) {
                    return;
                }
            }
        }
    }

    /***
     * 存款
     */
    private static void addMoney(User user,SqlSessionFactory sqlSessionFactory) {
        System.out.println("输入您的存款金额");
        double d=SC.nextDouble();
        double money = chechCardid(user.getCardId(), sqlSessionFactory).get(0).getMoney();
        double dd=money+d;
        String cardId = user.getCardId();
        SqlSession sqlSession=sqlSessionFactory.openSession(true);
        UserMapper usersmapper=sqlSession.getMapper(UserMapper.class);
        usersmapper.updateMoneyByCardId(cardId,dd);
        sqlSession.close();

    }

    /***
     * 查询账户信息
     */
    private static void selectAll(User user) {
        System.out.println(user.getUserName());
        System.out.println(user.getMoney());
        System.out.println(user.getName());
        System.out.println(user.getCardId());
    }
}
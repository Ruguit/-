package com.yangyang.test;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
//元素搜索 创建并搜索学生信息
public class Test {
    public static void main(String[] args) {
        //1：建立学生类 存储学生信息
        //2：建立集合 存储学生信息
        ArrayList<student> students=new ArrayList<>();
        students.add(new student("1111","1李华",16,"1111班"));
        students.add(new student("1112","2李华",17,"1112班"));
        students.add(new student("1113","3李华",18,"1113班"));
        students.add(new student("1114","4李华",19,"1114班"));
        //3：遍历学生信息
        System.out.println("学生信息");
        for (int i=0;i<students.size();i++){
            student s=students.get(i);
            System.out.println(s.getStudynumber()+" "+s.getName()+" "+s.getAge()+" "+s.getStudentnumber());
        }
        //4：定义方法完成搜索功能
        while(true){
            System.out.println("请输入学号:");
            Scanner sc=new Scanner(System.in);
            String studynumber=sc.next();
            //5:调用方法
            student s=GetstudentById(students,studynumber);
            //判断s是否存在于学生对象
            if(s==null){
                System.out.println("查无此人");
            }else{
                System.out.println("您查询的学生信息是：");
                System.out.println(s.getStudynumber()+" "+s.getName()+" "+s.getAge()+" "+s.getStudentnumber());
            }
        }

    }

    /**
     * 根据学号查询学生对象并返回
     * @param students 存储全部对象的学生集合
     * @param studynumber 搜索到学生号码
     * @return 返回号码 / null（查无）
     */
    public static student GetstudentById(ArrayList<student> students,String studynumber){
        for (int i = 0; i < students.size(); i++) {
            student s=students.get(i);
            if(s.getStudynumber().equals(studynumber)){
             return s;
            }
        }
        return null;
    }
}

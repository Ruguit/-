package com.yangyang.test;
//定义学生类
public class student {
    private String studynumber;
    private String name;
    private int age;
    private String studentnumber;

    public student(){}

    public student(String studynumber, String name, int age, String studentnumber) {
        this.studynumber = studynumber;
        this.name = name;
        this.age = age;
        this.studentnumber = studentnumber;
    }

    public String getStudynumber() {
        return studynumber;
    }

    public void setStudynumber(String studynumber) {
        this.studynumber = studynumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getStudentnumber() {
        return studentnumber;
    }

    public void setStudentnumber(String studentnumber) {
        this.studentnumber = studentnumber;
    }
}

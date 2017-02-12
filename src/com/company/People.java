package com.company;

/**
 * Created by Tanusha on 10/02/2017.
 */
public class People {
    private String name;
    private int age;
    private double salary;

    public People(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }


    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getSalary() {
        return salary;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    protected void paySalary(){
        System.out.println("pay salary");
    }

    public People() {
    }
}

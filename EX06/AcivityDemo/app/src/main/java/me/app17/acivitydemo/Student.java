package me.app17.acivitydemo;

import java.io.Serializable;

public class Student implements Serializable {
    String name;
    double chinese;
    double english;
    double math;

    public Student(String name, double chinese, double english, double math) {
        this.name = name;
        this.chinese = chinese;
        this.english = english;
        this.math = math;
    }


    public double getAvg() {
        return (chinese + english + math) / 3;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", chinese=" + chinese +
                ", english=" + english +
                ", math=" + math +
                '}' + "平均分為:" + getAvg();
    }
}
package edu.isu.cs.cs2263;

import java.io.Serializable;

public class Course implements Serializable {
    // variables
    private String subject;
    private int number;
    private String title;

    //methods
    public Course(){
        subject = "N/A";
        number = 1234;
        title = "Default";
    }

    public Course(String subject, int number, String title) {
        this.subject = subject;
        this.number = number;
        this.title = title;
    }

    public int getNumber() {
        return number;
    }
    public String getSubject() {
        return subject;
    }

    public String getTitle() {
        return title;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String toString(){
        return subject + " " + title + " " + number;
    }
}

package edu.isu.cs.cs2263;

import java.io.Serializable;
import java.util.LinkedList;

public class Student implements Serializable {
    // variables
    private String firstname;
    private String lastname;
    private LinkedList<Course> enrolledCourses;

    //methods

    public Student(String firstname, String lastname){
        this.firstname = firstname;
        this.lastname = lastname;
        enrolledCourses = new LinkedList<>();
    }
    public Student(String firstname, String lastname, LinkedList<Course> enrolledCourses){
        this.firstname = firstname;
        this.lastname = lastname;
        this.enrolledCourses = enrolledCourses;
    }

    public void addCourse(Course toEnroll){
        enrolledCourses.add(toEnroll);
    }

    public LinkedList<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public String toString() {
        return firstname + " " + lastname;
    }
}

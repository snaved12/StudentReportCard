package net.java.naved;

//Student.java
import java.util.List;


public class Student {
 private String name;
 private String rollNumber;
 private List<Subject> subjects;

 public String getName() {
     return name;
 }

 public void setName(String name) {
     this.name = name;
 }

 public String getRollNumber() {
     return rollNumber;
 }

 public void setRollNumber(String rollNumber) {
     this.rollNumber = rollNumber;
 }

 public List<Subject> getSubjects() {
     return subjects;
 }

 public void setSubjects(List<Subject> subjects) {
     this.subjects = subjects;
 }
}

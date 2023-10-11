package net.java.naved;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

public class Application {
    public static void main(String[] args) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            StudentData studentData = objectMapper.readValue(new File("json//j.json"), StudentData.class);
            List<Student> students = studentData.getStudents();

            for (Student student : students) {
                System.out.println("Student Name: " + student.getName());
                System.out.println("Roll Number: " + student.getRollNumber());
                System.out.println("Subjects:");

                List<Subject> subjects = student.getSubjects();
                for (Subject subject : subjects) {
                    System.out.println("Subject: " + subject.getSubject());
                    System.out.println("Marks: " + subject.getMarks());
                    System.out.println("Grade: " + subject.getGrade());
                }
                System.out.println();
                
                String filePath="C:\\Users\\ssyed\\eclipse-workspace\\ReportCard\\src\\main\\resources\\ProgressReport.jrxml";
                //JRBeanCollectionDataSource dataSource=new JRBeanCollectionDataSource(students);
                JRBeanCollectionDataSource dataSource2=new JRBeanCollectionDataSource(subjects);
                Map<String,Object> parameters=new HashMap<String,Object>();
                String name=student.getName();
                String rollno=student.getRollNumber();
                parameters.put("name",name);
        		parameters.put("rollno",rollno);
        		parameters.put("dataset",dataSource2);
        		
        		JasperReport report=JasperCompileManager.compileReport(filePath);
        		JasperPrint print=JasperFillManager.fillReport(report,parameters,new JREmptyDataSource());
        		
        		JasperExportManager.exportReportToPdfFile(print,"C:\\Users\\ssyed\\OneDrive\\Desktop\\SCOREME\\SCHOOL\\"+rollno+".pdf");
        		
        		JRXlsxExporter exporter=new JRXlsxExporter();
        		exporter.setExporterInput(new SimpleExporterInput(print));
        		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(new FileOutputStream(new File("C:\\Users\\ssyed\\OneDrive\\Desktop\\SCOREME\\SCHOOL\\"+rollno+".xlsx"))));
        		
        		exporter.exportReport();
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


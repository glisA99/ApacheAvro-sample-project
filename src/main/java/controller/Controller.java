package controller;

import java.io.IOException;
import java.util.List;
import lib.avro.Student;
import repository.StudentRepository;


public class Controller {
    
    private static Controller instance;
    private final StudentRepository studentRepository;

    private Controller() throws IOException {
        this.studentRepository = new StudentRepository();
    }
    
    public static Controller getInstance() throws IOException {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }
    
    public List<Student> getAllStudents() throws IOException {
        return studentRepository.getAll();
    }
    
    public Student getStudent(String index) throws RuntimeException, IOException {
        Student student = studentRepository.getByIndex(index);
        if (student == null) throw new RuntimeException("Student sa indexom: " + index + " ne postoji!");
        return student;
    }
    
    public void addStudent(Student student) throws IOException {
        studentRepository.add(student);
    }
    
}

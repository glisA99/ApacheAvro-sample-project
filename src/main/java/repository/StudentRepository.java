package repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import lib.avro.Student;
import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

public class StudentRepository {

    private final Schema schema = new Student().getSchema();
    private final File file = new File("students.avro");

    public StudentRepository() throws IOException {
        if (!file.exists()) {
            file.createNewFile();
            DatumWriter<Student> studentDatumWriter = new SpecificDatumWriter<>(Student.class);
            DataFileWriter<Student> dataFileWriter = new DataFileWriter<>(studentDatumWriter);
            dataFileWriter.create(schema, file);
        }
    }

    // Vraca sve studente koji su serijalizovani
    public List<Student> getAll() throws IOException {
        List<Student> students = new ArrayList<>();
        // Deserialize Students from disk
        DatumReader<Student> studentDatumReader = new SpecificDatumReader<>(Student.class);
        try {
            DataFileReader<Student> dataFileReader = new DataFileReader<>(file, studentDatumReader);
            while (dataFileReader.hasNext()) {
                Student student = dataFileReader.next();
                students.add(student);
            }
            System.out.println("Uspesno ucitana lista studenata!");
            dataFileReader.close();
        } catch (IOException ex) {
            System.out.println("Neuspesno ucitavanje liste studenata!");
            throw ex;
        }

        return students;
    }

    // Vraca studenta sa trazenim indexom ako postoji
    // ako ne postoji, vraca se null!
    public Student getByIndex(String index) throws IOException {
        Student student = null;
        // Deserialize Students from disk
        DatumReader<Student> studentDatumReader = new SpecificDatumReader<>(Student.class);
        try {
            DataFileReader<Student> dataFileReader = new DataFileReader<>(file, studentDatumReader);
            while (dataFileReader.hasNext()) {
                Student _student = dataFileReader.next(student);
                if (_student.getIndex().toString().equals(index)) {
                    student = _student;
                    break;
                }
            }
            dataFileReader.close();
        } catch (IOException ex) {
            System.out.println("Neuspesno pronalazenje studenta!");
            throw ex;
        }

        return student;
    }

    public void add(Student student) throws IOException {
        // Serialize student to disk
        DatumWriter<Student> studentDatumWriter = new SpecificDatumWriter<>(Student.class);
        DataFileWriter<Student> dataFileWriter = new DataFileWriter<>(studentDatumWriter);
        try {
            dataFileWriter.appendTo(file);
            dataFileWriter.append(student);
            System.out.println("Uspesno dodavanje novog studenta!");
            dataFileWriter.close();
        } catch (IOException ex) {
            System.out.println("Neuspesno dodavanje novog studenta!");
            throw ex;
        }
    }

}

package avro;

import controller.Controller;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import lib.avro.Student;

public class Test {

    public static void main(String[] args) throws IOException {
        Controller controller = Controller.getInstance();
        try {
            for(Student _student : controller.getAllStudents()) {
                System.out.println(_student);
            }
        } catch (IOException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

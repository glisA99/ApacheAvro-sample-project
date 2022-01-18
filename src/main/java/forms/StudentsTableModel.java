package forms;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import lib.avro.Student;


public class StudentsTableModel extends AbstractTableModel {
    
    private final String[] columnNames = new String[] { "Index", "Firstname", "Lastname", "Faculty", "ESPB" };
    private final List<Student> students;

    public StudentsTableModel(List<Student> students) {
        this.students = students;
    }
    
    @Override
    public int getRowCount() {
        return students.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int arg0, int arg1) {
        Student student = this.students.get(arg0);
        switch(arg1) {
            case 0: 
                return student.getIndex();
            case 1:
                return student.getFirstname();
            case 2: 
                return student.getLastname();
            case 3: 
                return student.getFaculty();
            case 4: 
                return student.getEspb();
            default: 
                return "N/A";
        }
    }
    
    
    
}

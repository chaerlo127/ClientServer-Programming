
import java.io.Serializable;
import java.util.StringTokenizer;

public class Reservation implements Serializable{
	private static final long serialVersionUID = 1L;
	protected String studentId;
	protected String courseId;

    // ���� �������� �ڹ��� class ���·� : Entity Class, Value Object
    public Reservation(String inputString) {
        StringTokenizer stringTokenizer = new StringTokenizer(inputString);
    	this.studentId = stringTokenizer.nextToken();
    	this.courseId = stringTokenizer.nextToken();
    }
    
    public boolean match(String studentId) {
        return this.studentId.equals(studentId);
    }
    
    public String getStudentID() {
        return this.studentId;
    }
    
    public String getCourseID() {
    	return this.courseId;
    }
    
    public String toString() {
        return this.studentId + "\t" + this.courseId;
    }
}

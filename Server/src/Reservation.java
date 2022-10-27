
import java.io.Serializable;
import java.util.StringTokenizer;

public class Reservation implements Serializable{
	private static final long serialVersionUID = 1L;
	protected String studentId;
	protected String courseId;

    // 여러 정보들을 자바의 class 형태로 : Entity Class, Value Object
    public Reservation(String inputString) {
        StringTokenizer stringTokenizer = new StringTokenizer(inputString);
    	this.studentId = stringTokenizer.nextToken();
    	this.courseId = stringTokenizer.nextToken();
    }
    public boolean match(String studentId, String courseId) {
        return this.studentId.equals(studentId) && this.courseId.equals(courseId);
    }
	public boolean matchStudent(String studentId) {
		return this.studentId.equals(studentId);
	}
    public String getStudentID() {
        return this.studentId;
    }
    
    public String getCourseID() {
    	return this.courseId;
    }
    
    public String toString() {
        return "학번: " + this.studentId + "\t" + "과목 코드: " + this.courseId;
    }

}

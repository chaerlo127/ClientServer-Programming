
import java.io.Serializable;
import java.util.StringTokenizer;
import java.util.logging.Logger;

public class Reservation implements Serializable{
	private static final long serialVersionUID = 1L;
	protected String studentId;
	protected String courseId;

    // 여러 정보들을 자바의 class 형태로 : Entity Class, Value Object
    public Reservation(String inputString, Logger log, String logUser) {
        StringTokenizer stringTokenizer = new StringTokenizer(inputString);
    	this.studentId = stringTokenizer.nextToken();
    	this.courseId = stringTokenizer.nextToken();
    	log.info(logUser);
    }
    public boolean match(String studentId) {
        return this.studentId.equals(studentId);
    }
	public boolean matchCourse(String courseId) {
		return this.courseId.equals(courseId);
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

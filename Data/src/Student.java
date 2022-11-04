
import java.io.Serializable;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Student implements Serializable{
	private static final long serialVersionUID = 1L;
	protected String studentId;
    protected String name;
    protected String department;
    protected String password;
    protected ArrayList<String> vStudent;

    // 여러 정보들을 자바의 class 형태로 : Entity Class, Value Object
    public Student(String inputString) {
        StringTokenizer stringTokenizer = new StringTokenizer(inputString);
    	this.studentId = stringTokenizer.nextToken();
    	this.password = stringTokenizer.nextToken();
    	this.name = stringTokenizer.nextToken();
    	this.name += " " + stringTokenizer.nextToken();
    	this.department = stringTokenizer.nextToken();
    	this.vStudent = new ArrayList<String>();
    	while (stringTokenizer.hasMoreTokens()) {
    		this.vStudent.add(stringTokenizer.nextToken());
    	}
    }
    
    public boolean match(String studentId) {
        return this.studentId.equals(studentId);
    }
    
    public boolean matchpw(String password) {
        return this.password.equals(password);
    }
    
    public String getName() {
        return this.name;
    }
    
    public ArrayList<String> getCompletedCourses() {
        return this.vStudent;
    }
    
    public String toString() {
        String stringReturn = this.studentId + "\t" + this.password + "\t" + this.name + "\t" + this.department + "\t";
        for (int i = 0; i < this.vStudent.size(); i++) {
            stringReturn = stringReturn + " " + this.vStudent.get(i).toString();
        }
        return stringReturn;
    }
}

import java.io.Serializable;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Course implements Serializable{
	private static final long serialVersionUID = 1L;
	protected String courseId;
    protected String name;
    protected String courseName;
    protected ArrayList<String> precourseNameList;

    public Course(String inputString) {
        StringTokenizer stringTokenizer = new StringTokenizer(inputString);
    	this.courseId = stringTokenizer.nextToken();
    	this.name = stringTokenizer.nextToken();
    	this.courseName = stringTokenizer.nextToken();
    	this.precourseNameList = new ArrayList<String>();
    	while (stringTokenizer.hasMoreTokens()) {
    		this.precourseNameList.add(stringTokenizer.nextToken());
    	}
    }
    
    public boolean matchStudent(String courseId) {return this.courseId.equals(courseId); }
    
    public String getName() {return this.name;}
    
    public ArrayList<String> getPrecourseList() { return this.precourseNameList;}
    
    public String toString() {
        String stringReturn = this.courseId + "\t" + this.name + "\t" + this.courseName + "\t";
        for (int i = 0; i < this.precourseNameList.size(); i++) {
            stringReturn = stringReturn +  " " + this.precourseNameList.get(i).toString();
        }
        return stringReturn;
    }
}

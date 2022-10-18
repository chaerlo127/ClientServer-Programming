
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CourseList {
	protected ArrayList<Course> vCourse;
	
	public CourseList(String sCourseFileName) throws FileNotFoundException, IOException {
		BufferedReader objCourseFile = new BufferedReader(new FileReader(sCourseFileName));
		this.vCourse = new ArrayList<Course>();
		while (objCourseFile.ready()) {
			String stuInfo = objCourseFile.readLine();
			if (!stuInfo.equals("")) {
				this.vCourse.add(new Course(stuInfo));
			}
		}
		objCourseFile.close();
	}

	public ArrayList<Course> getAllCourseRecords() throws NullDataException{
		if(this.vCourse.size() == 0) throw new NullDataException("-------------- Course data is null --------------");
		return this.vCourse;
	}
	
	public boolean addCourseRecords(String courseInfo) throws NullDataException { // student로 해도 됨. 어려움. 예외 사항 발생
		if(courseInfo == null) throw new NullDataException("-------------- courseInfo data is null --------------");
		if(this.vCourse.add(new Course(courseInfo))) return true;
		else return false;
	}
	
	public boolean deleteCourseRecords(String courseId) {
		for (int i = 0; i < this.vCourse.size(); i++) {
			Course student = (Course) this.vCourse.get(i);
			if (student.match(courseId)) {
				if(this.vCourse.remove(student)) return true;
				else return false;
			}
		}
		return false;
	}
	
	public boolean checkCourseWithSID(String sSID) {
		for (int i = 0; i < this.vCourse.size(); i++) {
			Course objCourse = (Course) this.vCourse.get(i);
			if (objCourse.match(sSID)) {
				return true;
			}
		}
		return false;
	}
}

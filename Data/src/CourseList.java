import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

public class CourseList {
	protected ArrayList<Course> vCourse;
	private static Logger LOG;
	private String sCourseFileName;
	public CourseList(String sCourseFileName, Logger log) throws FileNotFoundException, IOException {
		LOG = log;
		this.sCourseFileName = sCourseFileName;
		BufferedReader objCourseFile = new BufferedReader(new FileReader(sCourseFileName));
		this.vCourse = new ArrayList<Course>();
		while (objCourseFile.ready()) {
			String stuInfo = objCourseFile.readLine();
			if (!stuInfo.equals("")) this.vCourse.add(new Course(stuInfo));
		}
		objCourseFile.close();
	}

	public ArrayList<Course> getAllCourseRecords(String logUser) throws NullDataException{
		LOG.info(logUser);
		if(this.vCourse.size() == 0) throw new NullDataException("-------------- Course data is null --------------");
		return this.vCourse;
	}
	
	public boolean addCourseRecords(String courseInfo, String logUser) throws NullDataException {
		LOG.info(logUser);
		if(courseInfo == null) throw new NullDataException("-------------- courseInfo data is null --------------");
		if(this.vCourse.add(new Course(courseInfo))) {
			addCourseFile(courseInfo, logUser);
			return true;
		}
		else return false;
	}

	private void addCourseFile(String courseInfo, String logUser) {
		try {
			LOG.info(logUser);
			BufferedWriter objFileWrite = new BufferedWriter(new FileWriter(sCourseFileName, true));
			objFileWrite.newLine();
			objFileWrite.newLine();
			objFileWrite.write(courseInfo);
			objFileWrite.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean deleteCourseRecords(String courseId, String logUser) {
		LOG.info(logUser);
		for (int i = 0; i < this.vCourse.size(); i++) {
			Course student = (Course) this.vCourse.get(i);
			if (student.matchStudent(courseId)) {
				if(this.vCourse.remove(student)) {
					deleteCourseFile();
					return true;
				}
				else return false;
			}
		}
		return false;
	}

	private void deleteCourseFile() {
		try {
			BufferedWriter objFileWrite = new BufferedWriter(new FileWriter(sCourseFileName));
			for(Course course: this.vCourse) {
				objFileWrite.newLine();
				objFileWrite.newLine();
				objFileWrite.write(course.toString());
			}
			objFileWrite.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean checkPreCourseWithSID(String sSID, String logUser) {
		LOG.info(logUser);
		for (int i = 0; i < this.vCourse.size(); i++) {
			Course course = (Course) this.vCourse.get(i);
			if (course.matchStudent(sSID)) {
				return true;
			}
		}
		return false;
	}
	
	public Course checkCourseWithSID(String sSID, String logUser) {
		LOG.info(logUser);
		for (int i = 0; i < this.vCourse.size(); i++) {
			Course course = (Course) this.vCourse.get(i);
			if (course.matchStudent(sSID)) {
				return course;
			}
		}
		return null;
	}
}

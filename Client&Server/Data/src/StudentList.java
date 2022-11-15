
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

public class StudentList {
	protected ArrayList<Student> vStudentList;
	private static Logger LOG;
	private String sStudentFileName;
	
	public StudentList(String sStudentFileName, Logger log) throws FileNotFoundException, IOException {
		BufferedReader objStudentFile = new BufferedReader(new FileReader(sStudentFileName));
		LOG = log;
		this.sStudentFileName = sStudentFileName;
		this.vStudentList = new ArrayList<Student>();
		while (objStudentFile.ready()) {
			String stuInfo = objStudentFile.readLine();
			if (!stuInfo.equals("")) {
				this.vStudentList.add(new Student(stuInfo));
			}
		}
		objStudentFile.close();
	}

	public ArrayList<Student> getAllStudentRecords(String logUser) throws NullDataException{
		LOG.info(logUser);
		if(this.vStudentList.size() == 0) throw new NullDataException("-------------- Student data is null --------------");
		return this.vStudentList;
	}
	
	public boolean addStudentRecords(String studentInfo, String logUser) throws NullDataException {
		LOG.info(logUser);
		if(studentInfo == null) throw new NullDataException("-------------- studentInfo data is null --------------");
		if(this.vStudentList.add(new Student(studentInfo))) {
			addCourseFile(studentInfo, logUser);
			return true;
		}
		else return false;
	}
	
	public boolean deleteStudentRecords(String studentId, String logUser) {
		LOG.info(logUser);
		for (int i = 0; i < this.vStudentList.size(); i++) {
			Student student = (Student) this.vStudentList.get(i);
			if (student.matchStudent(studentId)) {
				if(this.vStudentList.remove(student)) {
					deleteStudentFile();
					return true;
				}
				else return false;
			}
		}
		return false;
	}
	
	private void deleteStudentFile() {
		try {
			BufferedWriter objFileWrite = new BufferedWriter(new FileWriter(sStudentFileName));
			for(Student student: this.vStudentList) {
				objFileWrite.newLine();
				objFileWrite.newLine();
				objFileWrite.write(student.toString());
			}
			objFileWrite.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void addCourseFile(String studentInfo, String logUser) {
		try {
			LOG.info(logUser);
			BufferedWriter objFileWrite = new BufferedWriter(new FileWriter(sStudentFileName, true));
			objFileWrite.newLine();
			objFileWrite.newLine();
			objFileWrite.write(studentInfo);
			objFileWrite.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Student isRegisteredStudent(String sSID, String logUser) {
		LOG.info(logUser);
		for (int i = 0; i < this.vStudentList.size(); i++) {
			Student student = (Student) this.vStudentList.get(i);
			if (student.matchStudent(sSID)) {
				return student;
			}
		}
		return null;
	}
	
	public Student checkStudent(String sSID, String password, String logUser) {
		LOG.info(logUser);
		for (int i = 0; i < this.vStudentList.size(); i++) {
			Student student = (Student) this.vStudentList.get(i);
			if (student.matchStudent(sSID) && student.matchpw(password)) {
				return student;
			}
		}
		return null;
	}
	
	
}
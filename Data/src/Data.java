import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Data extends UnicastRemoteObject implements DataIF{
	protected static StudentList studentList;
	protected static CourseList courseList;
	private static final long serialVersionUID = 1L;
	private static String path = "C:\\Users\\chaeun\\Desktop\\JAVA\\workspace\\ClientServerProgramming\\Data";
	String name;
	
	protected Data() throws RemoteException {
		super();
	}
	
	// 프로세스 
	public static void main(String[] args)  {
		try {
			Data data = new Data();
			Naming.bind("Data", data);
			System.out.println("Data is ready !!!");
			studentList = new StudentList(path + "\\Students.txt");
			courseList = new CourseList(path + "\\Courses.txt");
		} 
		catch (MalformedURLException e) {System.out.println("MalformedURLException: rmiRegistry를 찾을 수 없습니다.");} 
		catch (RemoteException e) {System.out.println("RemoteException: 원격지에서 예외가 발생했습니다.");} 
		catch (AlreadyBoundException e) {System.out.println("AlreadyBoundException: 이미 rmiRegistry에 연결이 되었습니다.");}
		catch (FileNotFoundException e) {System.out.println("FileNotFoundException: 파일의 값이 없습니다.");} 
		catch (IOException e) {System.out.println("IOException: 파일을 읽어올 수 없습니다.");}
	}

	@Override
	public ArrayList<Student> getAllStudentData() throws RemoteException, NullDataException{
		return studentList.getAllStudentRecords();
	}
	
	@Override
	public boolean addStudent(String studentInfo) throws RemoteException, NullDataException {
		if(studentList.addStudentRecords(studentInfo)) return true;
		else return false;
	}

	@Override
	public boolean deleteStudent(String studentId) throws RemoteException {
		if(studentList.deleteStudentRecords(studentId)) return true;
		else return false;
	}
	
	@Override
	public ArrayList<Course> getAllCourseList() throws RemoteException, NullDataException{
		return courseList.getAllCourseRecords();
	}

	@Override
	public boolean addCourse(String courseInfo) throws RemoteException, NullDataException {
		if(courseList.addCourseRecords(courseInfo)) return true;
		else return false;
	}

	@Override
	public boolean deleteCourse(String courseId) throws RemoteException {
		if(courseList.deleteCourseRecords(courseId)) return true;
		else return false;
	}

	
}

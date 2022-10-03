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
		// TODO Auto-generated constructor stub
	}
	// 프로세스 
	public static void main(String[] args)  {
			// 브로커에 등록을 해줌. 
			try {
				Data data = new Data();
				Naming.bind("Data", data);
				System.out.println("Data is ready !!!");
				studentList = new StudentList(path + "\\Students.txt");
				courseList = new CourseList(path + "\\Courses.txt");
			} 
			catch (MalformedURLException e) {
				System.out.println("MalformedURLException: rmiRegistry를 찾을 수 없습니다.");
//				e.printStackTrace();
			} 
			catch (RemoteException e) {
				// TODO Auto-generated catch block
				System.out.println("RemoteException: 원격지에서 예외가 발생했습니다.");
//				e.printStackTrace();
			} catch (AlreadyBoundException e) {
				// TODO Auto-generated catch block
				System.out.println("AlreadyBoundException: 이미 rmiRegistry에 연결이 되었습니다.");
//				e.printStackTrace();
			}catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println("FileNotFoundException: 파일의 값이 없습니다.");
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("IOException: 파일을 읽어올 수 없습니다.");
//				e.printStackTrace();
			}
			


	}

	@Override
	public ArrayList<Student> getAllStudentData() throws RemoteException {
		return studentList.getAllStudentRecords();
	}
	@Override
	public ArrayList<Course> getAllCourseList() throws RemoteException {
		return courseList.getAllCourseRecords();
	}
}

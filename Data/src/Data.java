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
	// ���μ��� 
	public static void main(String[] args)  {
			// ���Ŀ�� ����� ����. 
			try {
				Data data = new Data();
				Naming.bind("Data", data);
				System.out.println("Data is ready !!!");
				studentList = new StudentList(path + "\\Students.txt");
				courseList = new CourseList(path + "\\Courses.txt");
			} 
			catch (MalformedURLException e) {
				System.out.println("MalformedURLException: rmiRegistry�� ã�� �� �����ϴ�.");
//				e.printStackTrace();
			} 
			catch (RemoteException e) {
				// TODO Auto-generated catch block
				System.out.println("RemoteException: ���������� ���ܰ� �߻��߽��ϴ�.");
//				e.printStackTrace();
			} catch (AlreadyBoundException e) {
				// TODO Auto-generated catch block
				System.out.println("AlreadyBoundException: �̹� rmiRegistry�� ������ �Ǿ����ϴ�.");
//				e.printStackTrace();
			}catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println("FileNotFoundException: ������ ���� �����ϴ�.");
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("IOException: ������ �о�� �� �����ϴ�.");
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

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
	private static final long serialVersionUID = 1L;
	private static String path = "C:\\Users\\chaeun\\Desktop\\JAVA\\workspace\\ClientServerProgramming\\Data";
	String name;
	
	protected Data() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
	// 프로세스 
	public static void main(String[] args) throws FileNotFoundException, IOException {
			// 브로커에 등록을 해줌. 
			try {
				Data data = new Data();
				Naming.bind("Data", data);
				System.out.println("Data is ready !!!");
				studentList = new StudentList(path+"\\Students.txt");
				
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (AlreadyBoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			


	}

	@Override
	public ArrayList<Student> getAllStudentData() throws RemoteException {
		return studentList.getAllStudentRecords();
	}
}

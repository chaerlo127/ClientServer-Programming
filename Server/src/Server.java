import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Server extends UnicastRemoteObject implements ServerIF{
	private static final long serialVersionUID = 1L;
	private static DataIF data;
	String name;
	protected Server() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
	// 프로세스 
	public static void main(String[] args) {
			// 브로커에 등록을 해줌. 
			try {

				Server server = new Server();
				Naming.bind("Server", server);
				System.out.println("Server is ready !!!");
				// Data 코드 호출하기
				data = (DataIF) Naming.lookup("Data");
			} catch (MalformedURLException e) {
				System.out.println("MalformedURLException: rmiRegistry를 찾을 수 없습니다.");
//					e.printStackTrace();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				System.out.println("RemoteException: 원격지에서 예외가 발생했습니다.");
//					e.printStackTrace();
			} catch (AlreadyBoundException e) {
				// TODO Auto-generated catch block
				System.out.println("AlreadyBoundException: 이미 rmiRegistry에 연결이 되었습니다.");
//					e.printStackTrace();
			} catch (NotBoundException e) {
				// TODO Auto-generated catch block
				System.out.println("NotBoundException: 이미 rmiRegistry에 연결할 Data가 없습니다.");
//				e.printStackTrace();
			}
	}
	public int add(int a, int b) {  
		System.out.println("Server's response !!!");
		return a+b;
	}
	public void save(String name) throws RemoteException {
		System.out.println("Server's response !!!");
		this.name = name;
	}
	@Override
	public ArrayList<Student> getAllStudentData() throws RemoteException {
		return data.getAllStudentData();
	}
	@Override
	public ArrayList<Course> getAllCourseList() throws RemoteException {
		return data.getAllCourseList();
	}
}

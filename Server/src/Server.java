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
	// ���μ��� 
	public static void main(String[] args) {
			// ���Ŀ�� ����� ����. 
			try {

				Server server = new Server();
				Naming.bind("Server", server);
				System.out.println("Server is ready !!!");
				// Data �ڵ� ȣ���ϱ�
				data = (DataIF) Naming.lookup("Data");
			} catch (MalformedURLException e) {
				System.out.println("MalformedURLException: rmiRegistry�� ã�� �� �����ϴ�.");
//					e.printStackTrace();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				System.out.println("RemoteException: ���������� ���ܰ� �߻��߽��ϴ�.");
//					e.printStackTrace();
			} catch (AlreadyBoundException e) {
				// TODO Auto-generated catch block
				System.out.println("AlreadyBoundException: �̹� rmiRegistry�� ������ �Ǿ����ϴ�.");
//					e.printStackTrace();
			} catch (NotBoundException e) {
				// TODO Auto-generated catch block
				System.out.println("NotBoundException: �̹� rmiRegistry�� ������ Data�� �����ϴ�.");
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

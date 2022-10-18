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
		try {
			Server server = new Server();
			Naming.bind("Server", server);
			System.out.println("Server is ready !!!");
			data = (DataIF) Naming.lookup("Data");
		} catch (MalformedURLException e) {System.out.println("MalformedURLException: rmiRegistry�� ã�� �� �����ϴ�.");} 
		catch (RemoteException e) {System.out.println("RemoteException: ���������� ���ܰ� �߻��߽��ϴ�.");} 
		catch (AlreadyBoundException e) {System.out.println("AlreadyBoundException: �̹� rmiRegistry�� ������ �Ǿ����ϴ�.");} 
		catch (NotBoundException e) {System.out.println("NotBoundException: �̹� rmiRegistry�� ������ Data�� �����ϴ�.");}
	}
	
	@Override
	public ArrayList<Student> getAllStudentData() throws RemoteException, NullDataException{
		return data.getAllStudentData();
	}
	
	@Override
	public boolean addStudent(String studentInfo) throws RemoteException, NullDataException {
		if(data.addStudent(studentInfo)) return true;
		// �й��� ������ ����
		else return false;
	}
	
	@Override
	public boolean deleteStudent(String studentId) throws RemoteException { // exception�� �ִٸ� ���⼭ �ؾ���. 
		if(data.deleteStudent(studentId)) return true;
		else return false;
	}
	
	@Override
	public ArrayList<Course> getAllCourseList() throws RemoteException, NullDataException{
		return data.getAllCourseList();
	}
	@Override
	public boolean addCourse(String courseInfo) throws RemoteException, NullDataException {
		if(data.addCourse(courseInfo)) return true;
		// course ������ ������ ����
		else return false;
	}
	@Override
	public boolean deleteCourse(String courseId) throws RemoteException {
		if(data.deleteCourse(courseId)) return true;
		else return false;
	}
	@Override
	public String addReservation(String reservationInfo) throws RemoteException, NullDataException {
		Reservation reservation = new Reservation(reservationInfo);
		Student studentInfo = data.checkStudent(reservation.studentId);
		if(studentInfo == null) return "�������� ���� �й��Դϴ�.";
		
		if(!data.checkCourse(reservation.courseId)) return "�������� �ʴ� ���� ��ȣ �Դϴ�.";
		if(data.addReservation(reservationInfo)) return "����";
		else return "����";
	}
	@Override
	public boolean deleteReservation(String reservationId) throws RemoteException {
		if(data.deleteReservation(reservationId)) return true;
		else return false;
	}
	@Override
	public ArrayList<Reservation> getAllReservationList() throws RemoteException, NullDataException {
		return data.getAllReservationList();
	}
	@Override
	public boolean checkStudent(String userId) throws RemoteException {
		if(data.checkStudent(userId) == null) return false;
		else return true;
	}
	@Override
	public boolean checkCourse(String courseId) throws RemoteException {
		return data.checkCourse(courseId);
	}

}

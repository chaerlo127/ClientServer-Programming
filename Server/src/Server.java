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
		try {
			Server server = new Server();
			Naming.bind("Server", server);
			System.out.println("Server is ready !!!");
			data = (DataIF) Naming.lookup("Data");
		} catch (MalformedURLException e) {System.out.println("MalformedURLException: rmiRegistry를 찾을 수 없습니다.");} 
		catch (RemoteException e) {System.out.println("RemoteException: 원격지에서 예외가 발생했습니다.");} 
		catch (AlreadyBoundException e) {System.out.println("AlreadyBoundException: 이미 rmiRegistry에 연결이 되었습니다.");} 
		catch (NotBoundException e) {System.out.println("NotBoundException: 이미 rmiRegistry에 연결할 Data가 없습니다.");}
	}
	
	@Override
	public ArrayList<Student> getAllStudentData() throws RemoteException, NullDataException{
		return data.getAllStudentData();
	}
	
	@Override
	public boolean addStudent(String studentInfo) throws RemoteException, NullDataException {
		if(data.addStudent(studentInfo)) return true;
		// 학번이 같으면 에러
		else return false;
	}
	
	@Override
	public boolean deleteStudent(String studentId) throws RemoteException { // exception이 있다면 여기서 해야함. 
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
		// course 정보가 같으면 에러
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
		if(studentInfo == null) return "존재하지 않은 학번입니다.";
		
		if(!data.checkCourse(reservation.courseId)) return "존재하지 않는 강의 번호 입니다.";
		if(data.addReservation(reservationInfo)) return "성공";
		else return "실패";
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

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
		else return false; // course 정보가 같으면 에러
	}
	
	@Override
	public boolean deleteCourse(String courseId) throws RemoteException {
		if(data.deleteCourse(courseId)) return true;
		else return false;
	}
	
	@Override
	public String addReservation(String reservationInfo) throws RemoteException, NullDataException {
		System.out.println(reservationInfo);
		if(reservationInfo.equals(" ")) return new StringReturn(StringReturnException.HAVE_NOT_VALUE).getErrorMessage();
		Reservation reservation = new Reservation(reservationInfo);
		Student studentInfo = data.checkStudent(reservation.studentId);
		Course course = data.checkCourse(reservation.courseId);
		if(studentInfo == null) return new StringReturn(StringReturnException.HAVE_NOT_STUDENT).getErrorMessage();
		if(course == null) return new StringReturn(StringReturnException.HAVE_NOT_COURSE).getErrorMessage();
		
		for(int i = 0; i<studentInfo.vStudent.size(); i++) {
			if(course.courseId.equals(studentInfo.vStudent.get(i))) return new StringReturn(StringReturnException.ALREADY_REGISTERED_COURSE).getErrorMessage();
		}
		
		// 변경이 필요하다.
		if(course.getPrecourseName().size()>0) {
			int checkPrecourse = 0;
			for(int i = 0 ; i<course.getPrecourseName().size() ; i++) {
				for(int j = 0; j<studentInfo.getCompletedCourses().size(); j++) {
					if(studentInfo.getCompletedCourses().get(j).equals(course.getPrecourseName().get(i))) {
						checkPrecourse++;
					}
				}
			}
			if(checkPrecourse != course.getPrecourseName().size()) return new StringReturn(StringReturnException.NOT_REGISTERED_PRECOURSE).getErrorMessage();;
		}
	
		if(data.addReservation(reservationInfo)) return new StringReturn(StringReturnException.SUCCESS).getErrorMessage();
		else return new StringReturn(StringReturnException.FAIL).getErrorMessage();
	}
	
	@Override
	public boolean deleteReservation(String reservationId) throws RemoteException {
		if(data.deleteReservation(reservationId)) return true;
		else return false;
	}
	@Override
	public ArrayList<Reservation> getAllReservationList() throws RemoteException {
		return data.getAllReservationList();
	}
	@Override
	public boolean checkStudent(String userId) throws RemoteException {
		if(data.checkStudent(userId) == null) return false;
		else return true;
	}
	@Override
	public boolean checkCourse(String courseId) throws RemoteException {
		if(data.checkCourse(courseId) == null) return false;
		else return true;
	}
	@Override
	public boolean login(String studentNum, String password) throws RemoteException {
		if(studentNum.equals(null) || password.equals(null)) return false;
		
		if(data.checkLogin(studentNum, password) != null) return true;
		else return false;
	}
	@Override
	public boolean signUP(String studentNum, String password, String name, String major) throws RemoteException {
		if(studentNum.equals(null) || password.equals(null) || name.equals(null) || major.equals(null)) return false;
		if(data.checkStudent(studentNum) != null) return false;
		
		if(data.signUP(studentNum, password, name, major)) return true;
		return false;
	}
}

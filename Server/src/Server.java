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
	}
	
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
	public String addStudent(String studentInfo) throws RemoteException, NullDataException {
		if(data.addStudent(studentInfo)) return new StringReturn(StringReturnException.SUCCESS).getErrorMessage();
		// �й��� ������ ����
		else return new StringReturn(StringReturnException.NOT_ADD_STUDENT).getErrorMessage();
	}
	
	@Override
	public String deleteStudent(String studentId) throws RemoteException { // exception�� �ִٸ� ���⼭ �ؾ���. 
		if(data.deleteStudent(studentId)) return new StringReturn(StringReturnException.SUCCESS).getErrorMessage();
		else return new StringReturn(StringReturnException.NOT_DELETED_STUDENT).getErrorMessage();
	}
	
	@Override
	public ArrayList<Course> getAllCourseList() throws RemoteException, NullDataException{
		return data.getAllCourseList();
	}
	
	@Override
	public String addCourse(String courseInfo) throws RemoteException, NullDataException {
		if(data.checkCourse(courseInfo) != null) return new StringReturn(StringReturnException.HAVE_COURSE).getErrorMessage();
		if(data.addCourse(courseInfo)) return new StringReturn(StringReturnException.SUCCESS).getErrorMessage();
		else return new StringReturn(StringReturnException.NOT_ADD_COURSE).getErrorMessage();
	}
	
	@Override
	public String deleteCourse(String courseId) throws RemoteException {
		if(data.deleteCourse(courseId)) return new StringReturn(StringReturnException.SUCCESS).getErrorMessage();
		else return new StringReturn(StringReturnException.NOT_ADD_COURSE).getErrorMessage();
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
		
		for(int i = 0; i<studentInfo.vStudent.size(); i++) {if(course.courseId.equals(studentInfo.vStudent.get(i))) return new StringReturn(StringReturnException.ALREADY_REGISTERED_COURSE).getErrorMessage();}
		
		if(checkPrecourse(studentInfo, course) != course.getPrecourseList().size()) return new StringReturn(StringReturnException.NOT_REGISTERED_PRECOURSE).getErrorMessage();;
	
		// �̹� ������û�� �� ���
		if(data.checkReservation(reservation)) return new StringReturn(StringReturnException.ALREADY_REGISTERED_COURSE).getErrorMessage();
		if(data.addReservation(reservationInfo)) return new StringReturn(StringReturnException.SUCCESS).getErrorMessage();
		else return new StringReturn(StringReturnException.FAIL).getErrorMessage();
	}

	private int checkPrecourse(Student studentInfo, Course course) {
		int checkPrecourse = 0;
		for(int i = 0 ; i<course.getPrecourseList().size() ; i++) {
			for(int j = 0; j<studentInfo.getCompletedCourses().size(); j++) {
				if(studentInfo.getCompletedCourses().get(j).equals(course.getPrecourseList().get(i))) {
					checkPrecourse++;
				}
			}
		}
		return checkPrecourse;
	}
	
	@Override
	public String deleteReservation(String reservationId) throws RemoteException {
		if(data.deleteReservation(reservationId)) return new StringReturn(StringReturnException.SUCCESS).getErrorMessage();
		else return new StringReturn(StringReturnException.NOT_DELETED_RESERVATION).getErrorMessage();
	}
	
	@Override
	public ArrayList<Reservation> getAllReservationList() throws RemoteException {
		return data.getAllReservationList();
	}
	
	@Override
	public String checkStudent(String userId) throws RemoteException {
		if(data.checkStudent(userId) == null) return new StringReturn(StringReturnException.SUCCESS).getErrorMessage();
		else return new StringReturn(StringReturnException.HAVE_NOT_STUDENT).getErrorMessage();
	}
	
	@Override
	public String checkCourse(String courseId) throws RemoteException {
		if(data.checkCourse(courseId) == null) return new StringReturn(StringReturnException.SUCCESS).getErrorMessage();
		else return new StringReturn(StringReturnException.HAVE_NOT_COURSE).getErrorMessage();
	}
	
	@Override
	public String login(String studentNum, String password) throws RemoteException {
		if(studentNum.equals(null) || password.equals(null)) return new StringReturn(StringReturnException.HAVE_NOT_VALUE).getErrorMessage();
		
		if(data.checkLogin(studentNum, password) != null) return new StringReturn(StringReturnException.SUCCESS).getErrorMessage();
		else return new StringReturn(StringReturnException.FAIL).getErrorMessage();
	}
	
	@Override
	public String signUP(String studentNum, String password, String name, String major) throws RemoteException {
		if(studentNum.equals(null) || password.equals(null) || name.equals(null) || major.equals(null)) return new StringReturn(StringReturnException.HAVE_NOT_VALUE).getErrorMessage();
		if(data.checkStudent(studentNum) != null) return new StringReturn(StringReturnException.HAVE_STUDENT).getErrorMessage();
		
		if(data.signUP(studentNum, password, name, major)) return new StringReturn(StringReturnException.SUCCESS).getErrorMessage();
		return new StringReturn(StringReturnException.FAIL).getErrorMessage();
	}
}

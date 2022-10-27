import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ServerIF extends Remote{
	ArrayList<Student> getAllStudentData() throws RemoteException, NullDataException;
	ArrayList<Course> getAllCourseList() throws RemoteException, NullDataException;
	ArrayList<Reservation> getAllReservationList() throws RemoteException;
	String addStudent(String studentInfo) throws RemoteException, NullDataException;
	String deleteStudent(String studentId) throws RemoteException;
	String addCourse(String courseInfo) throws RemoteException, NullDataException;
	String deleteCourse(String courseId) throws RemoteException;
	String addReservation(String reservationInfo) throws RemoteException, NullDataException;
	String deleteReservation(String reservationId) throws RemoteException;
	
	String checkStudent(String userId) throws RemoteException;
	String checkCourse(String courseId) throws RemoteException;
	
	String login(String studentNum, String password) throws RemoteException;
	String signUP(String studentNum, String password, String name, String major) throws RemoteException;
}

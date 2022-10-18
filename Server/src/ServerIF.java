import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ServerIF extends Remote{
	ArrayList<Student> getAllStudentData() throws RemoteException, NullDataException;
	ArrayList<Course> getAllCourseList() throws RemoteException, NullDataException;
	ArrayList<Reservation> getAllReservationList() throws RemoteException, NullDataException;
	boolean addStudent(String studentInfo) throws RemoteException, NullDataException;
	boolean deleteStudent(String studentId) throws RemoteException;
	boolean addCourse(String courseInfo) throws RemoteException, NullDataException;
	boolean deleteCourse(String courseId) throws RemoteException;
	String addReservation(String reservationInfo) throws RemoteException, NullDataException;
	boolean deleteReservation(String reservationId) throws RemoteException;
	
	boolean checkStudent(String userId) throws RemoteException;
	boolean checkCourse(String courseId) throws RemoteException;
}

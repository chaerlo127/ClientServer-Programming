import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface DataIF extends Remote{
	ArrayList<Student> getAllStudentData() throws RemoteException, NullDataException;
	ArrayList<Course> getAllCourseList() throws RemoteException, NullDataException;
	ArrayList<Reservation> getAllReservationList() throws RemoteException, NullDataException;
	boolean addStudent(String studentInfo) throws RemoteException, NullDataException;
	boolean deleteStudent(String studentId) throws RemoteException;
	boolean addCourse(String courseInfo) throws RemoteException, NullDataException;
	boolean deleteCourse(String courseId) throws RemoteException;
	boolean addReservation(String reservationInfo) throws RemoteException, NullDataException;
	boolean deleteReservation(String reservationId) throws RemoteException;
	
	Student checkStudent(String userId) throws RemoteException;
	Course checkCourse(String courseId) throws RemoteException;
	Student checkLogin(String userId, String password) throws RemoteException;
	boolean signUP(String studentNum, String password, String name, String major) throws RemoteException, NullDataException;
	boolean checkReservation(Reservation reservation);
}

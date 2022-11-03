import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ServerIF extends Remote{
	StringReturnResponse<ArrayList<Student>> getAllStudentData() throws RemoteException, NullDataException;
	StringReturnResponse<ArrayList<Course>> getAllCourseList() throws RemoteException, NullDataException;
	StringReturnResponse<ArrayList<Reservation>> getAllReservationList() throws RemoteException;
	StringReturnResponse<String> addStudent(String studentInfo) throws RemoteException, NullDataException;
	StringReturnResponse<String>  deleteStudent(String studentId) throws RemoteException;
	StringReturnResponse<String>  addCourse(String courseInfo) throws RemoteException, NullDataException;
	StringReturnResponse<String>  deleteCourse(String courseId) throws RemoteException;
	StringReturnResponse<String>  addReservation(String reservationInfo) throws RemoteException, NullDataException;
	StringReturnResponse<String>  deleteReservation(String reservationId) throws RemoteException;
	
	StringReturnResponse<String>  checkStudent(String userId) throws RemoteException;
	StringReturnResponse<String>  checkCourse(String courseId) throws RemoteException;
	
	StringReturnResponse<String>  login(String studentNum, String password) throws RemoteException;
	StringReturnResponse<String>  signUP(String studentNum, String password, String name, String major) throws RemoteException;
	
	void sendSeverStudentForLog(String logUser) throws RemoteException;
}

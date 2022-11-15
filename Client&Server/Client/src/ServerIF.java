import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ServerIF extends Remote{
	StringReturnResponse<ArrayList<Student>> getAllStudentData(String userToken) throws RemoteException, NullDataException;
	StringReturnResponse<ArrayList<Course>> getAllCourseList(String userToken) throws RemoteException, NullDataException;
	StringReturnResponse<ArrayList<Reservation>> getAllReservationList(String userToken) throws RemoteException;
	StringReturnResponse<String> addStudent(String studentInfo, String userToken) throws RemoteException, NullDataException;
	StringReturnResponse<String>  deleteStudent(String studentId, String userToken) throws RemoteException;
	StringReturnResponse<String>  addCourse(String courseInfo, String userToken) throws RemoteException, NullDataException;
	StringReturnResponse<String>  deleteCourse(String courseId, String userToken) throws RemoteException;
	StringReturnResponse<String>  addReservation(String reservationInfo, String userToken) throws RemoteException, NullDataException;
	StringReturnResponse<String>  deleteReservation(String reservationId, String userToken) throws RemoteException;
	
	StringReturnResponse<String>  checkStudent(String userId, String userToken) throws RemoteException;
	StringReturnResponse<String>  checkCourse(String courseId, String userToken) throws RemoteException;
	
	StringReturnResponse<String>  login(String studentNum, String password) throws RemoteException;
	StringReturnResponse<String>  signUP(String studentNum, String password, String name, String major) throws RemoteException;
	
	void sendSeverStudentForLog(String logUser) throws RemoteException;
}

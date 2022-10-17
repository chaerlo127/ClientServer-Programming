import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface DataIF extends Remote{
	ArrayList<Student> getAllStudentData() throws RemoteException, NullDataException;
	ArrayList<Course> getAllCourseList() throws RemoteException, NullDataException;
	boolean addStudent(String studentInfo) throws RemoteException, NullDataException;
	boolean deleteStudent(String studentId) throws RemoteException;
}

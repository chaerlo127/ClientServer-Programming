import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface DataIF extends Remote{
	// �������̽��� �������� RMI ȣ���� ������.
	ArrayList<Student> getAllStudentData() throws RemoteException;
	ArrayList<Course> getAllCourseList() throws RemoteException;
}

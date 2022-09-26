import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ServerIF extends Remote{
	// �������̽��� �������� RMI ȣ���� ������.
	int add(int a, int b) throws RemoteException;
	void save(String name) throws RemoteException;
	ArrayList<Student> getAllStudentData() throws RemoteException;
	ArrayList<Course> getAllCourseList() throws RemoteException;
}

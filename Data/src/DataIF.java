import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface DataIF extends Remote{
	// 인터페이스를 만들어야지 RMI 호출이 가능함.
	ArrayList<Student> getAllStudentData() throws RemoteException;
}

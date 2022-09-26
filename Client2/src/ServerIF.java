import java.rmi.Remote;
import java.rmi.RemoteException;

//stub
public interface ServerIF extends Remote{
	// 인터페이스를 만들어야지 RMI 호출이 가능함.
	int add(int a, int b) throws RemoteException;
	void save(String name) throws RemoteException;
	String read() throws RemoteException;
}


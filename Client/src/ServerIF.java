import java.rmi.Remote;
import java.rmi.RemoteException;

//stub
public interface ServerIF extends Remote{
	// �������̽��� �������� RMI ȣ���� ������.
	int add(int a, int b) throws RemoteException;
}


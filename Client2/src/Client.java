import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client {
	/*
	 * java [project name] -> run
	 * start rmiregistry -> registry 띄우는 명령어 
	 */

	public static void main(String[] args) {
		ServerIF server;
		try {
			server = (ServerIF) Naming.lookup("AddServer");
			if(server.read()!=null) {
				System.out.println(server.read());
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

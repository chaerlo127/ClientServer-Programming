import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client {
	/*
	 * java [project name] -> run
	 * start rmiregistry -> registry ���� ��ɾ� 
	 */

	public static void main(String[] args) {
		ServerIF server;
		BufferedReader objReader = new BufferedReader(new InputStreamReader(System.in));
		String sChoice = "";
		
		try {
			while(!sChoice.equals("3")) {
			server = (ServerIF) Naming.lookup("Server");
			System.out.println("****************************MENU****************************");
			System.out.println("1. List Students");
			System.out.println("2. List Courses");
			System.out.println("3. End");
			
			sChoice = objReader.readLine().trim();
			
			if(sChoice.equals("1")) {
				// ����Ʈ �̻ڰ� �����ֱ�
				System.out.println("Server's answer: ");
				System.out.println(server.getAllStudentData());
			}
			else if(sChoice.equals("2")) {
				// ���� ���� ����Ʈ �ҷ����� 
				System.out.println("Server's answer: ");
				System.out.println(server.getAllCourseList());
			}
		}
		} catch (MalformedURLException e) {
			System.out.println("MalformedURLException: rmiRegistry�� ã�� �� �����ϴ�.");
//					e.printStackTrace();
		} catch (RemoteException e) {
			System.out.println("RemoteException: ���������� ���ܰ� �߻��߽��ϴ�.");
//					e.printStackTrace();
		} catch (NotBoundException e) {
			System.out.println("NotBoundException: �̹� rmiRegistry�� ������ Data�� �����ϴ�.");
//				e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IOException: ������ �о�� �� �����ϴ�.");
//			e.printStackTrace();
		}
	}

}

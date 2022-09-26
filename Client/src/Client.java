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
	 * start rmiregistry -> registry 띄우는 명령어 
	 */

	public static void main(String[] args) {
		ServerIF server;
		BufferedReader objReader = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			server = (ServerIF) Naming.lookup("Server");
			System.out.println("****************************MENU****************************");
			System.out.println("1. List Students");
			System.out.println("2. List Courses");
			
			String sChoice = objReader.readLine().trim();
			
			if(sChoice.equals("1")) {
				// 리스트 이쁘게 보여주기
				System.out.println("Server's answer: ");
				System.out.println(server.getAllStudentData());
			}
			else if(sChoice.equals("2")) {
				// 수강 과목 리스트 불러오기 
				System.out.println("Server's answer: ");
				System.out.println(server.getAllCourseList());
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
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

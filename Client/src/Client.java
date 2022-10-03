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
				// 리스트 이쁘게 보여주기
				System.out.println("Server's answer: ");
				System.out.println(server.getAllStudentData());
			}
			else if(sChoice.equals("2")) {
				// 수강 과목 리스트 불러오기 
				System.out.println("Server's answer: ");
				System.out.println(server.getAllCourseList());
			}
		}
		} catch (MalformedURLException e) {
			System.out.println("MalformedURLException: rmiRegistry를 찾을 수 없습니다.");
//					e.printStackTrace();
		} catch (RemoteException e) {
			System.out.println("RemoteException: 원격지에서 예외가 발생했습니다.");
//					e.printStackTrace();
		} catch (NotBoundException e) {
			System.out.println("NotBoundException: 이미 rmiRegistry에 연결할 Data가 없습니다.");
//				e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IOException: 파일을 읽어올 수 없습니다.");
//			e.printStackTrace();
		}
	}

}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class Client {
	public static void main(String[] args) {
		ServerIF server;
		BufferedReader objReader = new BufferedReader(new InputStreamReader(System.in));
		String userConsoleInput = "";
		try {
			while (!userConsoleInput.equals("0")) {
				server = (ServerIF) Naming.lookup("Server");
				showMenu();
				userConsoleInput = objReader.readLine().trim();
				switch(userConsoleInput) {
					case "1": showData(server.getAllStudentData()); break;
					case "2": showData(server.getAllCourseList()); break;
					case "3": addStudent(server, objReader); break;
					case "4": /* add course */ 	break;
					case "5": deleteStudent(server, objReader);	break;
					case "6": /* delete course */ break;
					case "0": return;
					default: System.out.println("***********잘못입력했습니다. 다시 입력해주세요.***********"); break;
				}
			}
		} 
		catch (MalformedURLException e) { System.out.println("MalformedURLException: rmiRegistry를 찾을 수 없습니다.");}
		catch (RemoteException e) {System.out.println("RemoteException: 원격지에서 예외가 발생했습니다.");}
		catch (NotBoundException e) {System.out.println("NotBoundException: 이미 rmiRegistry에 연결할 Data가 없습니다.");}
		catch (IOException e) {	System.out.println("IOException: 파일을 읽어올 수 없습니다.");}
		catch(NullDataException e) {System.out.println("NullDataException: 데이터에 값이 없습니다."); e.printStackTrace();}
	}

	//throws RemoteException
	private static void showData(ArrayList<?> dataList)  {
		String list = "";
		for(int i = 0; i<dataList.size(); i++) {
			list += dataList.get(i) + "\n";
		}
		System.out.println(list);
	}
	
	private static void addStudent(ServerIF server, BufferedReader objReader) throws RemoteException, IOException, NullDataException{
		System.out.println("----------- Student Information -----------"); 
		System.out.print("Student ID: "); String studentId = objReader.readLine().trim();
		System.out.print("Student Name: "); String studentName = objReader.readLine().trim();
		System.out.print("Student Department: "); String studentDept = objReader.readLine().trim();
		System.out.print("Student Completed Course: "); String completedCourse = objReader.readLine().trim();
		
		if(server.addStudent(studentId + " " + studentName + " " + studentDept + " " + completedCourse)) System.out.println("SUCCESS");
		else System.out.println("FAIL");
	}
	
	private static void deleteStudent(ServerIF server, BufferedReader objReader) throws RemoteException, IOException {
		System.out.print("Student ID: "); 
		if(server.deleteStudent(objReader.readLine().trim())) System.out.println("SUCCESS");
		else System.out.println("FAIL");
	}

	private static void showMenu() {
		System.out.println("****************************MENU****************************");
		System.out.println("1. List Students");
		System.out.println("2. List Courses");
		System.out.println("3. Add Student");
		System.out.println("4. Add Course");
		System.out.println("5. Delete Student");
		System.out.println("6. Delete Course");
		System.out.println("0. End");
	}

}

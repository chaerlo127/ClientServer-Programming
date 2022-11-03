import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {
	private final static Logger LOG = Logger.getGlobal();
	private static String logUser;
	public static void main(String[] args) {
		ServerIF server;
		BufferedReader objReader = new BufferedReader(new InputStreamReader(System.in));
		String userConsoleInput = "";
		try {
			logConfigurationMethod();
			String checkLogin = null;
			while (!userConsoleInput.equals("0")) {
				server = (ServerIF) Naming.lookup("Server");
				
				showInitialView();
				userConsoleInput = objReader.readLine().trim();
				checkLogin = userChecked(server, objReader, userConsoleInput);
				if (checkLogin != null) {
					logUser = checkLogin;
					server.sendSeverStudentForLog(checkLogin);
					while (!userConsoleInput.equals("99")) {userConsoleInput = registerCourseMenu(server, objReader);}
				} else System.out.println("***********잘못입력했습니다. 다시 입력해주세요.***********"); 
				checkLogin = null;
			}
		} 
		catch (MalformedURLException e) { System.out.println("MalformedURLException: rmiRegistry를 찾을 수 없습니다.");}
		catch (RemoteException e) {System.out.println("RemoteException: 원격지에서 예외가 발생했습니다."); e.printStackTrace();}
		catch (NotBoundException e) {System.out.println("NotBoundException: 이미 rmiRegistry에 연결할 Data가 없습니다.");}
		catch (IOException e) {	System.out.println("IOException: 파일을 읽어올 수 없습니다.");}
		catch(NullDataException e) {System.out.println("NullDataException: 데이터에 값이 없습니다."); e.printStackTrace();}
	}


	private static void logConfigurationMethod() throws IOException {
		Logger logger = Logger.getLogger("");
		Handler[] handlers = logger.getHandlers();
		if (handlers[0] instanceof ConsoleHandler) logger.removeHandler(handlers[0]);
		
		LOG.setLevel(Level.INFO);
		Handler fileHandler = new FileHandler("client.log", true);
		LogFormat formatter = new LogFormat();
		fileHandler.setFormatter(formatter);
		LOG.addHandler(fileHandler);
	}

	private static String userChecked(ServerIF server, BufferedReader objReader, String userConsoleInput) throws IOException {
		String checkLogin = null;
		switch(userConsoleInput) {
		case "1": checkLogin = login(server, objReader); break;
		case "2": checkLogin = signUP(server, objReader); break;
		case "0": System.exit(0);
		default: System.out.println("***********잘못입력했습니다***********"); break;
		}
		return checkLogin;
	}
	
	private static String registerCourseMenu(ServerIF server, BufferedReader objReader)
			throws IOException, RemoteException, NullDataException {
		String userConsoleInput;
		showMenu();
		userConsoleInput = objReader.readLine().trim();
		switch(userConsoleInput) {
		case "1": showData(server.getAllStudentData().getResult()); break;
		case "2": showData(server.getAllCourseList().getResult()); break;
		case "3": addStudent(server, objReader); break;
		case "4": addCourse(server, objReader);	break;
		case "5": deleteStudent(server, objReader);	break;
		case "6": deleteCourse(server, objReader); break;
		case "7": makeReservation(server, objReader); break;
		case "8": showData(server.getAllReservationList().getResult()); break;
		case "99": break;
		default: System.out.println("*********** 잘못입력했습니다. 다시 입력해주세요.***********"); break;
		}
		return userConsoleInput;
	}

	private static String login(ServerIF server, BufferedReader objReader) throws IOException, RemoteException {
		System.out.println("----------- Login Information -----------"); 
		System.out.print("학번:"); String studentNum = objReader.readLine().trim();
		System.out.print("비밀번호:"); String password = objReader.readLine().trim();
		String answer = server.login(studentNum, password).getMessage();
		System.out.println(answer);
		
		LOG.info(studentNum);
		return answer.contains("성공") ? studentNum : null;
	}

	private static String signUP(ServerIF server, BufferedReader objReader) throws IOException {
		System.out.println("----------- signUP Information -----------"); 
		System.out.print("학번:"); String studentNum = objReader.readLine().trim();
		System.out.print("비밀번호:"); String password = objReader.readLine().trim();
		System.out.print("이름:"); String name = objReader.readLine().trim();
		System.out.print("전공:"); String major = objReader.readLine().trim();
		String answer = server.signUP(studentNum, password, name, major).getMessage();
		System.out.println(answer);
		
		LOG.info(studentNum);
		return answer.contains("성공") ? studentNum: null;
	}

	private static void makeReservation(ServerIF server, BufferedReader objReader) throws IOException, RemoteException, NullDataException {
		/* Make reservation(수강 신청: Student ID Course ID 넣고 저장): student 체크, courseId 체크, 선수 과목 체크 home work */
		System.out.println("----------- Reservation Information -----------"); 
		System.out.print("Student ID: "); String studentId = objReader.readLine().trim();
		System.out.print("Course ID: "); String courseId = objReader.readLine().trim();
		
		String answer = server.addReservation(studentId + " " + courseId).getMessage();
		if(answer == "성공") System.out.println("SUCCESS");
		else System.out.println(answer);
		LOG.info(logUser);
	}

	private static void deleteCourse(ServerIF server, BufferedReader objReader) throws RemoteException, IOException {
		System.out.print("Course ID: "); 
		System.out.println(server.deleteCourse(objReader.readLine().trim()));
		LOG.info(logUser);
	}

	// throws RemoteException
	private static void showData(ArrayList<?> dataList) {
		String list = "";
		for (int i = 0; i < dataList.size(); i++) {
			list += dataList.get(i) + "\n";
		}
		System.out.println(list);
		LOG.info(logUser);
	}
	
	private static void addStudent(ServerIF server, BufferedReader objReader) throws RemoteException, IOException, NullDataException{
		System.out.println("----------- Student Information -----------"); 
		System.out.print("Student ID: "); String studentId = objReader.readLine().trim();
		System.out.print("Student password: "); String password = objReader.readLine().trim();
		System.out.print("Student Name: "); String studentName = objReader.readLine().trim();
		System.out.print("Student Department: "); String studentDept = objReader.readLine().trim();
		System.out.print("Student Completed Course: "); String completedCourse = objReader.readLine().trim();
		
		System.out.println(server.addStudent(studentId + " " + password+ " " + studentName + " " + studentDept + " " + completedCourse));
		LOG.info(logUser);
	}
	
	private static void deleteStudent(ServerIF server, BufferedReader objReader) throws RemoteException, IOException {
		System.out.print("Student ID: "); 
		System.out.println(server.deleteStudent(objReader.readLine().trim()));
		LOG.info(logUser);
	}
	
	private static void addCourse(ServerIF server, BufferedReader objReader) throws IOException, RemoteException, NullDataException {
		/* add course: home work */ 
		System.out.println("----------- Course Information -----------"); 
		System.out.print("Course ID: "); String courseId = objReader.readLine().trim();
		System.out.print("Professor Name: "); String name = objReader.readLine().trim();
		System.out.print("Course Name: "); String courseName = objReader.readLine().trim();
		System.out.print("PreCourse Name List: "); String precourseNameList = objReader.readLine().trim();

		System.out.println(server.addCourse(courseId + " " + name + " " + courseName + " " + precourseNameList));
		LOG.info(logUser);
	}

	private static void showMenu() {
		System.out.println("****************************MENU****************************");
		System.out.println("1. List Students");
		System.out.println("2. List Courses");
		System.out.println("3. Add Student");
		System.out.println("4. Add Course");
		System.out.println("5. Delete Student");
		System.out.println("6. Delete Course");
		System.out.println("7. Make Reservation");
		System.out.println("8. List Reservation");
		System.out.println("99. End");
		LOG.info(logUser);
	}

	private static void showInitialView() {
		System.out.println("*********** 수강신청 ***********");
		System.out.println("1. Login");
		System.out.println("2. Sign up");
		System.out.println("0. END");
		
		LOG.info("사용자 정보 없음");
	}

}

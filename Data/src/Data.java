import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Data extends UnicastRemoteObject implements DataIF{
	protected static StudentList studentList;
	protected static CourseList courseList;
	protected static ReservationList reservationList;
	private static final long serialVersionUID = 1L;
	private static String path = "C:\\Users\\chaeun\\Desktop\\JAVA\\workspace\\ClientServerProgramming\\Data";
	private final static Logger LOG = Logger.getGlobal();
	String logUser;
	protected Data() throws RemoteException {
		super();
	}
	
	// 프로세스 
	public static void main(String[] args)  {
		try {
			Data data = new Data();
			Naming.bind("Data", data);
			System.out.println("Data is ready !!!");
			logConfigurationMethod();
			studentList = new StudentList(path + "\\data\\Students.txt", LOG);
			courseList = new CourseList(path + "\\data\\Courses.txt", LOG);
			reservationList = new ReservationList();
		} 
		catch (MalformedURLException e) {System.out.println("MalformedURLException: rmiRegistry를 찾을 수 없습니다.");} 
		catch (RemoteException e) {System.out.println("RemoteException: 원격지에서 예외가 발생했습니다.");} 
		catch (AlreadyBoundException e) {System.out.println("AlreadyBoundException: 이미 rmiRegistry에 연결이 되었습니다.");}
		catch (FileNotFoundException e) {System.out.println("FileNotFoundException: 파일의 값이 없습니다.");} 
		catch (IOException e) {System.out.println("IOException: 파일을 읽어올 수 없습니다.");}
	}
	
	private static void logConfigurationMethod() throws IOException {
		Logger logger = Logger.getLogger("");
		Handler[] handlers = logger.getHandlers();
		if (handlers[0] instanceof ConsoleHandler) logger.removeHandler(handlers[0]);
		
		LOG.setLevel(Level.INFO);
		Handler fileHandler = new FileHandler("data.log", true);
		LogFormat formatter = new LogFormat();
		fileHandler.setFormatter(formatter);
		LOG.addHandler(fileHandler);
	}
	
	@Override
	public ArrayList<Student> getAllStudentData() throws RemoteException, NullDataException{
		LOG.info(logUser);
		return studentList.getAllStudentRecords(this.logUser);
	}
	
	@Override
	public boolean addStudent(String studentInfo) throws RemoteException, NullDataException {
		LOG.info(logUser);
		if(studentList.addStudentRecords(studentInfo, this.logUser)) return true;
		else return false;
	}

	@Override
	public boolean deleteStudent(String studentId) throws RemoteException {
		LOG.info(logUser);
		if(studentList.deleteStudentRecords(studentId, this.logUser)) return true;
		else return false;
	}
	
	@Override
	public ArrayList<Course> getAllCourseList() throws RemoteException, NullDataException{
		LOG.info(logUser);
		return courseList.getAllCourseRecords(this.logUser);
	}

	@Override
	public boolean addCourse(String courseInfo) throws RemoteException, NullDataException {
		LOG.info(logUser);
		if(courseList.addCourseRecords(courseInfo, this.logUser)) return true;
		else return false;
	}

	@Override
	public boolean deleteCourse(String courseId) throws RemoteException {
		LOG.info(logUser);
		if(courseList.deleteCourseRecords(courseId, this.logUser)) return true;
		else return false;
	}

	@Override
	public boolean addReservation(String reservationInfo) throws RemoteException, NullDataException {
		LOG.info(logUser);
		if(reservationList.addReservationRecords(reservationInfo)) return true;
		else return false;
	}

	@Override
	public boolean deleteReservation(String reservationId) throws RemoteException {
		LOG.info(logUser);
		if(reservationList.deleteReservationRecords(reservationId)) return true;
		else return false;
	}

	@Override
	public ArrayList<Reservation> getAllReservationList() throws RemoteException, NullDataException {
		LOG.info(logUser);
		return reservationList.getAllReservation();
	}

	@Override
	public Student checkStudent(String userId) throws RemoteException {
		LOG.info(logUser);
		return studentList.isRegisteredStudent(userId, this.logUser);
	}

	@Override
	public Course checkCourse(String courseId) throws RemoteException {
		LOG.info(logUser);
		return courseList.checkCourseWithSID(courseId, this.logUser);
	}

	@Override
	public Student checkLogin(String userId, String password) throws RemoteException {
		LOG.info(logUser);
		return studentList.checkStudent(userId, password, this.logUser);
	}

	@Override
	public boolean signUP(String studentNum, String password, String name, String major) throws RemoteException, NullDataException {
		LOG.info(logUser);
		return studentList.addStudentRecords(studentNum + " " + password + " " + name + " " + major, this.logUser);
	}

	@Override
	public boolean checkReservation(Reservation reservation) throws RemoteException{
		LOG.info(logUser);
		return reservationList.isRegisteredStudent(reservation.studentId, reservation.courseId);
	}

	@Override
	public void sendServerStudentForLog(String logUser) throws RemoteException{
		this.logUser = logUser;
		LOG.info(logUser);
	}
}

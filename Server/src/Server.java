import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server extends UnicastRemoteObject implements ServerIF{
	private static final long serialVersionUID = 1L;
	private final static Logger LOG = Logger.getGlobal();
	private static DataIF data;
	String name;
	private static String logUser;
	
	protected Server() throws RemoteException {
		super();
	}
	
	public static void main(String[] args) {
		try {
			Server server = new Server();
			Naming.bind("Server", server);
			System.out.println("Server is ready !!!");
			logConfigurationMethod();
			data = (DataIF) Naming.lookup("Data");
		} catch (MalformedURLException e) {System.out.println("MalformedURLException: rmiRegistry를 찾을 수 없습니다.");} 
		catch (RemoteException e) {System.out.println("RemoteException: 원격지에서 예외가 발생했습니다.");} 
		catch (AlreadyBoundException e) {System.out.println("AlreadyBoundException: 이미 rmiRegistry에 연결이 되었습니다.");} 
		catch (NotBoundException e) {System.out.println("NotBoundException: 이미 rmiRegistry에 연결할 Data가 없습니다.");} 
		catch (IOException e) {	System.out.println("IOException: 파일을 읽어올 수 없습니다.");}
	}
	
	private static void logConfigurationMethod() throws IOException {
		Logger logger = Logger.getLogger("");
		Handler[] handlers = logger.getHandlers();
		if (handlers[0] instanceof ConsoleHandler) {logger.removeHandler(handlers[0]);}
		
		LOG.setLevel(Level.INFO);
		Handler fileHandler = new FileHandler("server.log", true);
		LogFormat formatter = new LogFormat();
		fileHandler.setFormatter(formatter);
		LOG.addHandler(fileHandler);
	}
	
	@Override
	public ArrayList<Student> getAllStudentData() throws RemoteException, NullDataException{
		LOG.info(logUser);
		return data.getAllStudentData();
	}
	
	@Override
	public String addStudent(String studentInfo) throws RemoteException, NullDataException {
		LOG.info(logUser);
		if(data.addStudent(studentInfo)) return new StringReturn(StringReturnException.SUCCESS).getErrorMessage();
		// 학번이 같으면 에러
		else return new StringReturn(StringReturnException.NOT_ADD_STUDENT).getErrorMessage();
	}
	
	@Override
	public String deleteStudent(String studentId) throws RemoteException { // exception이 있다면 여기서 해야함. 
		LOG.info(logUser);
		if(data.deleteStudent(studentId)) return new StringReturn(StringReturnException.SUCCESS).getErrorMessage();
		else return new StringReturn(StringReturnException.NOT_DELETED_STUDENT).getErrorMessage();
	}
	
	@Override
	public ArrayList<Course> getAllCourseList() throws RemoteException, NullDataException{
		LOG.info(logUser);
		return data.getAllCourseList();
	}
	
	@Override
	public String addCourse(String courseInfo) throws RemoteException, NullDataException {
		LOG.info(logUser);
		if(data.checkCourse(courseInfo) != null) return new StringReturn(StringReturnException.HAVE_COURSE).getErrorMessage();
		if(data.addCourse(courseInfo)) return new StringReturn(StringReturnException.SUCCESS).getErrorMessage();
		else return new StringReturn(StringReturnException.NOT_ADD_COURSE).getErrorMessage();
	}
	
	@Override
	public String deleteCourse(String courseId) throws RemoteException {
		LOG.info(logUser);
		if(data.deleteCourse(courseId)) return new StringReturn(StringReturnException.SUCCESS).getErrorMessage();
		else return new StringReturn(StringReturnException.NOT_ADD_COURSE).getErrorMessage();
	}
	
	@Override
	public String addReservation(String reservationInfo) throws RemoteException, NullDataException {
		LOG.info(logUser);
		System.out.println(reservationInfo);
		if(reservationInfo.equals(" ")) return new StringReturn(StringReturnException.HAVE_NOT_VALUE).getErrorMessage();
		
		Reservation reservation = new Reservation(reservationInfo, LOG, logUser);
		Student studentInfo = data.checkStudent(reservation.studentId);
		Course course = data.checkCourse(reservation.courseId);
		
		if(studentInfo == null) return new StringReturn(StringReturnException.HAVE_NOT_STUDENT).getErrorMessage();
		if(course == null) return new StringReturn(StringReturnException.HAVE_NOT_COURSE).getErrorMessage();
		
		for(int i = 0; i<studentInfo.vStudent.size(); i++) {if(course.courseId.equals(studentInfo.vStudent.get(i))) return new StringReturn(StringReturnException.ALREADY_REGISTERED_COURSE).getErrorMessage();}
		
		if(checkPrecourse(studentInfo, course) != course.getPrecourseList().size()) return new StringReturn(StringReturnException.NOT_REGISTERED_PRECOURSE).getErrorMessage();;
	
		// 이미 수강신청을 한 경우
		if(data.checkReservation(reservation)) return new StringReturn(StringReturnException.ALREADY_REGISTERED_COURSE).getErrorMessage();
		if(data.addReservation(reservationInfo)) return new StringReturn(StringReturnException.SUCCESS).getErrorMessage();
		else return new StringReturn(StringReturnException.FAIL).getErrorMessage();
	}

	private int checkPrecourse(Student studentInfo, Course course) {
		LOG.info(logUser);
		int checkPrecourse = 0;
		for(int i = 0 ; i<course.getPrecourseList().size() ; i++) {
			for(int j = 0; j<studentInfo.getCompletedCourses().size(); j++) {
				if(studentInfo.getCompletedCourses().get(j).equals(course.getPrecourseList().get(i))) checkPrecourse++;
			}
		}
		return checkPrecourse;
	}
	
	@Override
	public String deleteReservation(String reservationId) throws RemoteException {
		LOG.info(logUser);
		if(data.deleteReservation(reservationId)) return new StringReturn(StringReturnException.SUCCESS).getErrorMessage();
		else return new StringReturn(StringReturnException.NOT_DELETED_RESERVATION).getErrorMessage();
	}
	
	@Override
	public ArrayList<Reservation> getAllReservationList() throws RemoteException {
		LOG.info(logUser);
		return data.getAllReservationList();
	}
	
	@Override
	public String checkStudent(String userId) throws RemoteException {
		LOG.info(logUser);
		if(data.checkStudent(userId) == null) return new StringReturn(StringReturnException.SUCCESS).getErrorMessage();
		else return new StringReturn(StringReturnException.HAVE_NOT_STUDENT).getErrorMessage();
	}
	
	@Override
	public String checkCourse(String courseId) throws RemoteException {
		LOG.info(logUser);
		if(data.checkCourse(courseId) == null) return new StringReturn(StringReturnException.SUCCESS).getErrorMessage();
		else return new StringReturn(StringReturnException.HAVE_NOT_COURSE).getErrorMessage();
	}
	
	@Override
	public String login(String studentNum, String password) throws RemoteException {
		LOG.info(logUser);
		if(studentNum.equals(null) || password.equals(null)) return new StringReturn(StringReturnException.HAVE_NOT_VALUE).getErrorMessage();
		
		if(data.checkLogin(studentNum, password) != null) return new StringReturn(StringReturnException.SUCCESS).getErrorMessage();
		else return new StringReturn(StringReturnException.FAIL).getErrorMessage();
	}
	
	@Override
	public String signUP(String studentNum, String password, String name, String major) throws RemoteException {
		LOG.info(logUser);
		if(studentNum.equals(null) || password.equals(null) || name.equals(null) || major.equals(null)) return new StringReturn(StringReturnException.HAVE_NOT_VALUE).getErrorMessage();
		if(data.checkStudent(studentNum) != null) return new StringReturn(StringReturnException.HAVE_STUDENT).getErrorMessage();
		
		if(data.signUP(studentNum, password, name, major)) return new StringReturn(StringReturnException.SUCCESS).getErrorMessage();
		return new StringReturn(StringReturnException.FAIL).getErrorMessage();
	}

	@Override
	public void sendSeverStudentForLog(String logUser2) throws RemoteException {
		logUser = logUser2;
		LOG.info(logUser);
	}
}

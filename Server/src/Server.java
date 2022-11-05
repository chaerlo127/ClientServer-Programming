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
	public static String USER_INFO_PASSWORD_KEY = "o9pqYVC9-F8_.PEzEiw!L9F6.AYj9jcfVJ*_i.ifXYnyE68kix@Q2dL6rw*bV-rpdZYwcqZG-jPF-fw3CiJyKsfZ778ks-*jnZn";
	private static DataIF data;
	String logUser;
	
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
		if (handlers[0] instanceof ConsoleHandler) logger.removeHandler(handlers[0]);
		
		LOG.setLevel(Level.INFO);
		Handler fileHandler = new FileHandler("server.log", true);
		LogFormat formatter = new LogFormat();
		fileHandler.setFormatter(formatter);
		LOG.addHandler(fileHandler);
	}
	
	@Override
	public StringReturnResponse<ArrayList<Student>> getAllStudentData(String userToken) throws RemoteException, NullDataException{
		LOG.info(logUser);
//		if(!checkAccessUser(userToken)) return new StringReturnResponse<ArrayList<Student>>(StringReturnException.CAN_NOT_MATCH_USER_AND_TOKEN);
		return new StringReturnResponse<ArrayList<Student>>(data.getAllStudentData());
	}
	
	@Override
	public StringReturnResponse<String> addStudent(String studentInfo, String userToken) throws RemoteException, NullDataException {
		LOG.info(logUser);
		if(!checkAccessUser(userToken)) return new StringReturnResponse<String>(StringReturnException.CAN_NOT_MATCH_USER_AND_TOKEN);
		Student student = new Student(studentInfo);
		if(data.checkStudent(student.studentId) != null) return new StringReturnResponse<String>(StringReturnException.HAVE_STUDENT);
		if(data.addStudent(studentInfo)) return new StringReturnResponse<String>("성공");
		else return new StringReturnResponse<String>(StringReturnException.NOT_ADD_STUDENT);
	}
	
	@Override
	public StringReturnResponse<String> deleteStudent(String studentId, String userToken) throws RemoteException {
		LOG.info(logUser);
		if(!checkAccessUser(userToken)) return new StringReturnResponse<String>(StringReturnException.CAN_NOT_MATCH_USER_AND_TOKEN);
		if(data.deleteStudent(studentId)) return new StringReturnResponse<String>("성공");
		else return new StringReturnResponse<String>(StringReturnException.NOT_DELETED_STUDENT);
	}
	
	@Override
	public StringReturnResponse<ArrayList<Course>>  getAllCourseList(String userToken) throws RemoteException, NullDataException{
		LOG.info(logUser);
//		if(!checkAccessUser(userToken)) return new StringReturnResponse<ArrayList<Course>>(StringReturnException.CAN_NOT_MATCH_USER_AND_TOKEN);
		return new StringReturnResponse<ArrayList<Course>>(data.getAllCourseList());
	}
	
	@Override
	public StringReturnResponse<String> addCourse(String courseInfo, String userToken) throws RemoteException, NullDataException {
		LOG.info(logUser);
		if(!checkAccessUser(userToken)) return new StringReturnResponse<String>(StringReturnException.CAN_NOT_MATCH_USER_AND_TOKEN);
		if(data.checkCourse(courseInfo) != null) return new StringReturnResponse<String>(StringReturnException.HAVE_COURSE);
		if(data.addCourse(courseInfo)) return new StringReturnResponse<String>("성공");
		else return new StringReturnResponse<String>(StringReturnException.NOT_ADD_COURSE);
	}
	
	@Override
	public StringReturnResponse<String> deleteCourse(String courseId, String userToken) throws RemoteException {
		LOG.info(logUser);
		if(!checkAccessUser(userToken)) return new StringReturnResponse<String>(StringReturnException.CAN_NOT_MATCH_USER_AND_TOKEN);
		if(data.deleteCourse(courseId)) return new StringReturnResponse<String>("성공");
		else return new StringReturnResponse<String>(StringReturnException.NOT_ADD_COURSE);
	}
	
	@Override
	public StringReturnResponse<String> addReservation(String reservationInfo, String userToken) throws RemoteException, NullDataException {
		LOG.info(logUser);
		if(!checkAccessUser(userToken)) return new StringReturnResponse<String>(StringReturnException.CAN_NOT_MATCH_USER_AND_TOKEN);
		if(reservationInfo.equals(" ")) return new StringReturnResponse<String>(StringReturnException.HAVE_NOT_VALUE);
		
		Reservation reservation = new Reservation(reservationInfo);
		Student studentInfo = data.checkStudent(reservation.studentId);
		Course course = data.checkCourse(reservation.courseId);
		
		if(studentInfo == null) return new StringReturnResponse<String>(StringReturnException.HAVE_NOT_STUDENT);
		if(course == null) return new StringReturnResponse<String>(StringReturnException.HAVE_NOT_COURSE);
		
		for(int i = 0; i<studentInfo.vStudent.size(); i++) {if(course.courseId.equals(studentInfo.vStudent.get(i))) return new StringReturnResponse<String>(StringReturnException.ALREADY_REGISTERED_COURSE);}
		
		if(checkPrecourse(studentInfo, course) != course.getPrecourseList().size()) return new StringReturnResponse<String>(StringReturnException.NOT_REGISTERED_PRECOURSE);
	
		// 이미 수강신청을 한 경우
		if(data.checkReservation(reservation)) return new StringReturnResponse<String>(StringReturnException.ALREADY_REGISTERED_COURSE);
		if(data.addReservation(reservationInfo)) return new StringReturnResponse<String>("성공");
		else return new StringReturnResponse<String>(StringReturnException.FAIL);
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
	public StringReturnResponse<String> deleteReservation(String reservationId, String userToken) throws RemoteException {
		LOG.info(logUser);
		if(!checkAccessUser(userToken)) return new StringReturnResponse<String>(StringReturnException.CAN_NOT_MATCH_USER_AND_TOKEN);
		if(data.deleteReservation(reservationId)) return new StringReturnResponse<String>("성공");
		else return new StringReturnResponse<String>(StringReturnException.NOT_DELETED_RESERVATION);
	}
	
	@Override
	public StringReturnResponse<ArrayList<Reservation>> getAllReservationList(String userToken) throws RemoteException {
		LOG.info(logUser);
//		if(!checkAccessUser(userToken)) return new StringReturnResponse<ArrayList<Reservation>>(StringReturnException.CAN_NOT_MATCH_USER_AND_TOKEN);
		return new StringReturnResponse<ArrayList<Reservation>>(data.getAllReservationList());
	}
	
	@Override
	public StringReturnResponse<String> checkStudent(String userId, String userToken) throws RemoteException {
		LOG.info(logUser);
		if(!checkAccessUser(userToken)) return new StringReturnResponse<String>(StringReturnException.CAN_NOT_MATCH_USER_AND_TOKEN);
		if(data.checkStudent(userId) == null) return new StringReturnResponse<String>("성공");
		else return new StringReturnResponse<String>(StringReturnException.HAVE_NOT_STUDENT);
	}
	
	@Override
	public StringReturnResponse<String> checkCourse(String courseId, String userToken) throws RemoteException {
		LOG.info(logUser);
		if(!checkAccessUser(userToken)) return new StringReturnResponse<String>(StringReturnException.CAN_NOT_MATCH_USER_AND_TOKEN);
		if(data.checkCourse(courseId) == null) return new StringReturnResponse<String>("성공");
		else return new StringReturnResponse<String>(StringReturnException.HAVE_NOT_COURSE);
	}
	
	@Override
	public StringReturnResponse<String> login(String studentNum, String password) throws RemoteException {
		LOG.info(logUser);
		if(studentNum.equals(null) || password.equals(null)) return new StringReturnResponse<String>(StringReturnException.HAVE_NOT_VALUE);
		if(data.checkLogin(studentNum, password) != null) {
			String token = createToken(studentNum);
			return token != null ? new StringReturnResponse<String>(token) :new StringReturnResponse<String>(StringReturnException.FAIL);
		}
		else return new StringReturnResponse<String>(StringReturnException.FAIL);
	}
	
	@Override
	public StringReturnResponse<String> signUP(String studentNum, String password, String name, String major) throws RemoteException {
		LOG.info(logUser);
		if(studentNum.equals(null) || password.equals(null) || name.equals(null) || major.equals(null)) return new StringReturnResponse<String>(StringReturnException.HAVE_NOT_VALUE);
		
		if(data.checkStudent(studentNum) != null) return new StringReturnResponse<String>(StringReturnException.HAVE_STUDENT);
		if(data.signUP(studentNum, password, name, major)) {
			String token = createToken(studentNum);
			return token != null ? new StringReturnResponse<String>(token) :new StringReturnResponse<String>(StringReturnException.FAIL);
		}
		return new StringReturnResponse<String>(StringReturnException.FAIL);
	}
	
	@Override
	public void sendSeverStudentForLog(String logUser) throws RemoteException {
		this.logUser = logUser;
		data.sendServerStudentForLog(logUser);
		LOG.info(logUser);
	}
	
	private String createToken(String studentNum) {
		try {
			return new AES128(USER_INFO_PASSWORD_KEY).encrypt(studentNum);
		} catch (Exception ignored) { // 암호화가 실패하였을 경우 에러 발생
		    System.out.println("error");        
		} return null;
	}
	
	private boolean checkAccessUser(String token) {
		try {
			return token.equals(new AES128(USER_INFO_PASSWORD_KEY).encrypt(logUser)) ? true : false;
		} catch (Exception ignored) { // 암호화가 실패하였을 경우 에러 발생
		    System.out.println("error");        
		} return false;
	}

}

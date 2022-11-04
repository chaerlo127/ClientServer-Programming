
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

public class ReservationList {
	protected ArrayList<Reservation> vReservList;
	private static Logger LOG;
	private String sReservationFileName;
	public ReservationList(String sReservationFileName, Logger log) throws IOException{
		BufferedReader objStudentFile = new BufferedReader(new FileReader(sReservationFileName));
		LOG = log;
		this.sReservationFileName = sReservationFileName;
		this.vReservList = new ArrayList<Reservation>();
		while (objStudentFile.ready()) {
			String stuInfo = objStudentFile.readLine();
			if (!stuInfo.equals("")) {
				this.vReservList.add(new Reservation(stuInfo));
			}
		}
		objStudentFile.close();
	}

	public ArrayList<Reservation> getAllReservation(String logUser) throws NullDataException{
		LOG.info(logUser);
		return this.vReservList;
	}
	
	public boolean addReservationRecords(String studReserInfo, String logUser) throws NullDataException {
		LOG.info(logUser);
		if(studReserInfo == null) throw new NullDataException("-------------- studentInfo data is null --------------");
		if(this.vReservList.add(new Reservation(studReserInfo))) {
			addReservationFile(studReserInfo, logUser);
			return true;
		}
		else return false;
	}
	
	private void addReservationFile(String studReserInfo, String logUser) {
		try {
			LOG.info(logUser);
			BufferedWriter objFileWrite = new BufferedWriter(new FileWriter(sReservationFileName, true));
			objFileWrite.newLine();
			objFileWrite.newLine();
			objFileWrite.write(studReserInfo);
			objFileWrite.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean deleteReservationRecords(String studentId, String logUser) {
		LOG.info(logUser);
		for (int i = 0; i < this.vReservList.size(); i++) {
			Reservation reservation = (Reservation) this.vReservList.get(i);
			if (reservation.match(studentId)) {
				if(this.vReservList.remove(reservation)) {
					deleteStudentFile();
					return true;
				}
				else return false;
			}
		}
		return false;
	}
	
	private void deleteStudentFile() {
		try {
			BufferedWriter objFileWrite = new BufferedWriter(new FileWriter(sReservationFileName));
			for(Reservation reservation: this.vReservList) {
				objFileWrite.newLine();
				objFileWrite.newLine();
				objFileWrite.write(reservation.toString());
			}
			objFileWrite.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isRegisteredStudent(String studentId, String courseId, String logUser) {
		LOG.info(logUser);
		for (int i = 0; i < this.vReservList.size(); i++) {
			Reservation reservation = (Reservation) this.vReservList.get(i);
			if (reservation.match(studentId) && reservation.matchCourse(courseId)) {
				return true;
			}
		}
		return false;
	}
}
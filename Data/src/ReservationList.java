
import java.util.ArrayList;

public class ReservationList {
	protected ArrayList<Reservation> vReservList;
	
	public ReservationList(){
		this.vReservList = new ArrayList<Reservation>();
	}

	public ArrayList<Reservation> getAllReservation() throws NullDataException{
		return this.vReservList;
	}
	
	public boolean addReservationRecords(String studReserInfo) throws NullDataException { // student로 해도 됨. 어려움. 예외 사항 발생
		if(studReserInfo == null) throw new NullDataException("-------------- studentInfo data is null --------------");
		if(this.vReservList.add(new Reservation(studReserInfo))) return true;
		else return false;
	}
	
	public boolean deleteReservationRecords(String studentId) {
		for (int i = 0; i < this.vReservList.size(); i++) {
			Reservation reservation = (Reservation) this.vReservList.get(i);
			if (reservation.match(studentId)) {
				if(this.vReservList.remove(reservation)) return true;
				else return false;
			}
		}
		return false;
	}
	
	public boolean isRegisteredStudent(String sSID) {
		for (int i = 0; i < this.vReservList.size(); i++) {
			Reservation reservation = (Reservation) this.vReservList.get(i);
			if (reservation.match(sSID)) {
				return true;
			}
		}
		return false;
	}
}
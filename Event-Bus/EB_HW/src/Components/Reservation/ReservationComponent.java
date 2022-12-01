/**
 * Copyright(c) 2021 All rights reserved by Jungho Kim in Myungji University
 */
package Components.Reservation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import Components.Course.CourseComponent;
import Components.Student.StudentComponent;

public class ReservationComponent {
	protected ArrayList<Reservation> vReservList;
	protected CourseComponent courseList = new CourseComponent("Courses.txt");
	protected StudentComponent studentList = new StudentComponent("Students.txt");
	
	private String sReservationFileName;
	
	public ReservationComponent(String sReservationFileName) throws FileNotFoundException, IOException {
		BufferedReader objStudentFile = new BufferedReader(new FileReader(sReservationFileName));
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

	public ArrayList<Reservation> getReservationList(){
		return this.vReservList;
	}
	
	public boolean addReservationRecords(String studReserInfo) {
		if(this.vReservList.add(new Reservation(studReserInfo))) {
			addReservationFile(studReserInfo);
			return true;
		}
		else return false;
	}
	
	private void addReservationFile(String studReserInfo) {
		try {
			BufferedWriter objFileWrite = new BufferedWriter(new FileWriter(sReservationFileName, true));
			objFileWrite.newLine();
			objFileWrite.newLine();
			objFileWrite.write(studReserInfo);
			objFileWrite.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean deleteReservationRecords(String reservationInfo) {
		Reservation userInfo = new Reservation(reservationInfo);
		for (int i = 0; i < this.vReservList.size(); i++) {
			Reservation reservation = (Reservation) this.vReservList.get(i);
			if (reservation.courseId.equals(userInfo.courseId) && reservation.studentId.equals(userInfo.studentId)) {
				if(this.vReservList.remove(reservation)) {
					deleteReservationFile();
					return true;
				}
				else return false;
			}
		}
		return false;
	}
	
	private void deleteReservationFile() {
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
	
	public boolean isRegisteredStudent(String studentId, String courseId) {
		for (int i = 0; i < this.vReservList.size(); i++) {
			Reservation reservation = (Reservation) this.vReservList.get(i);
			if (reservation.matchStudent(studentId) && reservation.matchCourse(courseId)) {
				return true;
			}
		}
		return false;
	}
}
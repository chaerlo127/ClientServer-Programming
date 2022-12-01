/**
 * Copyright(c) 2021 All rights reserved by Jungho Kim in MyungJi University 
 */
package Components.Reservation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.util.ArrayList;

import Framework.Event;
import Framework.EventId;
import Framework.EventQueue;
import Framework.RMIEventBus;

public class ReservationMain {
	public static void main(String[] args) throws FileNotFoundException, IOException, NotBoundException {
		RMIEventBus eventBus = (RMIEventBus) Naming.lookup("EventBus");
		long componentId = eventBus.register();
		System.out.println("CourseMain (ID:" + componentId + ") is successfully registered...");

		ReservationComponent reservationsList = new ReservationComponent("Reservation.txt");
		Event event = null;
		boolean done = false;
		while (!done) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			EventQueue eventQueue = eventBus.getEventQueue(componentId);
			for (int i = 0; i < eventQueue.getSize(); i++) {
				event = eventQueue.getEvent();
				switch (event.getEventId()) {
				case ListReservations:
					printLogEvent("Get", event);
					eventBus.sendEvent(new Event(EventId.ClientOutput, makeReservationList(reservationsList)));
					break;
				case RegisterReservation:
					printLogEvent("Post", event);
					eventBus.sendEvent(new Event(EventId.ClientOutput, makeRegisterReservation(reservationsList, event.getMessage())));
					break;
				case QuitTheSystem:
					eventBus.unRegister(componentId);
					done = true;
					break;
				default:
					break;
				}
			}
		}
	}

	private static String makeRegisterReservation(ReservationComponent reservationsList, String message) {
		Reservation reservation = new Reservation(message);
		if(!reservationsList.courseList.isRegisteredCourse(reservation.courseId)) return "This course is not registered. ";
		if(!reservationsList.studentList.isRegisteredStudent(reservation.studentId)) return "This student is not registered. ";
		if(reservationsList.studentList.getStudentInfo(reservation.studentId).isRegisteredCourse(reservation.courseId)) return "This Student had already registered. ";
		
		ArrayList<String> checkPreCourse = new ArrayList<String>();
		ArrayList<String> preCourse = reservationsList.courseList.getCourseInfo(reservation.courseId).getPreCourseList();
		for(int i = 0; i<preCourse.size(); i++) {
			if(!reservationsList.studentList.getStudentInfo(reservation.studentId).getCompletedCourses().contains(preCourse.get(i))) checkPreCourse.add(preCourse.get(i));
		}
		
		if(!checkPreCourse.isEmpty()) return "This student didn't registered preCourse. ";
		
		if(reservationsList.isRegisteredStudent(reservation.studentId, reservation.courseId)) return "This Student has already registered. ";

		if(reservationsList.addReservationRecords(message)) return "This course is successfully added. ";
		else return "This course is not successfully added. ";
	}

	private static String makeReservationList(ReservationComponent reservationsList) {
		String returnString = "";
		for (int j = 0; j < reservationsList.vReservList.size(); j++) {
			returnString += reservationsList.getReservationList().get(j).toString() + "\n";
		}
		return returnString;
	}

	private static void printLogEvent(String comment, Event event) {
		System.out.println(
				"\n** " + comment + " the event(ID:" + event.getEventId() + ") message: " + event.getMessage());
	}
}

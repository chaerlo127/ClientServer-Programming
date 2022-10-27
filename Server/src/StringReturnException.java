
public enum StringReturnException {
	SUCCESS(200, "성공"),
	FAIL(201, "실패"),
	HAVE_NOT_STUDENT(202, "존재하지 않는 학생입니다."),
	HAVE_NOT_COURSE(203, "존재하지 않은 강의입니다."),
	ALREADY_REGISTERED_COURSE(204, "이미 수강한 과목입니다."),
	HAVE_NOT_VALUE(205, "값이 없습니다."),
	NOT_REGISTERED_PRECOURSE(206, "선이수 과목을 신청하지 않았습니다.");
	
	private final String message;
	private final int code;
	StringReturnException(int code, String message){
		this.code = code;
		this.message = message;
	}
	public String getMessage() {return message;}
	public int getCode() {return code;}
}

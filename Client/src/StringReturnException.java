
public enum StringReturnException {
	SUCCESS(true, 200, "성공"),
	FAIL(false, 201, "실패"),
	HAVE_NOT_STUDENT(false, 202, "존재하지 않는 학생입니다."),
	HAVE_NOT_COURSE(false, 203, "존재하지 않은 강의입니다."),
	ALREADY_REGISTERED_COURSE(false, 204, "이미 수강한 과목입니다."),
	HAVE_NOT_VALUE(false, 205, "값이 없습니다."),
	NOT_REGISTERED_PRECOURSE(false, 206, "선이수 과목을 신청하지 않았습니다."),
	NOT_ADD_STUDENT(false, 207, "학생이 등록되지 않았습니다."),
	NOT_ADD_COURSE(false, 208, "강좌가 등록되지 않았습니다."),
	NOT_DELETED_STUDENT(false, 209, "학생이 삭제되지 않았습니다."),
	NOT_DELETED_RESERVATION(false, 210, "수강신청이 삭제되지 않았습니다."),
	HAVE_STUDENT(false, 211, "이미 학생 정보가 저장되어 있습니다."),
	HAVE_COURSE(false, 212, "이미 등록된 과목 번호 입니다.");
	
	private final boolean isSuccess;
	private final String message;
	private final int code;
	StringReturnException(boolean isSuccess, int code, String message){
		this.isSuccess = isSuccess;
		this.code = code;
		this.message = message;
	}
	public String getMessage() {return message;}
	public int getCode() {return code;}
	public boolean getSuccess() {return isSuccess;}
}


public enum StringReturnException {
	SUCCESS(200, "����"),
	FAIL(201, "����"),
	HAVE_NOT_STUDENT(202, "�������� �ʴ� �л��Դϴ�."),
	HAVE_NOT_COURSE(203, "�������� ���� �����Դϴ�."),
	ALREADY_REGISTERED_COURSE(204, "�̹� ������ �����Դϴ�."),
	HAVE_NOT_VALUE(205, "���� �����ϴ�."),
	NOT_REGISTERED_PRECOURSE(206, "���̼� ������ ��û���� �ʾҽ��ϴ�.");
	
	private final String message;
	private final int code;
	StringReturnException(int code, String message){
		this.code = code;
		this.message = message;
	}
	public String getMessage() {return message;}
	public int getCode() {return code;}
}

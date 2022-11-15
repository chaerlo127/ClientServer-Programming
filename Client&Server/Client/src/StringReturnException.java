
public enum StringReturnException {
	SUCCESS(true, 200, "����"),
	FAIL(false, 201, "����"),
	HAVE_NOT_STUDENT(false, 202, "�������� �ʴ� �л��Դϴ�."),
	HAVE_NOT_COURSE(false, 203, "�������� ���� �����Դϴ�."),
	ALREADY_REGISTERED_COURSE(false, 204, "�̹� ������ �����Դϴ�."),
	HAVE_NOT_VALUE(false, 205, "���� �����ϴ�."),
	NOT_REGISTERED_PRECOURSE(false, 206, "���̼� ������ ��û���� �ʾҽ��ϴ�."),
	NOT_ADD_STUDENT(false, 207, "�л��� ��ϵ��� �ʾҽ��ϴ�."),
	NOT_ADD_COURSE(false, 208, "���°� ��ϵ��� �ʾҽ��ϴ�."),
	NOT_DELETED_STUDENT(false, 209, "�л��� �������� �ʾҽ��ϴ�."),
	NOT_DELETED_RESERVATION(false, 210, "������û�� �������� �ʾҽ��ϴ�."),
	HAVE_STUDENT(false, 211, "�̹� �л� ������ ����Ǿ� �ֽ��ϴ�."),
	HAVE_COURSE(false, 212, "�̹� ��ϵ� ���� ��ȣ �Դϴ�.");
	
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


public class NullDataException extends NullPointerException{
	private static final long serialVersionUID = 1L;

	public NullDataException(String errorMessage) {
		System.out.println("안에 값이 없습니다.");
//		super(errorMessage);
	}
}

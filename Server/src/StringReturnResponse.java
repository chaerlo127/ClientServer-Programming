import java.io.Serializable;

public class StringReturnResponse<T> implements Serializable  {

	private static final long serialVersionUID = 1L;
	private final Boolean isSuccess;
	private final String message;
	private final int code;
	private T result;

	// 요청에 성공한 경우
	public StringReturnResponse(T result) {
	        this.isSuccess = StringReturnException.SUCCESS.getSuccess();
	        this.message = StringReturnException.SUCCESS.getMessage();
	        this.code = StringReturnException.SUCCESS.getCode();
	        this.result = result;
	}
	
	
	// 요청에 실패한 경우
	public StringReturnResponse(StringReturnException e) {
		this.isSuccess = e.getSuccess();
		this.message = e.getMessage();
		this.code = e.getCode();
	}
	
	public String toString() {
		return "[code] " + code + " [exception] " + message;
	}
	
	public T getResult() {return result;}
	public String getMessage() {return this.message;}
}

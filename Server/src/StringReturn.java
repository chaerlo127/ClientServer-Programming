
public class StringReturn {
	private String errorMessage;
	
	StringReturn(StringReturnException e){
		errorMessage = "[�ڵ�] " + e.getCode() + " [exception] " + e.getMessage();
	}
	public String getErrorMessage() {
		return this.errorMessage;
	}
}

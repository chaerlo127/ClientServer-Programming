
public class StringReturn {
	private String errorMessage;
	
	StringReturn(StringReturnException e){
		errorMessage = e.getCode() + " " + e.getMessage();
	}
	public String getErrorMessage() {
		return this.errorMessage;
	}
}

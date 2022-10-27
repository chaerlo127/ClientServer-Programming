
public class StringReturn {
	private String errorMessage;
	
	StringReturn(StringReturnException e){
		errorMessage = "[ÄÚµå] " + e.getCode() + " [exception] " + e.getMessage();
	}
	public String getErrorMessage() {
		return this.errorMessage;
	}
}

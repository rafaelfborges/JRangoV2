package tratamento;

public class LoginException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String msg;
	
	public LoginException (String msg) {
		this.msg = msg;
	}
	
	public String getErro() {
		return msg;
	}
}

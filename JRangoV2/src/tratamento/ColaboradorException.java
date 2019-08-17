package tratamento;

public class ColaboradorException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String msg;
	
	public ColaboradorException(String msg) {
		this.msg = msg;
	}
	
	public String getErro() {
		return msg;
	}
}

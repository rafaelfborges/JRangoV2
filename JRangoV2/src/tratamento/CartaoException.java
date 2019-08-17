package tratamento;

public class CartaoException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String msg;
	
	public CartaoException(String msg) {
		this.msg = msg;
	}
	
	public String getErro() {
		return msg;
	}
}

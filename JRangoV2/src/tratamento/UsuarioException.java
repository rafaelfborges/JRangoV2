package tratamento;

public class UsuarioException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String msg;
	
	public UsuarioException(String msg) {
		this.msg = msg;
	}
	
	public String getErro() {
		return msg;
	}
}

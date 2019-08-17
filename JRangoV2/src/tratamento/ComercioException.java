package tratamento;

public class ComercioException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String msg;
	
	public ComercioException(String msg) {
		this.msg = msg;
	}
	
	public String getErro(){
		return msg;
	}
}

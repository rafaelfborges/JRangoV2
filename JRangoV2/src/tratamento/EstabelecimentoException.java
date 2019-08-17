package tratamento;

public class EstabelecimentoException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String msg;
	
	public EstabelecimentoException(String msg) {
		this.msg = msg;
	}
	
	public String getErro() {
		return msg;
	}
}

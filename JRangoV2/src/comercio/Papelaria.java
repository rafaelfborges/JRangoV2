package comercio;
public class Papelaria extends Estabelecimento {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Papelaria(String nome) {
		super(nome);
	}
	
	public String getDescricao() {
		return "Papelaria";
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getRamo() {
		return "papelaria";
	}
}

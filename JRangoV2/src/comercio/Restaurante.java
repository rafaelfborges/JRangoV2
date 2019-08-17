package comercio;
public class Restaurante extends Estabelecimento{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Restaurante(String nome) {
		super(nome);
	}

	public String getDescricao() {
		return "Restaurante";
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getRamo() {
		return "aliminticio";
	}
}

package comercio;
public class Pizzaria extends Estabelecimento{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Pizzaria(String nome) {
		super(nome);
	}
	
	public String getDescricao() {
		return "Pizzaria";
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getRamo() {
		return "aliminticio";
	}
}

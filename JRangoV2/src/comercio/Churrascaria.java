package comercio;
public class Churrascaria extends Estabelecimento{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Churrascaria(String nome) {
		super(nome);
	}
	
	public String getDescricao() {
		return "Churrascaria";
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getRamo() {
		return "aliminticio";
	}
	
}

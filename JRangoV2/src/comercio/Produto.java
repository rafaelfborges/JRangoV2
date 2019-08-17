package comercio;

import java.io.Serializable;

public class Produto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String descricao;
	private int preco;
	
	public Produto(String descricao, int preco) {
		this.descricao = descricao;
		this.preco = preco;
	}

	public String getDescricao() {
		return descricao;
	}

	public int getPreco() {
		return preco;
	}
}

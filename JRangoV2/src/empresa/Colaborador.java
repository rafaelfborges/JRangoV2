package empresa;

import java.io.Serializable;

import tratamento.ColaboradorException;

public class Colaborador  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nomeCompleto;
	private boolean cartaoRefeicao;
	
	public Colaborador(String nomeCompleto) throws ColaboradorException {
		if(nomeCompleto.isEmpty() == true) {
			throw new ColaboradorException("O campo nome, não pode estar vazio!");
		} else {
			this.nomeCompleto = nomeCompleto;
			this.cartaoRefeicao = false;
		}
	}
	
	public String getNomeCompleto() {
		return nomeCompleto;
	}
	
	public boolean isCartaoRefeicao() {
		return cartaoRefeicao;
	}

	public void setCartaoRefeicao(boolean cartaoRefeicao) {
		this.cartaoRefeicao = cartaoRefeicao;
	}
}
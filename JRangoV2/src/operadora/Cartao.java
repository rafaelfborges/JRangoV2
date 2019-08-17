package operadora;

import java.io.Serializable;

import empresa.Colaborador;

public class Cartao  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Colaborador colaborador;
	private int numCartao;
	private int senhaCartao;
	private int credito;
	
	public Cartao(Colaborador colaborador, int numCartao, int senhaCartao) {
		this.colaborador = colaborador;
		this.numCartao = numCartao;
		this.senhaCartao = senhaCartao;
		this.credito = 0;
	}

	public Colaborador getColaborador() {
		return colaborador;
	}

	public int getSenhaCartao() {
		return senhaCartao;
	}

	public int getNumCartao() {
		return numCartao;
	}

	public int getCredito() {
		return credito;
	}

	public void setCredito(int credito) {
		this.credito = credito;
	}
}
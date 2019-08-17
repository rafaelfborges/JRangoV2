package operadora;

import java.io.Serializable;

import comercio.Estabelecimento;

public class Consumo  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int numCartao, valorConsumido;
	private String data;
	private Estabelecimento estabelecimento;
	
	public Consumo(int numCartao, String data, Estabelecimento estabelecimento, int valorConsumido) {
		this.numCartao = numCartao;
		this.data = data;
		this.estabelecimento = estabelecimento;
		this.valorConsumido = valorConsumido;
	}

	public String getData() {
		return data;
	}

	public int getNumCartao() {
		return numCartao;
	}

	public Estabelecimento getEstabelecimento() {
		return estabelecimento;
	}

	public int getValorConsumido() {
		return valorConsumido;
	}
}

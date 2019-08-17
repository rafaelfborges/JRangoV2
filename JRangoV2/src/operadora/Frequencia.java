package operadora;

public class Frequencia {
	private Consumo nomeEstabelecimento;
	private int qtdOcorrencia;
	
	public Frequencia(Consumo nomeEstabelecimento, int qtdOcorrencia) {
		this.nomeEstabelecimento = nomeEstabelecimento;
		this.qtdOcorrencia= qtdOcorrencia;
	}

	public int incrementaQtdOcorrencia() {
		return qtdOcorrencia++;
	}

	public int getQtdOcorrencia() {
		return qtdOcorrencia;
	}

	public Consumo getNomeEstabelecimento() {
		return nomeEstabelecimento;
	}
}

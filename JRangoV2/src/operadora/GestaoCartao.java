package operadora;
import java.util.ArrayList;
import java.util.Random;
import tratamento.CartaoException;

public class GestaoCartao {
	private ArrayList<Cartao> cartoes;
	private ArrayList<Consumo> consumos;
	
	private int numCartao = 1000;
	
	public GestaoCartao() {
		cartoes = new ArrayList<Cartao>();
		consumos = new ArrayList<Consumo>();
	}
	
	public boolean cadastrarCartao(Cartao novoCartao) {
		if(novoCartao != null) {
			cartoes.add(novoCartao);
			return true;
		}
		return false;
	}
	
	public int listarCartoes() {
		int id = -1;
		Cartao temp;
		for(int cont = 0; cont < cartoes.size(); cont++) {
			temp = cartoes.get(cont);
			id = cont;
			System.out.println(cont + ") Nome colaborador: " + temp.getColaborador().getNomeCompleto() + 
								", Número cartão: " + temp.getNumCartao() + 
								", Créditos disponíveis: " + temp.getCredito());
		}
		return id;
	}
	
	public boolean carregarCreditos(int numCartao, int valorRecarga) throws CartaoException {
		if(valorRecarga <= 0) {
			throw new CartaoException("-> ATENÇÃO: Você precisa recarregar um valor maior que 0.");
		} else {
			Cartao temp;
			int aux;
			for(int cont = 0; cont < cartoes.size(); cont++) {
				temp = cartoes.get(cont);
				if(temp.getNumCartao() == numCartao) {
					aux = temp.getCredito();
					temp.setCredito(aux + valorRecarga);
					return true;
				}
			}
			return false;
		}
	}
	
	public int consultarCreditos(int numCartao) {
		Cartao temp;
		for(int cont = 0; cont < cartoes.size(); cont++) {
			temp = cartoes.get(cont);
			if(temp.getNumCartao() == numCartao) {
				return temp.getCredito();
			}
		}
		return -1;
	}
	
	public int realizarPagamento(int numCartao, int senha, int valorConsumo) throws CartaoException {
		Cartao temp;
		for(int cont = 0; cont < cartoes.size(); cont++) {
			temp = cartoes.get(cont);
			if(temp.getNumCartao() == numCartao && temp.getSenhaCartao() == senha) {
				if(temp.getCredito() >= valorConsumo) {
					temp.setCredito(temp.getCredito() - valorConsumo);
					return temp.getCredito();
				}
				throw new CartaoException("Você possuí créditos insuficientes: R$" + temp.getCredito());
			}
		}
		throw new CartaoException("Número do cartão e ou, senha errada! Tente novamente.");
	}
	
	public boolean registrarConsumo(Consumo novoConsumo) {
		if(novoConsumo != null) {
			consumos.add(novoConsumo);
			return true;
		}
		return false;
	}
	
	public void listarConsumo(int numCartao) {
		Consumo temp;
		for(int cont = 0; cont < consumos.size(); cont++) {
			temp = consumos.get(cont);
			if(temp.getNumCartao() == numCartao) {
				System.out.println(cont + ") Data consumo: " + temp.getData() + ", Nome estabelecimento: " + temp.getEstabelecimento().getNome() + ", Valor consumido: " + temp.getValorConsumido());
			}
		}
	}
	
	public void restaurantesMaisFrequentados() {
		ArrayList<Frequencia> lista;
		int cont1, cont2;
	    boolean aux;
	    	    	    
	    lista = new ArrayList<Frequencia>();
	    for(cont1 = 0; cont1 < consumos.size(); cont1++){
	    	aux = false;
	    	for(cont2 = 0; cont2 < lista.size(); cont2++){
	    		if (consumos.get(cont1).getEstabelecimento().getNome() == lista.get(cont2).getNomeEstabelecimento().getEstabelecimento().getNome()){
	    			lista.get(cont2).incrementaQtdOcorrencia();
	    			aux = true;
	    			break;
	        }
	      }
	      if (!aux) {
	    	  lista.add(new Frequencia(consumos.get(cont1), 1));  
	      }       
	    }
	    for (Frequencia freq:lista) {
	    	System.out.println("-> Nome estabelecimento: " + freq.getNomeEstabelecimento().getEstabelecimento().getNome() + ", Preferência: " + freq.getQtdOcorrencia() + "x.");
	    }    
	}
	
	public int gerarNumCartao() {
		Cartao temp;
		for(int cont = 0; cont < cartoes.size(); cont++) {
			temp = cartoes.get(cont);
			if(temp.getNumCartao() > numCartao) {
				numCartao = cartoes.get(cont).getNumCartao();
				System.out.println(numCartao);
				return numCartao++;
			}
		}
		return numCartao++;	
	}
	
	public int gerarSenha() {
		Random senha = new Random();
		return senha.nextInt(9000);
	}
	
	public int retornarNumCartao(int idCartao) throws CartaoException{
		if(idCartao >= cartoes.size()) {
			throw new CartaoException("Cartão inexistente! Selecione um número presente na lista.");
		} else {
			return cartoes.get(idCartao).getNumCartao();
		}
	}
	
	public boolean verificarExistenciaCartoes() {
		if(cartoes.size() != 0) {
			return true;
		}
		return false;
	}
	
	public boolean verificarExistenciaConsumos() {
		if(consumos.size() != 0) {
			return true;
		}
		return false;
	}
	
	public ArrayList<Cartao> retornarListaCartoes() {
		return cartoes;
	}
	
	public boolean carregarListaCartoes(ArrayList<Cartao> cartoes){
		this.cartoes = cartoes;
		return true;
	}
	
	public ArrayList<Consumo> retornarListaConsumos() {
		return consumos;
	}
	
	public boolean carregarListaConsumos(ArrayList<Consumo> consumos){
		this.consumos = consumos;
		return true;
	}
}
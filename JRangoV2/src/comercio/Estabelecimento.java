package comercio;
import java.io.Serializable;
import java.util.ArrayList;

import tratamento.EstabelecimentoException;

abstract public class Estabelecimento  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Produto> produtos;
	protected String nome;
	protected String ramo;
	protected String descricao;
	
	public Estabelecimento(String nome) {
		produtos = new ArrayList<Produto>();
		this.nome = nome;
	}
	
	public boolean cadastrarProduto(Produto novoProduto) {
		if(novoProduto != null) {
			produtos.add(novoProduto);
			return true;
		}
		return false;
	}
	
	public int listarProdutos() {
		Produto temp;
		int id = -1;
		for(int cont = 0; cont < produtos.size(); cont++) {
			temp = produtos.get(cont);
			id = cont;
			System.out.println(id + ") Descrição produto: " + temp.getDescricao() + ", Preço: R$" + temp.getPreco());
		}
		return id;
	}
	
	public int retornaPrecoProduto(int idProduto) throws EstabelecimentoException{
		if(idProduto >= produtos.size()) {
			throw new EstabelecimentoException("-> ATENÇÃO: Produto inexistente! Selecione um número presente na lista.");
		}else {
			return produtos.get(idProduto).getPreco();
		}
		
	}
	
	abstract public String getNome();
	abstract public String getRamo();
	abstract public String getDescricao();
}

package comercio;
import java.util.ArrayList;

import tratamento.ComercioException;

public class Comercio {
	private ArrayList<Estabelecimento> estabelecimentos;
	
	public Comercio() {
		estabelecimentos = new ArrayList<Estabelecimento>();
	}
	
	public boolean cadastrarEstabelecimento(Estabelecimento novoEstabelecimento) {
		if(novoEstabelecimento != null) {
			estabelecimentos.add(novoEstabelecimento);
			return true;
		}
		return false;
	}
	
	public int listarEstabelecimentos() {
		Estabelecimento temp;
		int id = -1;
		for(int cont = 0; cont < estabelecimentos.size(); cont++) {
			temp = estabelecimentos.get(cont);
			id = cont;
			System.out.println(id + ") Descrição: " + temp.getDescricao() + ", Nome estabelecimento: " + temp.getNome());
		}
		return id;
	}
	
	public Estabelecimento retornarEstabelecimento(int idEstabelecimento) throws ComercioException{
		if(idEstabelecimento >= estabelecimentos.size()) {
			throw new ComercioException("-> ATENÇÃO: Estabelecimento inexistente! Selecione um número presente na lista.");
		} else {
			return estabelecimentos.get(idEstabelecimento);
		}
	}
	
	public ArrayList<Estabelecimento> retornarListaEstabelecimentos() {
		return estabelecimentos;
	}
	
	public boolean carregarListaEstabelecimentos(ArrayList<Estabelecimento> estabelecimentos){
		this.estabelecimentos = estabelecimentos;
		return true;
	}
	
}
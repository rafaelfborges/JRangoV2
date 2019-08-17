package fluxo;

import java.io.Serializable;
import java.util.ArrayList;
import comercio.Estabelecimento;
import empresa.Colaborador;
import empresa.Usuario;
import operadora.Cartao;
import operadora.Consumo;

public class Fluxo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Usuario> usuarios;
	private ArrayList<Colaborador> colaboradores;
	private ArrayList<Cartao> cartoes;
	private ArrayList<Consumo> consumos;
	private ArrayList<Estabelecimento> estabelecimentos;

	public Fluxo(ArrayList<Usuario> usuarios, ArrayList<Colaborador> colaboradores, ArrayList<Cartao> cartoes, ArrayList<Consumo> consumos, ArrayList<Estabelecimento> estabelecimentos) {
		this.usuarios = usuarios;
		this.colaboradores = colaboradores;
		this.cartoes = cartoes;
		this.consumos = consumos;
		this.estabelecimentos = estabelecimentos;
	}

	public ArrayList<Colaborador> getColaboradores() {
		return colaboradores;
	}

	public ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}

	public ArrayList<Cartao> getCartoes() {
		return cartoes;
	}

	public ArrayList<Consumo> getConsumos() {
		return consumos;
	}

	public ArrayList<Estabelecimento> getEstabelecimentos() {
		return estabelecimentos;
	}
}

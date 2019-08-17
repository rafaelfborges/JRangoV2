package empresa;
import java.util.ArrayList;

import tratamento.ColaboradorException;
import tratamento.LoginException;

public class SistemaGestao {
	private ArrayList<Colaborador> colaboradores;
	private ArrayList<Usuario> usuarios;
	
	public SistemaGestao() {
		colaboradores = new ArrayList<Colaborador>();
		usuarios = new ArrayList<Usuario>();
	}
	
	public boolean cadastrarColaborador(Colaborador novoColaborador) {
		if(novoColaborador != null) {
			colaboradores.add(novoColaborador);
			return true;
		}
		return false;
	}
	
	public boolean cadastrarUsuario(Usuario novoUsuario) {
		if(novoUsuario != null) {
			usuarios.add(novoUsuario);
			return true;
		}
		return false;
	}
	
	public int listarColaboradores() {
		String cartaoRefeicao;
		Colaborador temp;
		int id = -1;
		for(int cont = 0; cont < colaboradores.size(); cont++) {
			id = cont;
			temp = colaboradores.get(cont);
			if(temp.isCartaoRefeicao() == true) {
				cartaoRefeicao = "Sim";
			} else {
				cartaoRefeicao = "N�o";
			}
			System.out.println(id + ") Nome colaborador: " + temp.getNomeCompleto() + ", Cart�o refei��o: " + cartaoRefeicao);
		}
		return id;
	}
	
	public void listarUsuarios() {
		String nivelAcesso;
		Usuario temp;
		for(int cont = 0; cont < usuarios.size(); cont++) {
			temp = usuarios.get(cont);
			if(temp.isAdministrador() == true) {
				nivelAcesso = "Administrador";
			} else {
				nivelAcesso = "Usu�rio padr�o";
			}
			System.out.println(cont + ") Usu�rio: " + temp.getUsuario() + ", N�vel de acesso: " + nivelAcesso);
		}
	}
	
	public boolean validarLogin(String usuario, String senha) throws LoginException {
		if(usuario.isEmpty() == true || senha.isEmpty() == true) {
			throw new LoginException("O campo usu�rio ou senha, n�o pode ficar vazio!");
		} else {
			Usuario temp;
			for(int cont = 0; cont < usuarios.size(); cont++) {
				temp = usuarios.get(cont);
				if(temp.getUsuario().equals(usuario) && temp.getSenha().equals(senha)) {
					return true;
				}
			}
		}
		throw new LoginException("ATEN��O: Usu�rio inexistente ou senha inv�lida! Verifique e tente novamente.");
	}
	
	public boolean verificarAdministrador(String usuario) {
		Usuario temp;
		for(int cont = 0; cont < usuarios.size(); cont++) {
			temp = usuarios.get(cont);
			if(temp.getUsuario().equals(usuario)) {
				return temp.isAdministrador();
			}
		}
		return false;
	}
	
	public Colaborador retornaColaborador(int idColaborador) throws ColaboradorException {
		if(idColaborador >= colaboradores.size()) {
			throw new ColaboradorException("-> ATEN��O: Colaborador inexistente! Selecione um n�mero presente na lista.");
		} else {
			return colaboradores.get(idColaborador);
		}
		
	}
	
	public boolean verificarVRColaborador(int idColaborador) throws ColaboradorException {
		if(idColaborador >= colaboradores.size()) {
			throw new ColaboradorException("-> ATEN��O: Colaborador inexistente! Selecione um n�mero presente na lista.");
		} else {
			return colaboradores.get(idColaborador).isCartaoRefeicao();
		}
	}
	
	public boolean confirmarVRColaborador(int valor) {
		colaboradores.get(valor).setCartaoRefeicao(true);
		return true;
	}
	
	public boolean verificarExistenciaUsuarios() {
		if(usuarios.size() != 0) {
			return true;
		}
		return false;
	}
	
	public boolean verificarExistenciaUsuarios(String usuario) {
		Usuario temp;
		for(int cont = 0; cont < usuarios.size(); cont++) {
			temp = usuarios.get(cont);
			if(temp.getUsuario().equals(usuario) == true)  {
				return true;
			}
		}
		return false;
	}
	
	public boolean verificarExistenciaColaboradores() {
		if(colaboradores.size() != 0) {
			return true;
		}
		return false;
	}
	
	public ArrayList<Colaborador> retornarListaColaboradores() {
		return colaboradores;
	}
	
	public boolean carregarListaColaboradores(ArrayList<Colaborador> colaboradores){
		this.colaboradores = colaboradores;
		return true;
	}
	
	public ArrayList<Usuario> retornarListaUsuarios() {
		return usuarios;
	}
	
	public boolean carregarListaUsuarios(ArrayList<Usuario> usuarios){
		this.usuarios = usuarios;
		return true;
	}
}
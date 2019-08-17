package empresa;

import java.io.Serializable;

import tratamento.UsuarioException;

public class Usuario  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String usuario;
	private String senha;
	private boolean administrador;
	
	public Usuario(String usuario, String senha, boolean admin) throws UsuarioException {
		if(usuario.isEmpty() == true || senha.isEmpty() == true) {
			throw new UsuarioException("O campo usuário, senha e administrador, não pode estar vazio!");
		} else {
			this.usuario = usuario;
			this.senha = senha;
		}
		this.administrador = admin;
	}

	public boolean isAdministrador() {
		return administrador;
	}

	public String getUsuario() {
		return usuario;
	}

	public String getSenha() {
		return senha;
	}
	
}

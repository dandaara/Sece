package Control;

import Model.Usuario;

public class CLogin {
	
	private Usuario usuario;
	private boolean temPermissao = false;
	
	public void passarDados(String nome, String login)
	{
		usuario = new Usuario(nome, login);
		temPermissao=usuario.verificarLogin();
	}
	
	public boolean getVerificar()
	{
		return temPermissao;
	}

}

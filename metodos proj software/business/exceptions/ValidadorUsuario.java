package util;

import business.model.Usuario;

public class ValidadorUsuario {
	
	private final static int MAX_LOGIN = 15,
			 MAX_SENHA = 18,
			 MIN_SENHA = 6,
			 VAZIO = 0;
	
	public boolean validaLogin(Usuario usuario) throws LoginInvalido {
		
		if(usuario.getLogin().length() == VAZIO) {
			
			throw new LoginInvalido("Login nao pode ser deixado em branco!");
			
		
		}else if(usuario.getLogin().length() > MAX_LOGIN) {
			throw new LoginInvalido("Login excedeu o numero maximo (15) de caracteres!");
		
		}else if(usuario.getLogin().matches(".*\\d.*")) {
			
			throw new LoginInvalido("Login nao pode conter numeros!");
			
		}else {
			return true;
		}
		
	}
	
	public boolean validaSenha(Usuario usuario) throws SenhaInvalida{
		
		if(usuario.getSenha().length() > MAX_SENHA) {
			throw new SenhaInvalida("A senha excedeu o numero maximo (18) de caractes!");
		
		}else if(usuario.getSenha().length() < MIN_SENHA) {
			throw new SenhaInvalida("A senha nao atingiu a quantidade minima (6) de caracteres!");
		
		}else if(!usuario.getSenha().matches(".*\\d.*\\d.*")){
			throw new SenhaInvalida("A senha deve possuir pelo menos 2 numeros!");
		
		}else if(!usuario.getSenha().matches("(.*)[a-z|A-Z](.*)")) {
			throw new SenhaInvalida("A senha deve possuir letras!");
		
		}
		
		return true;
	}

}

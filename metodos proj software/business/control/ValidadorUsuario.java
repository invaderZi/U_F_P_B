package jobufpb3.business.control;

import jobufpb3.business.model.Usuario;
import jobufpb3.business.exceptions.LoginInvalidoException;
import jobufpb3.business.exceptions.SenhaInvalidaException;




public class ValidadorUsuario implements ControleDeAutenticacao {

    

	
	public void validaLoginUsuario(Usuario usuario) throws LoginInvalidoException {
		String Login;
                Login = usuario.getLogin();
            
                 jobufpb3.business.control.ControleDeAutenticacao.validaLogin(Login);
                        

		
	}
	
	public void validaSenhaUsuario (Usuario usuario) throws SenhaInvalidaException{
		
		String Senha;
                Senha = usuario.getSenha();
            
                 jobufpb3.business.control.ControleDeAutenticacao.verificaSenha(Senha);
	}

}

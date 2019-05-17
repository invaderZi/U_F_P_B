package jobufpb3.business.control;

import jobufpb3.business.exceptions.LoginInvalidoException;
import jobufpb3.business.exceptions.SenhaInvalidaException;

public interface ControleDeAutenticacao {
	
        
        
       public static void validaLogin(String login) throws LoginInvalidoException
    { 
        
        if(login.length() > 15)
            throw new LoginInvalidoException("Login muito grande! nao pode conter mais que 15 caracteres");
            if(login.matches(".*\\d.*"))
              throw new LoginInvalidoException("Erro! O login nao pode conter numeros");
                if(login.isEmpty())
                    throw new LoginInvalidoException("Erro! login nao pode ser vazio");
    }
       
        public static void verificaSenha(String senha) throws SenhaInvalidaException
    {
   
        if(senha.length() > 18 || senha.length() < 6)
            throw new SenhaInvalidaException("A senha deve ter entre 6 e 18 caracteres");
        if(senha.matches("[0-9]+") || senha.matches("[a-zA-Z]+"))
            throw new SenhaInvalidaException(" A senha deve conter letras e numeros");
        if(!senha.matches(".*\\d.*\\d.*"))
            throw new SenhaInvalidaException("A senha deve conter pelo menos DOIS numeros");
        if(senha.isEmpty())
            throw new SenhaInvalidaException(" Senha vazia");
    }
    
}

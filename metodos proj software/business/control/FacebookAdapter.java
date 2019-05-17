package jobufpb3.business.control;

import infra.API_Facebook;

public class FacebookAdapter extends API_Facebook{
	
	public void validaCadastro(String login, String senha) {
		facebook_ValidaCadastro(login, senha);
	}
	
	public void validaLogin(String login, String senha) {
		facebook_ValidaLogin(login, senha);
	}

}

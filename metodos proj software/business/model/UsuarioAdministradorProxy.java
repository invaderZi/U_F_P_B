package jobufpb3.business.model;

import jobufpb3.business.control.ControleDeAdmnistrador;

public class UsuarioAdministradorProxy extends UsuarioAdministrador {
	protected String usuario, senha;
        
        public ControleDeAdmnistrador adm;
	
	public UsuarioAdministradorProxy(String usuario, String senha) {
		this.usuario = usuario;
		this.senha = senha;
	}
	
	public Relatorio gerarRelatorio(ControleDeAdmnistrador adm) {
		if(!this.temPermissaoDeAcesso()) {
			return adm.gerarRelatorio();
		}
		return null;
	}
	
	private boolean temPermissaoDeAcesso() {
		return this.usuario == "admin" && this.senha == "admin";
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String toString() {
		return "AdministradorProxy [usuario=" + usuario + ", senha=" + senha + "]";
	}
	
}

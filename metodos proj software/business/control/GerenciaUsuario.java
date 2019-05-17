package business.control;

import business.model.Usuario;

import java.util.ArrayList;

import infra.UsuarioDAOImpl;
import util.LoginInvalido;
import util.SenhaInvalida;
import util.ValidadorUsuario;
import util.ArquivoNaoEncontrado;

public class GerenciaUsuario {
	
	public static GerenciaUsuario instancia;
	private GerenciaUsuarioCareTaker memento;
	private ArrayList<Usuario> listaDeUsuarios = new ArrayList<Usuario>();
	private UsuarioDAOImpl p = new UsuarioDAOImpl();
	private ValidadorUsuario validador = new ValidadorUsuario();
	
	protected GerenciaUsuario() {}
		
	public static GerenciaUsuario getInstancia() {
		if(instancia == null)
			instancia = new GerenciaUsuario();
		return instancia;
	}
	
	public boolean validaSenha(Usuario usuario) throws SenhaInvalida{
		return validador.validaSenha(usuario);
	}
	
	public boolean validaLogin(Usuario usuario) throws LoginInvalido{
		return validador.validaLogin(usuario);
	}
	
	public void adicionaUsuario(Usuario usuario){
		memento.atualizarMemento(this.listaDeUsuarios);
		listaDeUsuarios.add(new Usuario(usuario.getLogin(), usuario.getSenha(), usuario.getNome(), usuario.getIdade(),
							usuario.getSexo(), usuario.getCidade(), usuario.getEstado(), usuario.getCaracteristicasAnimais()));
		System.out.printf("\nO usuario '%s' foi adicionado com sucesso \n", usuario.getLogin());
		
	}
	
	public void listaDeUsuarios() {
		
		System.out.printf("\nTamanho da lista de usuarios: %d\n" , listaDeUsuarios.size());
		
		for (IteradorUsuario iterator = new IteradorUsuario(this.listaDeUsuarios); !iterator.isDone(); iterator.next()) {
			
			Usuario u = iterator.currentItem();
			
			u.setId(iterator.contador);
			
			System.out.printf("\nUsuario %d: ", u.getId());
			System.out.println(u.toString());
			
		}
		
	}
	
	
	public boolean removeUsuario(String login) {
		memento.atualizarMemento(this.listaDeUsuarios);
		for (IteradorUsuario iterator = new IteradorUsuario(this.listaDeUsuarios); !iterator.isDone(); iterator.next()) {
				
			Usuario u = iterator.currentItem();
				
			if (login.equals(u.getLogin())) {
				listaDeUsuarios.remove(u);
				return true;
			}
		}
			
		return false;
	}
	
	public void salvaListaEmArquivo() throws ArquivoNaoEncontrado{
		
		try {
			p.salvaLista(listaDeUsuarios);
		}catch(ArquivoNaoEncontrado e) {
			throw e;
		}
		
	}
	
	public void leLista() throws ArquivoNaoEncontrado{
		
		try {
			listaDeUsuarios = p.carregaLista();
			memento = new GerenciaUsuarioCareTaker(this.listaDeUsuarios);
		}catch(ArquivoNaoEncontrado e) {
			throw e;
		}	
	
	}
	
	public Usuario buscaLogin(String login) {
		for (IteradorUsuario iterator = new IteradorUsuario(this.listaDeUsuarios); !iterator.isDone(); iterator.next()) {
			Usuario u = iterator.currentItem();					
			if (login.equals(u.getLogin())) {
				return u;
			}
		}
		
		return null;
	}
	
	public void retornarEstado() {
		this.listaDeUsuarios = memento.getEstadoSalvo();
	}

}

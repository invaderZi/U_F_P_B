/*
****************************************
Universidade Federal da Paraiba
Ano 2018
Projeto da disciplina  	METODOS DE PROJETO DE SOFTWARE 
Professor Raoni
Alunos:
Sabrina Silva
Vinicius Guedes
****************************************
*/
package jobufpb3.business.control;

import jobufpb3.business.exceptions.UsuarioNCadastradoException;
import jobufpb3.business.model.Usuario;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import jobufpb3.business.exceptions.ArquivoNaoEncontrado;
import jobufpb3.business.exceptions.LoginInvalidoException;
import jobufpb3.business.exceptions.SenhaInvalidaException;
import jobufpb3.infra.DAO.CadastroDAO;

public class ControleDeUsuario {
    
    private ArrayList<Usuario> listaDeUsuarios;        
        private ControleDeUsuariosMemento memento;
        public static ControleDeUsuario instancia;
	private final CadastroDAO cad = new CadastroDAO();
	private final ValidadorUsuario validador = new ValidadorUsuario();
   
    
    public ControleDeUsuario() throws IOException, ClassNotFoundException {
        this.listaDeUsuarios = new ArrayList<>();
        
             getInstancia();
       
    }
    
    
    public static ControleDeUsuario getInstancia() throws IOException, ClassNotFoundException {
		if(instancia == null)
			instancia = new ControleDeUsuario();
		return instancia;
	}
	  
    
    public List getUsuarios() {
        return listaDeUsuarios;
    }
    
    
    public void addUsuario(Usuario usuario) {
        listaDeUsuarios.add(usuario);
    }
   
    public int getUsers(String nome) throws UsuarioNCadastradoException{
       
        for(int i = 0; i < listaDeUsuarios.size(); i++){
            listaDeUsuarios.get(i);
                return i;
        }
        throw new UsuarioNCadastradoException("Este usuario nao esta cadastrado!");
    }
    
   
    public boolean usuarioExiste(String nome) {
        return listaDeUsuarios.stream().anyMatch((usuario) -> (usuario.getLogin().equals(nome)));
    }

      Usuario deletaUsuariosNalista(int i) {
             return listaDeUsuarios.remove(i);
    }
    
      public void validaSenha(Usuario usuario) throws SenhaInvalidaException {
             validador.validaSenhaUsuario(usuario);
    }
	
	public void validaLogin(Usuario usuario) throws LoginInvalidoException{
             validador.validaLoginUsuario(usuario);
      }
	
        
        
        public void adicionaUsuario(Usuario usuario){
	    memento.atualizarMemento(this.listaDeUsuarios);
            
            listaDeUsuarios.add(new Usuario(usuario.getLogin(), usuario.getSenha()));
                
		System.out.printf("\n O usuario '%s' foi adicionado com sucesso \n", usuario.getLogin());
		
	}
	
	public void listaDeUsuarios() {
		
		System.out.printf("\n Usuarios Cadastrados: %d\n" , listaDeUsuarios.size());
		
		for (IteradorUsuario iterator = new IteradorUsuario(this.listaDeUsuarios); !iterator.isDone(); iterator.next()) {
			
			Usuario u = iterator.currentItem();
			
			u.setId(iterator.contador);
			
			System.out.printf("\n Usuario %d: ", u.getId());
			System.out.println(u.toString());
			
		}
		
	}
	
	
	public boolean removeUsuarioPorlogin(String login) {
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
		
            cad.Salvar(listaDeUsuarios);
		
	}
	
	public void leLista() throws ArquivoNaoEncontrado{
		
            listaDeUsuarios = cad.Carregar();
            memento = new ControleDeUsuariosMemento(this.listaDeUsuarios);	
	
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

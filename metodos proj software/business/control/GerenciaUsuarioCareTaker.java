package business.control;

import java.util.ArrayList;

import business.model.Usuario;

public class GerenciaUsuarioCareTaker {
	protected  ArrayList<Usuario> listaMemento;
	
	public GerenciaUsuarioCareTaker(ArrayList<Usuario> lista) {
		this.listaMemento = new ArrayList<Usuario>(lista);
	}
	
	public void atualizarMemento(ArrayList<Usuario> lista) {
		this.listaMemento = new ArrayList<Usuario>(lista);
	}
	
	public ArrayList<Usuario> getEstadoSalvo() {
		return this.listaMemento;
	}
	
	public void print() {
		System.out.printf("\nTamanho da lista de usuarios: %d\n" , listaMemento.size());
		
		for (int i = 0; i < listaMemento.size(); i++) {
			
			Usuario u = (Usuario) listaMemento.get(i);
			
			u.setId(i);
			
			System.out.printf("\nUsuario %d: ", u.getId());
			System.out.println(u.toString());
			
		}
	}

}

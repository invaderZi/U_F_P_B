package jobufpb3.business.control;

import java.util.ArrayList;

import jobufpb3.business.model.Usuario;

public class ControleDeUsuariosMemento {
	protected  ArrayList<Usuario> listaMemento;
	
	public ControleDeUsuariosMemento(ArrayList<Usuario> lista) {
		this.listaMemento = new ArrayList<>(lista);
	}
	
	public void atualizarMemento(ArrayList<Usuario> lista) {
		this.listaMemento = new ArrayList<>(lista);
	}
	
	public ArrayList<Usuario> getEstadoSalvo() {
		return this.listaMemento;
	}
	
	public void print() {
		System.out.printf("\n Tamanho da lista de usuarios: %d\n" , listaMemento.size());
		
		for (int i = 0; i < listaMemento.size(); i++) {
			
			Usuario user = (Usuario) listaMemento.get(i);
			
			user.setId(i);
			
			System.out.printf("\nUsuario %d: ", user.getId());
			System.out.println(user.toString());
			
		}
	}

}

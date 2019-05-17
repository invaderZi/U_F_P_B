package jobufpb3.view;

import java.io.IOException;
import jobufpb3.business.exceptions.ArquivoNaoEncontrado;
        

import static java.lang.System.exit;
import jobufpb3.business.control.ControleDeUsuario;
import jobufpb3.business.control.ControlePublicacoes;


public class Facade {
    
  private ControleDeUsuario gerenteUsuario;
  
   
  public void inicializarSistema() throws IOException, ClassNotFoundException {
       try {
    gerenteUsuario = ControleDeUsuario.getInstancia();
   
  	  gerenteUsuario.leLista();
          
  	}catch(ArquivoNaoEncontrado e) {
  		System.out.println("\n" + e.getMessage());
  	}
        
    
  }
    
  public void finalizarSistema() {
    
    try {
      gerenteUsuario.salvaListaEmArquivo();
    }catch(ArquivoNaoEncontrado e) {
      System.out.println("\n" + e.getMessage());
    }
     exit(0);
    
  }
    
  public void listaDeUsuarios() {
    System.out.println("\n Lista de usuarios SALVAS em arquivo: ");
    gerenteUsuario.listaDeUsuarios();
  }
  
 
  public void ListaDEPUblicacoes() {
    System.out.println("\n  Lista de Publicacoes no arquivo: ");
    ControlePublicacoes.listadePublicacoes();
  }
  
  
  
}
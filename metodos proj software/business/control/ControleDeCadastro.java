

package jobufpb3.business.control;

import jobufpb3.business.exceptions.LoginInvalidoException;
import jobufpb3.business.exceptions.SenhaInvalidaException;
import jobufpb3.business.exceptions.UsuarioNCadastradoException;
import jobufpb3.business.model.Usuario;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import jobufpb3.view.ViewCadastro;


public class ControleDeCadastro {
    
     private int idCadastro;
     private static ViewCadastro usuarioview;
     private static ControleDeUsuario controlador;
     private static ControleDeAutenticacao autenticador;
     
     
     public ControleDeCadastro(){}
     
     
     public static void controladorDeCadastro(){

     try {
            //tenta instanciar um banco de usuarios
          controlador = new ControleDeUsuario();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao abrir programa, verifique arquivo de usuarios");
            System.exit(-1);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "O arquivo de usuarios pode estar corrompido");
            System.exit(-2);
        }
  

        usuarioview = new ViewCadastro();
        usuarioview.setVisible(true);
     
        }

    
    public static void login(java.awt.event.ActionEvent evt) {
        
        Usuario usuario = null;
        try {
            usuario = (Usuario) controlador.getUsuarios().get(controlador.getUsers(usuarioview.getLogin()));
            
            if(!usuarioview.getSenha().equals(usuario.getSenha()))
                usuarioview.mostraErro("Senha incorreta");
        else 
            usuarioview.mostraMensagem("Loggado com sucesso como: "+usuario.getLogin());
        } catch (UsuarioNCadastradoException ex) {
            usuarioview.mostraErro(ex.getMessage());
        } 
    }
    
    public static void cadastra() {
        if(!controlador.usuarioExiste(usuarioview.getLogin())) {
            try {
                ControleDeAutenticacao.validaLogin(usuarioview.getLogin());
                ControleDeAutenticacao.verificaSenha(usuarioview.getSenha());
                
                Usuario usuario = new Usuario(usuarioview.getLogin(), usuarioview.getSenha());
                controlador.addUsuario(usuario);
                
                usuarioview.mostraMensagem("Usuario "+usuario.getLogin()+" cadastrado com sucesso!");
            } catch (SenhaInvalidaException | LoginInvalidoException ex) {
                usuarioview.mostraErro(ex.getMessage());
            }
        }
        else
            usuarioview.mostraErro("Usuario ja cadastrado");
    }
    
    public static void deleta(java.awt.event.ActionEvent evt) {
        if(controlador.usuarioExiste(usuarioview.getLogin())){
            try {
                int i = controlador.getUsers(usuarioview.getLogin());
                Usuario usuario = (Usuario)controlador.getUsuarios().get(i);
                
                if(!usuarioview.getSenha().equals(usuario.getSenha()))
                    usuarioview.mostraErro("Senha incorreta");
                else{
                    controlador.deletaUsuariosNalista(i);
                    
                    usuarioview.mostraMensagem("Usuario "+usuario.getLogin()+" deletado com sucesso!");
                }
            } catch (UsuarioNCadastradoException ex) {
                usuarioview.mostraErro(ex.getMessage());
            } 
        }
    }
      public void setId(int id) {
            this.idCadastro = id;    }

    public int getId() {
        return idCadastro;
        
        }
    
    }
    


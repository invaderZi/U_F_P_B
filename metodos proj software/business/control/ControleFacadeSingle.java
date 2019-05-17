package jobufpb3.business.control;
import business.exceptions.InfraException;
import business.exceptions.SenhaInvalidaException;
import business.model.Usuario;
import java.util.Map;
import javax.security.auth.login.LoginException;


/**
 * Facade class for users management.
 */
public class ControleFacadeSingle {
    //Creation of Singleton object
   private static ControleFacadeSingle uniqueInstance = null;

    //Facade
    private ControleRelatorio reportMng = null;
    private ControleDeUsuario userMng = null;
    
    public static ControleFacadeSingle getInstance(ControleRelatorio reportMng, ControleDeUsuario userMng) {
        //Singleton
        if(uniqueInstance == null) {
            uniqueInstance = new ControleFacadeSingle(reportMng, userMng);
        }
        return uniqueInstance;
    }
    
    //Singleton
    private ControleFacadeSingle(ControleRelatorio reportMng, ControleDeUsuario userMng){
        this.reportMng = reportMng;
        this.userMng = userMng;
    }
    
    
    //other methods
    public boolean addNewUser(String login, String pw) throws LoginException, SenhaInvalidaException {
        return userMng.addUsuario(login, pw);
    }
    
    public Usuario deleteUser(int id) throws LoginException{
        return userMng.deletaUsuarios(id);
    }
    
    public String search(String login) {
        return userMng.searchUser(login);
    }
    
    //public Map<String, String> getUsers() throws InfraException {
    //    return userMng.getUserList();
    //}
    
    
}

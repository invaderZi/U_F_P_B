package jobufpb3.view;
import jobufpb3.view.ViewLogin;


import jobufpb3.business.control.ControleDeCadastro;

//falta finalizar implementacao para chamar tela de login
//no momento so usando interface antiga pra cadastro do usuario

public class Interface {
   
     private static ControleDeCadastro controladorDeCad;
    
    public static void main(String[] args){
        
        controladorDeCad = new ControleDeCadastro();
        
        
        //Usando Interface NIMBUS padrao java 
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        
        controladorDeCad.controladorDeCadastro();
        
    }
    
    
}
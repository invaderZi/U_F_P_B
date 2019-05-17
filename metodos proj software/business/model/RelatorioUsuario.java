
package jobufpb3.business.model;

import java.util.Calendar;

public class RelatorioUsuario extends Relatorio{
    
    private Usuario usuario;
    private Calendar dataRelalatorio;
    private String log;
    private int horadeAcesso;
    private Calendar horaDeLogin, horaDelogout;

    /**
     * @return the horadeAcesso
     */
    public int getTimesAccessed() {
        return horadeAcesso;
    }

    /**
     * @return the horaDeLogin
     */
    public Calendar getLoginTime() {
        return horaDeLogin;
    }

    /**
     * @param loginTime the horaDeLogin to set
     */
    public void setLoginTime(Calendar loginTime) {
        this.horaDeLogin = loginTime;
    }

    /**
     * @return the horaDelogout
     */
    public Calendar getLogoutTime() {
        return horaDelogout;
    }

    /**
     * @param logoutTime the horaDelogout to set
     */
    public void setLogoutTime(Calendar logoutTime) {
        this.horaDelogout = logoutTime;
    }
    
    @Override
    public String toString(){
        return "Report from: " + usuario.getLogin() + "\nGenerated at " + dataRelalatorio.getInstance().toString() + "\nLogin time: " + horaDeLogin.toString() + "\t Logout time: " + horaDelogout.toString() + "\nTimes accessed: "  + horadeAcesso + "\nLog: " + log;
    }
    
    
    public Usuario getUser() {
        return usuario;
    }

    /**
     * @return the dataRelalatorio
     */
    public Calendar getReportDate() {
        return dataRelalatorio;
    }

    /**
     * @param reportDate the dataRelalatorio to set
     */

    public void setReportDate(Calendar reportDate) {
        this.dataRelalatorio = reportDate;
    }

    /**
     * @return the log
     */
    public String getLog() {
        return log;
    }

    /**
     * @param log the log to set
     */
    public void setLog(String log) {
        this.log = log;
    }
    
}

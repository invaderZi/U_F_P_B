
package jobufpb3.business.model;

import java.util.Calendar;

/**
 *
 * @author thiago
 */
public class RelatorioErro extends Relatorio{
    
    private String errorName;
    private Calendar errorTime;
    private int gravity;
    private Usuario user;
    private Calendar reportDate;
    private String log;

    /**
     * @return the errorTime
     */
    public Calendar getErrorTime() {
        return errorTime;
    }

    /**
     * @param errorTime the errorTime to set
     */
    public void setErrorTime(Calendar errorTime) {
        this.errorTime = errorTime;
    }

    /**
     * @return the gravity
     */
    public int getGravity() {
        return gravity;
    }

    /**
     * @param gravity the gravity to set
     */
    public void setGravity(int gravity) {
        this.gravity = gravity;
    }
    
    @Override
    public String toString(){
        return "Report from: " + user.getLogin() + "\nGenerated at " + reportDate.toString() + "\nError type: " + getErrorName() + "\nGravity: " + getGravity() + "\nError Time: " + errorTime.toString() + "\nLog: " + log;
    }

    /**
     * @return the errorName
     */
    public String getErrorName() {
        return errorName;
    }

    /**
     * @param errorName the errorName to set
     */
    public void setErrorName(String errorName) {
        this.errorName = errorName;
    }
    
    public Usuario getUser() {
        return user;
    }

    /**
     * @return the reportDate
     */
    public Calendar getReportDate() {
        return reportDate;
    }

    /**
     * @param reportDate the reportDate to set
     */

    public void setReportDate(Calendar reportDate) {
        this.reportDate = reportDate;
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

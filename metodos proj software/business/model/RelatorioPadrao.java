
package jobufpb3.business.model;

import java.util.Calendar;

public class RelatorioPadrao extends Relatorio {
    private Usuario user;
    private Calendar reportDate;
    private String log;

    /**
     * @return the user
     */
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

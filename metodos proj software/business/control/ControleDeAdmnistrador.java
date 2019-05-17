/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jobufpb3.business.control;

import jobufpb3.business.model.Relatorio;
import jobufpb3.business.control.GeradorRelatorioPDF;

/**
 *
 * @author zi
 */
public class ControleDeAdmnistrador {

    public ControleDeAdmnistrador(int login) {
        
        
        
    }
    public Relatorio gerarRelatorio() {
	
	GeradorRelatorioPDF geradorDeRelatorio = new GeradorRelatorioPDF();
	geradorDeRelatorio.gerarRelatorio();
			
		
		return null;
	}
}

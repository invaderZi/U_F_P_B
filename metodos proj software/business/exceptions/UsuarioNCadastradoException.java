/*
****************************************
Universidade Federal da Paraiba
Ano 2018
Projeto da disciplina  	METODOS DE PROJETO DE SOFTWARE 
Professor Raoni
Alunos:
Sabrina Silva
Vinicius Guedes

Parte 1 projeto: implementação do crud de cadastro da entidade usuário usando padrao MVC
****************************************
*/

package jobufpb3.business.exceptions;


public class UsuarioNCadastradoException extends Exception {
    public static final long serialVersionUID = 1149241039409861914L;
    
    public UsuarioNCadastradoException(String msg) {
        super(msg);
    }
    
    public UsuarioNCadastradoException(String msg, Throwable causa){
        super(msg, causa);
    }
}

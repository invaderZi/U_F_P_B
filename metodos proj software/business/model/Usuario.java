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

package jobufpb3.business.model;

import java.io.Serializable;

public class Usuario implements Serializable{
    String login;
    private String senha;
    int id;
    
    
  public Usuario() {}

    
    public Usuario(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getId() {
            return id;    }

    public void setId(int i) {
            this.id = i;
    }

  
    
    
}
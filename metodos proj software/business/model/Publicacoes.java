
package jobufpb3.business.model;


public class Publicacoes {
    
    public Publicacoes() {}

    public String titulo;                 //titulo da vaga    
    public String descricao;             //breve descricao ate 150 carac
    public int quantidadeDeVagas;       //quantidade de vagas
    public String tipoDeRemuneracao;   //se é remunerado ou voluntario
    public float remuneracao;          // valor da remuneração
    public String programa;            //programa ou extensao vinculada

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setQuantidadeDeVagas(int quantidadeDeVagas) {
        this.quantidadeDeVagas = quantidadeDeVagas;
    }

    public void setTipoDeRemuneracao(String tipoDeRemuneracao) {
        this.tipoDeRemuneracao = tipoDeRemuneracao;
    }

    public void setRemuneracao(float remuneracao) {
        this.remuneracao = remuneracao;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }
    
        

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getQuantidadeDeVagas() {
        return quantidadeDeVagas;
    }

    public String getTipoDeRemuneracao() {
        return tipoDeRemuneracao;
    }

    public float getRemuneracao() {
        return remuneracao;
    }

    public String getPrograma() {
        return programa;
    }
    
 

}

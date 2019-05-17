#include <iostream>
#include <vector>
#include <fstream>
#include <algorithm>

using namespace std;



//classe para Objetos inicio
class Objeto{
public:
    int peso;
    int valor;

    Objeto(); //constructor
    Objeto(int, int); //constructor 2


}; //fim classe objetos

Objeto::Objeto(){ //implementacao construtor classe objetos
    this->peso = 0;
    this->valor = 0;
}

Objeto::Objeto(int peso, int valor){	//implementacao construtor classe objetos
    this->peso = peso;
    this->valor = valor;
}


//inicio metodo mochila
int Mochila(int linhas, int colunas, vector<Objeto> &obj){

    int matrizPesos[linhas][colunas];

    //zerando matriz

    for (int i = 0; i < linhas; i ++){
        for (int j = 0; j < colunas; j++){
            matrizPesos[i][j] = 0;
        }
    }


    //ALGORITMO MOCHILA
    for (int i = 1; i < linhas; i++){
        for (int j = 1; j < colunas; j++){
            if (obj[i-1].peso <= j){
                matrizPesos[i][j] = max(matrizPesos[i-1][j], obj[i-1].valor + matrizPesos[i-1][j-obj[i-1].peso]);
            }else{
                matrizPesos[i][j] = matrizPesos[i-1][j];
            }
        }

    }

    return matrizPesos[linhas-1][colunas-1];
} //fim metodo mochila


void produtosNaMochila(int valortt, int linhas, int colunas, vector<Objeto> &obj){

	int valorTotal = valortt;

    int newcolunas = 0;
    linhas = colunas;
    int matrizPesos[linhas][newcolunas];
    while(valorTotal)
    {
        if(!matrizPesos[linhas][newcolunas])
        {
            ++newcolunas;
        }
        else
        {
            std::cout << newcolunas + 1;
            linhas -= obj[newcolunas].peso;
            valorTotal -= obj[newcolunas].valor;
            ++newcolunas;
            if(valorTotal)
            {
                std::cout << ", ";
            }
        }        
    } 
    std::cout << std::endl;
}


int main(int argc, char const *argv[])

{   /// endereço dos arquivos de entrada
    ifstream mochila("instancias/mochila01.txt.txt", ios::in);
    // ifstream mochila("instancias/mochila02.txt.txt", ios::in);
    // ifstream mochila("instancias/mochila1000.txt.txt", ios::in);
    //ifstream mochila("instancias/mochila2500.txt.txt", ios::in);
    //ifstream mochila("instancias/mochila5000.txt.txt", ios::in);

    ///Lê as duas primeiras linhas do arquivo, qntde objetos e capacidade da mochila
    int quantidadeObj, capacidadeMoch;
    mochila >> quantidadeObj >> capacidadeMoch;

    ///Lê do arquivo e armazena informações no vec de Objetos
    vector<Objeto> vecObjetos;
    for (int i = 0; i < quantidadeObj; i++){
        Objeto objTemp;
        mochila >> objTemp.peso;
        mochila >> objTemp.valor;
        vecObjetos.push_back(objTemp);
    }


    //INS. SORT PARA ORDENAR OS OBJETOS DE ACORDO COM O PESO
    int pivo = 0;
    for(unsigned int i = 0; i < vecObjetos.size()-1; i++){
        pivo = i+1;
        while(pivo > 0 && (vecObjetos[pivo].peso < vecObjetos[pivo-1].peso)){
            swap(vecObjetos[pivo], vecObjetos[pivo-1]);
            pivo--;
        }
    }

    //Cria matriz para armazenar valores relacionados ao custo benefício dos objetos de acordo com o peso
    unsigned int linhas = vecObjetos.size() + 1;
    unsigned int colunas = capacidadeMoch + 1;

     //int valorTotal = Mochila(linhas, colunas, vecObjetos) << endl;

    cout << "Valor maximo da mochila: " << Mochila(linhas, colunas, vecObjetos) << endl;
    //std::cout << "produtos escolhidos : "<< produtosNaMochila(valorTotal,linhas, colunas, vecObjetos); 


    return 0;
}






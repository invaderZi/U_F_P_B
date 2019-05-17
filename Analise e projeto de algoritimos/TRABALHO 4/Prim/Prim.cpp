#include <iostream>
#include <vector>
#include <fstream>
#include <queue>
#include <algorithm>
#include <string>

using namespace std;

#define INFINITO 999999999

typedef pair<int, int> par;

int main()
{
    //ifstream arquivo_de_matriz("instancias_teste/matriz_adj10.txt", ios::in);
    //ifstream arquivo_de_matriz("instancias_teste/matriz_adj20.txt", ios::in);
    ifstream arquivo_de_matriz("instancias_teste/matriz_adj40.txt", ios::in);
    //ifstream arquivo_de_matriz("instancias_teste/matriz_adj50.txt", ios::in);

    int V;                              //tamanho do da matriz
    int num_arestas = 0;
    arquivo_de_matriz >> V;             //L� a primeira linha da matriz que contem o tamanho

    int matriz_adj[V][V] = {0};            //Cria matriz adjac�ncia do tamanho certo
    int n, m;                             // n�mero de v�rtices e arestas
    int distancia[V];           // o array de dist�ncias � fonte
    int processado[V];          // o array que guarda se um v�rtice foi processado
    vector<par> lista_adj[V];    // nossas listas de adjac�ncia. O primeiro elemento do par representa a dist�ncia e o segundo representa o v�rtice

    for (int i = 0; i < V; i++){
        for (int j = i; j < V; j++){
            if (i == j)             //Dist�ncia entre um v�rtice em rela��o a ele mesmo � 0
                continue;

            arquivo_de_matriz >> matriz_adj[i][j];
            matriz_adj[j][i] = matriz_adj[i][j];
            num_arestas++;
            lista_adj[i].push_back(par(matriz_adj[i][j], j));
            lista_adj[j].push_back(par(matriz_adj[i][j], i));
        }
    }




    for(int i = 2; i <= num_arestas; i++) distancia[i] = INFINITO; // definimos todas as dist�ncias como infinito, exceto a de S = 1.
    distancia[1] = 0;                                  // Assim, garantimos que o primeiro v�rtice selecionado ser� o pr�prio 1.

	priority_queue< par, vector<par>, greater<par> > fila;      // Criamos uma fila de prioridade onde o menor fica no topo.
	fila.push( par(distancia[1], 1) );                          // Como se pode ver, colocamos o primeiro elemento como seja a dist�ncia do
                                                                // v�rtice a �rvore Geradora M�nima e o segundo como sendo o pr�prio v�rtice

	while(true){

		int vertice_navez = -1;

		// selecionamos o v�rtice mais pr�ximo
		while(!fila.empty()){

			int vertice_atual = fila.top().second;
			fila.pop();

			if(!processado[vertice_atual]){         // podemos usar esse v�rtice porque ele ainda n�o foi processado
				vertice_navez = vertice_atual;
				break;
			}

			// se n�o, continuamos procurando
		}

		if(vertice_navez == -1) break;     // se n�o achou ningu�m, � o fim do algoritmo

		processado[vertice_navez] = true; // marcamos para n�o voltar para este v�rtice

		// agora, tentamos atualizar as dist�ncias
		for(int i = 0;i < (int)lista_adj[vertice_navez].size();i++){

			int dist  = lista_adj[vertice_navez][i].first;
			int vertice_atual = lista_adj[vertice_navez][i].second;

			// A nova poss�vel dist�ncia � dist.
			// Comparamos isso com distancia[vertice_atual].
			// Por�m, � importante checar se o v�rtice vertice_atual n�o foi processado ainda

			if( distancia[vertice_atual] > dist && !processado[vertice_atual]){  // vemos que vale a pena usar o v�rtice vertice_navez
				distancia[vertice_atual] = dist;                                 // atualizamos a dist�ncia
				fila.push( par(distancia[vertice_atual], vertice_atual) );       // inserimos na fila de prioridade
			}
		}
	}

	int custo_arvore = 0;
	for(int i = 1;i <= V;i++) custo_arvore += distancia[i];

    cout << "Custo: " << custo_arvore << endl;

    return 0;
}

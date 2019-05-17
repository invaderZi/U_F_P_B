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
    arquivo_de_matriz >> V;             //Lê a primeira linha da matriz que contem o tamanho

    int matriz_adj[V][V] = {0};            //Cria matriz adjacência do tamanho certo
    int n, m;                             // número de vértices e arestas
    int distancia[V];           // o array de distâncias à fonte
    int processado[V];          // o array que guarda se um vértice foi processado
    vector<par> lista_adj[V];    // nossas listas de adjacência. O primeiro elemento do par representa a distância e o segundo representa o vértice

    for (int i = 0; i < V; i++){
        for (int j = i; j < V; j++){
            if (i == j)             //Distância entre um vértice em relação a ele mesmo é 0
                continue;

            arquivo_de_matriz >> matriz_adj[i][j];
            matriz_adj[j][i] = matriz_adj[i][j];
            num_arestas++;
            lista_adj[i].push_back(par(matriz_adj[i][j], j));
            lista_adj[j].push_back(par(matriz_adj[i][j], i));
        }
    }




    for(int i = 2; i <= num_arestas; i++) distancia[i] = INFINITO; // definimos todas as distâncias como infinito, exceto a de S = 1.
    distancia[1] = 0;                                  // Assim, garantimos que o primeiro vértice selecionado será o próprio 1.

	priority_queue< par, vector<par>, greater<par> > fila;      // Criamos uma fila de prioridade onde o menor fica no topo.
	fila.push( par(distancia[1], 1) );                          // Como se pode ver, colocamos o primeiro elemento como seja a distância do
                                                                // vértice a Árvore Geradora Mínima e o segundo como sendo o próprio vértice

	while(true){

		int vertice_navez = -1;

		// selecionamos o vértice mais próximo
		while(!fila.empty()){

			int vertice_atual = fila.top().second;
			fila.pop();

			if(!processado[vertice_atual]){         // podemos usar esse vértice porque ele ainda não foi processado
				vertice_navez = vertice_atual;
				break;
			}

			// se não, continuamos procurando
		}

		if(vertice_navez == -1) break;     // se não achou ninguém, é o fim do algoritmo

		processado[vertice_navez] = true; // marcamos para não voltar para este vértice

		// agora, tentamos atualizar as distâncias
		for(int i = 0;i < (int)lista_adj[vertice_navez].size();i++){

			int dist  = lista_adj[vertice_navez][i].first;
			int vertice_atual = lista_adj[vertice_navez][i].second;

			// A nova possível distância é dist.
			// Comparamos isso com distancia[vertice_atual].
			// Porém, é importante checar se o vértice vertice_atual não foi processado ainda

			if( distancia[vertice_atual] > dist && !processado[vertice_atual]){  // vemos que vale a pena usar o vértice vertice_navez
				distancia[vertice_atual] = dist;                                 // atualizamos a distância
				fila.push( par(distancia[vertice_atual], vertice_atual) );       // inserimos na fila de prioridade
			}
		}
	}

	int custo_arvore = 0;
	for(int i = 1;i <= V;i++) custo_arvore += distancia[i];

    cout << "Custo: " << custo_arvore << endl;

    return 0;
}

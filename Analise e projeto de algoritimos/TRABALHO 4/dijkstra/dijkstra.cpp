

#include <iostream>
#include <list>
#include <queue>

#include <vector>
#include <fstream>
#include <string>
#include <cstdio>
#include <algorithm>


#define INFINITO 10000000



using namespace std;
typedef pair<int, int> par;


class Grafo
{
private:
	int V; // número de vértices
    int tam_matriz;


	// ponteiro para um array contendo as listas de adjacências
	list<pair<int, int> > * adj;
	vector<pair<int,int> > *adjvector;

public:

	// construtor
	Grafo(int V)
	{
		this->V = V; // atribui o número de vértices

		/*
			cria as listas onde cada lista é uma lista de pairs
			onde cada pair é formado pelo vértice destino e o custo
		*/
		adj = new list<pair<int, int> >[V];
        adjvector = new vector<pair<int,int> > [V];

	}

	// adiciona uma aresta ao grafo de v1 à v2
	//void addAresta(int v1, int v2, int custo)
    void addAresta()

	{
		//adj[v1].push_back(make_pair(v2, custo));

     ifstream arq_matriz("instancias_teste/dij10.txt", ios::in);
    //ifstream arq_matriz("instancias_teste/dij20.txt", ios::in);
    //ifstream arq_matriz("instancias_teste/dij40.txt", ios::in);
    //ifstream arq_matriz("instancias_teste/dij50.txt", ios::in);


    this->tam_matriz = V;
    // Quantidade de vertices
    int num_arestas = 0; // Quantidade de arestas

    arq_matriz >> tam_matriz; //Lê a primeira linha da matriz que diz respeito ao tamanho

    int dij[tam_matriz][tam_matriz] = {0}; //Cria matriz adjacência do tamanho certo
    int distancia[tam_matriz];           // o array de distâncias à fonte
    int processado[tam_matriz];          // o array que guarda se um vértice foi processado
    //vector<par> adjvector[tam_matriz];

    for (int i = 0; i < tam_matriz; i++){
        for (int j = i; j < tam_matriz; j++){
            if (i == j) //Distância entre um vértice em relação a ele mesmo é 0
                continue;

            arq_matriz >> dij[i][j];
            dij[j][i] = dij[i][j];
            num_arestas++;
            adjvector[i].push_back(par(dij[i][j], j));
            adjvector[j].push_back(par(dij[i][j], i));
        }
    }
	}

	// algoritmo de Dijkstra
int dijkstra(int orig, int dest)
	{
		// vetor de distâncias
		int dist[V];

		/*
		   vetor de visitados serve para caso o vértice já tenha sido
		   expandido (visitado), não expandir mais
		*/
		int visitados[V];

		// fila de prioridades de pair (distancia, vértice)
		priority_queue < pair<int, int>,
					   vector<pair<int, int> >, greater<pair<int, int> > > pq;

		// inicia o vetor de distâncias e visitados
		for(int i = 0; i < V; i++)
		{
			dist[i] = INFINITO;
			visitados[i] = false;
		}

		// a distância de orig para orig é 0
		dist[orig] = 0;

		// insere na fila
		pq.push(make_pair(dist[orig], orig));

		// loop do algoritmo
		while(!pq.empty())
		{
			pair<int, int> p = pq.top(); // extrai o pair do topo
			int u = p.second; // obtém o vértice do pair
			pq.pop(); // remove da fila

			// verifica se o vértice não foi expandido
			if(visitados[u] == false)
			{
				// marca como visitado
				visitados[u] = true;

				list<pair<int, int> >::iterator it;

				// percorre os vértices "v" adjacentes de "u"
				//for(it = adj[u].begin(); it != adj[u].end(); it++)
               // for(it = adjvector[u].begin(); it != adjvector[u].end(); it++)
                for(int i = 0;i < (int)adjvector[u].size();i++)


				{
					// obtém o vértice adjacente e o custo da aresta
					//int v = it->first;
					//int custo_aresta = it->second;
					int v  = adjvector[u][i].first;
                    int custo_aresta = adjvector[u][i].second;

					// relaxamento (u, v)
					if(dist[v] > (dist[u] + custo_aresta))
					{
						// atualiza a distância de "v" e insere na fila
						dist[v] = dist[u] + custo_aresta;
						pq.push(make_pair(dist[v], v));
					}
				}
			}
		}

		// retorna a distância mínima até o destino
		return dist[dest];
	}
};

int main(int argc, char *argv[])
{
	Grafo g(5);
	g.addAresta();

	/*g.addAresta(0, 1, 4);
	g.addAresta(0, 2, 2);
	g.addAresta(0, 3, 5);
	g.addAresta(1, 4, 1);
	g.addAresta(2, 1, 1);
	g.addAresta(2, 3, 2);
	g.addAresta(2, 4, 1);
	g.addAresta(3, 4, 1);*/

	cout << g.dijkstra(0, 8) << endl;

	return 0;
}

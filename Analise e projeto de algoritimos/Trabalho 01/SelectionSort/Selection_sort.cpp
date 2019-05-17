/*Universidade Federal Da Paraiba
2018.1
Aluno: Sabrina Alicrim Silva
Matricula : 2016022764

Atividade 01 APA- selection sort

*/

#include <iostream>
#include <stdlib.h>
#include <stdio.h>
#include <fstream>

using namespace std;

///assinaturas dos metodos

void selection_sort(int arrayNum[],int tam);

int main(){

  std::string arquivoEntrada;
	int array[100000];
	int linhas = 0;

	std::cout << "Digite o nome do arquivo de entrada seguindo o exemplo : exemplo.txt  :  " << std::endl;
	std::cin >> arquivoEntrada;

	 std::ifstream arquivo;
	 arquivo.open(arquivoEntrada);
	 char output[1000];
	 if (arquivo.is_open()) {
	 while (!arquivo.eof()) {
	    arquivo >> output;
	    array[linhas] = atoi(output);
	   	linhas++;
	 }
	}
	arquivo.close();

    selection_sort(array, linhas);


return 0;
}

///metodos

void selection_sort(int arrayNum[],int tam){
    int tmp,menor;
    for(int i=0;i<tam;i++)
    {
        menor = i;
        for(int j=i+1;j<tam;j++)
        {
            if(arrayNum[j]<arrayNum[menor])
            {
                menor = j;
            }
        }
        tmp = arrayNum[i];
        arrayNum[i] = arrayNum[menor];
        arrayNum[menor] = tmp;
    }

        std::cout<< "\n### ENTRADA ORDENADA  :  ###"<< "\n\n";

        for (int i = 0; i < tam; ++i)
        {
            std :: cout << "|"<< arrayNum[i];
        }

}

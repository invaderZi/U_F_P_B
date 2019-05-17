/*Universidade Federal Da Paraiba
2018.1
Aluno: Sabrina Alicrim Silva
Matricula : 2016022764

Atividade 01 APA- insertion sort

*/


#include <iostream>
#include <stdlib.h>
#include <fstream>

///assinatura dos metodos
void InsertionSort(int arrayNum[],int tam);


int main(){
    std::string arquivoEntrada;
	int array[100000];
	int linhas = 0;

	std::cout << "Digite o nome do arquivo de entrada seguindo o exemplo : exemplo.txt  : " << std::endl;
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

    InsertionSort(array, linhas);


return 0;
}

///metodos

void InsertionSort(int arrayNum[],int tam){

   for(int i=1;i<tam;i++)
       {
            int temp = arrayNum[i];
            int j=i-1;

                while((j>=0)&&(arrayNum[j]>temp))
                {
                    arrayNum[j+1] = arrayNum [j];
                    j--;
                }
                arrayNum[j+1]=temp;

        }
            std::cout<< "\n### ENTRADA ORDENADA  :  ###"<< "\n\n";

    for (int i = 0; i < tam; ++i)
        {

            std :: cout << "|"<< arrayNum[i];
        }

}

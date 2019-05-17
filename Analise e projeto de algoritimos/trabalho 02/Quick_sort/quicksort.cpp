/*Universidade Federal Da Paraiba
2018.1
Aluno: Sabrina Alicrim Silva
Matricula : 2016022764
Atividade 02 APA- Quick sort
*/


#include <iostream>
#include <fstream>
#include <stdlib.h>

using namespace std;

void imprime(int array[], int tam){

	for (int i = 0; i < tam; ++i)
	{
		std :: cout << "|" << array[i];

	}

}

void quicksort(int *arr, int esq, int dir){
    int min = ((esq+dir)/2);
    int i = esq, j = dir;
    int pivo = arr[min];
    int aux;
    while(esq<j || i<dir) {
        while(arr[i]<pivo) i++;
        while(arr[j]>pivo) j--;

        if(i<=j){
            aux = arr[i];
		    arr[i] = arr[j];
		    arr[j] = aux;
            i++;
            j--;
        }
        else{
            if(esq<j)
                quicksort(arr, esq, j);
            if(i<dir)
                quicksort(arr,i,dir);
            return;
        }
    }
}

int main() {

	char arquivodeentrada[20];
	cout << "Digite o nome do arquivo de entrada seguindo o exemplo : exemplo.txt  : "  << endl;
	cin >> arquivodeentrada;

	int array[100000];
	int linhas = 0;

	 ifstream myReadFile;
	 myReadFile.open(arquivodeentrada);
	 char output[1000];
	 if (myReadFile.is_open()) {
	 while (!myReadFile.eof()) {

	    myReadFile >> output;
	    array[linhas] = atoi(output);
	   	linhas++;

	 }
	}
	myReadFile.close();

	quicksort(array, 0, linhas-1);
	imprime(array, linhas);
	return 0;
}

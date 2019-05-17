/*Universidade Federal Da Paraiba
2018.1
Aluno: Sabrina Alicrim Silva
Matricula : 2016022764
Atividade 03 APA- heapsort
*/


#include <iostream>
#include <fstream>
#include <stdlib.h>
using namespace std;

void ImprimeResultado(int array[], int tam){

	for (int i = 0; i < tam; ++i)
	{
	std :: cout << "|" << array[i];
		}

}


int FilhoEsquerdo(int i)
{
    return 2 * i + 1;
}

int FilhoDireito(int i)
{
    return 2 * i + 2;
}

int Pai(int i)
{
    return (i / 2) - 1;
}

void maxHeapify(int arr[], int tam, int i)
{
   int maior = i;
   int filhoesquerdo = FilhoEsquerdo(i);
   int filhodireito = FilhoDireito(i);

   if (filhoesquerdo < tam && arr[filhoesquerdo] > arr[maior])
     maior = filhoesquerdo;

   if (filhodireito < tam && arr[filhodireito] > arr[maior])
     maior = filhodireito;

   if (maior != i)
   {
     swap(arr[i], arr[maior]);
     maxHeapify(arr, tam, maior);
   }
}

void buildMaxHeap(int arr[], int tam){

for (int i = Pai(tam); i >= 0; i--)
     maxHeapify(arr, tam, i);

}

void heapSort(int arr[], int tam)
{
    buildMaxHeap(arr,tam);

   for (int i=tam-1; i>=0; i--)
   {
     swap(arr[0], arr[i]);
        tam--;

     maxHeapify(arr, i, 0);
   }
}




int main() {

	char arquivodeentrada[20];
	cout << "Digite o nome do arquivo de entrada seguindo o exemplo : exemplo.txt  : " << endl;
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
	heapSort(array, linhas);
	ImprimeResultado(array, linhas);
	return 0;
}

/*Universidade Federal Da Paraiba
2018.1
Aluno: Sabrina Alicrim Silva
Matricula : 2016022764
Atividade 03 APA- couting sort com numeros negativos
*/


#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>

using namespace std;

void ImprimeResultado(vector <int> & array){

	for (int i = 0; i < array.size(); ++i)
        cout << array[i] << " ";

    cout << "\n";
}

void countSort(vector <int>& array)
{
    int max = *max_element(array.begin(), array.end()); //estabelece o maior valor dentro do vetor
    int min = *min_element(array.begin(), array.end()); // estabelece o menor valor dentro do vetor
    int range = max - min + 1;          // calcula o alcance do  vetor com tods as posicoes do menor ao maior numero

   // cout<< max<<"\n"<<min<<"\n"<<range<<"\n\n";

    vector<int> count(range+1), arrayresultado(array.size()); // vector ja inicializa os indices com 0

     for(int i = 0; i < array.size(); i++)
        count[array[i]-min]++;  //  contagem dos caracteres (- min para que o menor valor possivel  seja sempre 0)

    for(int i = 1; i < count.size(); i++)  // Altera a count [i] para que count [i] agora contenha a posição real desse caractere no array de saida
           count[i] += count[i-1];

    for(int i = array.size()-1; i >= 0; i--)
    {
         arrayresultado[ count[array[i]-min] -1 ] = array[i]; //copia os elementos de array no araayresultado na ordem correta
              count[array[i]-min]--;                           //incrementa count para que os elementos com mesmo valor do elemento atual sejam mantidos em suas posicoes
    }

    for(int i=0; i < array.size(); i++)
            array[i] = arrayresultado[i];       //copia os elementos organizados de arrayresultado para o array original

}

int main() {

	char arquivodeentrada[20];
	cout << "Digite o arquivo de entrada, exemplo : arquivo.txt " << endl;
	cin >> arquivodeentrada;
	vector<int> array;

	 ifstream myReadFile;
	 myReadFile.open(arquivodeentrada);
	 char output[1000];
	 if (myReadFile.is_open()) {
	 while (!myReadFile.eof()) {

	    myReadFile >> output;
        array.push_back(atoi(output));
	 }
	}
        myReadFile.close();


    countSort (array);
    ImprimeResultado(array);


	return 0;
}

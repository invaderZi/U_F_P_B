package tsp2;
import java.io.*;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import static java.lang.Math.sqrt;
import static tsp2.Tsp2.CustoCalcular;
import java.util.ArrayList;


public class Tsp2 {
////////////////////// colcoar nome do arquivo de testes aqui /////////////
            static String pathCup = "teste3.txt";  ///////////alterar essa linha para modificar o arquivo cup
            static int[] totalCusto = null;
            static String pathTestes = "ch150.txt"; //modificar de acordo com o arquivo de testes  
            static int[][] matrizarqtestes;        
            static int verticesdoteste; //modificar de acordo com o arquivo de testes  (usado no grasp no arquivo ta ok)


public static void main(String[] args) throws FileNotFoundException {
    
    
        ////////////// declatacoes e inicializacoes 
        matrizarqtestes = Matrisarqtestes(); 
        PersistenciaArquivo arquivo = new PersistenciaArquivo();
        arquivo.leitura(pathCup);
        ArrayList<Float> pos_x = arquivo.x;
        ArrayList<Float> pos_y = arquivo.y;
        ArrayList<Integer> vertices = arquivo.v;
        MatrizDeAdj matrizAdj = new MatrizDeAdj();
        BuscaLocal vndObj = new BuscaLocal();
        GRASP graspObj = new GRASP();
        int[][] mAdjacencia;
        
        
              // para utilizar arquivos tspcup (descomentar e comentar abaixo)
              //mAdjacencia= matrizAdj.matriz(vertices, pos_x, pos_y);
        
            // para utilizar arquivos de teste (descomentar e comentar acima)
                    mAdjacencia = matrizarqtestes.clone(); 
        
        

                    //Contrução da Heuristica

                    System.out.println("##### Heurística de construcao: ######\n");
                    int[] verticesSolucaoKnn = HeuristicaKNN.vMaisProximo(mAdjacencia, 0);
                    int custoSolucaoKNN = CustoCalcular(mAdjacencia, verticesSolucaoKnn);
                       //vizinho mais proximo
                    System.out.println("/////////  KNN: \n" + PrintSolucao(verticesSolucaoKnn) + "0");
                    System.out.println(" Custo inicial KNN: --->>  " + custoSolucaoKNN);   
                    int valorRodada = 1;
                    
                    
////////////////////////////////// loop de execucao com menu

        while (true) {
            
                 System.out.println(" rodada de numero : " + valorRodada);
                     valorRodada++;
                          

                  
            String menu = Menu();
            
            switch (menu) {
                case "0":   
                    
                    //vnd
                    int[] verticesSolucaoVND = vndObj.VND(custoSolucaoKNN, mAdjacencia, verticesSolucaoKnn);
                    int custoSolucaoVND = CustoCalcular(mAdjacencia, verticesSolucaoVND);

                    System.out.println("//////// VND: \n" + PrintSolucao(verticesSolucaoVND) + "0"); // chama vnd passando matriz e flag 0
                    System.out.println(" Custo VND: --->>  " + custoSolucaoVND + "\n");
                    
                    break;

                case "1": 
                    
                    int custoSolucaoGRASP = 0;
 
                    //int[] melhorSolucaoDoGRASP = new int[vertices.size()];
                    //int[] auxGRASP = new int[vertices.size()];
                    int[] melhorSolucaoDoGRASP = new int[verticesdoteste];
                    int[] auxGRASP = new int[verticesdoteste];
                    int custoFinalGRASP = Integer.MAX_VALUE;
                    
                    
                    //##################Construção da Meta-Heuristica #################################

                    System.out.println(" #### META - Heurística ######");

                    for (int i = 0; i < 20; i++) {

                        //auxGRASP = graspObj.SolGulosaRandonGRASP(matrizarqtestes, alpha, auxGRASP, vertices.size()); // construcao original restrita
                        auxGRASP = graspObj.SolGulosaRandonGRASP(mAdjacencia, verticesSolucaoKnn, verticesSolucaoKnn.length); // recebe mat adj original, alpha, array auxsolucao vazio e tanmanho da matriz original
                        custoSolucaoGRASP = CustoCalcular(mAdjacencia, auxGRASP);                 // custo original restrito

                        auxGRASP = vndObj.VND(custoSolucaoGRASP, mAdjacencia, auxGRASP); // vnd para melhorar construcao restrita
                        custoSolucaoGRASP = CustoCalcular(mAdjacencia, auxGRASP);           // custo melhorado da solucao restrito

                        if (custoSolucaoGRASP < custoFinalGRASP) {                          // compara se é o melhor (menor) custo gerado
                                                                                            // se for
                            melhorSolucaoDoGRASP = auxGRASP.clone();                        // melhor solucao é a calculada
                            custoFinalGRASP = custoSolucaoGRASP;                           //  melhor custo final é o calculado
                            
                        }   
                         
       
                    }

                    System.out.println("\n\n  ////// GRASP FINAL : \n" + PrintSolucao(melhorSolucaoDoGRASP) + "0"); //  sai do loop com a melhor solucao encontrada
                    System.out.println(" custo  GRASP FINAL :  --->> " + custoFinalGRASP + "\n");                   // calcula o custo da solucao encontrada no fim das iteracoes

                    break;

                case "2":
                    System.exit(0);
                    break;

                default:

                    System.out.printf("Inválido");

                    break;

            }

        }

    }
public static String Menu (){ 
            String menu;
            System.out.println(" (clique aqui)\nopcao  0 - RODAR VND NA SOLUCAO KNN \n opcao 1 - Meta-Heuristica GRASP NA SOLUCAO KNN \n opcao 2 - finalizar \n");
            //leitura  de opcoes do usuario
            Scanner ler = new Scanner(System.in);
            menu  = ler.nextLine();
            
        return menu;    
}
public static int CustoCalcular(int[][] Matriz, int[] listaSolucoes) {

                int i;
                int custo = 0;

                for (i = 0; i < listaSolucoes.length - 1; i++) {

                    custo += Matriz[listaSolucoes[i]][listaSolucoes[i + 1]];
                }

                custo += Matriz[listaSolucoes[i]][0];

                return custo;
            }
public static String PrintSolucao(int[] solucaoVertices) {
        
                    String arraySolucao = "";
                    for (int i = 0; i < solucaoVertices.length; i++) {
                        arraySolucao += String.valueOf(solucaoVertices[i]) + ", ";

                    }

                    return arraySolucao;
    }
public static int [][] Matrisarqtestes() throws FileNotFoundException {       
                 String vert; //salvar quantidade de vertices
		//lendo o arquivo e criando a matriz
				
		File arquivo = new File(pathTestes);
		Scanner scanarquivo = null;
		scanarquivo = new Scanner(arquivo);
		scanarquivo.nextLine(); //pula uma linha
		
		vert = scanarquivo.nextLine(); //pega a linha que contem as dimensoes
		int vertq = Integer.parseInt(vert.replaceAll("[\\D]", "")); //pega apenas os numeros da linha
		scanarquivo.nextLine(); //pula linha
		int[][] matriz = new int[vertq][vertq]; //cria matriz do tamanho da quantidade dos v�rtices
		//preenche a matriz
		ArrayList<Integer> valores = new ArrayList<Integer>();
		while(scanarquivo.hasNextInt()){
			valores.add(scanarquivo.nextInt());	
		}
		int tt = 0;
		for (int i = 0; i < vertq; i++) { //linhas
			for (int j = 0; j < vertq; j++) { //colunas
				matriz[i][j] = valores.get(tt);
				tt++;
			}
		}
		scanarquivo.close();
   verticesdoteste = vertq;
   return matriz;  //retorna matriz 
    }
}

//////////////////////////////////////////////////////////////////////////////////////////
class PersistenciaArquivo {
    
    // tratamento de arquivos da tspcup ( nao funciona com o cabeçalho)
    
    String thisLine = null;
    ArrayList<Integer> v = new ArrayList<>(); //armazena os vertices
    ArrayList<Float> x = new ArrayList<>(); //armazena a coord. x
    ArrayList<Float> y = new ArrayList<>(); //armazena a cood. y
    String[] separacaoString = new String[2];
    public void leitura(String caminho) {
        try {
            BufferedReader bufferleitor = new BufferedReader(new FileReader(caminho));

            while ((thisLine = bufferleitor.readLine()) != null) {
                thisLine = thisLine.trim(); //remove o espaço em branco do inicio
                separacaoString = thisLine.split("   "); //separa os itens do arquivo de acordo com o separador de 3 espaços

                int i = 0;

                for (String element : separacaoString) {
                    switch (i) {
                        case 0:
                            v.add(Integer.parseInt(element));
                            break;
                        case 1:
                            x.add(Float.parseFloat(element));
                            break;
                        case 2:
                            y.add(Float.parseFloat(element));
                            break;
                    }
                    i++;
                }
            }

            bufferleitor.close();
        } catch (IOException | NumberFormatException e) {
        }

    }
}

/////////////////////////////////////////////////////////////////////////////////////////////

class MatrizDeAdj {
    
    //criar matriz de adj n com arquivos tspcup

    public static float Arestas(float x1, float x2, float y1, float y2) {

        float aresta = 0;
        float x;
        float y;
        x = x1 - x2;
        y = y1 - y2;
        aresta = (float) sqrt(Math.pow(x, 2) + Math.pow(y, 2)); // distancia euclidiana raiz (x1-x2)²+(y1-y2)²
        return aresta;
    }

    public int[][] matriz(ArrayList<Integer> vertices, ArrayList<Float> pos_x, ArrayList<Float> pos_y) throws FileNotFoundException {
            // recebe lista de vertices, posicoes x e posicoes y
        int[][] matriz = new int[vertices.size()][vertices.size()];

        for (int i = 0; i < vertices.size(); i++) { // for para linhas
            for (int j = 0; j < vertices.size(); j++) {// for para colunas
                if (matriz[i][j] == 0) { //se m [0][0] = 0 
                    if (vertices.get(i) >= vertices.size()) { // se a posicao do vertice for o tamanho do vetor ele termina
                        break; //sai
                    }
                    if (vertices.get(i) == vertices.get(j)) { //se linha e coluna forem iguais
                        matriz[i][j] = 0; // preenche diagonal com zero
                    } else if (vertices.get(i) != vertices.get(j)) { // se os indices nao forem iguais
                        float valoraresta = Arestas(pos_x.get(i), pos_x.get(j), pos_y.get(i), pos_y.get(j)); // calcula valor da aresta
                        valoraresta = Math.round(valoraresta); // arrendonda float para salvar valor arredondado inteiro, salvando o resultado inteiro
                        // talvez substituir para funcao que extrai valor inteiro da variavel e despreza o ponto flutuante
                        matriz[i][j] = (int) valoraresta; // atribui o valor ao resultado ( verificar esse int aqui, desnecvessario)
                        if (matriz[i][j] != matriz[j][i]) { // se os indices inversos tiverem valores diferentee
                            matriz[j][i] = (int) valoraresta; //espelha o de baixo ( i, j) = (j,i) 
                        }
                    }
                }

            }
        }

        return matriz; // retorna matriz espelhada
    }
}

///////////////////////////////////////////////////////////////////
class HeuristicaKNN {
 
public static int[] solucaoKNN; // declaracao do array do melhor caminho

public static int[] vMaisProximo(int[][] Matriz, int origem) { // recebe a matriz e o valor 0 como sendo origem  

        solucaoKNN = new int[Matriz[0].length]; //inicia o array, com 1500 vertices (mesmo tamanho da linha 0 da matriz)

        //inicia a o array com infinito
        for (int i = 0; i < solucaoKNN.length; i++) {
            solucaoKNN[i] = Integer.MAX_VALUE;  // inicia com os maiores valores possiveis, pois vamos procurar pelos menores

        }

        solucaoKNN[0] = origem; //primira posicao do array é setaca como  o inicio escolhido para a solucao
        
        int vAtual = origem;  //vertice atual

        int i = 1;
        

    //Enquanto não houver cidade a serem visitadas
        while (i < solucaoKNN.length) {  // de i = 1 ate i = tamanho vetor solucao ( nao percorre o 0 pois a origem ja foi adicionada a solucao)
            // encontro a próxima cidade
            int proximaCidade = FoundMinimo(Matriz[vAtual]); // encontro menor valor na proxima linha ( da ultima cidade adicionada na solucao)
                                                        // ignorando os 0 e os espelhos pela funcao verificaseaacidade
               
            if (proximaCidade != -1) {   // se a cidade for diferente de - 1 
                                    //(significa que a mesma não foi visitada) ( verificar funcao foundminima)
                
                solucaoKNN[i] = proximaCidade; // adiciono a cidade ao array
                // atualizo a cidade atual e o contador
                vAtual = proximaCidade;

                i++;
            }

        }

        return solucaoKNN;
    }

 public static int FoundMinimo(int[] linha) {

        int proxCidade = -1; // define proxima cidade como -1 para marcar que nao foi escolhida
        int i = 0;
        int minimo = Integer.MAX_VALUE;    //valor minimo é  infinito

        while (i < linha.length) {  //do inicio ao fim da linha

            if (!verificaSeCidadeEsta(solucaoKNN, i) && linha[i] < minimo) { // se a cidade nao esta na solucao, e o valor é menor que o minimo atual
                minimo = linha[i];      // novo valor minimo é o valor atual (menor distancia encontrada)
                proxCidade = i;         // nova cidade é a de menor distancia 
            }

            i++;

        }

        return proxCidade;
    }

//verifica se a cidade está no array (int[] solucaoKNN)
 public static boolean verificaSeCidadeEsta(int[] solucao, int cidade) {
        for (int i = 0; i < solucao.length; i++) { // verifica se proxima cidade a ser visitada ja esta no array de solucao
            if (solucao[i] == cidade) {
                return true;  // se estiver retorna true
            }
        }
        return false;       // se nao, retorna false
    }

}
////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////
class BuscaLocal {
 
    int[] listaSolucaoswap;
    int[] melhorSolucaoVND;
    
    

    public static int[] Reinsertion(int[][] matriz, int[] solucao, int valorsolparam, int tamanhosolucao ) {
   
		
		int melhorvalor = valorsolparam;
		int auxsolucaovalor = Integer.MAX_VALUE;
		int i, j;
		int node1 = 0, node2 = 0;
		i = j = 1;
		int valor = 0;
		int rnode = 0;

		tamanhosolucao--;
                for (i=1;i <tamanhosolucao; i++){
			
                        for(j=i; j<tamanhosolucao; j++){
				if(i == j || i == j-1){
					j++;
					continue;
				}
				 auxsolucaovalor = valorsolparam 
						- matriz[solucao[i-1]][solucao[i]] //aresta até o vertice 
						- matriz[solucao[i]][solucao[i+1]]  //aresta do vertice até o próximo
						- matriz[solucao[j]][solucao[j+1]] // 
						+ matriz[solucao[i-1]][solucao[i+1]]
						+ matriz[solucao[j]][solucao[i]]
						+ matriz[solucao[i]][solucao[j+1]];
				
				if(auxsolucaovalor < melhorvalor){
					
					node1 = i;
					node2 = j;
					melhorvalor = auxsolucaovalor;
				}
				
			}
			j = 1;
			
		}
		
		
		

		if(melhorvalor < valorsolparam){
			rnode = solucao[node1];
			if(node1 < node2){
				for(i = node1; i <= node2; i++){
					if(i == node2){
						solucao[i] = rnode;
						break;
					}
					solucao[i] = solucao[i+1];
				}
			}else{
				for(i = node1; i >= node2; i--){
					if(i == node2){
						solucao[i] = rnode;
						break;
					}
					solucao[i] = solucao[i-1];
				}
			}
			
			
		}	

        return solucao;  
    }
	


    
    
    public int[] SWAP(int[][] MatrizParametro, int[] solucaoParametro, int custoparametro) {

        int[] melhorSolucaoSWAP = solucaoParametro.clone();
        int novoCustoSWAP;
        int[] auxSolucao;

        for (int i = 1; i < melhorSolucaoSWAP.length - 1; i++) {

            auxSolucao = melhorSolucaoSWAP.clone();

            for (int j = i + 1; j < melhorSolucaoSWAP.length; j++) {

                int aux = auxSolucao[i];
                auxSolucao[i] = auxSolucao[j];
                auxSolucao[j] = aux;

                novoCustoSWAP = CustoCalcular(MatrizParametro, auxSolucao);

                if (novoCustoSWAP < custoparametro) {
                    custoparametro = novoCustoSWAP;
                    melhorSolucaoSWAP = auxSolucao.clone();

                }
            }
        }

        return melhorSolucaoSWAP;
    }

    public int[] VND(int custoparametro, int[][] MatrizParametro, int[] listaSolucaoParametro) {

        int[] listaSolucaoReinsertion = listaSolucaoParametro.clone();
        int[] auxSolucaoReinsertion = listaSolucaoReinsertion.clone();
        int k, r;
        r=2;
        k=1;
        int custoinicial = custoparametro;
        int novocusto;
        int custoswap;
        while (true){
        
           while (k < r) { // k < r sendo r numero de movimentos de vizinhanca
            
            if (k==1) { //k = 1 primeiro movimento
                
                listaSolucaoReinsertion = Reinsertion(MatrizParametro, auxSolucaoReinsertion, custoinicial, auxSolucaoReinsertion.length); // recebe array do reinsertion 
                novocusto = CustoCalcular(MatrizParametro, listaSolucaoReinsertion);                     // recebe custo do reinsertinon
               System.out.println(" INICIAL VND ");
               
                     if (novocusto < custoinicial) { 
                                
                                melhorSolucaoVND = listaSolucaoReinsertion.clone();     // seta melhor valor atual
                                custoinicial = novocusto; 
                                 k = 1;   
                               System.out.println(" REINSERTION MELHOROU ");

                       }   
                         else {   System.out.println(" REINSERTION NAAAO MELHOROU ");
                            
                            k =k + 1;
                            melhorSolucaoVND = auxSolucaoReinsertion.clone();
                            
                            listaSolucaoswap = melhorSolucaoVND.clone();  //clona melhor solucao ate o momento quando saia do reinsertion
                            listaSolucaoswap = SWAP(MatrizParametro, listaSolucaoswap, custoinicial); //mesma logica anteror, usando swap
                            custoswap = CustoCalcular(MatrizParametro, listaSolucaoswap);
                            System.out.println(" FIZ SWAP ");  
                                       if (custoswap < custoinicial) { 
                                    System.out.println(" SWAP MELHOROU ");
                                            auxSolucaoReinsertion = listaSolucaoswap.clone();
                                            custoinicial = custoswap;
                                            melhorSolucaoVND = listaSolucaoswap.clone();
                                            k=1;
                                          } else {System.out.println(" swap nao melhorou ");}
                        } System.out.println(" SAI DO SWAP ");
            }           
        }System.out.println(" SAI DO WHILE V<R ");// fim while        
           
     break;  
     } // fim wilhe true
 
  return melhorSolucaoVND;
} // fim vnd

} // fim buscalocal



/////////////////////////////////////////////////////////////////

    class GRASP {

    public boolean VerificaCidade(int[] solucaoParametro, int cidade) {
        for (int i = 0; i < solucaoParametro.length; i++) {
            if (solucaoParametro[i] == cidade) {
                return true;
            }
        }
        return false;
    }

    public int BuscaMaior(int[][] Matriz, int[] solucaoParametro, int origem, int numVertices) {
        int maior = 0;

        for (int fim = 0; fim < numVertices; fim++) {

            if ((Matriz[origem][fim]) != 0 && Matriz[origem][fim] > maior && (!VerificaCidade(solucaoParametro, fim))) {
                maior = Matriz[origem][fim];
            }
        }

        return maior;
    }

    public int BuscaMenor(int[][] Matriz, int[] solucaoParametro, int origem, int numVertices) {

        int menor = Integer.MAX_VALUE;

        for (int fim = 0; fim < numVertices; fim++) {

            if (((Matriz[origem][fim]) != 0) && (Matriz[origem][fim]) < menor && ((!VerificaCidade(solucaoParametro, fim)))) {
                menor = Matriz[origem][fim];

            }
        }

        return menor;
    }

    public int[] SolGulosaRandonGRASP(int[][] MatrizParametro, int[] solucaoParametro, int numerodevertices) {

        Random aleatorio = new Random();
        float alpha = 0.10f;

        int inicioLinhaCidade = 0;
        int[] auxSolucao = new int[solucaoParametro.length]; // cria array solucao auxiliar para realizar os calculos do mesmo tam da matriz original
        int[] listaIndicesLCR = new int[auxSolucao.length]; // cria lista de candidatos restriros inicialmente com o mesmo tamanho da lista original

        int maiorDistancia; 
        int menorDistancia;
        float lcrLimitesuperior; 
        int indAleatorio;

        for (int i = 1; i <= numerodevertices - 1; i++) {  // do indice 1 ate o final da lista -1 ( pois a cada iteracao termos um vertice a menos para analisar)
            
            auxSolucao[0] = 0; // origem inicial 0
            
            menorDistancia = BuscaMenor(MatrizParametro, auxSolucao, inicioLinhaCidade, numerodevertices); // busca menor dentro da matriz, e verifica se este vertice esta em aux
            maiorDistancia = BuscaMaior(MatrizParametro, auxSolucao, inicioLinhaCidade, numerodevertices); // busca maior dentro da matriz, e verifica se este vertice esta em aux

            lcrLimitesuperior = (menorDistancia + ((maiorDistancia - menorDistancia) * alpha)); // restringe o tamanho da lista lcr ate o maximo tamanho do limite superior
            for (int j = 0; j < numerodevertices; j++) { // do inicio da lista solucao ate o numero total de vertices
                for (int cidadefinalColuna = 0; cidadefinalColuna < numerodevertices; cidadefinalColuna++) {// do inicio da linha DEFINIDA ate o numero total de vertices nessa linha
                    
                    if (MatrizParametro[inicioLinhaCidade][cidadefinalColuna] <= lcrLimitesuperior && MatrizParametro[inicioLinhaCidade][cidadefinalColuna] != 0 && (!VerificaCidade(auxSolucao, cidadefinalColuna))) {
                        listaIndicesLCR[j] = cidadefinalColuna; //adiciona cidade a lista de elementos restritos
                    }

                    indAleatorio = aleatorio.nextInt(listaIndicesLCR.length); // sorteia um indice aleatorio da lista

                    inicioLinhaCidade = listaIndicesLCR[indAleatorio]; // define o inicio da nova construcao como sendo o indice aleatorio gerado acima

                    if (!VerificaCidade(auxSolucao, inicioLinhaCidade)) { // verifica se a nova origem esta na solucao, se nao estiver
                        auxSolucao[i] = inicioLinhaCidade; // se nao estiver adiciona a lista de solucao sendo construida
                    }

                }
            }
        }

        return auxSolucao; // retorna a solucao restrita final (array de vertices)
    }
}

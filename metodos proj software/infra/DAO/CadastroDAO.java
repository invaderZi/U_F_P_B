/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jobufpb3.infra.DAO;

import jobufpb3.business.model.Usuario;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import jobufpb3.business.exceptions.ArquivoNaoEncontrado;

/**
 *
 * @author ziaod
 */
public class CadastroDAO {
    

public void Salvar(ArrayList<Usuario> lista) throws ArquivoNaoEncontrado {
			
		try {

            File file = new File("UsuariosCadastrados.dat");
				
            FileOutputStream saveFile = new FileOutputStream(file);

            ObjectOutputStream stream = new ObjectOutputStream(saveFile);

            stream.writeObject(lista);

            stream.close();

         } catch (Exception e) {

        	  throw new ArquivoNaoEncontrado("Arquivo nao encontrado");

          }	
	}
		
	public ArrayList<Usuario> Carregar() throws ArquivoNaoEncontrado{
			
       try {
            File file = new File("UsuariosCadastrados.dat");
	            
            ObjectInputStream input = new ObjectInputStream(new FileInputStream(file));
	            
            ArrayList<Usuario> listaDeUsuarios = (ArrayList<Usuario>) input.readObject();
	            
            input.close();
            
            return listaDeUsuarios;
		}
		catch(Exception e){
            throw new ArquivoNaoEncontrado("Arquivo nao encontrado");
		}
	
        }}

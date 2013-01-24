package br.com.unb.sece.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Serializacao {
	
	public Serializacao(Object ob, String nomeAruivo) throws IOException,Exception{
				// Cria��o de um arquivo de sa�da.
				FileOutputStream fos = new	FileOutputStream(nomeAruivo);
				// Cria��o de objeto Stream que permitir� a grava��o do objeto no arquivo de sa�da.
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				// Objeto � efetivamente gravado no arquivo.
				oos.writeObject(ob);
				oos.close();
	}
}

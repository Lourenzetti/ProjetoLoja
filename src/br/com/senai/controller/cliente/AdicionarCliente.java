package br.com.senai.controller.cliente;

import java.util.Scanner;

public class AdicionarCliente {

	Scanner entrada = new Scanner(System.in);
	
	public String definirCliente() {
		
		System.out.println("Informe o nome do Cliente: ");
		String nome = entrada.nextLine();
		
		return nome;
	}
	
}

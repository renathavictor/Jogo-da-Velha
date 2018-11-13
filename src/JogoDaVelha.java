import java.io.File;
import java.io.FileWriter;
import java.util.Random;
import java.io.IOException;

public class JogoDaVelha {
	private int[][] tabuleiro = new int[3][3];
	private String nomeJogador1;
	private String nomeJogador2;
	private int linha;
	private int coluna;
	private String historico = "";
	private int resultado;
	private int ultimoJogador;
	private int[] sorteio = {0, 1, 2};
	private FileWriter arquivo;
	
	public JogoDaVelha(String nomeJogador1, String nomeJogador2) {
		this.nomeJogador1 = nomeJogador1;
		this.nomeJogador2 = nomeJogador2;
		try {
			arquivo = new FileWriter(new File("jogo.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		historico += "Inicio da partida!\n Jogando: " + getNomeJogador(1) + " contra " + getNomeJogador(2) + "\n";
		historico += "\n";
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				tabuleiro[i][j] = 0;
			}
		}
	}
	
	public JogoDaVelha(String nomeJogador1) {
		this.nomeJogador1 = nomeJogador1;
		this.nomeJogador2 = "Maquina";
		try {
			arquivo = new FileWriter(new File("jogo.txt"));
			historico += "Inicio da partida!\n\n Jogando: " + getNomeJogador(1) + " contra " + getNomeJogador(2) + "\n";
			
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					tabuleiro[i][j] = 0;
				}
			}
		}
		catch(IOException e) {
			System.out.println("arquivo nao pode ser gravado");
			System.exit(0);     
		}
	}
	
	public boolean jogarJogador(int numeroJogador, int l, int c) {
		if (tabuleiro[l][c] == 0) {
			this.linha = l;
			this.coluna = c;
			if (numeroJogador == 1) {
				tabuleiro[l][c] = 1;
				historico += "\n O jogador " + nomeJogador1 + " jogou na posição: " + l + "-" + c;
				ultimoJogador = numeroJogador;
				return true;				
			}
			else {
				tabuleiro[l][c] = 2;
				historico += "\n O jogador " + nomeJogador2 + " jogou na posição: " + l + "-" + c;
				ultimoJogador = numeroJogador;
				return true;
			}
		} 
		else {
			return false;
		}
	}
	
	public void jogarMaquina() { 
		do {
			Random random = new Random();
			linha = sorteio[random.nextInt(sorteio.length)];
			coluna = sorteio[random.nextInt(sorteio.length)];
		} while (tabuleiro[linha][coluna] != 0);
		tabuleiro[linha][coluna] = 2;
		historico += "\n O jogador " + nomeJogador2 + " jogou na posição: " + linha + ", " + coluna + "\n";
		ultimoJogador = 2;		
	}
	
	public boolean terminou() { 
		printBoard();
		if (tabuleiro[0][0] != 0 && tabuleiro[0][0] == tabuleiro[0][1] && tabuleiro[0][0] == tabuleiro[0][2]) {
			resultado = tabuleiro[0][0];
			return true;
		}
		else if (tabuleiro[1][0] != 0 && tabuleiro[1][0] == tabuleiro[1][1] && tabuleiro[1][0] == tabuleiro[1][2]) {
			resultado = tabuleiro[1][0];
			return true;
		}
		else if (tabuleiro[2][0] != 0 && tabuleiro[2][0] == tabuleiro[2][1] && tabuleiro[2][0] == tabuleiro[2][2]) {
			resultado = tabuleiro[2][0];
			return true;
		}
		else if (tabuleiro[0][0] != 0 && tabuleiro[0][0] == tabuleiro[1][0] && tabuleiro[0][0] == tabuleiro[2][0]) {
			resultado = tabuleiro[0][0];
			return true;
		}
		else if (tabuleiro[0][1] != 0 && tabuleiro[0][1] == tabuleiro[1][1] && tabuleiro[0][1] == tabuleiro[2][1]) {
			resultado = tabuleiro[0][1];
			return true;
		}
		else if (tabuleiro[0][2] != 0 && tabuleiro[0][2] == tabuleiro[1][2] && tabuleiro[0][2] == tabuleiro[2][2]) {
			resultado = tabuleiro[0][2];
			return true;
		}
		else if (tabuleiro[0][0] != 0 && tabuleiro[0][0] == tabuleiro[1][1] && tabuleiro[0][0] == tabuleiro[2][2]) {
			resultado = tabuleiro[0][0];
			return true;
		}
		else if (tabuleiro[0][2] != 0 && tabuleiro[0][2] == tabuleiro[1][1] && tabuleiro[0][2] == tabuleiro[2][0]) {
			resultado = tabuleiro[0][2];
			return true;
		}
		else if (tabuleiro[0][0] != 0 && tabuleiro[0][1] != 0 && tabuleiro[0][2] != 0 && tabuleiro[1][0] != 0 && tabuleiro[1][1] != 0 && tabuleiro[1][2] != 0 && tabuleiro[2][0] != 0 && tabuleiro[2][1] != 0 && tabuleiro[2][2] != 0){
			resultado = 3;
			return true;
		}
		return false;
	}
	
	public int getResultado() { 
		if (resultado == 1) {
			historico += "\n" + getNomeJogador(resultado) + " venceu!";
			try {
				arquivo.write(historico + "\n");
				arquivo.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return 1;
		}
		else if (resultado == 2) {
			historico += "\n" + getNomeJogador(resultado) + " venceu!";
			try {
				arquivo.write(historico + "\n");
				arquivo.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return 2;
		}
		else {
			historico += "\n Ninguém venceu!";
			try {
				arquivo.write(historico + "\n");
				arquivo.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return 3;
		}
	}
	
	public int getUltimaLinha() {
		return linha;
	}
	public int getUltimaColuna() {
		return coluna;
	}
	public int getUltimoJogador() {
		return ultimoJogador;
	}
	public String getNomeJogador(int numero) {
		if (numero == 1) {
			if (nomeJogador1.equals("")) 
				return "X";
			return nomeJogador1;
		}
		else {
			if (nomeJogador2.equals(""))
				return "O";
			return nomeJogador2;
		}
	} 
	
	public void printBoard() {
			
		System.out.println("-------------");
		for (int i = 0; i < 3; i++) {
			System.out.print("| ");
			for (int j = 0; j < 3; j++) {
				
				System.out.print(tabuleiro[i][j] + " | ");
				
			}
			System.out.println();
			
		}
		System.out.println("-------------");	
	}

}

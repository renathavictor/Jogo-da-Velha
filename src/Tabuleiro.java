import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;


public class Tabuleiro  {

	private JFrame frame;
	private JLabel[][] labels = new JLabel[3][3];
	private JogoDaVelha jogo;
	private int numeroJogador=1;
	private JLabel lblJogoDaVelha;
	private JPanel panelJogo = new JPanel();
	private JTextField textFieldJogadorX;
	private JTextField textFieldJogadorO;
	private JLabel lblJogadorX;
	private JLabel lblJogadorO;
	private JLabel lblVez;
	private JRadioButton rdbtnJogador;
	private JRadioButton rdbtnJogadores;
	private JButton btnIniciar;
	private ButtonGroup bg;
	private ImageIcon j1 =new ImageIcon(Tabuleiro.class.getResource("/img/j1.png"));
	private ImageIcon j2 =new ImageIcon(Tabuleiro.class.getResource("/img/j2.png"));
	private ImageIcon j1Select = new ImageIcon(Tabuleiro.class.getResource("/img/j1Select.png"));
	private ImageIcon j2Select = new ImageIcon(Tabuleiro.class.getResource("/img/j2Select.png"));
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tabuleiro window = new Tabuleiro();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Tabuleiro() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Jogo da Velha");
		frame.setBounds(500, 200, 720, 591);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		// imagens tabuleiro
		ImageIcon trophy =new ImageIcon(Tabuleiro.class.getResource("/img/award.png"));
		ImageIcon trophyBW =new ImageIcon(Tabuleiro.class.getResource("/img/empate.png"));
		ImageIcon X =new ImageIcon(Tabuleiro.class.getResource("/img/X.png"));
		ImageIcon O =new ImageIcon(Tabuleiro.class.getResource("/img/O.png"));
		ImageIcon titulo =new ImageIcon(Tabuleiro.class.getResource("/img/titulo.png"));
		String[] opcoes = {"Reiniciar", "Fechar jogo"};

		// Titulo do jogo
		lblJogoDaVelha = new JLabel();
		lblJogoDaVelha.setIcon(titulo);
		lblJogoDaVelha.setHorizontalAlignment(SwingConstants.CENTER);
		lblJogoDaVelha.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblJogoDaVelha.setBounds(152, 31, 420, 65);
		frame.getContentPane().add(lblJogoDaVelha);
		
		// Painel do jogo
		panelJogo.setBounds(305, 154, 362, 362);
		panelJogo.setLayout(null);
		frame.getContentPane().add(panelJogo);			
		
		lblVez = new JLabel("");
		lblVez.setHorizontalAlignment(SwingConstants.LEFT);
		lblVez.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblVez.setBounds(305, 522, 362, 29);
		frame.getContentPane().add(lblVez);
		
		// radio buttons
		rdbtnJogador = new JRadioButton();
		rdbtnJogador.setIcon(j1Select);
		rdbtnJogador.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		rdbtnJogador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnJogador.isSelected()) {
					rdbtnJogador.setIcon(j1Select);
					rdbtnJogadores.setIcon(j2);
					textFieldJogadorO.setText("Máquina");
					textFieldJogadorO.setEnabled(false);
				}
			}
		});
		rdbtnJogador.setBounds(17, 176, 134, 30);
		frame.getContentPane().add(rdbtnJogador);
		
		rdbtnJogadores = new JRadioButton();
		rdbtnJogadores.setIcon(j2);
		rdbtnJogadores.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		rdbtnJogadores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnJogadores.isSelected()) {
					rdbtnJogadores.setIcon(j2Select);
					rdbtnJogador.setIcon(j1);
					textFieldJogadorO.setText("");
					textFieldJogadorO.setEnabled(true);
				}
			}
		});
		rdbtnJogadores.setBounds(152, 176, 132, 30);
		frame.getContentPane().add(rdbtnJogadores);
		rdbtnJogador.setSelected(true);
		
		bg = new ButtonGroup();
		bg.add(rdbtnJogador);
		bg.add(rdbtnJogadores);
		
		// botão para iniciar o jogo
		
		btnIniciar = new JButton();
		btnIniciar.setFont(new Font("Tahoma", Font.BOLD, 16));
		ImageIcon jogarBtn =new ImageIcon(Tabuleiro.class.getResource("/img/jogar2.png"));
		btnIniciar.setIcon(jogarBtn);
		btnIniciar.setBorderPainted(false);
		btnIniciar.setContentAreaFilled(false);  
		btnIniciar.setFocusPainted(false);
		btnIniciar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnIniciar.setBounds(73, 446, 150, 60);
		frame.getContentPane().add(btnIniciar);
		
		//inicializar a matriz de labels
		for (int i=0; i<3; i++) {
			for (int j=0; j<3; j++) {
				labels[i][j]=new JLabel();
				panelJogo.add(labels[i][j]);
				labels[i][j].setBounds(i*120, j*120, 120, 120);	//x,y, width, height - 40x40
				labels[i][j].setBackground(Color.GRAY);
				labels[i][j].setBorder(new LineBorder(new Color(0, 0, 0)));
				labels[i][j].setOpaque(true);
				labels[i][j].setText("");
				labels[i][j].addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					JLabel l = (JLabel)e.getSource();
					int indX = l.getX()/120;
					int indY = l.getY()/120;
					if (rdbtnJogador.isSelected()) {
						if (labels[indX][indY].getText().trim().equals("") && !jogo.terminou()) {
							if (numeroJogador == 1) {
								X.setImage(X.getImage().getScaledInstance(labels[indX][indY].getWidth(), labels[indX][indY].getHeight(), 1) );
								labels[indX][indY].setIcon(X);
								jogo.jogarJogador(numeroJogador, indY, indX);
								labels[indX][indY].setText("X");
								labels[indX][indY].setBackground(Color.WHITE);
								lblVez.setText(jogo.getNomeJogador(jogo.getUltimoJogador()) + " jogou, é a vez de " + jogo.getNomeJogador(2));								
							}
							if (!jogo.terminou()) {
								jogo.jogarMaquina();
								indY = jogo.getUltimaLinha();
								indX = jogo.getUltimaColuna();
								O.setImage(O.getImage().getScaledInstance(labels[indX][indY].getWidth(), labels[indX][indY].getHeight(), 1) );
								labels[indX][indY].setIcon(O);
								labels[indX][indY].setText("O");
								labels[indX][indY].setBackground(Color.WHITE);
								lblVez.setText(jogo.getNomeJogador(jogo.getUltimoJogador())+ " jogou, é a vez de " + jogo.getNomeJogador(1));
								//lblVez.setText("O jogador O jogou, é a vez do X   ");
							}
						}
					}
					else if (rdbtnJogadores.isSelected()) {							
						if (labels[indX][indY].getText().trim().equals("") && !jogo.terminou()) {
							jogo.jogarJogador(numeroJogador, indX, indY);
							if (numeroJogador == 1) {
								X.setImage(X.getImage().getScaledInstance(labels[indX][indY].getWidth(), labels[indX][indY].getHeight(), 1) );
								labels[indX][indY].setIcon(X);
								labels[indX][indY].setText("X");
								labels[indX][indY].setBackground(Color.WHITE);
								//lblVez.setText("\u00C9 a vez do O   ");
								lblVez.setText(jogo.getNomeJogador(jogo.getUltimoJogador()) + " jogou, é a vez de " + jogo.getNomeJogador(2));
							} if (numeroJogador == 2) {
								O.setImage(O.getImage().getScaledInstance(labels[indX][indY].getWidth(), labels[indX][indY].getHeight(), 1) );
								labels[indX][indY].setIcon(O);
								labels[indX][indY].setText("O");
								labels[indX][indY].setBackground(Color.WHITE);
								//lblVez.setText("\u00C9 a vez do X   ");
								lblVez.setText(jogo.getNomeJogador(jogo.getUltimoJogador()) + " jogou, é a vez de " + jogo.getNomeJogador(1));
							}
							if (numeroJogador == 1)
								numeroJogador = 2;
							else numeroJogador = 1;
						} 						
					}
					
					if (jogo.terminou()) {
						switch(jogo.getResultado()) {
						case 1: {
							int op = JOptionPane.showOptionDialog(null, jogo.getNomeJogador(1) + " venceu!", "Fim de Jogo...", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, trophy, opcoes, opcoes[0]);
							if (op == 1) {
								frame.dispose();
							} else {
								reiniciarJogo();
							}
							break;
						}
						case 2: {
							int op = JOptionPane.showOptionDialog(null, jogo.getNomeJogador(2) + " venceu!", "Fim de Jogo...", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, trophy, opcoes, opcoes[0]);
							if (op == 1) {
								frame.dispose();
							} else {
								reiniciarJogo();
							}
							break;
						}
						case 3: {
							int op = JOptionPane.showOptionDialog(null, "Ninguém venceu!", "Fim de Jogo...", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, trophyBW, opcoes, opcoes[0]);
							if (op == 1) {
								frame.dispose();
							} else {
								reiniciarJogo();
							}
							break;
						}
						}
					}
				}
				});	
			}		 	
		}

		btnIniciar.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				btnIniciar.setEnabled(false);
				System.out.println("Quem está selecionado? ");
				System.out.println(rdbtnJogador.isSelected());
				System.out.println(rdbtnJogadores.isSelected());
				
				if (rdbtnJogador.isSelected()) { 
					jogo = new JogoDaVelha(textFieldJogadorX.getText());
					rdbtnJogadores.setEnabled(false);									
				}	

				else if (rdbtnJogadores.isSelected()) {
					jogo = new JogoDaVelha(textFieldJogadorX.getText(), textFieldJogadorO.getText());
					rdbtnJogador.setEnabled(false);
				}
				lblVez.setText("É a vez de " +  jogo.getNomeJogador(1));
			}
		});
		

		// campos para os nomes dos jogadores
		textFieldJogadorX = new JTextField();
		textFieldJogadorX.setBounds(134, 254, 134, 30);
		frame.getContentPane().add(textFieldJogadorX);
		textFieldJogadorX.setColumns(10);
		
		textFieldJogadorO = new JTextField();
		textFieldJogadorO.setColumns(10);
		textFieldJogadorO.setBounds(134, 309, 134, 30);
		frame.getContentPane().add(textFieldJogadorO);
		textFieldJogadorO.setText("Máquina");
		textFieldJogadorO.setEnabled(false);
		
		lblJogadorX = new JLabel();
		lblJogadorX.setIcon(new ImageIcon(Tabuleiro.class.getResource("/img/jogX.png")));
		lblJogadorX.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblJogadorX.setBounds(29, 261, 95, 23);
		frame.getContentPane().add(lblJogadorX);
		
		lblJogadorO = new JLabel("Jogador O");
		lblJogadorO.setIcon(new ImageIcon(Tabuleiro.class.getResource("/img/jogO.png")));
		lblJogadorO.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblJogadorO.setBounds(29, 312, 95, 23);
		frame.getContentPane().add(lblJogadorO);

	}
	
	private void reiniciarJogo() {
		numeroJogador=1;
		for(int i=0; i < 3; i++) {
			for(int j=0; j < 3; j++) {
				labels[i][j].setBackground(Color.GRAY);
				labels[i][j].setText("");	
				labels[i][j].setIcon(null);
			}
		}
		lblVez.setText("");
		rdbtnJogador.setEnabled(true);
		rdbtnJogadores.setEnabled(true);
		btnIniciar.setEnabled(true);
		jogo = null;
		if (rdbtnJogador.isSelected()) {
			rdbtnJogador.setIcon(j1Select);
			rdbtnJogadores.setIcon(j2);			
		} else {
			rdbtnJogador.setIcon(j1);
			rdbtnJogadores.setIcon(j2Select);	
		}
	}
}

package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

import model.Gramatica;

public class MainWindow extends JFrame{
	
	private MainPanel mainPanel;
	private Gramatica gramatica;

	public MainWindow() {
		setTitle("Forma Normal de Chomsky");
		setSize(new Dimension(2000, 2000));
		setLayout(new BorderLayout());
		setResizable(true );
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		mainPanel = new MainPanel();
		add(BorderLayout.CENTER,mainPanel);
		
		pack();
	}

	public static void main(String[] args) {
		MainWindow gui = new MainWindow();
		gui.setVisible(true);
	}
}

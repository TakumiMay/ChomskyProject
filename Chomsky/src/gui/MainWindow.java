package gui;

import java.awt.Dimension;

import javax.swing.JFrame;

import model.Principal;

public class MainWindow extends JFrame{
	
	private Principal app;

	public MainWindow() {
		setTitle("Forma Normal de Chomsky");
		setSize(new Dimension(1000, 600));
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		
		pack();
	}

	public static void main(String[] args) {
		MainWindow gui = new MainWindow();
		gui.setVisible(true);
	}
}

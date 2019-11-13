package gui;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class RightPanelOptions extends JPanel implements ActionListener {
	
	/**
	 * 
	 */
	public static final String FNC ="FNC";
	/**
	 * 
	 */
	public static final String PASOS ="PASOS";
	
	/**
	 * 
	 */
	private Label lbTitle;
	/**
	 * 
	 */
	private Button chomsky;
	/**
	 * 
	 */
	private Button pasos;
	
	/**
	 * 
	 */
	private MainWindow window;
	
	/**
	 * 
	 * @param window
	 */
	public RightPanelOptions(MainWindow window) {
		setVisible(true);
		setPreferredSize(new Dimension(360, 600));
		setBackground(new Color(255,255,255));
		
		this.window = window;
			
		lbTitle = new Label("Opciones");
		lbTitle.setAlignment(Label.CENTER);
		
		chomsky = new Button("FNC");
		chomsky.addActionListener(this);
		chomsky.setActionCommand(FNC);
		chomsky.setBackground(Color.WHITE);
		
		pasos = new Button ("Ver pasos");
		pasos.addActionListener(this);
		pasos.setActionCommand(PASOS);
		pasos.setBackground(Color.WHITE);
		
		add(lbTitle);
		add(chomsky);
		add(pasos);
	}
	
	/**
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals(FNC)) {
			 window.iniciarFNC();
		}
		if(e.getActionCommand().equals(PASOS)) {
						
		}
	}

}

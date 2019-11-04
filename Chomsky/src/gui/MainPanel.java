package gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class MainPanel extends JPanel implements ActionListener {

	public static final String FNC ="FNC";
	public static final String PASOS ="PASOS";
	
	private TextArea tfGramatica;
	private Button chomsky;
	private Button pasos;
	private TextArea tfGramaticaFNC;

	private MainWindow mw;
	
	public MainPanel(MainWindow mw) {
		
		setVisible(true);
		setSize(new Dimension(1000, 1000));
		setLayout(new BorderLayout());
		setBackground(new Color(255,255,255));

		this.mw = mw;
		
		tfGramatica = new TextArea();
		add(BorderLayout.NORTH, tfGramatica);
				
		chomsky = new Button("FNC");
		chomsky.addActionListener(this);
		chomsky.setActionCommand(FNC);
		chomsky.setBackground(Color.WHITE);
		add(BorderLayout.WEST, chomsky);
		
		add(BorderLayout.CENTER, new Label());
		
		pasos = new Button ("Ver pasos");
		pasos.addActionListener(this);
		pasos.setActionCommand(PASOS);
		pasos.setBackground(Color.WHITE);
		add(BorderLayout.EAST, pasos);
		
		tfGramaticaFNC = new TextArea();
		add(BorderLayout.SOUTH, tfGramaticaFNC);
	}


	public TextArea getTfGramatica() {
		return tfGramatica;
	}


	public void setTfGramatica(TextArea tfGramatica) {
		this.tfGramatica = tfGramatica;
	}


	public TextArea getTfGramaticaFNC() {
		return tfGramaticaFNC;
	}


	public void setTfGramaticaFNC(TextArea tfGramaticaFNC) {
		this.tfGramaticaFNC = tfGramaticaFNC;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals(FNC)) {
			 if(tfGramatica.getText()!=null) {
				mw.ingresarTexto(tfGramatica.getText());
			}
		}
		if(e.getActionCommand().equals(PASOS)) {
			if(tfGramatica.getText()!=null) {
				
			}
			
		}
	}
	
}

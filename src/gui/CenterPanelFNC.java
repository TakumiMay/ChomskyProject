package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class CenterPanelFNC extends JPanel{

	/**
	 *
	 */
	private Label lbTitle;
	/**
	 *
	 */
	private TextArea txtFNCGrammar;

	/**
	 *
	 */
	private MainWindow window;

	/**
	 *
	 * @param window
	 */
	public CenterPanelFNC(MainWindow window) {
		setVisible(true);
		setPreferredSize(new Dimension(320, 600));
		setLayout(new BorderLayout());
		setBackground(new Color(255,255,255));

		this.window = window;

		lbTitle = new Label("Gramatica en Forma Normal de Chomsky");
		lbTitle.setAlignment(Label.CENTER);
		txtFNCGrammar = new TextArea();
		txtFNCGrammar.setEditable(false);
		Font font = new Font("Segoe Script", Font.BOLD, 15);
		txtFNCGrammar.setFont(font);

		add(BorderLayout.NORTH, lbTitle);
		add(BorderLayout.CENTER, txtFNCGrammar);
	}

	/**
	 *
	 * @return
	 */
	public TextArea getTxtFNCGrammar() {
		return txtFNCGrammar;
	}

	/**
	 *
	 * @param txtFNCGrammar
	 */
	public void setTxtFNCGrammar(TextArea txtFNCGrammar) {
		this.txtFNCGrammar = txtFNCGrammar;
	}

	public void cleanTxt(){
		this.txtFNCGrammar.setText("");
	}
}

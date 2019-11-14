package gui;

import java.awt.*;

import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class LeftPanelGrammar extends JPanel {

    /**
     *
     */
    private Label lbTitle;
    /**
     *
     */
    private TextArea txtGrammar;

    /**
     *
     */
    private MainWindow window;

    /**
     * @param window
     */
    public LeftPanelGrammar(MainWindow window) {
        setVisible(true);
        setPreferredSize(new Dimension(320, 600));
        setLayout(new BorderLayout());
        setBackground(new Color(255, 255, 255));

        this.window = window;

        lbTitle = new Label("Gramatica original");
        lbTitle.setAlignment(Label.CENTER);
        txtGrammar = new TextArea();
        Font font = new Font("Segoe Script", Font.BOLD, 15);
        txtGrammar.setFont(font);

        add(BorderLayout.NORTH, lbTitle);
        add(BorderLayout.CENTER, txtGrammar);
    }

    /**
     * @return
     */
    public String getTxtGrammar() {
        return txtGrammar.getText();
    }

    /**
     * @param txtGrammar
     */
    public void setTxtGrammar(TextArea txtGrammar) {
        this.txtGrammar = txtGrammar;
    }

    public void cleanTxt() {
        this.txtGrammar.setText("");
    }
}

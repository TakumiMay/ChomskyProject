package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.Grammar;

public class MainWindow extends JFrame {

    /**
     *
     */
    private LeftPanelGrammar grammarPanel;
    /**
     *
     */
    private CenterPanelFNC fncPanel;
    /**
     *
     */
    private RightPanelOptions optionsPanel;

    /**
     *
     */
    private Grammar grammarModel;

    /**
     *
     */
    public MainWindow() {
        setTitle("Conversor de gramatica a FNC");
        setSize(new Dimension(1000, 600));
        setLayout(new BorderLayout());
        setResizable(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        grammarPanel = new LeftPanelGrammar(this);
        fncPanel = new CenterPanelFNC(this);
        optionsPanel = new RightPanelOptions(this);

        add(BorderLayout.WEST, grammarPanel);
        add(BorderLayout.CENTER, fncPanel);
        add(BorderLayout.EAST, optionsPanel);
        pack();
    }

    /**
     *
     */
    public void iniciarFNC() {
        String grammarTxt = grammarPanel.getTxtGrammar();
        try {
            grammarModel = new Grammar(grammarTxt);
            grammarModel.getFNC();
            fncPanel.getTxtFNCGrammar().setText(
                    "GRAMATICA SIN NO TERMINALES \n" +
                            grammarModel.toString(grammarModel.getTerminalesRules()) + "\n" +
                            "\nGRAMATICA SIN NO ALCANZABLES  \n" +
                            grammarModel.toString(grammarModel.getAlcanzablesRules()) + "\n" +
                            "\nGRAMATICA SIN ANULABLES  \n" +
                            grammarModel.toString(grammarModel.getAnulablesRules()) + "\n" +
                            "\nGRAMATICA SIN UNITARIAS  \n" +
                            grammarModel.toString(grammarModel.getUnitariasRules()) + "\n"
            );
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No ha ingresado la gramatica a convertir");
        }

    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        MainWindow gui = new MainWindow();
        gui.setVisible(true);
    }

    public void clean() {
        grammarPanel.cleanTxt();
        fncPanel.cleanTxt();
    }
}

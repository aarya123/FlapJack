package UI;

import Engine.Casino;
import Engine.UIListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

/**
 * User: AnubhawArya
 * Date: 9/13/13
 * Time: 3:05 PM
 */
public class MainWindow implements ItemListener, ActionListener {
    private JComboBox casinoList;
    private JButton goButton;
    private JPanel mainWindow;
    private JLabel numDecks;
    private JLabel soft17s;
    private JLabel doubleSplitting;
    private JLabel optSurrender;
    private JLabel resplitAces;
    ArrayList<Casino> casinos;
    static MainWindow ui;
    static JFrame frame;

    public static void main(String[] args) {
        frame = new JFrame("MainWindow");
        ui = new MainWindow();
        frame.setContentPane(ui.mainWindow);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ui.initUI();
        frame.pack();
        frame.setVisible(true);
    }

    private void initUI() {
        initListeners();
        initCasinos();
    }

    private void initListeners() {
        goButton.addActionListener(this);
        casinoList.addItemListener(this);
    }

    private void initCasinos() {
        casinos = new ArrayList<Casino>();
        casinos.add(new Casino("Bellagio", 6, true, true, true, true));
        casinos.add(new Casino("Caesar's Palace", 2, true, false, false, false));
        casinos.add(new Casino("MGM Grand", 6, false, true, true, true));
        for (Casino casino : casinos)
            casinoList.addItem(casino.getName());
        setLabels((String) casinoList.getSelectedItem());
    }

    public void itemStateChanged(ItemEvent itemEvent) {
        setLabels((String) itemEvent.getItem());
    }

    public void setLabels(String val) {
        for (Casino casino : casinos) {
            if (val.equals(casino.getName())) {
                numDecks.setText(String.valueOf(casino.getNumberOfDecks()));
                soft17s.setText(String.valueOf(casino.isHitOnSoft17s()));
                doubleSplitting.setText(String.valueOf(casino.isDoubleAfterSplit()));
                optSurrender.setText(String.valueOf(casino.isSurrenderOption()));
                resplitAces.setText(String.valueOf(casino.isResplitAfterAce()));
                frame.pack();
                return;
            }
        }
    }

    public void actionPerformed(ActionEvent actionEvent) {
        for (Casino casino : casinos)
            if (casinoList.getSelectedItem() == casino.getName())
                UIListener.setCasino(casino);
    }

    public static void addPoints(double[] points) {
        //Add points to graph
    }
}

package UI;

import Engine.Casino;
import Engine.Session;
import Engine.Simulator;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
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
public class MainWindow implements ItemListener, ActionListener, ChangeListener {
    static Boolean dev = true;
    static JFrame frame;
    static MainWindow ui;
    ArrayList<Casino> casinos;
    private JPanel mainWindow;
    private JComboBox casinoList;
    private JLabel numDecks;
    private JCheckBox soft17s, doubleSplitting, resplitAces;
    private JButton goButton;
    private JSpinner numGames;
    private JLabel bjMultiplier;

    public static void main(String[] args) {
        if (dev) {
            Casino casino = new Casino("Bellagio", 1.5, 6, true, true, true);
            casino.setNumberOfGames(1);
            new Simulator(casino);
        } else {
            initUI();
        }
    }

    public static void initUI() {
        frame = new JFrame("FlapJack");
        ui = new MainWindow();
        frame.setContentPane(ui.mainWindow);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        ui.initListeners();
        ui.initCasinos();
        ui.numGames.setValue(1);
    }

    private void initListeners() {
        goButton.addActionListener(this);
        casinoList.addItemListener(this);
        numGames.addChangeListener(this);
    }

    private void initCasinos() {
        casinos = new ArrayList<Casino>();
        casinos.add(new Casino("Bellagio", 1.5, 6, true, true, true));
        casinos.add(new Casino("Caesar's Palace", 1.5, 2, true, false, false));
        casinos.add(new Casino("MGM Grand", 1.5, 6, false, true, true));
        for (Casino casino : casinos)
            casinoList.addItem(casino.getName());
        setLabels((String) casinoList.getSelectedItem());
    }

    public void itemStateChanged(ItemEvent itemEvent) {
        if (itemEvent.getSource() == casinoList)
            setLabels((String) casinoList.getSelectedItem());
    }

    public void setLabels(String val) {
        for (Casino casino : casinos) {
            if (val.equals(casino.getName())) {
                numDecks.setText(String.valueOf(casino.getNumberOfDecks()));
                bjMultiplier.setText(String.valueOf(casino.getBlackjackPayoutMultiple()));
                soft17s.setSelected(casino.isHitOnSoft17s());
                doubleSplitting.setSelected(casino.isDoubleAfterSplit());
                resplitAces.setSelected(casino.isResplitAfterAce());
                frame.pack();
                return;
            }
        }
    }

    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == goButton)
            for (Casino casino : casinos)
                if (casinoList.getSelectedItem().equals(casino.getName())) {
                    casino.setNumberOfGames((Integer) numGames.getValue());
                    new Simulator(casino);
                }
    }

    public void stateChanged(ChangeEvent changeEvent) {
        if (changeEvent.getSource() == numGames)
            if ((Integer) numGames.getValue() < 1)
                numGames.setValue(1);
        frame.pack();
    }

    public static void simulateSession(Session session) {
        //TODO: Take session and display crap
    }
}

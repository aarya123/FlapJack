package UI;

import Engine.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
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
    static Boolean dev = false;
    JFrame frame;
    ArrayList<Casino> casinos;
    private JPanel mainWindow;
    private JComboBox casinoList;
    private JLabel numDecks;
    private JCheckBox soft17s, doubleSplitting, resplitAces;
    private JButton goButton;
    private JSpinner numGames;
    private JLabel bjMultiplier;
    private JLabel numWon;
    private JLabel totWage;
    private JLabel profit;
    private Graph graph;
    private JLabel numGamesCount;
    private JLabel aceValue;
    private JLabel twoValue;
    private JLabel threeValue;
    private JLabel fourValue;
    private JLabel fiveValue;
    private JLabel sixValue;
    private JLabel sevenValue;
    private JLabel eightValue;
    private JLabel nineValue;
    private JLabel faceValue;
    private JLabel profitPerWaged;
    private JComboBox strategyList;

    MainWindow() {
        frame = new JFrame("FlapJack");
        frame.setContentPane(mainWindow);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        initListeners();
        initCasinos();
        numGames.setValue(1);
    }


    public static void main(String[] args) {
        if (dev) {
            Casino casino = new Casino("Bellagio", 1.5, 6, true, true, true);
            casino.setNumberOfGames(1000000);
            new Simulator(casino,"Basic Strategy");
        } else {
            new MainWindow();
        }
    }

    public void simulateSession(final Session session) {
        profit.setText("$" + String.format("%.0f", session.getTotalProfit()));
        numWon.setText(String.format("%.2g%n", session.getGameWonPercentage()) + "%");
        totWage.setText("$" + String.format("%.0f", session.getTotalWage()));
        numGamesCount.setText(String.valueOf(session.getCasino().getNumberOfGames()));
        graph.setPoints(session.getCumProfit());
        profitPerWaged.setText("$"+String.format("%.4f",session.getTotalProfit()/session.getTotalWage()));
        Strategy s = session.getStrategy();
        aceValue.setText("A: "+s.getHottnessForCard(new Card("A")));
        twoValue.setText("2: "+s.getHottnessForCard(new Card("2")));
        threeValue.setText("3: "+s.getHottnessForCard(new Card("3")));
        fourValue.setText("4: "+s.getHottnessForCard(new Card("4")));
        fiveValue.setText("5: "+s.getHottnessForCard(new Card("5")));
        sixValue.setText("6: "+s.getHottnessForCard(new Card("6")));
        sevenValue.setText("7: "+s.getHottnessForCard(new Card("7")));
        eightValue.setText("8: "+s.getHottnessForCard(new Card("8")));
        nineValue.setText("9: "+s.getHottnessForCard(new Card("9")));
        faceValue.setText("F: "+s.getHottnessForCard(new Card("J")));
        System.out.println(session.getExpectedValue());
        //System.out.println(session.)
        frame.pack();
    }

    private void initListeners() {
        goButton.addActionListener(this);
        casinoList.addItemListener(this);
        strategyList.addItemListener(this);
        numGames.addChangeListener(this);
    }

    private void initCasinos() {
        casinos = new ArrayList<Casino>();
        casinos.add(new Casino("Bellagio", 1.4, 6, true, true, true));
        casinos.add(new Casino("Caesar's Palace", 1.5, 2, true, false, false));
        casinos.add(new Casino("MGM Grand", 1.3, 6, false, true, true));
        for (Casino casino : casinos)
            casinoList.addItem(casino.getName());
        strategyList.addItem("Basic Strategy");
        strategyList.addItem("Counting Strategy");
        strategyList.addItem("Optimized Counting");
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
                    new Simulator(casino, this, (String)(strategyList.getSelectedItem()));
                }
    }

    public void stateChanged(ChangeEvent changeEvent) {
        if (changeEvent.getSource() == numGames)
            if ((Integer) numGames.getValue() < 1)
                numGames.setValue(1);
        frame.pack();
    }

    public void createUIComponents() {
        graph = new Graph(new double[0]);
    }
}

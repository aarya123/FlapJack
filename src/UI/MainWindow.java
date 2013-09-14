package UI;

import Engine.Casino;
import Engine.Session;
import Engine.Simulator;
import Engine.Strategy;
import Engine.TestStrategies;
import Engine.Strategy; // TODO: get rid of this dependency
import Engine.TestStrategies;

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
    static boolean moo = true;
    static Boolean geneticTest = true;
    static Boolean dev = true;
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

    	if (moo) {
   		//double[] heatmap = new double[] {1, -0.1, 0.2, -1.4, -0.4, -1.6, 1.518518613097199, -1.9, 0.2, -1};
//    		double[] heatmap = new double[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
 //   		double[] heatmap = new double[] {-1.2881094374190005, 0.162172601632273, -1.614138127560407, -0.7661004466682921, -0.4318724568369196, -4.961397802588075, 0.367080856398845, -0.6491184164196876, -1.6192482216585984, -2.6031034758127065};
    		double [] heatmap = new double[] {-0.0026796595899303832, 1.9615927020141337, 0.7108625127192184, 1.4613978953176354, 0.09439203962328646, 2.6418293615381216, 0.7137243936718929, 4.699783746842147, 0.5613397132434919, 3.3452397610800704};
   			Casino casino = new Casino("Bellagio", 1.5, 6, true, true, true);
            casino.setNumberOfGames(10000);
    		new Simulator(casino, new Strategy(heatmap));
    	}
    	else if (geneticTest) {    		
    		TestStrategies test = new TestStrategies();
    		System.out.println("Testing...");
    		test.test();
		} else if (dev) {
            Casino casino = new Casino("Bellagio", 1.5, 6, true, true, true);
           // casino.setNumberOfGames((int) (Math.random() * 100000));
            casino.setNumberOfGames(100);
            new Simulator(casino);
        } else {
            new MainWindow();
        }
    }

    public void simulateSession(Session session) {
        profit.setText("$" + session.getTotalProfit());
        numWon.setText(String.format("%.2g%n", session.getGameWonPercentage()) + "%");
        totWage.setText("$" + session.getTotalWage());
        numGamesCount.setText(String.valueOf(session.getCasino().getNumberOfGames()));
        graph.setPoints(session.getCumProfit());
        frame.pack();
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
                    new Simulator(casino, this);
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

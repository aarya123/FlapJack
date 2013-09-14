package UI;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;

public class Graph extends JPanel {

    private double points[];
    private ChartPanel chartPanel;

    public Graph(double points[]) {
        super();
        this.points = points;
        plot();
    }

    public void setPoints(double points[]) {
        this.points = points;
        plot();
    }

    private void plot() {
        removeAll();
        XYSeries series = new XYSeries("Win");
        for (int i = 0; i < points.length; i++) {
            series.add(1.0 * i, points[i]);
        }

        XYSeriesCollection dataset = new XYSeriesCollection(series);
        JFreeChart jFreeChart = ChartFactory.createXYLineChart("Chart", "#Games", "Expected win", dataset);

        chartPanel = new ChartPanel(jFreeChart);
        add(chartPanel);
        revalidate();
    }
}

package UI;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;

public class Graph extends JPanel {

    private double points[];

    public Graph(double points[]) {
        super();
        this.points = points;

        XYSeries series = new XYSeries("Win");
        for (int i = 0; i < points.length; i++) {
            series.add(1.0 * i, points[i]);
        }

        XYSeriesCollection dataset = new XYSeriesCollection(series);

        JFreeChart jFreeChart = ChartFactory.createXYLineChart("Chart", "#Games", "Expected win", dataset);
        //XYPlot plot = (XYPlot)jFreeChart.getPlot();

        //plot.addRangeMarker(new Marker(0, Color.red, new BasicStroke(1), Color.red, 1f));

        ChartPanel chartPanel = new ChartPanel(jFreeChart);
        add(chartPanel);
    }
}

/**
 * Clase de diagrama de lineas
 */

package Diagrama;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class LineDiagram extends Diagram {
    private DefaultCategoryDataset dataset;
    private JFreeChart lineChart;

    public LineDiagram(DefaultCategoryDataset dataset, String tittle, String chart) {
        super(dataset, tittle, chart);
        this.dataset = dataset;
        super.tittle = tittle;
        super.chart = chart;
    }

    public void drawDiagram(String xAxis, String yAxis) {
        lineChart = ChartFactory.createLineChart(
                chart,
                xAxis,
                yAxis,
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);
    }

    public JFreeChart getChart() {
        return lineChart;
    }
}

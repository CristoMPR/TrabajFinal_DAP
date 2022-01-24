/**
 * Clase de Diagrama de Barras
 */

package Diagrama;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;


public class BarDiagram extends Diagram {
    private DefaultCategoryDataset dataset;
    private JFreeChart barChart;

    public BarDiagram(DefaultCategoryDataset dataset, String tittle, String chart) {
        super(dataset, tittle, chart);
        this.dataset = dataset;
        super.tittle = tittle;
        super.chart = chart;
    }

    public void drawDiagram(String xAxis, String yAxis) {
        barChart = ChartFactory.createBarChart(
                chart,
                xAxis,
                yAxis,
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);
    }


    public JFreeChart getChart() {
        return barChart;
    }
}

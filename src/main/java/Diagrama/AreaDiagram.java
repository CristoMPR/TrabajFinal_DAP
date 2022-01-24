package Diagrama;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class AreaDiagram extends Diagram {
    private DefaultCategoryDataset dataset;
    private JFreeChart areaChart;

    public AreaDiagram(DefaultCategoryDataset dataset, String tittle, String chart) {
        super(dataset, tittle, chart);
        this.dataset = dataset;
        super.tittle = tittle;
        super.chart = chart;
    }

    public void drawDiagram(String xAxis, String yAxis) {
        areaChart = ChartFactory.createAreaChart(
                chart,
                xAxis,
                yAxis,
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);
    }


    public JFreeChart getChart() {
        return areaChart;
    }
}
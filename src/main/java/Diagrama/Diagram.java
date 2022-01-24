/**
 * Clase abstracta de Diagramas
 */

package Diagrama;

import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.ui.ApplicationFrame;

abstract public class Diagram extends ApplicationFrame implements DrawDiagram {
    protected String tittle, chart;

    public Diagram(DefaultCategoryDataset dataset, String tittle, String chart) {
        super(tittle);
    }

    public abstract JFreeChart getChart();

}

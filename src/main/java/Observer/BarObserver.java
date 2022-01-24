package Observer;

import Dataset.MyDataset;
import Diagrama.*;
import org.jfree.chart.ChartPanel;
import org.jfree.data.category.DefaultCategoryDataset;

import java.util.ArrayList;

public class BarObserver extends Observer {
    private Diagram diagram;
    private MyDataset dataset;
    private ChartPanel CP;
    public BarObserver(Manager manager, Diagram diagram, ChartPanel CP, MyDataset dataset) {
        this.manager = manager;
        this.manager.attach(this);
        this.diagram = diagram;
        this.CP = CP;
        this.dataset = dataset;
    }

    public void update() {
        ArrayList<Integer> values = new ArrayList<>();
        for(int i = 1; i < dataset.getBarValues().size(); i++)
            values.add(dataset.getBarValues().get(i));
        int target = dataset.getBarValues().get(0);
        dataset.setBarData(values, target);
        diagram.repaint();
        CP.repaint();
    }
}

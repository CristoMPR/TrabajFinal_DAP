package Observer;

import Dataset.MyDataset;
import Diagrama.Diagram;
import org.jfree.chart.ChartPanel;

import java.util.ArrayList;

public class AreaObserver extends Observer {
    private Diagram diagram;
    private MyDataset dataset;
    private ChartPanel CP;
    public AreaObserver(Manager manager, Diagram diagram, ChartPanel CP, MyDataset dataset) {
        this.manager = manager;
        this.manager.attach(this);
        this.diagram = diagram;
        this.CP = CP;
        this.dataset = dataset;
    }

    public void update() {
        ArrayList<Integer> values = new ArrayList<>();
        for(int i = 1; i < dataset.getAreaValues().size(); i++)
            values.add(dataset.getAreaValues().get(i));
        int target = dataset.getAreaValues().get(0);
        dataset.setAreaData(values.get(values.size()-1), target);
        diagram.repaint();
        CP.repaint();
    }
}

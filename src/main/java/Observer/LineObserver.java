package Observer;

import Dataset.MyDataset;
import Diagrama.*;
import org.jfree.chart.ChartPanel;

import java.util.ArrayList;

public class LineObserver extends Observer {
    private Diagram diagram;
    private MyDataset dataset;
    private ChartPanel CP;
    public LineObserver(Manager manager, Diagram diagram, ChartPanel CP, MyDataset dataset) {
        this.manager = manager;
        this.manager.attach(this);
        this.diagram = diagram;
        this.CP = CP;
        this.dataset = dataset;
    }

    public void update() {
        ArrayList<Integer> values = new ArrayList<>();
        for(int i = 1; i < dataset.getLineValues().size(); i++)
            values.add(dataset.getLineValues().get(i));
        int target = dataset.getLineValues().get(0);
        dataset.setLineData(values.get(values.size()-1), target);
        diagram.repaint();
        CP.repaint();
    }
}

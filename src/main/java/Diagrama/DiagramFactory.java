package Diagrama;

import Dataset.MyDataset;

public class DiagramFactory {
    static public Diagram diagramFactory(String option, MyDataset dataset){
        Diagram diagram = null;
        if(option.equals("Bar")) {
            diagram = new BarDiagram(
                    dataset.getBarData(),
                    "Diagrama de Barras",
                    "");
        }
        else if(option.equals("Line")) {
            diagram = new LineDiagram(
                    dataset.getLineData(),
                    "Diagrama de Lineas",
                    "");
        }
        else if(option.equals("Area")) {
            diagram = new AreaDiagram(
                    dataset.getAreaData(),
                    "Diagrama de Areas",
                    "");
        }
        return diagram;
    }
}

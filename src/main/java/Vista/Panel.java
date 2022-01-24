/**
 * Clase Panel:
 * Contiene el principal panel de la aplicación
 */

package Vista;

import Control.ControlSheets;
import Dataset.MyDataset;
import Diagrama.*;
import Observer.AreaObserver;
import Observer.BarObserver;
import Observer.LineObserver;
import Observer.Manager;
import org.jfree.chart.ChartPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;

public class Panel extends JPanel {
    private MyDataset dataset;
    private Diagram[] diagrams;
    private JButton loadButton, tableButton, barButton, lineButton, areaButton, refreshButton;
    private JButton loadBar, loadLine, loadSector;
    private Manager manager = new Manager();
    private JLabel tittle, dirLabel, rangLabel, lineLabel, barLabel, areaLabel;
    private JTextField dirField, rangField;
    private JTabbedPane tabbedPane = new JTabbedPane();
    private JPanel principal, tablePanel, barPanel, linePanel, areaPanel;
    private DefaultTableModel tableModel;
    private JTable table;
    private String[][] tabledata;
    private ChartPanel BCP = new ChartPanel(null);
    private ChartPanel LCP = new ChartPanel(null);
    private ChartPanel ACP = new ChartPanel(null);
    private ControlSheets controlador;

    private ArrayList<JLabel> attLabels = new ArrayList<>();
    private ArrayList<JCheckBox> attChecks = new ArrayList<>();

    private ArrayList<JLabel> attBarLabels = new ArrayList<>();
    private ArrayList<JCheckBox> attBarChecks = new ArrayList<>();

    private ArrayList<JLabel> attLineLabels = new ArrayList<>();
    private ArrayList<JCheckBox> attLineChecks = new ArrayList<>();

    private ArrayList<JLabel> attAreaLabels = new ArrayList<>();
    private ArrayList<JCheckBox> attAreaChecks = new ArrayList<>();

    private String[] headers;

    public Panel(ControlSheets controlador) {
        this.controlador = controlador;
        setLayout(null);
        panelInit();
        setBackground(Color.decode("#ffffff"));
        buttonInit();
        textFieldInit();
        labelInit();
    }

    public void buttonInit() {
        loadButton = new JButton("Cargar Datos");
        tableButton = new JButton("Crear Tabla");
        barButton = new JButton("Barras");
        lineButton = new JButton("Lineas");
        areaButton = new JButton("Areas");
        refreshButton = new JButton("Actualizar");
        loadBar = new JButton("Cargar Atributos");
        loadLine = new JButton("Cargar Atributos");
        loadSector = new JButton("Cargar Atributos");
        loadButton.setBounds(50, 100, 150, 30);
        tableButton.setBounds(50, 150,150,30);
        barButton.setBounds(50, 100, 150, 30);
        lineButton.setBounds(50, 100,150,30);
        areaButton.setBounds(50, 100,150,30);
        refreshButton.setBounds(800, 10, 150, 30);
        loadBar.setBounds(50, 50,150,30);
        loadLine.setBounds(50, 50,150,30);
        loadSector.setBounds(50, 50,150,30);
        principal.add(loadButton);
        principal.add(tableButton);
        barPanel.add(barButton);
        barPanel.add(loadBar);
        linePanel.add(lineButton);
        linePanel.add(loadLine);
        areaPanel.add(areaButton);
        areaPanel.add(loadSector);
        add(refreshButton);

        loadButton.addActionListener(new loadButtonAction());
        tableButton.addActionListener(new tableButtonAction());
        barButton.addActionListener(new barButtonAction());
        loadBar.addActionListener(new loadBarAction());
        lineButton.addActionListener(new lineButtonAction());
        loadLine.addActionListener(new loadLineAction());
        areaButton.addActionListener(new areaButtonAction());
        loadSector.addActionListener(new loadSectorAction());
        refreshButton.addActionListener((new refreshButtonAction()));
    }

    public void textFieldInit() {
        dirField = new JTextField();
        rangField = new JTextField();
        dirField.setBounds(50, 60, 300, 30);
        rangField.setBounds(400, 60, 200, 30);
        dirField.setText("1xlZnv7S0f5VbAyIQbJWRBI3LGIrWNdJIYeNVENG5_oE");
        rangField.setText("Hoja 1!A1:BJ");
        principal.add(dirField);
        principal.add(rangField);
    }

    public void labelInit() {
        tittle = new JLabel();
        dirLabel = new JLabel();
        rangLabel = new JLabel();
        barLabel = new JLabel();
        lineLabel = new JLabel();
        areaLabel = new JLabel();
        tittle.setBounds(350, 0, 600, 50);
        dirLabel.setBounds(50, 10, 400, 50);
        rangLabel.setBounds(400, 10, 200, 50);
        barLabel.setBounds(250, 50, 300, 50);
        lineLabel.setBounds(250, 50, 300, 50);
        areaLabel.setBounds(250, 50, 300, 50);
        tittle.setText("Representacion de datos");
        dirLabel.setText("Introduzca la direccion del documento:");
        rangLabel.setText("Introduzca el rango: ");
        barLabel.setText("Seleccione la variable que quiere analizar: ");
        lineLabel.setText("Seleccione la variable que quiere analizar: ");
        areaLabel.setText("Seleccione la variable que quiere analizar: ");
        tittle.setFont(new Font("SansSerif", Font.PLAIN, 26));
        add(tittle);
        principal.add(dirLabel);
        principal.add(rangLabel);
        barPanel.add(barLabel);
        linePanel.add(lineLabel);
        areaPanel.add(areaLabel);
        barLabel.setVisible(false);
        lineLabel.setVisible(false);
        areaLabel.setVisible(false);
    }

    public void panelInit() {
        principal = new JPanel(null);
        tablePanel = new JPanel(null);
        barPanel = new JPanel(null);
        linePanel = new JPanel(null);
        areaPanel = new JPanel(null);
        tabbedPane.setBounds(0, 70, 1300, 670);
        principal.setBackground(Color.WHITE);
        tablePanel.setBackground(Color.WHITE);
        linePanel.setBackground(Color.WHITE);
        barPanel.setBackground(Color.WHITE);
        areaPanel.setBackground(Color.WHITE);
        tabbedPane.add(principal, "Principal");
        tabbedPane.add(tablePanel, "Tabla");
        tabbedPane.add(barPanel, "Diagrama de barras");
        tabbedPane.add(linePanel, "Diagrama de lineas");
        tabbedPane.add(areaPanel, "Diagrama de areas");
        add(tabbedPane);
    }

    public void addAttributes() {
        String[] attributes = dataset.getParsedData().get(0);
        int x = 400;
        int y = 100;
        for(String attribute : attributes) {
            attChecks.add(new JCheckBox());
            attLabels.add(new JLabel(attribute));
            attChecks.get(attChecks.size() - 1).setBounds(x, y, 20, 30);
            attLabels.get(attLabels.size() - 1).setBounds(x + 20, y, 150, 30);
            attChecks.get(attChecks.size() - 1).setBackground(Color.WHITE);
            y += 30;
            if(y == 520) {
                y = 100;
                x += 170;
            }
        }
        showAttributes();
    }

    public void showAttributes() {
        for(int i = 0; i < attLabels.size(); i++) {
            principal.add(attLabels.get(i));
            principal.add(attChecks.get(i));
        }
        repaint();
    }

    class loadButtonAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                dataset = controlador.getData(dirField.getText(), rangField.getText());
            } catch (GeneralSecurityException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            addAttributes();
        }
    }

    public class tableButtonAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            setData();
            tableModel = new DefaultTableModel(tabledata, headers);
            table = new JTable(tableModel);
            JScrollPane sp = new JScrollPane(table);
            sp.setBounds(50 , 50, 1175,475);
            tablePanel.add(sp);
            tablePanel.repaint();
            repaint();
        }
    }

    public class refreshButtonAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            MyDataset newData = null;
            try {
                newData = controlador.getData(dirField.getText(), rangField.getText());
            } catch (GeneralSecurityException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            dataset.refresh(newData.getData());
            setData();
            restoreValues();
            manager.setData(dataset.getFinalData());
            repaint();
            BCP.repaint();
            LCP.repaint();
            ACP.repaint();
            barPanel.repaint();
            linePanel.repaint();
            areaPanel.repaint();
        }
    }

    public void restoreValues() {
        ArrayList<Integer> values1 = new ArrayList<Integer>();
        for(int i = 0; i  < attBarLabels.size(); i++)
            if(attBarChecks.get(i).isSelected())
                values1.add(i);
        dataset.storeBarValues(values1);
        ArrayList<Integer> values2 = new ArrayList<Integer>();
        for(int i = 0; i  < attLineLabels.size(); i++)
            if(attLineChecks.get(i).isSelected())
                values2.add(i);
        dataset.storeLineValues(values2);
        ArrayList<Integer> values3 = new ArrayList<Integer>();
        for(int i = 0; i  < attAreaLabels.size(); i++)
            if(attAreaChecks.get(i).isSelected())
                values3.add(i);
        dataset.storeAreaValues(values3);
    }
    public void setData() {
        for(int i = 0; i < dataset.getParsedData().size(); i++) {
            ArrayList<String> info = new ArrayList<>();
            for (int j = 0; j < dataset.getParsedData().get(i).length; j++) {
                if (attChecks.get(j).isSelected()) {
                    info.add(dataset.getParsedData().get(i)[j]);
                }
            }
            if(info.size() > 1)
                dataset.addFinalData(info);
        }
        headers = dataset.getFinalData().get(0).toArray(new String[0]);
        dataset.eraseHeaders();
        tabledata = dataset.getFinalData().stream()
                .map(u -> u.toArray(new String[0])).toArray(String[][]::new);
    }

    // Muestra el diagrama de barras
    public class barButtonAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            ArrayList<Integer> values = new ArrayList<Integer>();
            for(int i = 0; i  < attBarLabels.size(); i++)
                if(attBarChecks.get(i).isSelected())
                    values.add(i);
            dataset.storeBarValues(values);
            int target = values.get(0);
            values.remove(0);
            dataset.setBarData(values, target);
            Diagram barChart = DiagramFactory.diagramFactory("Bar", dataset);
            barChart.drawDiagram("Eje X", "Eje Y");
            BCP.setBounds(600, 50, 600, 400);
            BCP.setChart(barChart.getChart());
            barPanel.add(BCP);
            BCP.setVisible(true);
            new BarObserver(manager, barChart, BCP, dataset);
        }
    }

    // Muestra el diagrama de líneas
    public class lineButtonAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            ArrayList<Integer> values = new ArrayList<Integer>();
            for(int i = 0; i  < attLineLabels.size(); i++)
                if(attLineChecks.get(i).isSelected())
                    values.add(i);
            dataset.storeLineValues(values);
            int target = values.get(0);
            values.remove(0);
            dataset.setLineData(values.get(values.size()-1), target);
            Diagram lineChart = DiagramFactory.diagramFactory("Line", dataset);
            lineChart.drawDiagram("Eje X", "Eje Y");
            LCP.setBounds(600, 50, 600, 400);
            LCP.setChart(lineChart.getChart());
            linePanel.add(LCP);
            LCP.setVisible(true);
            new LineObserver(manager, lineChart, LCP, dataset);
        }
    }

    // Muestra el diagrama de sectores
    public class areaButtonAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            ArrayList<Integer> values = new ArrayList<Integer>();
            for(int i = 0; i  < attAreaLabels.size(); i++)
                if(attAreaChecks.get(i).isSelected())
                    values.add(i);
            dataset.storeAreaValues(values);
            int target = values.get(0);
            values.remove(0);
            dataset.setAreaData(values.get(values.size()-1), target);
            Diagram areaChart = DiagramFactory.diagramFactory("Area", dataset);
            areaChart.drawDiagram("Eje X", "Eje Y");
            ACP.setBounds(600, 50, 600, 400);
            ACP.setChart(areaChart.getChart());
            areaPanel.add(ACP);
            ACP.setVisible(true);
            new AreaObserver(manager, areaChart, ACP, dataset);
        }
    }

    public class loadBarAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int x = 250;
            int y = 100;
            for(String attribute : headers) {
                attBarChecks.add(new JCheckBox());
                attBarLabels.add(new JLabel(attribute));
                attBarChecks.get(attBarChecks.size() - 1).setBounds(x, y, 20, 30);
                attBarLabels.get(attBarLabels.size() - 1).setBounds(x + 20, y, 150, 30);
                attBarChecks.get(attBarChecks.size() - 1).setBackground(Color.WHITE);
                barPanel.add(attBarChecks.get(attBarChecks.size() - 1));
                barPanel.add(attBarLabels.get(attBarLabels.size() - 1));
                y += 30;
                if(y == 520) {
                    y = 100;
                    x += 170;
                }
            }
            barLabel.setVisible(true);
            repaint();
        }
    }

    public class loadLineAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int x = 250;
            int y = 100;
            for(String attribute : headers) {
                attLineChecks.add(new JCheckBox());
                attLineLabels.add(new JLabel(attribute));
                attLineChecks.get(attLineChecks.size() - 1).setBounds(x, y, 20, 30);
                attLineLabels.get(attLineLabels.size() - 1).setBounds(x + 20, y, 150, 30);
                attLineChecks.get(attLineChecks.size() - 1).setBackground(Color.WHITE);
                linePanel.add(attLineChecks.get(attLineChecks.size() - 1));
                linePanel.add(attLineLabels.get(attLineLabels.size() - 1));
                y += 30;
                if(y == 520) {
                    y = 100;
                    x += 170;
                }
            }
            lineLabel.setVisible(true);
            repaint();
        }
    }

    public class loadSectorAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int x = 250;
            int y = 100;
            for(String attribute : headers) {
                attAreaChecks.add(new JCheckBox());
                attAreaLabels.add(new JLabel(attribute));
                attAreaChecks.get(attAreaChecks.size() - 1).setBounds(x, y, 20, 30);
                attAreaLabels.get(attAreaLabels.size() - 1).setBounds(x + 20, y, 150, 30);
                attAreaChecks.get(attAreaChecks.size() - 1).setBackground(Color.WHITE);
                areaPanel.add(attAreaChecks.get(attAreaChecks.size() - 1));
                areaPanel.add(attAreaLabels.get(attAreaLabels.size() - 1));
                y += 30;
                if(y == 520) {
                    y = 100;
                    x += 170;
                }
            }
            areaLabel.setVisible(true);
            repaint();
        }
    }
}


package Dataset;

import org.checkerframework.checker.units.qual.A;
import org.jfree.data.category.DefaultCategoryDataset;

import java.sql.Array;
import java.sql.Timestamp;
import java.util.ArrayList;

public class MyDataset {

    private ArrayList<String> data;
    private ArrayList<String[]> parsedData = new ArrayList<>();
    private ArrayList<ArrayList<String>> finalData = new ArrayList<>();
    private ArrayList<String> headers = new ArrayList<>();

    private ArrayList<Integer> barValues = new ArrayList<>();
    private ArrayList<Integer> lineValues = new ArrayList<>();
    private ArrayList<Integer> areaValues = new ArrayList<>();

    private DefaultCategoryDataset barDataset = new DefaultCategoryDataset();
    private DefaultCategoryDataset lineDataset = new DefaultCategoryDataset();
    private DefaultCategoryDataset areaDataset = new DefaultCategoryDataset();

    public MyDataset(ArrayList<String> data) {
        this.data = data;
        parseData(";");
        decimalFormat(",");
    }

    public void refresh(ArrayList<String> data) {
        this.data = data;
        parsedData.clear();
        finalData.clear();
        parseData(";");
        decimalFormat(",");
    }

    /**
     * Función para crear un dataset a medida para un diagrama de barras
     * @param yData - Son los indices donde se encuentran las cantidades que se mostrarán
     * @param target - Indice donde se encuentra el dato (BTC, ETH, en el caso de crypto)
     */
    public void setBarData(ArrayList<Integer> yData, int target) {
        for(int i = 0; i < finalData.size(); i++)
            for(int j = 0; j < yData.size(); j++)
                barDataset.addValue(Double.parseDouble(finalData.get(i).get(yData.get(j)))
                        , finalData.get(i).get(target)
                        , headers.get(yData.get(j)));
    }

    public DefaultCategoryDataset getBarData() {
        return barDataset;
    }

    /**
     * Función para crear un dataset a medida para un diagrama de lineas
     * @param yData - Indice donde se encuentra la cantidad que se mostrará
     * @param target - Indice donde se encuentra el dato
     */
    public void setLineData(int yData, int target) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        for(int i = 0; i < finalData.size(); i++)
            lineDataset.addValue(Double.parseDouble(finalData.get(i).get(yData))
                    ,finalData.get(i).get(target)
                    , timestamp);
    }

    public DefaultCategoryDataset getLineData() {
        return lineDataset;
    }

    /**
     * Función para crear un dataset a medida para un diagrama de areas
     * @param yData - Son los indices donde se encuentran las cantidades que se mostrarán
     * @param target - Indice donde se encuentra el dato (BTC, ETH, en el caso de crypto)
     */
    public void setAreaData(int yData, int target) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        for(int i = 0; i < finalData.size(); i++)
            areaDataset.addValue(Double.parseDouble(finalData.get(i).get(yData))
                    ,finalData.get(i).get(target)
                    , timestamp);
    }

    public DefaultCategoryDataset getAreaData() {
        return areaDataset;
    }

    public void parseData(String separator) {
        for(int i = 0; i < data.size(); i++) {
            parsedData.add(data.get(i).split(separator));
        }
    }

    /**
     * Funcion para cambiar el caracter de decimal indicado por un punto
     * @param separator
     */
    public void decimalFormat(String separator) {
        for(int i = 0; i < parsedData.size(); i++)
            for(int j = 0; j < parsedData.get(i).length; j++)
                parsedData.get(i)[j] = parsedData.get(i)[j].replace(separator, ".");
    }

    public ArrayList<String> getData() {
        return data;
    }

    public ArrayList<String[]> getParsedData() {
        return parsedData;
    }

    public ArrayList<ArrayList<String>> getFinalData() {
        return finalData;
    }

    public void addFinalData(ArrayList<String> data) {
        finalData.add(data);
    }

    public void eraseHeaders() {
        headers = finalData.get(0);
        finalData.remove(0);
    }

    public void showParsedData() {
        for(String[] line : parsedData) {
            for(String chain : line) {
                System.out.print(chain + "\t");
            }
        }
    }

    public void storeBarValues(ArrayList<Integer> values) {
        barValues = values;
    }
    public void storeLineValues(ArrayList<Integer> values) {
        lineValues = values;
    }
    public void storeAreaValues(ArrayList<Integer> values) {
        areaValues = values;
    }

    public ArrayList<Integer> getBarValues() {
        return barValues;
    }
    public ArrayList<Integer> getLineValues() {
        return lineValues;
    }
    public ArrayList<Integer> getAreaValues() {
        return areaValues;
    }
}

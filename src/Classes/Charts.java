/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

/**
 *
 * @author Felipe
 */
import Config.Config;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.BasicStroke;
import java.awt.Color;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.plot.PlotOrientation;

public class Charts {

    private Config config;
    private XYSeries seriesDell;
    private XYSeries seriesMSI;
    private XYSeriesCollection dataset;
    private JFreeChart lineChart;
    private ComputerCompany dell;
    private ComputerCompany msi;

    /**
     * Constructor
     *
     * @param dell
     * @param msi
     */
    public Charts(ComputerCompany dell, ComputerCompany msi, Config config) {
        this.dell = dell;
        this.msi = msi;

        seriesDell = new XYSeries("DELL");
        seriesMSI = new XYSeries("MSI");
        dataset = new XYSeriesCollection();

        dataset.addSeries(seriesDell);
        dataset.addSeries(seriesMSI);

        lineChart = ChartFactory.createXYLineChart(
                "Time vs Profit",
                "Time",
                "Profit",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                false,
                false // URLs
        );

    }

    /**
     * Method to customize chart appearance
     */
    public void customizeChartUI(Color color) {
        XYPlot plot = getLineChart().getXYPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesStroke(0, new BasicStroke(1.0f));
        renderer.setSeriesPaint(0, color);
        plot.setRenderer(renderer);
    }

    public void updateChartData() {
        int dellProfit = getdell().getAccountant().getTotalProfitChart();
        int msiProfit = getmsi().getAccountant().getTotalProfitChart();

        int newTimestamp = getseriesDell().getItemCount() + 1;

        getseriesDell().addOrUpdate(newTimestamp, dellProfit);
        getseriesMSI().addOrUpdate(newTimestamp, msiProfit);
    }

    public ChartPanel getChartPanel() {
        return new ChartPanel(lineChart);
    }

    public ComputerCompany getdell() {
        return dell;
    }

    public void setdell(ComputerCompany dell) {
        this.dell = dell;
    }

    public ComputerCompany getmsi() {
        return msi;
    }

    public void setmsi(ComputerCompany msi) {
        this.msi = msi;
    }

    public XYSeries getseriesDell() {
        return seriesDell;
    }

    public void setseriesDell(XYSeries seriesDell) {
        this.seriesDell = seriesDell;
    }

    public XYSeries getseriesMSI() {
        return seriesMSI;
    }

    public void setseriesMSI(XYSeries seriesMSI) {
        this.seriesMSI = seriesMSI;
    }

    public Config getConfig() {
        return config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    public JFreeChart getLineChart() {
        return lineChart;
    }

    public void setLineChart(JFreeChart lineChart) {
        this.lineChart = lineChart;
    }

}

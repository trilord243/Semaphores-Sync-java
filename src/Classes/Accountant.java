/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import UserInterface.MainUI;

/**
 *
 * @author Felipe
 */
public class Accountant {

    private int companyInt;
    private int motherboardCosts;
    private int cpuCosts;
    private int ramCosts;
    private int powerSupplyCosts;
    private int gpuCosts;
    private int assemblersCosts;
    private int projectManagerCosts;
    private int directorCosts;

    private int standardComputerIncome;
    private int graphicsComputerIncome;

    private int totalOperationalCosts;
    private int totalIncome;

    private int totalProfit;
    private int totalProfitChart;
    private int totalIncomeChart;

    private MainUI userInterface;

    public Accountant(int companyInt, int motherboardCosts, int cpuCosts, int ramCosts,
            int powerSupplyCosts, int gpuCosts, int assemblersCosts, int projectManagerCosts,
            int directorCosts, int standardComputerIncome, int graphicsComputerIncome, int totalOperationalCosts,
            int totalIncome, int totalProfit) {
        this.companyInt = companyInt;
        this.motherboardCosts = motherboardCosts;
        this.cpuCosts = cpuCosts;
        this.ramCosts = ramCosts;
        this.powerSupplyCosts = powerSupplyCosts;
        this.gpuCosts = gpuCosts;
        this.assemblersCosts = assemblersCosts;
        this.projectManagerCosts = projectManagerCosts;
        this.directorCosts = directorCosts;
        this.standardComputerIncome = standardComputerIncome;
        this.graphicsComputerIncome = graphicsComputerIncome;
        this.totalOperationalCosts = totalOperationalCosts;
        this.totalIncome = totalIncome;
        this.totalProfit = totalProfit;
    }

    public Accountant(int companyInt, MainUI userInterface) {
        this.companyInt = companyInt;
        this.motherboardCosts = 0;
        this.cpuCosts = 0;
        this.ramCosts = 0;
        this.powerSupplyCosts = 0;
        this.gpuCosts = 0;
        this.assemblersCosts = 0;
        this.projectManagerCosts = 0;
        this.directorCosts = 0;
        this.standardComputerIncome = 0;
        this.graphicsComputerIncome = 0;
        this.totalOperationalCosts = 0;
        this.totalIncome = 0;
        this.totalProfit = 0;
        this.totalProfitChart = 0;
        this.totalIncomeChart = 0;
        this.userInterface = userInterface;
    }

    public void updatemotherboardCosts(int cost) {
        this.setmotherboardCosts(this.getmotherboardCosts() + cost);
    }

    public void updatecpuCosts(int cost) {
        this.setcpuCosts(this.getcpuCosts() + cost);
    }

    public void updateramCosts(int cost) {
        this.setramCosts(this.getramCosts() + cost);
    }

    public void updatepowerSupplyCosts(int cost) {
        this.setpowerSupplyCosts(this.getpowerSupplyCosts() + cost);
    }

    public void updateAssemblersCosts(int cost) {
        this.setAssemblersCosts(this.getAssemblersCosts() + cost);
    }

    public void updateProjectManagerCosts(int cost) {
        this.setProjectManagerCosts(this.getProjectManagerCosts() + cost);
    }

    public void updateDirectorCosts(int cost) {
        this.setDirectorCosts(this.getDirectorCosts() + cost);
    }

    public void updatestandardComputerIncome(int income) {
        this.setstandardComputerIncome(this.getstandardComputerIncome() + income);
    }

    public void updategraphicsComputerIncome(int income) {
        this.setgraphicsComputerIncome(this.getgraphicsComputerIncome() + income);
    }

    public void updateWorkerCosts(int typeInt, int cost) {
        switch (typeInt) {
            case -1:
                break;
            case 0:
                this.updatemotherboardCosts(cost);
                break;
            case 1:
                this.updatecpuCosts(cost);
                break;
            case 2:
                this.updateramCosts(cost);
                break;
            case 3:
                this.updatepowerSupplyCosts(cost);
                break;
            case 4:
                this.updatecpuCosts(cost);
                break;
            case 5:
                this.updateAssemblersCosts(cost);
                break;

        }
    }

    /**
     * @return
     */
    public int calculateTotalOperationalCosts() {
        this.setTotalOperationalCosts(
                this.getmotherboardCosts()
                + this.getcpuCosts()
                + this.getramCosts()
                + this.getpowerSupplyCosts()
                + this.getgpuCosts()
                + this.getAssemblersCosts()
                + this.getProjectManagerCosts()
                + this.getDirectorCosts());

        return this.getTotalOperationalCosts();

    }

    public void calculateTotalIncome() {
        this.setTotalIncome(
                this.getstandardComputerIncome()
                + this.getgraphicsComputerIncome());
    }

    public void calculateTotalProfit() {
        this.setTotalProfit(
                this.getTotalIncome()
                - this.getTotalOperationalCosts());
    }

    public void calculateTotalProfitChart() {
        this.setTotalProfitChart(
                this.getTotalIncomeChart()
                - this.getTotalOperationalCosts());
    }

    public void showCostsInUI(int totalCosts) {
        this.getUserInterface().showCosts(companyInt, totalCosts);
    }

    public void showIncomeInUI() {

    }

    public void showProfitInUI() {

    }

    /**
     * @return the companyInt
     */
    public int getcompanyInt() {
        return companyInt;
    }

    /**
     * @param companyInt the companyInt to set
     */
    public void setcompanyInt(int companyInt) {
        this.companyInt = companyInt;
    }

    /**
     * @return the motherboardCosts
     */
    public int getmotherboardCosts() {
        return motherboardCosts;
    }

    /**
     * @param motherboardCosts the motherboardCosts to set
     */
    public void setmotherboardCosts(int motherboardCosts) {
        this.motherboardCosts = motherboardCosts;
    }

    /**
     * @return the cpuCosts
     */
    public int getcpuCosts() {
        return cpuCosts;
    }

    /**
     * @param cpuCosts the cpuCosts to set
     */
    public void setcpuCosts(int cpuCosts) {
        this.cpuCosts = cpuCosts;
    }

    /**
     * @return the ramCosts
     */
    public int getramCosts() {
        return ramCosts;
    }

    /**
     * @param ramCosts the ramCosts to set
     */
    public void setramCosts(int ramCosts) {
        this.ramCosts = ramCosts;
    }

    /**
     * @return the powerSupplyCosts
     */
    public int getpowerSupplyCosts() {
        return powerSupplyCosts;
    }

    /**
     * @param powerSupplyCosts the powerSupplyCosts to set
     */
    public void setpowerSupplyCosts(int powerSupplyCosts) {
        this.powerSupplyCosts = powerSupplyCosts;
    }

    /**
     * @return the gpuCosts
     */
    public int getgpuCosts() {
        return gpuCosts;
    }

    /**
     * @param gpuCosts the gpuCosts to set
     */
    public void setgpuCosts(int gpuCosts) {
        this.gpuCosts = gpuCosts;
    }

    /**
     * @return the assemblersCosts
     */
    public int getAssemblersCosts() {
        return assemblersCosts;
    }

    /**
     * @param assemblersCosts the assemblersCosts to set
     */
    public void setAssemblersCosts(int assemblersCosts) {
        this.assemblersCosts = assemblersCosts;
    }

    /**
     * @return the projectManagerCosts
     */
    public int getProjectManagerCosts() {
        return projectManagerCosts;
    }

    /**
     * @param projectManagerCosts the projectManagerCosts to set
     */
    public void setProjectManagerCosts(int projectManagerCosts) {
        this.projectManagerCosts = projectManagerCosts;
    }

    /**
     * @return the directorCosts
     */
    public int getDirectorCosts() {
        return directorCosts;
    }

    /**
     * @param directorCosts the directorCosts to set
     */
    public void setDirectorCosts(int directorCosts) {
        this.directorCosts = directorCosts;
    }

    /**
     * @return the standardComputerIncome
     */
    public int getstandardComputerIncome() {
        return standardComputerIncome;
    }

    /**
     * @param standardComputerIncome the standardComputerIncome to set
     */
    public void setstandardComputerIncome(int standardComputerIncome) {
        this.standardComputerIncome = standardComputerIncome;
    }

    /**
     * @return the graphicsComputerIncome
     */
    public int getgraphicsComputerIncome() {
        return graphicsComputerIncome;
    }

    /**
     * @param graphicsComputerIncome the graphicsComputerIncome to set
     */
    public void setgraphicsComputerIncome(int graphicsComputerIncome) {
        this.graphicsComputerIncome = graphicsComputerIncome;
    }

    /**
     * @return the totalOperationalCosts
     */
    public int getTotalOperationalCosts() {
        return totalOperationalCosts;
    }

    /**
     * @param totalOperationalCosts the totalOperationalCosts to set
     */
    public void setTotalOperationalCosts(int totalOperationalCosts) {
        this.totalOperationalCosts = totalOperationalCosts;
    }

    /**
     * @return the totalIncome
     */
    public int getTotalIncome() {
        return totalIncome;
    }

    /**
     * @param totalIncome the totalIncome to set
     */
    public void setTotalIncome(int totalIncome) {
        this.totalIncome = totalIncome;
    }

    /**
     * @return the totalProfit
     */
    public int getTotalProfit() {
        return totalProfit;
    }

    /**
     * @param totalProfit the totalProfit to set
     */
    public void setTotalProfit(int totalProfit) {
        this.totalProfit = totalProfit;
    }

    /**
     * @return the userInterface
     */
    public MainUI getUserInterface() {
        return userInterface;
    }

    /**
     * @param userInterface the userInterface to set
     */
    public void setUserInterface(MainUI userInterface) {
        this.userInterface = userInterface;
    }

    public int getTotalProfitChart() {
        return totalProfitChart;
    }

    public void setTotalProfitChart(int totalProfitChart) {
        this.totalProfitChart = totalProfitChart;
    }

    public int getTotalIncomeChart() {
        return totalIncomeChart;
    }

    public void setTotalIncomeChart(int totalIncomeChart) {
        this.totalIncomeChart = totalIncomeChart;
    }

}

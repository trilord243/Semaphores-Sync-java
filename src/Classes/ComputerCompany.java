package Classes;

import Config.Parameters;
import UserInterface.MainUI;

import java.util.concurrent.Semaphore;

/**
 *
 * @author Felipe
 */
public class ComputerCompany {

    private int companyInt;
    private String companyString;
    private int maxWorkersQty;
    private int dayDurationInMs;
    private int deliveryDays;
    private Parameters studioParams;
    private Semaphore mutex;
    private Worker[] workers;
    private int totalCosts;
    private Drive drive;
    private Director director;
    private ProjectManager manager;
    private MainUI userInterface;
    private Accountant accountant;

    // Constructor with parameters
    public ComputerCompany(int companyInt, String companyString, int maxWorkersQty, Parameters studioParams,
            int dayDurationInMs,
            MainUI userInterface, int deliveryDays) {
        this.companyInt = companyInt;
        this.companyString = companyString;
        this.maxWorkersQty = maxWorkersQty;
        this.studioParams = studioParams;
        this.dayDurationInMs = dayDurationInMs;
        this.mutex = new Semaphore(1);
        this.drive = new Drive(companyInt, 25, 20, 55, 35, 10, studioParams.getcomputerSpecs(), userInterface);
        this.userInterface = userInterface;
        this.workers = new Worker[maxWorkersQty];
        this.totalCosts = 0;
        this.deliveryDays = deliveryDays;
        this.accountant = new Accountant(companyInt, userInterface);

    }

    public void start() {
        initializeManagerAndDirector();
        initializeWorkers();
    }

    public void printWorkersArray() {
        for (int i = 0; i < getWorkers().length; i++) {
            System.out.println(getWorkers()[i].getcomponentType());
        }
    }

    public void changeWorkerType(int workerType, int newWorkerType) {
        for (int i = 0; i < getWorkers().length; i++) {
            if (getWorkers()[i].getcomponentType() == workerType) {
                getWorkers()[i].changeParams(newWorkerType, getCompanyParams().getParamsByWorkerType(newWorkerType));

                // Sets workerType quantitys and interface values
                if (getCompanyParams().getParamsByWorkerType(workerType) != null) {
                    getCompanyParams().setParamsQuantityByWorkerType(workerType,
                            (getCompanyParams().getParamsByWorkerType(workerType).getQuantity() - 1));

                    getUserInterface().changeWorkersQtyTextByType(getcompanyInt(), workerType,
                            Integer.toString(getCompanyParams().getParamsByWorkerType(workerType).getQuantity()));
                }

                // Sets new workerType quantitys and interface values
                if (getCompanyParams().getParamsByWorkerType(newWorkerType) != null) {
                    getCompanyParams().setParamsQuantityByWorkerType(newWorkerType,
                            (getCompanyParams().getParamsByWorkerType(newWorkerType).getQuantity() + 1));

                    getUserInterface().changeWorkersQtyTextByType(getcompanyInt(), newWorkerType,
                            Integer.toString(getCompanyParams().getParamsByWorkerType(newWorkerType).getQuantity()));
                }
                break;
            }
        }
    }

    public boolean isFull() {
        return sumWorkers() == getMaxWorkersQty();
    }

    public int sumWorkers() {
        int sum = 0;

        for (int i = 0; i < getWorkers().length; i++) {
            if (getWorkers()[i].getcomponentType() != -1) {
                sum++;
            }
        }

        return sum;
    }

    public void initializeManagerAndDirector() {
        ProjectManager manager = new ProjectManager(getcompanyInt(), 40, getDeliveryDays(), getDayDurationInMs(),
                getUserInterface(), getMutex(), getAccountant());

        Director director = new Director(getcompanyInt(), 60, getUserInterface(), manager, getDrive(),
                getDayDurationInMs(), getMutex(), getAccountant());

        manager.start();
        director.start();
    }

    public void initializeWorkers() {
        int arrayIndex = 0;

        for (int i = 0; i <= 5; i++) {
            arrayIndex = initializeWorkersByType(i, arrayIndex);
        }

        if ((arrayIndex + 1) < getMaxWorkersQty()) {
            arrayIndex = initializeUnassignedWorkers(-1, arrayIndex);
        }

    }

    public int initializeWorkersByType(int workerType, int arrayIndex) {
        for (int i = 0; i < getCompanyParams().getWorkerParamsByType(workerType).getQuantity(); i++) {
            Worker worker = new Worker(getCompanyParams().getWorkerParamsByType(workerType).getcomponentName(), workerType,
                    getCompanyParams().getWorkerParamsByType(workerType).getSalaryPerHour(),
                    getCompanyParams().getWorkerParamsByType(workerType).getProductionRate(), getDayDurationInMs(),
                    getMutex(), getDrive(), getcompanyInt(), getAccountant());

            worker.start();

            getWorkers()[arrayIndex] = worker;
            arrayIndex += 1;
        }
        return arrayIndex;
    }

    public int initializeUnassignedWorkers(int unassignedType, int arrayIndex) {
        for (int index = arrayIndex; index < getMaxWorkersQty(); index++) {
            Worker worker = new Worker("Unassigned", -1, 0, 0, getDayDurationInMs(), getMutex(), getDrive(),
                    getcompanyInt(), getAccountant());

            worker.start();

            workers[arrayIndex] = worker;
            arrayIndex += 1;
        }

        return arrayIndex;
    }

    public int getWorkersCosts() {
        int currentCosts = 0;
        for (int i = 0; i < workers.length; i++) {
            currentCosts += workers[i].getAccumulatedSalary();
        }
        return currentCosts;
    }

    // Getters and Setters
    public int getcompanyInt() {
        return companyInt;
    }

    public void setcompanyInt(int companyInt) {
        this.companyInt = companyInt;
    }

    public int getMaxWorkersQty() {
        return maxWorkersQty;
    }

    public void setMaxWorkersQty(int maxWorkersQty) {
        this.maxWorkersQty = maxWorkersQty;
    }

    public Parameters getCompanyParams() {
        return studioParams;
    }

    public void setCompanyParams(Parameters studioParams) {
        this.studioParams = studioParams;
    }

    public int getDayDurationInMs() {
        return dayDurationInMs;
    }

    public void setDayDurationInMs(int dayDurationInMs) {
        this.dayDurationInMs = dayDurationInMs;
    }

    public Semaphore getMutex() {
        return mutex;
    }

    public void setMutex(Semaphore mutex) {
        this.mutex = mutex;
    }

    public int getTotalCosts() {
        return totalCosts;
    }

    public void setTotalCosts(int totalCosts) {
        this.totalCosts = totalCosts;
    }

    public String getcompanyString() {
        return companyString;
    }

    public void setcompanyString(String companyString) {
        this.companyString = companyString;
    }

    public Worker[] getWorkers() {
        return workers;
    }

    public void setWorkers(Worker[] workers) {
        this.workers = workers;
    }

    public Drive getDrive() {
        return drive;
    }

    public void setDrive(Drive drive) {
        this.drive = drive;
    }

    public MainUI getUserInterface() {
        return userInterface;
    }

    public void setUserInterface(MainUI userInterface) {
        this.userInterface = userInterface;
    }

    public int getDeliveryDays() {
        return deliveryDays;
    }

    public void setDeliveryDays(int deliveryDays) {
        this.deliveryDays = deliveryDays;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public ProjectManager getManager() {
        return manager;
    }

    public void setManager(ProjectManager manager) {
        this.manager = manager;
    }

    /**
     * @return the accountant
     */
    public Accountant getAccountant() {
        return accountant;
    }

    /**
     * @param accountant the accountant to set
     */
    public void setAccountant(Accountant accountant) {
        this.accountant = accountant;
    }
}

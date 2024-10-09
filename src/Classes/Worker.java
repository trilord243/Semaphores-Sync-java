package Classes;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

import Config.WorkerParams;

public class Worker extends Thread {

    private String componentName;
    private int componentType; 
    private int salaryPerHour;
    private float productionPerDay;
    private int dayDuration;
    private float productionAccount;
    private int accumulatedSalary;
    private Semaphore mutex;
    private Drive drive;
    private int companyInt; 
    private int assemblyTypeOnGoing = -1; 
    private Accountant accountant;

    public Worker(String componentName, int componentType, int salaryPerHour, float productionPerDay, int dayDuration,
            Semaphore mutex, Drive drive, int companyInt, Accountant accountant) {
        this.componentName = componentName;
        this.componentType = componentType;
        this.salaryPerHour = salaryPerHour;
        this.productionPerDay = productionPerDay;
        this.dayDuration = dayDuration;
        this.mutex = mutex;
        this.drive = drive;
        this.companyInt = companyInt;
        this.productionAccount = 0;
        this.accumulatedSalary = 0;
        this.accountant = accountant;
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (getcomponentType() == 5) {
                    assemble();
                } else {
                    work();
                }
                sleep(getDayDuration());
            } catch (InterruptedException ex) {
                Logger.getLogger(Worker.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public void work() {
        produce();
        getPaid();

        if (getProductionAccount() >= 1) {
            try {
                getMutex().acquire();
                this.accountant.updateWorkerCosts(componentType, this.getAccumulatedSalary());
                this.setAccumulatedSalary(0);
                getDrive().addElement(getcomponentType(), (int) getProductionAccount());
                getMutex().release();
            } catch (InterruptedException ex) {
                Logger.getLogger(Worker.class.getName()).log(Level.SEVERE, null, ex);
            }
            resetProductionAccount();
        }
    }

    public void assemble() {
        int typeOfChapterToAssemble;
        getPaid();

        if (this.getassemblyTypeOnGoing() == -1) {
            try {
                getMutex().acquire();
                this.accountant.updateWorkerCosts(componentType, this.getAccumulatedSalary());
                this.setAccumulatedSalary(0);
                typeOfChapterToAssemble = getDrive().decideWhichChapterToAssemble();
                if (typeOfChapterToAssemble != -1) {
                    getDrive().subtractcomponentElements(typeOfChapterToAssemble);
                    this.setassemblyTypeOnGoing(typeOfChapterToAssemble);
                }
                getMutex().release();
            } catch (InterruptedException ex) {
                Logger.getLogger(Worker.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        if (this.getassemblyTypeOnGoing() != -1) {
            produce();
            if (getProductionAccount() >= 1) {
                try {
                    getMutex().acquire();
                    getDrive().addChapterByType(this.getassemblyTypeOnGoing());
                    getMutex().release();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Worker.class.getName()).log(Level.SEVERE, null, ex);
                }

                resetProductionAccount();
                this.setassemblyTypeOnGoing(-1);
            }

        }

    }

    public void changeParams(int workerType, WorkerParams params) {
        switch (workerType) {
            case -1 -> {
                setProductionPerDay(0);
                setProductionAccount(0);

                setSalaryPerHour(0);

                setcomponentName("Unassigned");
                setcomponentType(-1);

            }
            case 0 -> {
                setProductionPerDay(params.getProductionRate());
                setProductionAccount(0);

                setSalaryPerHour(params.getSalaryPerHour());

                setcomponentName(params.getcomponentName());
                setcomponentType(0);
            }

            case 1 -> {
                setProductionPerDay(params.getProductionRate());
                setProductionAccount(0);

                setSalaryPerHour(params.getSalaryPerHour());

                setcomponentName(params.getcomponentName());
                setcomponentType(1);
            }

            case 2 -> {
                setProductionPerDay(params.getProductionRate());
                setProductionAccount(0);

                setSalaryPerHour(params.getSalaryPerHour());

                setcomponentName(params.getcomponentName());
                setcomponentType(2);
            }

            case 3 -> {
                setProductionPerDay(params.getProductionRate());
                setProductionAccount(0);

                setSalaryPerHour(params.getSalaryPerHour());

                setcomponentName(params.getcomponentName());
                setcomponentType(3);
            }

            case 4 -> {
                setProductionPerDay(params.getProductionRate());
                setProductionAccount(0);

                setSalaryPerHour(params.getSalaryPerHour());

                setcomponentName(params.getcomponentName());
                setcomponentType(4);
            }

            case 5 -> {
                setProductionPerDay(params.getProductionRate());
                setProductionAccount(0);

                setSalaryPerHour(params.getSalaryPerHour());

                setcomponentName(params.getcomponentName());
                setcomponentType(5);
            }

            default -> {
            }
        }
    }

    public void getPaid() {
        setAccumulatedSalary(getAccumulatedSalary() + (getSalaryPerHour() * 24));

    }

    private void produce() {
        setProductionAccount(getProductionAccount() + getProductionPerDay());
    }

    /**
     * @return the salaryPerHour
     */
    public int getSalaryPerHour() {
        return salaryPerHour;
    }

    /**
     * @param salaryPerHour the salaryPerHour to set
     */
    public void setSalaryPerHour(int salaryPerHour) {
        this.salaryPerHour = salaryPerHour;
    }

    /**
     * @return the productionPerDay
     */
    public float getProductionPerDay() {
        return productionPerDay;
    }

    /**
     * @param productionPerDay the productionPerDay to set
     */
    public void setProductionPerDay(float productionPerDay) {
        this.productionPerDay = productionPerDay;
    }

    /**
     * @return the dayDuration
     */
    public int getDayDuration() {
        return dayDuration;
    }

    /**
     * @param dayDuration the dayDuration to set
     */
    public void setDayDuration(int dayDuration) {
        this.dayDuration = dayDuration;
    }

    /**
     * @return the productionAccount
     */
    public float getProductionAccount() {
        return productionAccount;
    }

    /**
     * @param productionAccount the productionAccount to set
     */
    public void setProductionAccount(float productionAccount) {
        this.productionAccount = productionAccount;
    }

    public void resetProductionAccount() {
        setProductionAccount(0);
    }

    /**
     * @return the salaryAccount
     */
    public int getAccumulatedSalary() {
        return accumulatedSalary;
    }

    /**
     * @param salaryAccount the salaryAccount to set
     */
    public void setAccumulatedSalary(int salaryAccount) {
        this.accumulatedSalary = salaryAccount;
    }

    /**
     * @return the mutex
     */
    public Semaphore getMutex() {
        return mutex;
    }

    /**
     * @param mutex the mutex to set
     */
    public void setMutex(Semaphore mutex) {
        this.mutex = mutex;
    }

    /**
     * @return the componentName
     */
    public String getcomponentName() {
        return componentName;
    }

    /**
     * @param componentName the componentName to set
     */
    public void setcomponentName(String componentName) {
        this.componentName = componentName;
    }

    /**
     * @return the componentType
     */
    public int getcomponentType() {
        return componentType;
    }

    /**
     * @param componentType the componentType to set
     */
    public void setcomponentType(int componentType) {
        this.componentType = componentType;
    }

    /**
     * @return the drive
     */
    public Drive getDrive() {
        return drive;
    }

    /**
     * @param drive the drive to set
     */
    public void setDrive(Drive drive) {
        this.drive = drive;
    }

    public int getcompanyInt() {
        return companyInt;
    }

    public void setcompanyInt(int companyInt) {
        this.companyInt = companyInt;
    }

    /**
     * @return the assemblyTypeOnGoing
     */
    public int getassemblyTypeOnGoing() {
        return assemblyTypeOnGoing;
    }

    /**
     * @param assemblyTypeOnGoing the assemblyTypeOnGoing to set
     */
    public void setassemblyTypeOnGoing(int assemblyTypeOnGoing) {
        this.assemblyTypeOnGoing = assemblyTypeOnGoing;
    }



}

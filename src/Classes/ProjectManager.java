package Classes;

import javax.swing.JOptionPane;

import UserInterface.MainUI;
import static java.lang.Thread.sleep;
import java.util.concurrent.Semaphore;

/**
 *
 * @author Felipe
 */

/**
 * Representa al Gerente de Proyecto en la simulación de la compañía de computadoras.
 */
public class ProjectManager extends Thread {

    private int company; // Dell = 0, MSI = 1
    private int salaryPerHour;
    private int daysLeft;
    private float accumulatedSalary;
    private float accumulatedTime;
    private int defaultDeliveryDays;
    private int dayDurationInMs; 
    private MainUI userInterface;
    private float sixteenHoursTimeLapse; 
    private float eightHoursTimeLapse; 
    private float thirtyMinutesTimeLapse; 
    private boolean idle; 
    
      /**
     * Semáforo utilizado para controlar el acceso concurrente a recursos compartidos.
     */
    private Semaphore mutex;
    private Accountant accountant;
    private int discountedSalary;
    private int totalIncomeChart;
  /**
     * Constructor que inicializa el Gerente de Proyecto con sus parámetros.

     */
    public ProjectManager(int company, int salaryPerHour, int defaultDeliveryDays,
            int dayDurationInMs, MainUI userInterface, Semaphore mutex, Accountant accountant) {
        this.salaryPerHour = salaryPerHour;
        this.company = company;
        this.daysLeft = defaultDeliveryDays;
        this.defaultDeliveryDays = defaultDeliveryDays;
        this.dayDurationInMs = dayDurationInMs;
        this.userInterface = userInterface;
        this.accumulatedTime = 0;
        this.accumulatedSalary = 0;
        this.sixteenHoursTimeLapse = (float) (dayDurationInMs * 0.6667);
        this.eightHoursTimeLapse = (float) dayDurationInMs - this.sixteenHoursTimeLapse;
        this.thirtyMinutesTimeLapse = (float) (dayDurationInMs * 0.0208);
        this.idle = false;
        this.mutex = mutex;
        this.accountant = accountant;
        this.discountedSalary = 0;
        this.totalIncomeChart = 0;
    }

    @Override
    public void run() {

        while (true) {
            try {
                getPaid();
                if (getDaysLeft() >= 1) {
                    setDaysLeft(getDaysLeft() - 1);
                }

                 // Simula las primeras 16 horas del día
                while (getAccumulatedTime() < getSixteenHoursTimeLapse()) {
                    switchBetweenIdleAndWorking();
                }
                setIdle(false);

                // Simula las últimas 8 horas del día
                switchToChangingDaysLeft();

            } catch (InterruptedException e) {
                JOptionPane.showMessageDialog(null, "There's been an error with the Manager Thread: " + e);
            }
        }
    }
  /**
     * Reinicia el contador de días restantes para la entrega.
     */
    public void resetDaysLeft() {
        setDaysLeft(getDefaultDeliveryDays());
    }
    
    
    /**
     * Simula el cambio de días y actualiza los costos y ganancias.
     * Utiliza el semáforo para proteger las operaciones críticas.
     */
    public void switchToChangingDaysLeft() throws InterruptedException {
        String changingDaysLeftStatus = "Changing days";

        getUserInterface().changeManagerTextStatus(getcompany(), changingDaysLeftStatus);

        setAccumulatedTime(0);

        sleep((long) getEightHoursTimeLapse());

        if (getDaysLeft() >= 0) {
              // Sección crítica protegida por el semáforo
            getMutex().acquire();

            getAccountant().updateProjectManagerCosts(getSalaryPerHour() * 24);

            getAccountant().calculateTotalOperationalCosts();

            getUserInterface().showCosts(getcompany(), getAccountant().getTotalOperationalCosts());

            getAccountant().setTotalIncomeChart(getTotalIncomeChart());

            getAccountant().calculateTotalProfitChart();

            getUserInterface().getCharts().updateChartData();

            getUserInterface().changeDaysLeftCounter(getcompany(), Integer.toString(getDaysLeft()));
            getMutex().release();

        }
    }
    
      /**
     * Alterna entre estados de trabajo y ocio (ver anime).
     */
    public void switchBetweenIdleAndWorking() throws InterruptedException {

        String workingStatus = "Checking project advances";
        String watchingAnimeStatus = "Watching anime";

        updateStatusAndAccumulatedTime(workingStatus, watchingAnimeStatus);

    }
    
      /**
     * Actualiza el estado del gerente y el tiempo acumulado.
     */

    public void updateStatusAndAccumulatedTime(String workingStatus, String watchingAnimeStatus)
            throws InterruptedException {
        if (isIdle()) {
            getUserInterface().changeManagerTextStatus(getcompany(), watchingAnimeStatus);
        } else {
            getUserInterface().changeManagerTextStatus(getcompany(), workingStatus);
        }
        setAccumulatedTime(getAccumulatedTime() + getThirtyMinutesTimeLapse());
        setIdle(!isIdle());
        sleep((long) getThirtyMinutesTimeLapse());
    }
    
    
     /**
     * Actualiza el salario acumulado del gerente.
     */

    public void getPaid() {
        setAccumulatedSalary(getAccumulatedSalary() + (getSalaryPerHour() * 24));
    }

    // Getters and Setters
    public int getSalaryPerHour() {
        return salaryPerHour;
    }

    public void setSalaryPerHour(int salaryPerHour) {
        this.salaryPerHour = salaryPerHour;
    }

    public int getcompany() {
        return company;
    }

    public void setcompany(int company) {
        this.company = company;
    }

    public int getDaysLeft() {
        return daysLeft;
    }

    public void setDaysLeft(int daysLeft) {
        this.daysLeft = daysLeft;
    }

    public float getAccumulatedSalary() {
        return accumulatedSalary;
    }

    public void setAccumulatedSalary(float accumulatedSalary) {
        this.accumulatedSalary = accumulatedSalary;
    }

    public int getDefaultDeliveryDays() {
        return defaultDeliveryDays;
    }

    public void setDefaultDeliveryDays(int defaultDeliveryDays) {
        this.defaultDeliveryDays = defaultDeliveryDays;
    }

    public int getDayDurationInMs() {
        return dayDurationInMs;
    }

    public void setDayDurationInMs(int dayDurationInMs) {
        this.dayDurationInMs = dayDurationInMs;
    }

    public MainUI getUserInterface() {
        return userInterface;
    }

    public float getAccumulatedTime() {
        return accumulatedTime;
    }

    public void setAccumulatedTime(float accumulatedTime) {
        this.accumulatedTime = accumulatedTime;
    }

    public float getSixteenHoursTimeLapse() {
        return sixteenHoursTimeLapse;
    }

    public float getEightHoursTimeLapse() {
        return eightHoursTimeLapse;
    }

    public float getThirtyMinutesTimeLapse() {
        return thirtyMinutesTimeLapse;
    }

    public boolean isIdle() {
        return idle;
    }

    public void setIdle(boolean idle) {
        this.idle = idle;
    }

    public Semaphore getMutex() {
        return mutex;
    }

    public void setMutex(Semaphore mutex) {
        this.mutex = mutex;
    }

    public Accountant getAccountant() {
        return accountant;
    }

    public void setAccountant(Accountant accountant) {
        this.accountant = accountant;
    }

    public int getDiscountedSalary() {
        return discountedSalary;
    }

    public void setDiscountedSalary(int discountedSalary) {
        this.discountedSalary = discountedSalary;
    }

    public int getTotalIncomeChart() {
        return totalIncomeChart;
    }

    public void setTotalIncomeChart(int totalIncomeChart) {
        this.totalIncomeChart = totalIncomeChart;
    }

}

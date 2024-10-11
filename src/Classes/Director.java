/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.util.Random;

import javax.swing.JOptionPane;

import UserInterface.MainUI;
import static java.lang.Thread.sleep;
import java.util.concurrent.Semaphore;

/**
 *
 * @author Felipe
 */






/**
 * Representa al Director en la simulación de la compañía de computadoras.
 */
public class Director extends Thread {

    private int companyInt;
    private int salaryPerHour;
    private int dayDurationInMs;
    private MainUI userInterface;
    private ProjectManager manager;
    private Drive drive;
    private int accumulatedSalary;
    private int managerFaultsQty;
    private float accumulatedTime;
    private int discountedSalary;
    private int faultDiscount;
    private boolean trapped;
    private float thirtyFiveMinutesTimeLapse;
    private float oneHourTimeLapse;
    private float oneMinuteTimeLapse;
    
     /**
     * Semáforo utilizado para controlar el acceso concurrente a recursos compartidos.
     * Este semáforo se utiliza principalmente para sincronizar las operaciones
     * del Director con otros hilos, como el ProjectManager y los Workers.
     */
    private Semaphore mutex;
    private Accountant accountant;
    
    
    
       /**
     * Constructor que inicializa el Director con sus parámetros.
     */
    public Director(int companyInt, int salaryPerHour, MainUI userInterface, ProjectManager manager, Drive drive,
            int dayDurationInMs, Semaphore mutex, Accountant accountant) {
        this.companyInt = companyInt;
        this.salaryPerHour = salaryPerHour;
        this.dayDurationInMs = dayDurationInMs;
        this.userInterface = userInterface;
        this.manager = manager;
        this.drive = drive;
        this.accumulatedSalary = 0;
        this.accumulatedTime = 0;
        this.managerFaultsQty = 0;
        this.discountedSalary = 0;
        this.faultDiscount = 100;
        this.thirtyFiveMinutesTimeLapse = (float) (dayDurationInMs * 0.02431);
        this.oneHourTimeLapse = (float) (dayDurationInMs * 0.04167);
        this.oneMinuteTimeLapse = (float) (dayDurationInMs * 0.000694);
        this.mutex = mutex;
        this.accountant = accountant;
    }
    
    
    
    
    

    @Override
    public void run() {
        while (true) {// El director trabaja continuamente
            try {

                if (getManager().getDaysLeft() <= 0) {
                     // Cuando se acaba el plazo de entrega
                    sleep(getDayDurationInMs());  // Simula el paso de un día
                    
                    
                    
                    // Calcula los ingresos de las computadoras estándar y de alto rendimiento
                    int standardChaptersIncome = getDrive().getstandardComputersCounter()
                            * getDrive().getSpecs().getstandardPrice();

                    int plotTwistChaptersIncome = getDrive().getgraphicsComputersCounter()
                            * getDrive().getSpecs().gethighPerformancePrice();

                    // Sección crítica: actualiza contadores y reinicia días
                    getMutex().acquire(); // Adquiere el semáforo para exclusión mutua
                    getDrive().setgraphicsComputersCounter(0);
                    getDrive().setstandardComputersCounter(0);
                    getManager().resetDaysLeft();
                    getMutex().release();  // Libera el semáforo
                    // Actualiza ingresos y muestra resultados en la UI
                    getAccountant().setTotalIncome(plotTwistChaptersIncome + standardChaptersIncome);
                    getUserInterface().showEarnings(getcompanyInt(), getAccountant().getTotalIncome());

                    getUserInterface().resetChaptersCountersUI(getcompanyInt());

                    getAccountant().calculateTotalProfit();

                    getUserInterface().showProfit(getcompanyInt(), getAccountant().getTotalProfit());

                } else {
                    // Rutina diaria normal del director
                    Random random = new Random();

                    
                    int randomHour = random.nextInt(24); // Hora aleatoria para revisar al gerente
                   
                    int hoursPassed = 0;
                    String workingStatus = "Working";

                    getAccountant().updateDirectorCosts(getSalaryPerHour() * 24); // Actualiza costos del director


                    setTrapped(false);
                    setAccumulatedTime(0);
                     // Actualiza ingresos para el gráfico si hay computadoras producidas
                    if (getDrive().getstandardComputersCounter() != 0) {
                        int standardChaptersIncomeChart = getDrive().getstandardComputersCounter()
                                * getDrive().getSpecs().getstandardPrice();
                        int plotTwistChaptersIncomeChart = getDrive().getgraphicsComputersCounter()
                                * getDrive().getSpecs().gethighPerformancePrice();

                        getManager().setTotalIncomeChart(plotTwistChaptersIncomeChart + standardChaptersIncomeChart);
                    }

                   // Simula el paso de 24 horas
                    while (hoursPassed < 24) {
                        hoursPassed++;
                        // En la hora aleatoria, revisa el estado del gerente
                        if (hoursPassed == randomHour) {
                            float accumulatedTimeForWatchingInterval = 0;
                            checkManagerStatus(accumulatedTimeForWatchingInterval);
                        } else {
                            // En las otras horas, simplemente simula el paso del tiempo
                            setAccumulatedTime(getAccumulatedTime() + getOneHourTimeLapse());
                            sleep((long) getOneHourTimeLapse());
                        }
                        getUserInterface().changeDirectorStatusText(getcompanyInt(), workingStatus);
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "There's been an error with the Director Thread: " + e);
            }
        }

    }

     /**
     * Verifica el estado del gerente durante un intervalo de tiempo.
     * Este método no usa directamente el semáforo, pero interactúa con el ProjectManager
     * que sí lo utiliza para sincronizar su estado.
     */
    public void checkManagerStatus(float accumulatedTimeForWatchingInterval) throws InterruptedException {


        while (isTrapped() == false
                && (accumulatedTimeForWatchingInterval < (getThirtyFiveMinutesTimeLapse()))) {
            String directorWatchingStatus = "Watching Manager";
            getUserInterface().changeDirectorStatusText(getcompanyInt(),
                    directorWatchingStatus);

        
            if (getManager().isIdle()) {
                addManagerFault();
                updateManagerFaultsUI();
            }


            accumulatedTimeForWatchingInterval += getOneMinuteTimeLapse();
            setAccumulatedTime(getAccumulatedTime() + getOneMinuteTimeLapse());
            sleep((long) getOneMinuteTimeLapse());
        }


        float twentyFiveMinutesTimeLapse = getOneHourTimeLapse() - getThirtyFiveMinutesTimeLapse();
        sleep((long) twentyFiveMinutesTimeLapse);
        setAccumulatedTime(getAccumulatedTime()
                + twentyFiveMinutesTimeLapse);
    }
    

  /**
     * Añade una falta al gerente y actualiza el salario descontado.
     * Este método modifica el estado del ProjectManager, que está protegido por el semáforo.
     */
    public void addManagerFault() {
        setManagerFaultsQty(getManagerFaultsQty() + 1);
        setDiscountedSalary(getDiscountedSalary() + getFaultDiscount());
        getAccountant().updateProjectManagerCosts(-getFaultDiscount());
        setTrapped(true);
    }

    
        /**
     * Actualiza la interfaz de usuario con la información de faltas del gerente.
     */
    public void updateManagerFaultsUI() {
        getUserInterface().changeManagerFaultsQtyText(getcompanyInt(),
                Integer.toString(getManagerFaultsQty()));

        getUserInterface().changeManagerDiscountedText(getcompanyInt(),
                "$" + Integer.toString(getDiscountedSalary()));
    }
    
       /**
     * Actualiza el salario acumulado del Director.
     */

    public void getPaid() {
        setAccumulatedSalary(getAccumulatedSalary() + (getSalaryPerHour() * 24));
    }

    // Getters and Setters
    public int getcompanyInt() {
        return companyInt;
    }

    public void setcompanyInt(int companyInt) {
        this.companyInt = companyInt;
    }

    public int getSalaryPerHour() {
        return salaryPerHour;
    }

    public void setSalaryPerHour(int salaryPerHour) {
        this.salaryPerHour = salaryPerHour;
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

    public void setUserInterface(MainUI userInterface) {
        this.userInterface = userInterface;
    }

    public ProjectManager getManager() {
        return manager;
    }

    public void setManager(ProjectManager manager) {
        this.manager = manager;
    }

    public Drive getDrive() {
        return drive;
    }

    public void setDrive(Drive drive) {
        this.drive = drive;
    }

    public int getAccumulatedSalary() {
        return accumulatedSalary;
    }

    public void setAccumulatedSalary(int accumulatedSalary) {
        this.accumulatedSalary = accumulatedSalary;
    }

    public int getManagerFaultsQty() {
        return managerFaultsQty;
    }

    public void setManagerFaultsQty(int managerFaultsQty) {
        this.managerFaultsQty = managerFaultsQty;
    }

    public float getAccumulatedTime() {
        return accumulatedTime;
    }

    public void setAccumulatedTime(float accumulatedTime) {
        this.accumulatedTime = accumulatedTime;
    }

    public int getDiscountedSalary() {
        return discountedSalary;
    }

    public void setDiscountedSalary(int discountedSalary) {
        this.discountedSalary = discountedSalary;
    }

    public boolean isTrapped() {
        return trapped;
    }

    public void setTrapped(boolean trapped) {
        this.trapped = trapped;
    }

    public int getFaultDiscount() {
        return faultDiscount;
    }

    public void setFaultDiscount(int faultDiscount) {
        this.faultDiscount = faultDiscount;
    }

    public float getThirtyFiveMinutesTimeLapse() {
        return thirtyFiveMinutesTimeLapse;
    }

    public void setThirtyFiveMinutesTimeLapse(float thirtyFiveMinutesTimeLapse) {
        this.thirtyFiveMinutesTimeLapse = thirtyFiveMinutesTimeLapse;
    }

    public float getOneHourTimeLapse() {
        return oneHourTimeLapse;
    }

    public void setOneHourTimeLapse(float oneHourTimeLapse) {
        this.oneHourTimeLapse = oneHourTimeLapse;
    }

    public float getOneMinuteTimeLapse() {
        return oneMinuteTimeLapse;
    }

    public void setOneMinuteTimeLapse(float oneMinuteTimeLapse) {
        this.oneMinuteTimeLapse = oneMinuteTimeLapse;
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
}

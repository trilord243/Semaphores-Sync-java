/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Config;

/**
 *
 * @author Felipe
 */

/**
 * Clase que representa los parámetros de un tipo de trabajador en la simulación de la compañía de computadoras.
 */
public class WorkerParams {
  /** Cantidad de trabajadores de este tipo */
    private int quantity;
    
      /** Tasa de producción del trabajador */
    private float productionRate;
    /** Nombre o descripción del tipo de trabajador */

    private String typeString;
    
    /** Salario por hora del trabajador */
    private int salaryPerHour;
    /**
     * Constructor que inicializa todos los parámetros del trabajador.
     * @param quantity Cantidad inicial de trabajadores.
     * @param productionRate Tasa de producción del trabajador.
     * @param typeString Nombre o descripción del tipo de trabajador.
     * @param salaryPerHour Salario por hora del trabajador.
     */
    public WorkerParams(int quantity, float productionRate, String typeString, int salaryPerHour) {
        this.quantity = quantity;
        this.productionRate = productionRate;
        this.typeString = typeString;
        this.salaryPerHour = salaryPerHour;
    }

    // Getters and Setters
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getProductionRate() {
        return productionRate;
    }

    public void setProductionRate(float productionRate) {
        this.productionRate = productionRate;
    }

    public String getcomponentName() {
        return typeString;
    }

    public void setTypeString(String typeString) {
        this.typeString = typeString;
    }

    public int getSalaryPerHour() {
        return salaryPerHour;
    }

    public void setSalaryPerHour(int salaryPerHour) {
        this.salaryPerHour = salaryPerHour;
    }
}

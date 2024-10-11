/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

/**
 *
 * @author Felipe
 */


/**
 * Representa las especificaciones de las computadoras en la simulación.
 */
public class ComputerSpecs {

    private int[] highPerformanceSpecs;
    private int highPerformancePrice;
    private int[] standardSpecs;
    private int standardPrice;
    private int policyForHighPerformance;

    
    
        /**
     * Constructor que inicializa todas las especificaciones.
     */
    public ComputerSpecs(int[] highPerformanceSpecs, int highPerformancePrice, int[] standardSpecs,
            int standardPrice, int policyForHighPerformance) {
        this.highPerformanceSpecs = highPerformanceSpecs;
        this.highPerformancePrice = highPerformancePrice;
        this.standardSpecs = standardSpecs;
        this.standardPrice = standardPrice;
        this.policyForHighPerformance = policyForHighPerformance;
    }
    
      /**
     * Constructor que inicializa las especificaciones basadas en el tipo de estudio.
     */
    public ComputerSpecs(int studioInt) {
        this.setSpecs(studioInt);
    }

    
     /**
     * Verifica si los elementos disponibles cumplen con las especificaciones requeridas.
     */
    private boolean checkSpecs(int[] chapterElements, int[] chapterSpecs) {
        boolean flag = true;

        for (int i = 0; (i < chapterElements.length) && (flag); i++) {
            flag = (chapterElements[i] >= chapterSpecs[i]);
        }
        return flag;
    }
    
    
    
     /**
     * Verifica si se cumplen las especificaciones para una computadora estándar.
     */
    public boolean checkStandardSpecs(int[] chapterElements) {
        return this.checkSpecs(chapterElements, this.getstandardSpecs());
    }
    
    /**
     * Verifica si se cumplen las especificaciones para una computadora de alto rendimiento.
     */

    public boolean checkHighPerformanceSpecs(int[] chapterElements) {
        return this.checkSpecs(chapterElements, this.gethighPerformanceSpecs());
    }

    
       /**
     * Establece las especificaciones basadas en el tipo de estudio (Dell o MSI).
     */
    private void setSpecs(int studioInt) {
        boolean isDell = (studioInt == 0);

        sethighPerformanceSpecs((isDell) ? new int[] { 2, 1, 4, 4, 2 } : new int[] { 1, 2, 6, 5, 1 });
        sethighPerformancePrice((isDell) ? 500000 : 650000);
        setstandardSpecs((isDell) ? new int[] { 2, 1, 4, 4, 0 } : new int[] { 1, 2, 6, 5, 0 });
        setstandardPrice((isDell) ? 450000 : 300000);
        setpolicyForHighPerformance((isDell) ? 5 : 3);
    }

    /**
     * @return the highPerformanceSpecs
     */
    public int[] gethighPerformanceSpecs() {
        return highPerformanceSpecs;
    }

    /**
     * @param highPerformanceSpecs the highPerformanceSpecs to set
     */
    public void sethighPerformanceSpecs(int[] highPerformanceSpecs) {
        this.highPerformanceSpecs = highPerformanceSpecs;
    }

    /**
     * @return the highPerformancePrice
     */
    public int gethighPerformancePrice() {
        return highPerformancePrice;
    }

    /**
     * @param highPerformancePrice the highPerformancePrice to set
     */
    public void sethighPerformancePrice(int highPerformancePrice) {
        this.highPerformancePrice = highPerformancePrice;
    }

    /**
     * @return the standardSpecs
     */
    public int[] getstandardSpecs() {
        return standardSpecs;
    }

    /**
     * @param standardSpecs the standardSpecs to set
     */
    public void setstandardSpecs(int[] standardSpecs) {
        this.standardSpecs = standardSpecs;
    }

    /**
     * @return the standardPrice
     */
    public int getstandardPrice() {
        return standardPrice;
    }

    /**
     * @param standardPrice the standardPrice to set
     */
    public void setstandardPrice(int standardPrice) {
        this.standardPrice = standardPrice;
    }

    /**
     * @return the policyForHighPerformance
     */
    public int getpolicyForHighPerformance() {
        return policyForHighPerformance;
    }

    /**
     * @param policyForHighPerformance the policyForHighPerformance to set
     */
    public void setpolicyForHighPerformance(int policyForHighPerformance) {
        this.policyForHighPerformance = policyForHighPerformance;
    }

}

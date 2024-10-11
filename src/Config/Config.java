package Config;

/**
 *
 * @author Felipe
 */

/**
 * Clase que maneja la configuración general de la simulación de las compañías de computadoras.
 */
public class Config {

     /** Parámetros específicos para la compañía Dell */
    private Parameters DellParameters;
       /** Parámetros específicos para la compañía MSI */
    private Parameters MsiParameters;
    
     /** Duración de un día en la simulación (en milisegundos) */
    private int dayDuration;
    
       /** Número de días para la entrega de computadoras */
    private int deliveryDays;

    
       /**
     * Constructor que inicializa la configuración con valores predeterminados.
     */
    public Config() {
        this.DellParameters = new Parameters();
        this.MsiParameters = new Parameters();
        this.dayDuration = 1000; // 1 segundo por defecto
        this.deliveryDays = 30; // 30 días por defecto
    }

    
       /**
     * Imprime los parámetros de ambas compañías.

     */
    public void printCompanyParameters() {
        System.out.println("DELL Parameters:");
        printParameters(DellParameters);

        System.out.println("MSI ¡ Parameters:");
        printParameters(MsiParameters);
    }
    
        /**
     * Método auxiliar para imprimir los parámetros de una compañía.
     * Nota: Este método está incompleto y solo verifica si los parámetros son nulos.
     */

    private void printParameters(Parameters parameters) {
        if (parameters == null) {
            System.out.println("No parameters set.");
            return;
        }

 
    }

    // Getters and Setters
    public Parameters getDellParameters() {
        return DellParameters;
    }

    public void setDellParameters(Parameters DellParameters) {
        this.DellParameters = DellParameters;
    }

    public Parameters getMsiParameters() {
        return MsiParameters;
    }

    public void setMsiParameters(Parameters MsiParameters) {
        this.MsiParameters = MsiParameters;
    }

    public int getDayDuration() {
        return dayDuration;
    }

    public void setDayDuration(int dayDuration) {
        this.dayDuration = dayDuration;
    }

    public int getDeliveryDays() {
        return deliveryDays;
    }

    public void setDeliveryDays(int deliveryDays) {
        this.deliveryDays = deliveryDays;
    }

}

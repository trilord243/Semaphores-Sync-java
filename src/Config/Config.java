package Config;

/**
 *
 * @author Felipe
 */
public class Config {

    private Parameters DellParameters;
    private Parameters MsiParameters;
    private int dayDuration;
    private int deliveryDays;

    public Config() {
        this.DellParameters = new Parameters();
        this.MsiParameters = new Parameters();
        this.dayDuration = 1000;
        this.deliveryDays = 30;
    }

    public void printCompanyParameters() {
        System.out.println("DELL Parameters:");
        printParameters(DellParameters);

        System.out.println("MSI ¡ Parameters:");
        printParameters(MsiParameters);
    }

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

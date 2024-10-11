package UserInterface;

import Classes.ComputerCompany;
import Config.Config;
import Config.ReadFile;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextPane;

/**
 *
 * @author Felipe
 */


/**
 * Clase que maneja funciones relacionadas con la interfaz de usuario y la configuración de la simulación.
 */
public class UIFunctions {
/**
     * Reduce la cantidad de trabajadores de un tipo específico en una compañía.
     * @param company La compañía de computadoras.
     * @param workerType El tipo de trabajador a reducir.
     */
    public void reduceWorkersByType(ComputerCompany company, int workerType) {
        if (company.getCompanyParams().getParamsByWorkerType(workerType).getQuantity() > 1) {
            company.changeWorkerType(workerType, -1);
        } else {
            JOptionPane.showMessageDialog(null, "You have reached minimum capacity for " + company.getCompanyParams().getParamsByWorkerType(workerType).getcomponentName() + "s");
        }
    }
  /**
     * Aumenta la cantidad de trabajadores de un tipo específico en una compañía.
     * @param company La compañía de computadoras.
     * @param workerType El tipo de trabajador a aumentar.
     */
    public void increaseWorkersByType(ComputerCompany company, int workerType) {
        if (!company.isFull()) {
            company.changeWorkerType(-1, workerType);
        } else {
            JOptionPane.showMessageDialog(null, "You have reached maximum capacity");
        }
    }
 /**
     * Configura los ajustes iniciales en la interfaz de usuario.
     * @param config La configuración general.
     * @param ... Varios JSpinner y JTextPane para diferentes elementos de la UI.
     */
    public void setInitialSettings(Config config, JSpinner motherBoardDell, JSpinner cpuDell,
            JSpinner rammDell, JSpinner powerSupplyDell, JSpinner gpuDell, JSpinner assemblersDell,
            JSpinner motherBoardMsi, JSpinner cpuMsi, JSpinner ramMsi, JSpinner poweSupplyMsi,
            JSpinner gpuMsi, JSpinner assemblerMsi, JTextPane dayDurationInput,
            JTextPane deliveryDaysInput) {

        loadFile(config);
        setSpinnersValues(0, config, motherBoardDell, cpuDell, rammDell, powerSupplyDell,
                gpuDell, assemblersDell);
        setSpinnersValues(1, config, motherBoardMsi, cpuMsi, ramMsi, poweSupplyMsi,
                gpuMsi, assemblerMsi);
        setInputValues(dayDurationInput, deliveryDaysInput, config);
    }
    /**
     * Carga la configuración desde un archivo.
     * @param config El objeto de configuración a actualizar.
     */
    public void loadFile(Config config) {
        try {
            ReadFile fileToRead = new ReadFile();
            String txt = fileToRead.readTxt();
            fileToRead.readConfig(txt, config);

            JOptionPane.showMessageDialog(null, "Config loaded succesfully!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "An error has occurred: " + e);
        }
    }

     /**
     * Genera una cadena de configuración para una compañía.
     * @param companyName Nombre de la compañía.
     * @param configString Cadena de configuración actual.
     * @param ... Cantidades de diferentes tipos de trabajadores.
     * @return La cadena de configuración actualizada.
     */
  public String generateConfigString(String companyName, String configString, int motherboardProducers, int cpuProducers,
        int ramProducers, int powerSupplyProducers, int gpuProducers, int assemblers) {
    configString += "\n" + companyName + "\nmotherboardProducers\n" + motherboardProducers + "\ncpuProducers\n" + cpuProducers
            + "\nramProducers\n" + ramProducers + "\npowerSupplyProducers\n" + powerSupplyProducers
            + "\ngpuProducers\n" + gpuProducers + "\nassemblers\n" + assemblers + "\n";

    return configString;
}
  /**
     * Configura los valores de entrada en la interfaz de usuario.
     * @param dayDurationInput Campo de texto para la duración del día.
     * @param deliveryDaysInput Campo de texto para los días de entrega.
     * @param config Configuración actual.
     */
    public void setInputValues(JTextPane dayDurationInput, JTextPane deliveryDaysInput, Config config) {
        dayDurationInput.setText(Integer.toString(config.getDayDuration() / 1000));
        deliveryDaysInput.setText(Integer.toString(config.getDeliveryDays()));

    }
  /**
     * Configura los valores de los spinners en la interfaz de usuario.
     * @param company Identificador de la compañía (0 para Dell, 1 para MSI).
     * @param config Configuración actual.
     * @param ... Varios JSpinner para diferentes tipos de trabajadores.
     */
    public void setSpinnersValues(int company, Config config, JSpinner motherBoard, JSpinner cpu,
            JSpinner ram, JSpinner powerSupply, JSpinner gpu, JSpinner assemblers) {

        switch (company) {
            case 0 -> {
                motherBoard.setValue(config.getDellParameters().getmotherboardParams().getQuantity());
                cpu.setValue(config.getDellParameters().getcpuParams().getQuantity());
                ram.setValue(config.getDellParameters().getramParams().getQuantity());
                powerSupply.setValue(config.getDellParameters().getpowerSupplyParams().getQuantity());
                gpu.setValue(config.getDellParameters().getgpuParams().getQuantity());
                assemblers.setValue(config.getDellParameters().getAssemblersParams().getQuantity());

            }

            case 1 -> {
                motherBoard.setValue(config.getMsiParameters().getmotherboardParams().getQuantity());
                cpu.setValue(config.getMsiParameters().getcpuParams().getQuantity());
                ram.setValue(config.getMsiParameters().getramParams().getQuantity());
                powerSupply.setValue(config.getMsiParameters().getpowerSupplyParams().getQuantity());
                gpu.setValue(config.getMsiParameters().getgpuParams().getQuantity());
                assemblers.setValue(config.getMsiParameters().getAssemblersParams().getQuantity());
            }

            default -> {
            }

        }

    }
}

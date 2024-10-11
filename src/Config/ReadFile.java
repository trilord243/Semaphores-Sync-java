package Config;

import Classes.ComputerSpecs;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

/**
 *
 * @author Felipe
 */


/**
 * Clase que maneja la lectura y escritura de la configuración del proyecto desde/hacia un archivo de texto.
 */
public class ReadFile {

    /**
     * Sobrescribe el archivo de texto de configuración con nueva información.
     * @param txt String que contiene la nueva configuración.
     */
    public void printTxt(String txt) {
        try {
            File file = new File("test\\config.txt");
            PrintWriter pw = new PrintWriter(file);
            pw.print(txt);
            JOptionPane.showMessageDialog(null, "The configuration has changed!");
            pw.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "An error has ocurred: " + e);
        }
    }

       /**
     * Añade información al final del archivo de configuración existente.
     * @param txt String que contiene la información adicional.
     */
    public void appendTxt(String txt) {
        try {
            File file = new File("test\\config.txt");
            PrintWriter pw = new PrintWriter(new FileWriter(file, true));
            pw.append(txt);
            pw.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "An error has ocurred: " + e);
        }
    }

   /**
     * Lee el archivo de configuración y lo convierte en un string.
     * @return String que contiene toda la información del archivo de configuración.
     */
    public String readTxt() {
        String line;
        String txt = "";
        File file = new File("test\\config.txt");
        try {
            if (!file.exists()) {
                file.createNewFile();

            } else {
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);

                while ((line = br.readLine()) != null) {
                    if (!line.isEmpty()) {
                        if (line.contains("General") || line.contains("DELL")
                                || line.contains("MSI")) {
                            txt += "~\n";

                        } else {
                            txt += line + "\n";
                        }
                    }

                }
                br.close();

                return txt;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "An error occurred reading the file: " + e);
        }
        return null;
    }
    
    
    
 /**
     * Configura los parámetros específicos de una compañía (Dell o MSI) basándose en la información leída del archivo.
     * @param companyParameter Array de strings con los parámetros de la compañía.
     * @param config Objeto Config donde se almacenarán los parámetros.
     * @param studio Entero que indica qué compañía se está configurando (0 para Dell, 1 para MSI).
     */
    public void setStudioConfig(String[] companyParameter, Config config, int studio) {
    String[] workerTypeStrings = {"Motherboard Producer", "CPU Producer", "RAM Memory Producer", "Power Supply Producer",
        "GPU Producer", "Assembling producer"};

    int[] workerSalariesPerHour = {20, 26, 40, 16, 34, 50};

    switch (studio) {
        case 0 -> { // Dell
            ComputerSpecs dellSpecs = new ComputerSpecs(0);

            // Ajustar las tasas de producción y parámetros con base en el carnet 20171110576
            WorkerParams motherboardProducer = new WorkerParams(Integer.parseInt(companyParameter[2]), 1/4f, workerTypeStrings[0], workerSalariesPerHour[0]);
            WorkerParams cpuProducer = new WorkerParams(Integer.parseInt(companyParameter[4]), 1/4f, workerTypeStrings[1], workerSalariesPerHour[1]);
            WorkerParams ramProducer = new WorkerParams(Integer.parseInt(companyParameter[6]), 1f, workerTypeStrings[2], workerSalariesPerHour[2]);
            WorkerParams powerSupplyProducer = new WorkerParams(Integer.parseInt(companyParameter[8]), 5f, workerTypeStrings[3], workerSalariesPerHour[3]);
            WorkerParams gpuProducer = new WorkerParams(Integer.parseInt(companyParameter[10]), 1/2f, workerTypeStrings[4], workerSalariesPerHour[4]);
            WorkerParams assemblers = new WorkerParams(Integer.parseInt(companyParameter[12]), 2f, workerTypeStrings[5], workerSalariesPerHour[5]);

            Parameters dellParameter = new Parameters(motherboardProducer, cpuProducer, ramProducer, powerSupplyProducer, gpuProducer, assemblers, dellSpecs);
            config.setDellParameters(dellParameter);
        }

        case 1 -> { // MSI
            ComputerSpecs msiSpecs = new ComputerSpecs(1);

            // Ajustar las tasas de producción y parámetros con base en el carnet 20211110354
            WorkerParams motherboardProducer = new WorkerParams(Integer.parseInt(companyParameter[2]), 1/3f, workerTypeStrings[0], workerSalariesPerHour[0]);
            WorkerParams cpuProducer = new WorkerParams(Integer.parseInt(companyParameter[4]), 1/3f, workerTypeStrings[1], workerSalariesPerHour[1]);
            WorkerParams ramProducer = new WorkerParams(Integer.parseInt(companyParameter[6]), 2f, workerTypeStrings[2], workerSalariesPerHour[2]);
            WorkerParams powerSupplyProducer = new WorkerParams(Integer.parseInt(companyParameter[8]), 3f, workerTypeStrings[3], workerSalariesPerHour[3]);
            WorkerParams gpuProducer = new WorkerParams(Integer.parseInt(companyParameter[10]), 1/3f, workerTypeStrings[4], workerSalariesPerHour[4]);
            WorkerParams assemblers = new WorkerParams(Integer.parseInt(companyParameter[12]), 2f, workerTypeStrings[5], workerSalariesPerHour[5]);

            Parameters msiParameters = new Parameters(motherboardProducer, cpuProducer, ramProducer, powerSupplyProducer, gpuProducer, assemblers, msiSpecs);
            config.setMsiParameters(msiParameters);
        }

        default -> {
        }
    }
}
 /**
     * Lee la configuración del archivo y la aplica al objeto Config.
     * @param txt String que contiene toda la información de configuración.
     * @param config Objeto Config donde se aplicará la configuración leída.
     */

    public void readConfig(String txt, Config config) {

        String[] configs = txt.split("~");
        String[] general = configs[1].split("\n");

         // Configuración general
        config.setDayDuration(Integer.parseInt(general[2]));
        config.setDeliveryDays(Integer.parseInt(general[4]));

          // Configuración de Dell
        String[] dellParameter = configs[2].split("\n");
        setStudioConfig(dellParameter, config, 0);

       // Configuración de MSI
        String[] msiParameter = configs[3].split("\n");
        setStudioConfig(msiParameter, config, 1);

    }
}

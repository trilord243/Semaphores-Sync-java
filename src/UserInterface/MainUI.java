package UserInterface;

import Classes.ComputerCompany;
import Classes.Charts;
import Config.Config;
import Config.ReadFile;
import java.awt.Color;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;

/**
 *
 * @author Felipe
 */
public class MainUI extends javax.swing.JFrame {

    private Config config = new Config();
    private UIFunctions functions = new UIFunctions();
    private ComputerCompany Dell;
    private ComputerCompany Msi;
    private Charts charts;
    private static final String pattern = "#,###.##";
    private static final DecimalFormat formater = new DecimalFormat(pattern);

    /**
     * Creates new form MainUI
     */
    public MainUI() {
        super("Dashboard");

        initComponents();
        setVisible(true);
        setLocationRelativeTo(null);

        getFunctions().setInitialSettings(config, productores_placa_base_dell, productores_cpu_dell, productores_memoria_ram_dell,
                productores_fuente_alimentacion_dell, productores_gpu_dell, productores_ensambladores_dell, productores_placa_base_msi, productores_cpu_msi,
                productores_memoria_ram_msi, productores_fuente_alimentacion_msi, productores_gpu_msi, productores_ensambladores_msi, dayDurationInput, deliveryDaysInput);

        this.Dell = new ComputerCompany(0, "Dell", 18, getConfig().getDellParameters(),
                getConfig().getDayDuration(), this, getConfig().getDeliveryDays());
        this.Msi = new ComputerCompany(1, "Msi", 16, getConfig().getMsiParameters(),
                getConfig().getDayDuration(), this, getConfig().getDeliveryDays());

        this.charts = new Charts(getDell(), getMsi(), getConfig());

        getCharts().customizeChartUI(Color.ORANGE);

        chartPanel.add(getCharts().getChartPanel());

        setInitialUIParameters();
        toggleWorkersQuantityButtons(false);
    }

    public void resetChaptersCountersUI(int studioInt) {
        switch (studioInt) {
            case 0 -> {
                standardComputer_dell_qty.setText("0");
                gpuComputer_dell_qty.setText("0");
            }

            case 1 -> {
                standarComputermsi.setText("0");
                gpuComputerMSI.setText("0");
            }

            default -> {
            }
        }
    }

    public void toggleWorkersQuantityButtons(boolean enabled) {
        dell_MotherBoard_min.setEnabled(enabled);
        dell_cpu_min.setEnabled(enabled);
        dell_ram_min.setEnabled(enabled);
        dell_powerSupply_min.setEnabled(enabled);
        dell_gpu_min.setEnabled(enabled);
        dell_assembly_mina.setEnabled(enabled);

        dell_motherboard_plus.setEnabled(enabled);
        dell_cpu_plus.setEnabled(enabled);
        dell_ram_plus.setEnabled(enabled);
        dell_power_supply_plus.setEnabled(enabled);
        dell_gpu_plus.setEnabled(enabled);
        dell_assembly_plus.setEnabled(enabled);

        motherboard_Min_msi.setEnabled(enabled);
        cpu_Min_msi.setEnabled(enabled);
        tam_Min_msi.setEnabled(enabled);
        powerSupply_Min_msi.setEnabled(enabled);
        gpu_Min_msi.setEnabled(enabled);
        assembling_Min_msi.setEnabled(enabled);

        motherboard_plus_msi.setEnabled(enabled);
        cpu_plus_msi.setEnabled(enabled);
        ram_plus_msi.setEnabled(enabled);
        power_supply_plus_msi.setEnabled(enabled);
        gpu_plus_msi.setEnabled(enabled);
        assemblin_plus_msi.setEnabled(enabled);
    }

    public void toggleStartSimulationButton(boolean enabled) {
        startSimulation.setEnabled(enabled);
    }

    public void setInitialUIParameters() {
        setInitialDaysLeftUI();
        setInitialWorkersQtyUI();
    }

    public void setInitialDaysLeftUI() {
        for (int i = 0; i < 2; i++) {
            changeDaysLeftCounter(i, Integer.toString(getConfig().getDeliveryDays()));
        }
    }

    public void setInitialWorkersQtyUI() {
        for (int i = 0; i < 6; i++) {
            changeWorkersQtyTextByType(0, i,
                    Integer.toString(getConfig().getDellParameters().getParamsByWorkerType(i).getQuantity()));

            changeWorkersQtyTextByType(1, i,
                    Integer.toString(getConfig().getMsiParameters().getParamsByWorkerType(i).getQuantity()));
        }
    }

    public void changeWorkersQtyTextByType(int studioInt, int workerType, String workersQty) {
        switch (studioInt) {
            case 0 -> {
                switch (workerType) {
                    case 0 ->
                        dell_motherBoard.setText(workersQty);
                    case 1 ->
                        dell_cpu.setText(workersQty);
                    case 2 ->
                        dell_ram.setText(workersQty);
                    case 3 ->
                        dell_power_supply.setText(workersQty);
                    case 4 ->
                        dell_gpu.setText(workersQty);
                    case 5 ->
                        dell_assembly.setText(workersQty);
                    default -> {
                    }
                }
            }

            case 1 -> {
                switch (workerType) {
                    case 0 ->
                        qty_MotherBoard_msi.setText(workersQty);
                    case 1 ->
                        qty_cpu_msi.setText(workersQty);
                    case 2 ->
                        qty_ram_msi.setText(workersQty);
                    case 3 ->
                        qty_powerS_msi.setText(workersQty);
                    case 4 ->
                        qty_gpu_msi.setText(workersQty);
                    case 5 ->
                        qty_assembli_msi.setText(workersQty);
                    default -> {
                    }
                }
            }

            default -> {
            }
        }
    }

    public void changeDirectorStatusText(int studioInt, String directorStatus) {
        switch (studioInt) {
            case 0 ->
                director_status_dell.setText(directorStatus);
            case 1 ->
                directorStatusmsi.setText(directorStatus);
            default -> {
            }
        }
    }

    public void changeManagerFaultsQtyText(int studioInt, String faultsQty) {
        switch (studioInt) {
            case 0 ->
                qty_faults_dell.setText(faultsQty);
            case 1 ->
                faultsQtymsi.setText(faultsQty);
            default -> {
            }
        }
    }

    public void changeChapterQuantity(int studioInt, int chapterType, int chapterQuantity) {

        if (studioInt == 0) {

            switch (chapterType) {

                case 0 -> {

                    this.standardComputer_dell_qty.setText(Integer.toString(chapterQuantity));

                }

                case 1 -> {

                    this.gpuComputer_dell_qty.setText(Integer.toString(chapterQuantity));

                }

            }

        } else {

            switch (chapterType) {

                case 0 -> {

                    this.standarComputermsi.setText(Integer.toString(chapterQuantity));

                }

                case 1 -> {

                    this.gpuComputerMSI.setText(Integer.toString(chapterQuantity));

                }

            }

        }

    }

    public void changeDriveElements(int studioInt, int workerType, int[] chapterElements) {

        if (studioInt == 0) {
            switch (workerType) {
                case 0 -> {
                    dell_motherboard.setText(Integer.toString(chapterElements[workerType]));
                }
                case 1 -> {
                    cpu_dell.setText(Integer.toString(chapterElements[workerType]));
                }
                case 2 -> {
                    ram_dell.setText(Integer.toString(chapterElements[workerType]));
                }
                case 3 -> {
                    fuente_poder_dell.setText(Integer.toString(chapterElements[workerType]));
                }
                case 4 -> {
                    gpu_dell.setText(Integer.toString(chapterElements[workerType]));
                }
                case 5 -> {
                    dell_motherboard.setText(Integer.toString(chapterElements[0]));
                    cpu_dell.setText(Integer.toString(chapterElements[1]));
                    ram_dell.setText(Integer.toString(chapterElements[2]));
                    fuente_poder_dell.setText(Integer.toString(chapterElements[3]));
                    gpu_dell.setText(Integer.toString(chapterElements[4]));

                }

                default -> {
                }
            }
        } else {
            switch (workerType) {
                case 0 -> {
                    MSI_scritpt_motherboard.setText(Integer.toString(chapterElements[workerType]));
                }
                case 1 -> {
                    MSI_script_cpu.setText(Integer.toString(chapterElements[workerType]));
                }
                case 2 -> {
                    MSI_script_ram.setText(Integer.toString(chapterElements[workerType]));
                }
                case 3 -> {
                    MSI_script_power_supply.setText(Integer.toString(chapterElements[workerType]));
                }
                case 4 -> {
                    MSI_script_gpu.setText(Integer.toString(chapterElements[workerType]));
                }
                case 5 -> {
                    MSI_scritpt_motherboard.setText(Integer.toString(chapterElements[0]));
                    MSI_script_cpu.setText(Integer.toString(chapterElements[1]));
                    MSI_script_ram.setText(Integer.toString(chapterElements[2]));
                    MSI_script_power_supply.setText(Integer.toString(chapterElements[3]));
                    MSI_script_gpu.setText(Integer.toString(chapterElements[4]));

                }

                default -> {
                }
            }
        }
    }

    public void changeManagerDiscountedText(int studioInt, String discountedSalary) {
        switch (studioInt) {
            case 0 ->
                manager_discounted_qty_dekk.setText(discountedSalary);
            case 1 ->
                managerSalaryDiscountmsi.setText(discountedSalary);
            default -> {
            }
        }
    }

    public void changeManagerTextStatus(int studioInt, String status) {
        if (studioInt == 0) {
            manager_status_dell.setText(status);
        } else {
            managerStatusmsi.setText(status);
        }
    }

    public void changeDaysLeftCounter(int studioInt, String daysLeft) {
        if (studioInt == 0) {
            daysLeftdell.setText(daysLeft);
        } else {
            daysLeftmsi.setText(daysLeft);
        }
    }

    public void showCosts(int studioInt, int totalCosts) {

        switch (studioInt) {
            case 0:
                this.costsdell.setText("$" + formater.format(totalCosts));
                break;
            case 1:
                this.costsmsi.setText("$" + formater.format(totalCosts));
                break;
        }

    }

    public void showEarnings(int studioInt, int totalEarnings) {
        switch (studioInt) {
            case 0:
                this.earningsdell.setText("$" + formater.format(totalEarnings));
                break;
            case 1:
                this.earningsmsi.setText("$" + formater.format(totalEarnings));
                break;
        }

    }

    public void showProfit(int studioInt, int totalProfit) {
        switch (studioInt) {
            case 0:
                this.utilitydell.setText("$" + formater.format(totalProfit));
                break;
            case 1:
                this.utilitymsi.setText("$" + formater.format(totalProfit));
                break;
        }
    }

    // Getters and Setters
    public final Config getConfig() {
        return config;
    }

    public final void setConfig(Config config) {
        this.config = config;
    }

    public final UIFunctions getFunctions() {
        return functions;
    }

    public void setFunctions(UIFunctions functions) {
        this.functions = functions;
    }

    public ComputerCompany getDell() {
        return Dell;
    }

    public void setDell(ComputerCompany Dell) {
        this.Dell = Dell;
    }

    public ComputerCompany getMsi() {
        return Msi;
    }

    public void setMsi(ComputerCompany msi) {
        this.Msi = Msi;
    }

    public Charts getCharts() {
        return charts;
    }

    public void setCharts(Charts charts) {
        this.charts = charts;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Tabs = new javax.swing.JTabbedPane();
        ConfigurationTab = new javax.swing.JPanel();
        Config_label = new javax.swing.JLabel();
        General_label = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        dayDurationInput = new javax.swing.JTextPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        deliveryDaysInput = new javax.swing.JTextPane();
        setConfiguration = new javax.swing.JButton();
        infoLabel = new javax.swing.JLabel();
        dayDuration_label = new javax.swing.JLabel();
        deliveryDays_label = new javax.swing.JLabel();
        DellConfig_label = new javax.swing.JLabel();
        DelllMinWorker_label = new javax.swing.JLabel();
        MSIConfig_label = new javax.swing.JLabel();
        CNMaximumWorkers_label = new javax.swing.JLabel();
        MotherBoard_label = new javax.swing.JLabel();
        productores_placa_base_dell = new javax.swing.JSpinner();
        CPU_label = new javax.swing.JLabel();
        productores_cpu_dell = new javax.swing.JSpinner();
        dell_ram_label = new javax.swing.JLabel();
        productores_memoria_ram_dell = new javax.swing.JSpinner();
        PowerSupply_label = new javax.swing.JLabel();
        productores_fuente_alimentacion_dell = new javax.swing.JSpinner();
        gpu_label = new javax.swing.JLabel();
        productores_gpu_dell = new javax.swing.JSpinner();
        assemblers_label = new javax.swing.JLabel();
        productores_ensambladores_dell = new javax.swing.JSpinner();
        motherboard_label1 = new javax.swing.JLabel();
        cpu_label1 = new javax.swing.JLabel();
        Ram_label_msi = new javax.swing.JLabel();
        PowerSupply_label1 = new javax.swing.JLabel();
        gpu_label2 = new javax.swing.JLabel();
        assemblers_label2 = new javax.swing.JLabel();
        productores_placa_base_msi = new javax.swing.JSpinner();
        productores_cpu_msi = new javax.swing.JSpinner();
        productores_memoria_ram_msi = new javax.swing.JSpinner();
        productores_fuente_alimentacion_msi = new javax.swing.JSpinner();
        productores_gpu_msi = new javax.swing.JSpinner();
        productores_ensambladores_msi = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        DellTab = new javax.swing.JPanel();
        dell_label_ram = new javax.swing.JLabel();
        ram_dell = new javax.swing.JLabel();
        max_dell_ram = new javax.swing.JLabel();
        max_dell_cpu = new javax.swing.JLabel();
        dell_label_cpu = new javax.swing.JLabel();
        cpu_dell = new javax.swing.JLabel();
        max_mother_board = new javax.swing.JLabel();
        dell_motherboard = new javax.swing.JLabel();
        dell_label_mb = new javax.swing.JLabel();
        computer_parts_title_dell = new javax.swing.JLabel();
        dell_label_power_supply = new javax.swing.JLabel();
        fuente_poder_dell = new javax.swing.JLabel();
        max_dell_powersupply = new javax.swing.JLabel();
        dell_label_gpu = new javax.swing.JLabel();
        gpu_dell = new javax.swing.JLabel();
        max_dell_gpu = new javax.swing.JLabel();
        directordell_title = new javax.swing.JLabel();
        directori_status_label = new javax.swing.JLabel();
        director_status_dell = new javax.swing.JLabel();
        projectManager_dell = new javax.swing.JLabel();
        manager_status_dell = new javax.swing.JLabel();
        Status_pm_dell = new javax.swing.JLabel();
        manager_fault_label_dell = new javax.swing.JLabel();
        qty_faults_dell = new javax.swing.JLabel();
        disccounted_dell_label = new javax.swing.JLabel();
        manager_discounted_qty_dekk = new javax.swing.JLabel();
        standardComputer_dell_label = new javax.swing.JLabel();
        standardDell_Computer = new javax.swing.JLabel();
        gpuComputer_dell_qty = new javax.swing.JLabel();
        standardComputer_dell_qty = new javax.swing.JLabel();
        ComputerProduced_del = new javax.swing.JLabel();
        daysleftDell = new javax.swing.JLabel();
        daysLeftdell = new javax.swing.JLabel();
        earningsdell = new javax.swing.JLabel();
        earningsdell_label = new javax.swing.JLabel();
        costsdell_label = new javax.swing.JLabel();
        costsdell = new javax.swing.JLabel();
        utilitydell = new javax.swing.JLabel();
        utility_dell_days = new javax.swing.JFormattedTextField();
        dell_cpu = new javax.swing.JLabel();
        chas13 = new javax.swing.JLabel();
        chas14 = new javax.swing.JLabel();
        dell_power_supply = new javax.swing.JLabel();
        chas15 = new javax.swing.JLabel();
        dell_gpu = new javax.swing.JLabel();
        dell_motherBoard = new javax.swing.JLabel();
        chas16 = new javax.swing.JLabel();
        dell_assembly = new javax.swing.JLabel();
        wm_label5 = new javax.swing.JLabel();
        chas17 = new javax.swing.JLabel();
        chas18 = new javax.swing.JLabel();
        dell_ram = new javax.swing.JLabel();
        dell_motherboard_plus = new javax.swing.JButton();
        dell_cpu_plus = new javax.swing.JButton();
        dell_ram_plus = new javax.swing.JButton();
        dell_MotherBoard_min = new javax.swing.JButton();
        dell_cpu_min = new javax.swing.JButton();
        dell_ram_min = new javax.swing.JButton();
        dell_power_supply_plus = new javax.swing.JButton();
        dell_powerSupply_min = new javax.swing.JButton();
        dell_gpu_min = new javax.swing.JButton();
        dell_gpu_plus = new javax.swing.JButton();
        dell_assembly_mina = new javax.swing.JButton();
        dell_assembly_plus = new javax.swing.JButton();
        startSimulation = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        MsiTab = new javax.swing.JPanel();
        Computer_parts_dell_label = new javax.swing.JLabel();
        MotherBoard_tab = new javax.swing.JLabel();
        MSI_scritpt_motherboard = new javax.swing.JLabel();
        maxMotherBoard = new javax.swing.JLabel();
        scenariosCN_label = new javax.swing.JLabel();
        MSI_script_cpu = new javax.swing.JLabel();
        max_msi_cpu = new javax.swing.JLabel();
        animationsCN_label = new javax.swing.JLabel();
        MSI_script_ram = new javax.swing.JLabel();
        max_msi_ram = new javax.swing.JLabel();
        dubsCN_label = new javax.swing.JLabel();
        MSI_script_power_supply = new javax.swing.JLabel();
        max_msi_power_supply = new javax.swing.JLabel();
        plotTwistsCN_label = new javax.swing.JLabel();
        MSI_script_gpu = new javax.swing.JLabel();
        max_msi_gpu = new javax.swing.JLabel();
        directorMSI_title = new javax.swing.JLabel();
        directorStatusmsi_label = new javax.swing.JLabel();
        directorStatusmsi = new javax.swing.JLabel();
        projectManagermsi_title = new javax.swing.JLabel();
        managerStatusmsi = new javax.swing.JLabel();
        managerStatusmsi_label = new javax.swing.JLabel();
        managerFaultsmsi_label = new javax.swing.JLabel();
        faultsQtymsi = new javax.swing.JLabel();
        managerDiscountedmsi = new javax.swing.JLabel();
        managerSalaryDiscountmsi = new javax.swing.JLabel();
        gpuComputermsi = new javax.swing.JLabel();
        standardComputerProducedMSI = new javax.swing.JLabel();
        gpuComputerMSI = new javax.swing.JLabel();
        standarComputermsi = new javax.swing.JLabel();
        ComputerProducedmsi = new javax.swing.JLabel();
        DaysLeftmsi = new javax.swing.JLabel();
        daysLeftmsi = new javax.swing.JLabel();
        earningsmsi = new javax.swing.JLabel();
        costsmsi = new javax.swing.JLabel();
        utilitymsi = new javax.swing.JLabel();
        utilitymsi_label = new javax.swing.JFormattedTextField();
        qty_cpu_msi = new javax.swing.JLabel();
        Ram_Memory_msi = new javax.swing.JLabel();
        power_supply_msi = new javax.swing.JLabel();
        qty_powerS_msi = new javax.swing.JLabel();
        gpu_producer_msi = new javax.swing.JLabel();
        qty_gpu_msi = new javax.swing.JLabel();
        qty_MotherBoard_msi = new javax.swing.JLabel();
        assemblersmsi_Label = new javax.swing.JLabel();
        qty_assembli_msi = new javax.swing.JLabel();
        worker_msi = new javax.swing.JLabel();
        screenMotherBoard_msi_Label = new javax.swing.JLabel();
        cpu_label_Label = new javax.swing.JLabel();
        qty_ram_msi = new javax.swing.JLabel();
        motherboard_plus_msi = new javax.swing.JButton();
        motherboard_Min_msi = new javax.swing.JButton();
        cpu_plus_msi = new javax.swing.JButton();
        ram_plus_msi = new javax.swing.JButton();
        cpu_Min_msi = new javax.swing.JButton();
        tam_Min_msi = new javax.swing.JButton();
        power_supply_plus_msi = new javax.swing.JButton();
        powerSupply_Min_msi = new javax.swing.JButton();
        gpu_Min_msi = new javax.swing.JButton();
        gpu_plus_msi = new javax.swing.JButton();
        assembling_Min_msi = new javax.swing.JButton();
        assemblin_plus_msi = new javax.swing.JButton();
        earningsmsi_label = new javax.swing.JLabel();
        costsmsi_label = new javax.swing.JLabel();
        msi_Label = new javax.swing.JLabel();
        statisticsTab = new javax.swing.JPanel();
        chartPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Tabs.setBackground(new java.awt.Color(102, 102, 102));
        Tabs.setForeground(new java.awt.Color(255, 255, 255));
        Tabs.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N

        ConfigurationTab.setBackground(new java.awt.Color(51, 51, 51));
        ConfigurationTab.setForeground(new java.awt.Color(255, 255, 255));
        ConfigurationTab.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Config_label.setFont(new java.awt.Font("Microsoft YaHei", 1, 36)); // NOI18N
        Config_label.setForeground(new java.awt.Color(0, 204, 204));
        Config_label.setText("CONFIGURATION");
        ConfigurationTab.add(Config_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 30, 330, 60));

        General_label.setBackground(new java.awt.Color(102, 102, 102));
        General_label.setFont(new java.awt.Font("Microsoft YaHei", 1, 24)); // NOI18N
        General_label.setForeground(new java.awt.Color(255, 255, 255));
        General_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        General_label.setText("Time Config");
        ConfigurationTab.add(General_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 140, 250, 40));

        dayDurationInput.setFont(new java.awt.Font("Microsoft YaHei", 0, 12)); // NOI18N
        dayDurationInput.setForeground(new java.awt.Color(0, 0, 0));
        jScrollPane1.setViewportView(dayDurationInput);

        ConfigurationTab.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 230, 120, -1));

        deliveryDaysInput.setFont(new java.awt.Font("Microsoft YaHei", 0, 12)); // NOI18N
        deliveryDaysInput.setForeground(new java.awt.Color(0, 0, 0));
        jScrollPane2.setViewportView(deliveryDaysInput);

        ConfigurationTab.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 300, 120, -1));

        setConfiguration.setBackground(new java.awt.Color(0, 204, 204));
        setConfiguration.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 18)); // NOI18N
        setConfiguration.setForeground(new java.awt.Color(0, 0, 0));
        setConfiguration.setText("SET CONFIGURATION");
        setConfiguration.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setConfigurationActionPerformed(evt);
            }
        });
        ConfigurationTab.add(setConfiguration, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 350, 260, 50));

        infoLabel.setBackground(new java.awt.Color(102, 102, 102));
        infoLabel.setFont(new java.awt.Font("Microsoft YaHei", 1, 10)); // NOI18N
        infoLabel.setForeground(new java.awt.Color(0, 204, 204));
        infoLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        infoLabel.setText("You have to press the button to set the configuration");
        ConfigurationTab.add(infoLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 400, 280, 30));

        dayDuration_label.setBackground(new java.awt.Color(102, 102, 102));
        dayDuration_label.setFont(new java.awt.Font("Microsoft YaHei", 1, 12)); // NOI18N
        dayDuration_label.setForeground(new java.awt.Color(255, 255, 255));
        dayDuration_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dayDuration_label.setText("Day Duration (seconds)");
        ConfigurationTab.add(dayDuration_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 190, 170, 30));

        deliveryDays_label.setBackground(new java.awt.Color(102, 102, 102));
        deliveryDays_label.setFont(new java.awt.Font("Microsoft YaHei", 1, 12)); // NOI18N
        deliveryDays_label.setForeground(new java.awt.Color(255, 255, 255));
        deliveryDays_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        deliveryDays_label.setText("Days to Deliver");
        ConfigurationTab.add(deliveryDays_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 260, 110, 30));

        DellConfig_label.setBackground(new java.awt.Color(102, 102, 102));
        DellConfig_label.setFont(new java.awt.Font("Microsoft YaHei", 1, 24)); // NOI18N
        DellConfig_label.setForeground(new java.awt.Color(255, 255, 255));
        DellConfig_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        DellConfig_label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/fq9r43ur8i7legj7rmlg9okhtq (3).png"))); // NOI18N
        ConfigurationTab.add(DellConfig_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 100, 310, 200));

        DelllMinWorker_label.setBackground(new java.awt.Color(102, 102, 102));
        DelllMinWorker_label.setFont(new java.awt.Font("Microsoft YaHei", 1, 12)); // NOI18N
        DelllMinWorker_label.setForeground(new java.awt.Color(0, 204, 204));
        DelllMinWorker_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        DelllMinWorker_label.setText("MAXIMUM 18 WORKERS");
        ConfigurationTab.add(DelllMinWorker_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 180, 30));

        MSIConfig_label.setBackground(new java.awt.Color(102, 102, 102));
        MSIConfig_label.setFont(new java.awt.Font("Microsoft YaHei", 1, 24)); // NOI18N
        MSIConfig_label.setForeground(new java.awt.Color(255, 255, 255));
        MSIConfig_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MSIConfig_label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/pngwing.com (1).png"))); // NOI18N
        ConfigurationTab.add(MSIConfig_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 80, 270, 60));

        CNMaximumWorkers_label.setBackground(new java.awt.Color(102, 102, 102));
        CNMaximumWorkers_label.setFont(new java.awt.Font("Microsoft YaHei", 1, 12)); // NOI18N
        CNMaximumWorkers_label.setForeground(new java.awt.Color(0, 204, 204));
        CNMaximumWorkers_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        CNMaximumWorkers_label.setText("MAXIMUM 16 WORKERS");
        ConfigurationTab.add(CNMaximumWorkers_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 150, 170, 30));

        MotherBoard_label.setBackground(new java.awt.Color(102, 102, 102));
        MotherBoard_label.setFont(new java.awt.Font("Microsoft YaHei", 1, 12)); // NOI18N
        MotherBoard_label.setForeground(new java.awt.Color(255, 255, 255));
        MotherBoard_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MotherBoard_label.setText(" Motherboard Producers");
        ConfigurationTab.add(MotherBoard_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 190, 30));

        productores_placa_base_dell.setFont(new java.awt.Font("Microsoft YaHei", 1, 12)); // NOI18N
        productores_placa_base_dell.setModel(new javax.swing.SpinnerNumberModel(1, 1, 13, 1));
        ConfigurationTab.add(productores_placa_base_dell, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 230, -1, 30));

        CPU_label.setBackground(new java.awt.Color(102, 102, 102));
        CPU_label.setFont(new java.awt.Font("Microsoft YaHei", 1, 12)); // NOI18N
        CPU_label.setForeground(new java.awt.Color(255, 255, 255));
        CPU_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        CPU_label.setText("CPU Producers");
        ConfigurationTab.add(CPU_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 260, 120, 30));

        productores_cpu_dell.setFont(new java.awt.Font("Microsoft YaHei", 1, 12)); // NOI18N
        productores_cpu_dell.setModel(new javax.swing.SpinnerNumberModel(1, 1, 13, 1));
        ConfigurationTab.add(productores_cpu_dell, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 290, -1, 30));

        dell_ram_label.setBackground(new java.awt.Color(102, 102, 102));
        dell_ram_label.setFont(new java.awt.Font("Microsoft YaHei", 1, 12)); // NOI18N
        dell_ram_label.setForeground(new java.awt.Color(255, 255, 255));
        dell_ram_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dell_ram_label.setText("RAM Memory Producers");
        ConfigurationTab.add(dell_ram_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, 190, 30));

        productores_memoria_ram_dell.setFont(new java.awt.Font("Microsoft YaHei", 1, 12)); // NOI18N
        productores_memoria_ram_dell.setModel(new javax.swing.SpinnerNumberModel(1, 1, 13, 1));
        ConfigurationTab.add(productores_memoria_ram_dell, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 360, -1, 30));

        PowerSupply_label.setBackground(new java.awt.Color(102, 102, 102));
        PowerSupply_label.setFont(new java.awt.Font("Microsoft YaHei", 1, 12)); // NOI18N
        PowerSupply_label.setForeground(new java.awt.Color(255, 255, 255));
        PowerSupply_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PowerSupply_label.setText("Power Supply Producers");
        ConfigurationTab.add(PowerSupply_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 390, 170, 30));

        productores_fuente_alimentacion_dell.setFont(new java.awt.Font("Microsoft YaHei", 1, 12)); // NOI18N
        productores_fuente_alimentacion_dell.setModel(new javax.swing.SpinnerNumberModel(1, 1, 13, 1));
        ConfigurationTab.add(productores_fuente_alimentacion_dell, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 420, -1, 30));

        gpu_label.setBackground(new java.awt.Color(102, 102, 102));
        gpu_label.setFont(new java.awt.Font("Microsoft YaHei", 1, 12)); // NOI18N
        gpu_label.setForeground(new java.awt.Color(255, 255, 255));
        gpu_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gpu_label.setText("GPU Producers");
        ConfigurationTab.add(gpu_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 450, 110, 30));

        productores_gpu_dell.setFont(new java.awt.Font("Microsoft YaHei", 1, 12)); // NOI18N
        productores_gpu_dell.setModel(new javax.swing.SpinnerNumberModel(1, 1, 13, 1));
        ConfigurationTab.add(productores_gpu_dell, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 480, -1, 30));

        assemblers_label.setBackground(new java.awt.Color(102, 102, 102));
        assemblers_label.setFont(new java.awt.Font("Microsoft YaHei", 1, 12)); // NOI18N
        assemblers_label.setForeground(new java.awt.Color(255, 255, 255));
        assemblers_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        assemblers_label.setText("Assembling producers");
        ConfigurationTab.add(assemblers_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 510, 180, 30));

        productores_ensambladores_dell.setFont(new java.awt.Font("Microsoft YaHei", 1, 12)); // NOI18N
        productores_ensambladores_dell.setModel(new javax.swing.SpinnerNumberModel(1, 1, 13, 1));
        ConfigurationTab.add(productores_ensambladores_dell, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 550, -1, 30));

        motherboard_label1.setBackground(new java.awt.Color(102, 102, 102));
        motherboard_label1.setFont(new java.awt.Font("Microsoft YaHei", 1, 12)); // NOI18N
        motherboard_label1.setForeground(new java.awt.Color(255, 255, 255));
        motherboard_label1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        motherboard_label1.setText(" Motherboard Producers");
        ConfigurationTab.add(motherboard_label1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 190, 190, 30));

        cpu_label1.setBackground(new java.awt.Color(102, 102, 102));
        cpu_label1.setFont(new java.awt.Font("Microsoft YaHei", 1, 12)); // NOI18N
        cpu_label1.setForeground(new java.awt.Color(255, 255, 255));
        cpu_label1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cpu_label1.setText("CPU Producers");
        ConfigurationTab.add(cpu_label1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 270, 120, 30));

        Ram_label_msi.setBackground(new java.awt.Color(102, 102, 102));
        Ram_label_msi.setFont(new java.awt.Font("Microsoft YaHei", 1, 12)); // NOI18N
        Ram_label_msi.setForeground(new java.awt.Color(255, 255, 255));
        Ram_label_msi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Ram_label_msi.setText("RAM Memory Producers");
        ConfigurationTab.add(Ram_label_msi, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 330, 200, 30));

        PowerSupply_label1.setBackground(new java.awt.Color(102, 102, 102));
        PowerSupply_label1.setFont(new java.awt.Font("Microsoft YaHei", 1, 12)); // NOI18N
        PowerSupply_label1.setForeground(new java.awt.Color(255, 255, 255));
        PowerSupply_label1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PowerSupply_label1.setText("Power Supply Producers");
        ConfigurationTab.add(PowerSupply_label1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 390, 220, 30));

        gpu_label2.setBackground(new java.awt.Color(102, 102, 102));
        gpu_label2.setFont(new java.awt.Font("Microsoft YaHei", 1, 12)); // NOI18N
        gpu_label2.setForeground(new java.awt.Color(255, 255, 255));
        gpu_label2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gpu_label2.setText("GPU Producers");
        ConfigurationTab.add(gpu_label2, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 460, 110, 30));

        assemblers_label2.setBackground(new java.awt.Color(102, 102, 102));
        assemblers_label2.setFont(new java.awt.Font("Microsoft YaHei", 1, 12)); // NOI18N
        assemblers_label2.setForeground(new java.awt.Color(255, 255, 255));
        assemblers_label2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        assemblers_label2.setText("Assembling producers");
        ConfigurationTab.add(assemblers_label2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 510, 200, 30));

        productores_placa_base_msi.setFont(new java.awt.Font("Microsoft YaHei", 1, 12)); // NOI18N
        productores_placa_base_msi.setModel(new javax.swing.SpinnerNumberModel(1, 1, 5, 1));
        ConfigurationTab.add(productores_placa_base_msi, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 230, -1, 30));

        productores_cpu_msi.setFont(new java.awt.Font("Microsoft YaHei", 1, 12)); // NOI18N
        productores_cpu_msi.setModel(new javax.swing.SpinnerNumberModel(1, 1, 5, 1));
        ConfigurationTab.add(productores_cpu_msi, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 300, -1, 30));

        productores_memoria_ram_msi.setFont(new java.awt.Font("Microsoft YaHei", 1, 12)); // NOI18N
        productores_memoria_ram_msi.setModel(new javax.swing.SpinnerNumberModel(1, 1, 5, 1));
        ConfigurationTab.add(productores_memoria_ram_msi, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 360, -1, 30));

        productores_fuente_alimentacion_msi.setFont(new java.awt.Font("Microsoft YaHei", 1, 12)); // NOI18N
        productores_fuente_alimentacion_msi.setModel(new javax.swing.SpinnerNumberModel(1, 1, 5, 1));
        ConfigurationTab.add(productores_fuente_alimentacion_msi, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 430, -1, 30));

        productores_gpu_msi.setFont(new java.awt.Font("Microsoft YaHei", 1, 12)); // NOI18N
        productores_gpu_msi.setModel(new javax.swing.SpinnerNumberModel(1, 1, 5, 1));
        ConfigurationTab.add(productores_gpu_msi, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 480, -1, 30));

        productores_ensambladores_msi.setFont(new java.awt.Font("Microsoft YaHei", 1, 12)); // NOI18N
        productores_ensambladores_msi.setModel(new javax.swing.SpinnerNumberModel(1, 1, 5, 1));
        ConfigurationTab.add(productores_ensambladores_msi, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 550, -1, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/fq9r43ur8i7legj7rmlg9okhtq (3).png"))); // NOI18N
        ConfigurationTab.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/config_icon.png"))); // NOI18N
        jLabel3.setText("jLabel3");
        ConfigurationTab.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 10, 100, 100));

        Tabs.addTab("Configuration", ConfigurationTab);

        DellTab.setBackground(new java.awt.Color(51, 51, 51));
        DellTab.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        dell_label_ram.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        dell_label_ram.setForeground(new java.awt.Color(255, 255, 255));
        dell_label_ram.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        dell_label_ram.setText("RAM");
        DellTab.add(dell_label_ram, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 290, 120, 40));

        ram_dell.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        ram_dell.setForeground(new java.awt.Color(255, 255, 255));
        ram_dell.setText("0");
        DellTab.add(ram_dell, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 290, 40, 40));

        max_dell_ram.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        max_dell_ram.setForeground(new java.awt.Color(255, 255, 255));
        max_dell_ram.setText("/ 55");
        DellTab.add(max_dell_ram, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 290, 40, 40));

        max_dell_cpu.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        max_dell_cpu.setForeground(new java.awt.Color(255, 255, 255));
        max_dell_cpu.setText("/ 20");
        DellTab.add(max_dell_cpu, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 240, 40, 40));

        dell_label_cpu.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        dell_label_cpu.setForeground(new java.awt.Color(255, 255, 255));
        dell_label_cpu.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        dell_label_cpu.setText("CPU");
        DellTab.add(dell_label_cpu, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 240, 110, 40));

        cpu_dell.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        cpu_dell.setForeground(new java.awt.Color(255, 255, 255));
        cpu_dell.setText("0");
        DellTab.add(cpu_dell, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 240, 40, 40));

        max_mother_board.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        max_mother_board.setForeground(new java.awt.Color(255, 255, 255));
        max_mother_board.setText("/ 25");
        DellTab.add(max_mother_board, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 190, 40, 40));

        dell_motherboard.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        dell_motherboard.setForeground(new java.awt.Color(255, 255, 255));
        dell_motherboard.setText("0");
        DellTab.add(dell_motherboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 190, 40, 40));

        dell_label_mb.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        dell_label_mb.setForeground(new java.awt.Color(255, 255, 255));
        dell_label_mb.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        dell_label_mb.setText("MotherBoard");
        DellTab.add(dell_label_mb, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 150, 40));

        computer_parts_title_dell.setFont(new java.awt.Font("Microsoft YaHei", 1, 24)); // NOI18N
        computer_parts_title_dell.setForeground(new java.awt.Color(8, 107, 164));
        computer_parts_title_dell.setText("Computer parts");
        DellTab.add(computer_parts_title_dell, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 290, 40));

        dell_label_power_supply.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        dell_label_power_supply.setForeground(new java.awt.Color(255, 255, 255));
        dell_label_power_supply.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        dell_label_power_supply.setText("Power supply");
        DellTab.add(dell_label_power_supply, new org.netbeans.lib.awtextra.AbsoluteConstraints(-20, 340, 180, 40));

        fuente_poder_dell.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        fuente_poder_dell.setForeground(new java.awt.Color(255, 255, 255));
        fuente_poder_dell.setText("0");
        DellTab.add(fuente_poder_dell, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 340, 40, 40));

        max_dell_powersupply.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        max_dell_powersupply.setForeground(new java.awt.Color(255, 255, 255));
        max_dell_powersupply.setText("/ 35");
        DellTab.add(max_dell_powersupply, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 340, 40, 40));

        dell_label_gpu.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        dell_label_gpu.setForeground(new java.awt.Color(255, 255, 255));
        dell_label_gpu.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        dell_label_gpu.setText("GPU");
        DellTab.add(dell_label_gpu, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 390, 110, 40));

        gpu_dell.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        gpu_dell.setForeground(new java.awt.Color(255, 255, 255));
        gpu_dell.setText("0");
        DellTab.add(gpu_dell, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 390, 40, 40));

        max_dell_gpu.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        max_dell_gpu.setForeground(new java.awt.Color(255, 255, 255));
        max_dell_gpu.setText("/ 10");
        DellTab.add(max_dell_gpu, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 390, 40, 40));

        directordell_title.setFont(new java.awt.Font("Microsoft YaHei", 1, 24)); // NOI18N
        directordell_title.setForeground(new java.awt.Color(8, 107, 164));
        directordell_title.setText("DIRECTOR");
        DellTab.add(directordell_title, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 20, 140, 40));

        directori_status_label.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        directori_status_label.setForeground(new java.awt.Color(255, 255, 255));
        directori_status_label.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        directori_status_label.setText("Status:");
        DellTab.add(directori_status_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 60, 100, 40));

        director_status_dell.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        director_status_dell.setForeground(new java.awt.Color(255, 255, 255));
        director_status_dell.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        director_status_dell.setText("Working");
        DellTab.add(director_status_dell, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 60, 210, 40));

        projectManager_dell.setFont(new java.awt.Font("Microsoft YaHei", 1, 24)); // NOI18N
        projectManager_dell.setForeground(new java.awt.Color(8, 107, 164));
        projectManager_dell.setText("PROJECT MANAGER");
        DellTab.add(projectManager_dell, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 120, 260, 40));

        manager_status_dell.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        manager_status_dell.setForeground(new java.awt.Color(255, 255, 255));
        manager_status_dell.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        manager_status_dell.setText("Nothing");
        DellTab.add(manager_status_dell, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 160, 250, 40));

        Status_pm_dell.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        Status_pm_dell.setForeground(new java.awt.Color(255, 255, 255));
        Status_pm_dell.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Status_pm_dell.setText("Status:");
        DellTab.add(Status_pm_dell, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 160, 100, 40));

        manager_fault_label_dell.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        manager_fault_label_dell.setForeground(new java.awt.Color(255, 255, 255));
        manager_fault_label_dell.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        manager_fault_label_dell.setText("Faults:");
        DellTab.add(manager_fault_label_dell, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 200, 100, 40));

        qty_faults_dell.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        qty_faults_dell.setForeground(new java.awt.Color(255, 255, 255));
        qty_faults_dell.setText("0");
        DellTab.add(qty_faults_dell, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 200, 40, 40));

        disccounted_dell_label.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        disccounted_dell_label.setForeground(new java.awt.Color(255, 255, 255));
        disccounted_dell_label.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        disccounted_dell_label.setText("Discounted:");
        DellTab.add(disccounted_dell_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 240, 120, 40));

        manager_discounted_qty_dekk.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        manager_discounted_qty_dekk.setForeground(new java.awt.Color(255, 255, 255));
        manager_discounted_qty_dekk.setText("0");
        DellTab.add(manager_discounted_qty_dekk, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 240, 120, 40));

        standardComputer_dell_label.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        standardComputer_dell_label.setForeground(new java.awt.Color(255, 255, 255));
        standardComputer_dell_label.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        standardComputer_dell_label.setText("With GPU:");
        DellTab.add(standardComputer_dell_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 390, 170, 40));

        standardDell_Computer.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        standardDell_Computer.setForeground(new java.awt.Color(255, 255, 255));
        standardDell_Computer.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        standardDell_Computer.setText("Standard:");
        DellTab.add(standardDell_Computer, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 350, 100, 40));

        gpuComputer_dell_qty.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        gpuComputer_dell_qty.setForeground(new java.awt.Color(255, 255, 255));
        gpuComputer_dell_qty.setText("0");
        DellTab.add(gpuComputer_dell_qty, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 390, 40, 40));

        standardComputer_dell_qty.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        standardComputer_dell_qty.setForeground(new java.awt.Color(255, 255, 255));
        standardComputer_dell_qty.setText("0");
        DellTab.add(standardComputer_dell_qty, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 350, 40, 40));

        ComputerProduced_del.setFont(new java.awt.Font("Microsoft YaHei", 1, 24)); // NOI18N
        ComputerProduced_del.setForeground(new java.awt.Color(8, 107, 164));
        ComputerProduced_del.setText("Computers PRODUCED");
        DellTab.add(ComputerProduced_del, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 310, 330, 40));

        daysleftDell.setFont(new java.awt.Font("Microsoft YaHei", 1, 24)); // NOI18N
        daysleftDell.setForeground(new java.awt.Color(8, 107, 164));
        daysleftDell.setText("DAYS LEFT:");
        DellTab.add(daysleftDell, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 450, 150, 40));

        daysLeftdell.setFont(new java.awt.Font("Microsoft YaHei", 1, 24)); // NOI18N
        daysLeftdell.setForeground(new java.awt.Color(8, 107, 164));
        daysLeftdell.setText("0");
        DellTab.add(daysLeftdell, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 450, 50, 40));

        earningsdell.setFont(new java.awt.Font("Microsoft YaHei", 1, 24)); // NOI18N
        earningsdell.setForeground(new java.awt.Color(255, 255, 255));
        earningsdell.setText("0");
        DellTab.add(earningsdell, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 460, 210, 40));

        earningsdell_label.setFont(new java.awt.Font("Microsoft YaHei", 1, 24)); // NOI18N
        earningsdell_label.setForeground(new java.awt.Color(8, 107, 164));
        earningsdell_label.setText("EARNINGS: ");
        DellTab.add(earningsdell_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 460, 150, 40));

        costsdell_label.setFont(new java.awt.Font("Microsoft YaHei", 1, 24)); // NOI18N
        costsdell_label.setForeground(new java.awt.Color(8, 107, 164));
        costsdell_label.setText("COSTS:");
        DellTab.add(costsdell_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 510, 100, 40));

        costsdell.setFont(new java.awt.Font("Microsoft YaHei", 1, 24)); // NOI18N
        costsdell.setForeground(new java.awt.Color(255, 255, 255));
        costsdell.setText("0");
        DellTab.add(costsdell, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 510, 220, 40));

        utilitydell.setFont(new java.awt.Font("Microsoft YaHei", 1, 24)); // NOI18N
        utilitydell.setForeground(new java.awt.Color(8, 107, 164));
        utilitydell.setText("0");
        DellTab.add(utilitydell, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 500, 220, 50));

        utility_dell_days.setEditable(false);
        utility_dell_days.setBackground(new java.awt.Color(8, 107, 164));
        utility_dell_days.setForeground(new java.awt.Color(255, 255, 255));
        utility_dell_days.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        utility_dell_days.setText("TOTAL PROFIT:");
        utility_dell_days.setFont(new java.awt.Font("Microsoft YaHei", 1, 24)); // NOI18N
        DellTab.add(utility_dell_days, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 500, 210, 50));

        dell_cpu.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        dell_cpu.setForeground(new java.awt.Color(255, 255, 255));
        dell_cpu.setText("0");
        DellTab.add(dell_cpu, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 120, 40, 40));

        chas13.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        chas13.setForeground(new java.awt.Color(255, 255, 255));
        chas13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        chas13.setText("RAM Memory Producers");
        DellTab.add(chas13, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 160, 230, 40));

        chas14.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        chas14.setForeground(new java.awt.Color(255, 255, 255));
        chas14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        chas14.setText("Power Supply Producers");
        DellTab.add(chas14, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 200, 240, 40));

        dell_power_supply.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        dell_power_supply.setForeground(new java.awt.Color(255, 255, 255));
        dell_power_supply.setText("0");
        DellTab.add(dell_power_supply, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 200, 40, 40));

        chas15.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        chas15.setForeground(new java.awt.Color(255, 255, 255));
        chas15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        chas15.setText("GPU Producers");
        DellTab.add(chas15, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 240, 170, 40));

        dell_gpu.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        dell_gpu.setForeground(new java.awt.Color(255, 255, 255));
        dell_gpu.setText("0");
        DellTab.add(dell_gpu, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 240, 40, 40));

        dell_motherBoard.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        dell_motherBoard.setForeground(new java.awt.Color(255, 255, 255));
        dell_motherBoard.setText("0");
        DellTab.add(dell_motherBoard, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 80, 40, 40));

        chas16.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        chas16.setForeground(new java.awt.Color(255, 255, 255));
        chas16.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        chas16.setText("Assembling producers");
        DellTab.add(chas16, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 280, 230, 40));

        dell_assembly.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        dell_assembly.setForeground(new java.awt.Color(255, 255, 255));
        dell_assembly.setText("0");
        DellTab.add(dell_assembly, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 280, 40, 40));

        wm_label5.setFont(new java.awt.Font("Microsoft YaHei", 1, 24)); // NOI18N
        wm_label5.setForeground(new java.awt.Color(8, 107, 164));
        wm_label5.setText("WORKERS");
        DellTab.add(wm_label5, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 20, 140, 40));

        chas17.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        chas17.setForeground(new java.awt.Color(255, 255, 255));
        chas17.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        chas17.setText(" Motherboard Producers");
        DellTab.add(chas17, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 80, 270, 40));

        chas18.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        chas18.setForeground(new java.awt.Color(255, 255, 255));
        chas18.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        chas18.setText("CPU Producers");
        DellTab.add(chas18, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 120, 190, 40));

        dell_ram.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        dell_ram.setForeground(new java.awt.Color(255, 255, 255));
        dell_ram.setText("0");
        DellTab.add(dell_ram, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 160, 40, 40));

        dell_motherboard_plus.setBackground(new java.awt.Color(8, 107, 164));
        dell_motherboard_plus.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        dell_motherboard_plus.setForeground(new java.awt.Color(255, 255, 255));
        dell_motherboard_plus.setText("+");
        dell_motherboard_plus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dell_motherboard_plusActionPerformed(evt);
            }
        });
        DellTab.add(dell_motherboard_plus, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 90, 50, 20));

        dell_cpu_plus.setBackground(new java.awt.Color(8, 107, 164));
        dell_cpu_plus.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        dell_cpu_plus.setForeground(new java.awt.Color(255, 255, 255));
        dell_cpu_plus.setText("+");
        dell_cpu_plus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dell_cpu_plusActionPerformed(evt);
            }
        });
        DellTab.add(dell_cpu_plus, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 130, 50, 20));

        dell_ram_plus.setBackground(new java.awt.Color(8, 107, 164));
        dell_ram_plus.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        dell_ram_plus.setForeground(new java.awt.Color(255, 255, 255));
        dell_ram_plus.setText("+");
        dell_ram_plus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dell_ram_plusActionPerformed(evt);
            }
        });
        DellTab.add(dell_ram_plus, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 170, 50, 20));

        dell_MotherBoard_min.setBackground(new java.awt.Color(8, 107, 164));
        dell_MotherBoard_min.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        dell_MotherBoard_min.setForeground(new java.awt.Color(255, 255, 255));
        dell_MotherBoard_min.setText("-");
        dell_MotherBoard_min.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dell_MotherBoard_minActionPerformed(evt);
            }
        });
        DellTab.add(dell_MotherBoard_min, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 90, 50, 20));

        dell_cpu_min.setBackground(new java.awt.Color(8, 107, 164));
        dell_cpu_min.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        dell_cpu_min.setForeground(new java.awt.Color(255, 255, 255));
        dell_cpu_min.setText("-");
        dell_cpu_min.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dell_cpu_minActionPerformed(evt);
            }
        });
        DellTab.add(dell_cpu_min, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 130, 50, 20));

        dell_ram_min.setBackground(new java.awt.Color(8, 107, 164));
        dell_ram_min.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        dell_ram_min.setForeground(new java.awt.Color(255, 255, 255));
        dell_ram_min.setText("-");
        dell_ram_min.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dell_ram_minActionPerformed(evt);
            }
        });
        DellTab.add(dell_ram_min, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 170, 50, 20));

        dell_power_supply_plus.setBackground(new java.awt.Color(8, 107, 164));
        dell_power_supply_plus.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        dell_power_supply_plus.setForeground(new java.awt.Color(255, 255, 255));
        dell_power_supply_plus.setText("+");
        dell_power_supply_plus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dell_power_supply_plusActionPerformed(evt);
            }
        });
        DellTab.add(dell_power_supply_plus, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 210, 50, 20));

        dell_powerSupply_min.setBackground(new java.awt.Color(8, 107, 164));
        dell_powerSupply_min.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        dell_powerSupply_min.setForeground(new java.awt.Color(255, 255, 255));
        dell_powerSupply_min.setText("-");
        dell_powerSupply_min.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dell_powerSupply_minActionPerformed(evt);
            }
        });
        DellTab.add(dell_powerSupply_min, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 210, 50, 20));

        dell_gpu_min.setBackground(new java.awt.Color(8, 107, 164));
        dell_gpu_min.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        dell_gpu_min.setForeground(new java.awt.Color(255, 255, 255));
        dell_gpu_min.setText("-");
        dell_gpu_min.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dell_gpu_minActionPerformed(evt);
            }
        });
        DellTab.add(dell_gpu_min, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 250, 50, 20));

        dell_gpu_plus.setBackground(new java.awt.Color(8, 107, 164));
        dell_gpu_plus.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        dell_gpu_plus.setForeground(new java.awt.Color(255, 255, 255));
        dell_gpu_plus.setText("+");
        dell_gpu_plus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dell_gpu_plusActionPerformed(evt);
            }
        });
        DellTab.add(dell_gpu_plus, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 250, 50, 20));

        dell_assembly_mina.setBackground(new java.awt.Color(8, 107, 164));
        dell_assembly_mina.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        dell_assembly_mina.setForeground(new java.awt.Color(255, 255, 255));
        dell_assembly_mina.setText("-");
        dell_assembly_mina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dell_assembly_minaActionPerformed(evt);
            }
        });
        DellTab.add(dell_assembly_mina, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 290, 50, 20));

        dell_assembly_plus.setBackground(new java.awt.Color(8, 107, 164));
        dell_assembly_plus.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        dell_assembly_plus.setForeground(new java.awt.Color(255, 255, 255));
        dell_assembly_plus.setText("+");
        dell_assembly_plus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dell_assembly_plusActionPerformed(evt);
            }
        });
        DellTab.add(dell_assembly_plus, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 290, 50, 20));

        startSimulation.setBackground(new java.awt.Color(8, 107, 164));
        startSimulation.setFont(new java.awt.Font("Microsoft YaHei", 1, 24)); // NOI18N
        startSimulation.setForeground(new java.awt.Color(255, 255, 255));
        startSimulation.setText("Start");
        startSimulation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startSimulationActionPerformed(evt);
            }
        });
        DellTab.add(startSimulation, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 40, 130, 40));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/fq9r43ur8i7legj7rmlg9okhtq (3).png"))); // NOI18N
        DellTab.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, -1, -1));

        Tabs.addTab("Dell", DellTab);

        MsiTab.setBackground(new java.awt.Color(51, 51, 51));
        MsiTab.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Computer_parts_dell_label.setFont(new java.awt.Font("Microsoft YaHei", 1, 24)); // NOI18N
        Computer_parts_dell_label.setForeground(new java.awt.Color(238, 35, 43));
        Computer_parts_dell_label.setText("Computer parts");
        MsiTab.add(Computer_parts_dell_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 200, 40));

        MotherBoard_tab.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        MotherBoard_tab.setForeground(new java.awt.Color(255, 255, 255));
        MotherBoard_tab.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        MotherBoard_tab.setText("MotherBoard");
        MsiTab.add(MotherBoard_tab, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 190, 170, 40));

        MSI_scritpt_motherboard.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        MSI_scritpt_motherboard.setForeground(new java.awt.Color(255, 255, 255));
        MSI_scritpt_motherboard.setText("0");
        MsiTab.add(MSI_scritpt_motherboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 190, 40, 40));

        maxMotherBoard.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        maxMotherBoard.setForeground(new java.awt.Color(255, 255, 255));
        maxMotherBoard.setText("/ 25");
        MsiTab.add(maxMotherBoard, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 190, 40, 40));

        scenariosCN_label.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        scenariosCN_label.setForeground(new java.awt.Color(255, 255, 255));
        scenariosCN_label.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        scenariosCN_label.setText("CPU");
        MsiTab.add(scenariosCN_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 240, 110, 40));

        MSI_script_cpu.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        MSI_script_cpu.setForeground(new java.awt.Color(255, 255, 255));
        MSI_script_cpu.setText("0");
        MsiTab.add(MSI_script_cpu, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 240, 40, 40));

        max_msi_cpu.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        max_msi_cpu.setForeground(new java.awt.Color(255, 255, 255));
        max_msi_cpu.setText("/ 20");
        MsiTab.add(max_msi_cpu, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 240, 40, 40));

        animationsCN_label.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        animationsCN_label.setForeground(new java.awt.Color(255, 255, 255));
        animationsCN_label.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        animationsCN_label.setText("RAM");
        MsiTab.add(animationsCN_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 290, 120, 40));

        MSI_script_ram.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        MSI_script_ram.setForeground(new java.awt.Color(255, 255, 255));
        MSI_script_ram.setText("0");
        MsiTab.add(MSI_script_ram, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 290, 40, 40));

        max_msi_ram.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        max_msi_ram.setForeground(new java.awt.Color(255, 255, 255));
        max_msi_ram.setText("/ 55");
        MsiTab.add(max_msi_ram, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 290, 40, 40));

        dubsCN_label.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        dubsCN_label.setForeground(new java.awt.Color(255, 255, 255));
        dubsCN_label.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        dubsCN_label.setText("Power supply");
        MsiTab.add(dubsCN_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 140, 40));

        MSI_script_power_supply.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        MSI_script_power_supply.setForeground(new java.awt.Color(255, 255, 255));
        MSI_script_power_supply.setText("0");
        MsiTab.add(MSI_script_power_supply, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 340, 40, 40));

        max_msi_power_supply.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        max_msi_power_supply.setForeground(new java.awt.Color(255, 255, 255));
        max_msi_power_supply.setText("/ 35");
        MsiTab.add(max_msi_power_supply, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 340, 40, 40));

        plotTwistsCN_label.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        plotTwistsCN_label.setForeground(new java.awt.Color(255, 255, 255));
        plotTwistsCN_label.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        plotTwistsCN_label.setText("GPU");
        MsiTab.add(plotTwistsCN_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 390, 110, 40));

        MSI_script_gpu.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        MSI_script_gpu.setForeground(new java.awt.Color(255, 255, 255));
        MSI_script_gpu.setText("0");
        MsiTab.add(MSI_script_gpu, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 390, 40, 40));

        max_msi_gpu.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        max_msi_gpu.setForeground(new java.awt.Color(255, 255, 255));
        max_msi_gpu.setText("/ 10");
        MsiTab.add(max_msi_gpu, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 390, 40, 40));

        directorMSI_title.setFont(new java.awt.Font("Microsoft YaHei", 1, 24)); // NOI18N
        directorMSI_title.setForeground(new java.awt.Color(238, 35, 43));
        directorMSI_title.setText("DIRECTOR");
        MsiTab.add(directorMSI_title, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 20, 140, 40));

        directorStatusmsi_label.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        directorStatusmsi_label.setForeground(new java.awt.Color(255, 255, 255));
        directorStatusmsi_label.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        directorStatusmsi_label.setText("Status:");
        MsiTab.add(directorStatusmsi_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 60, 100, 40));

        directorStatusmsi.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        directorStatusmsi.setForeground(new java.awt.Color(255, 255, 255));
        directorStatusmsi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        directorStatusmsi.setText("Working");
        MsiTab.add(directorStatusmsi, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 60, 230, 40));

        projectManagermsi_title.setFont(new java.awt.Font("Microsoft YaHei", 1, 24)); // NOI18N
        projectManagermsi_title.setForeground(new java.awt.Color(238, 35, 43));
        projectManagermsi_title.setText("PROJECT MANAGER");
        MsiTab.add(projectManagermsi_title, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 120, 260, 40));

        managerStatusmsi.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        managerStatusmsi.setForeground(new java.awt.Color(255, 255, 255));
        managerStatusmsi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        managerStatusmsi.setText("Nothing");
        MsiTab.add(managerStatusmsi, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 160, 250, 40));

        managerStatusmsi_label.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        managerStatusmsi_label.setForeground(new java.awt.Color(255, 255, 255));
        managerStatusmsi_label.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        managerStatusmsi_label.setText("Status:");
        MsiTab.add(managerStatusmsi_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 160, 100, 40));

        managerFaultsmsi_label.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        managerFaultsmsi_label.setForeground(new java.awt.Color(255, 255, 255));
        managerFaultsmsi_label.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        managerFaultsmsi_label.setText("Faults:");
        MsiTab.add(managerFaultsmsi_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 200, 100, 40));

        faultsQtymsi.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        faultsQtymsi.setForeground(new java.awt.Color(255, 255, 255));
        faultsQtymsi.setText("0");
        MsiTab.add(faultsQtymsi, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 200, 40, 40));

        managerDiscountedmsi.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        managerDiscountedmsi.setForeground(new java.awt.Color(255, 255, 255));
        managerDiscountedmsi.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        managerDiscountedmsi.setText("Discounted:");
        MsiTab.add(managerDiscountedmsi, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 240, 120, 40));

        managerSalaryDiscountmsi.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        managerSalaryDiscountmsi.setForeground(new java.awt.Color(255, 255, 255));
        managerSalaryDiscountmsi.setText("0");
        MsiTab.add(managerSalaryDiscountmsi, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 240, 120, 40));

        gpuComputermsi.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        gpuComputermsi.setForeground(new java.awt.Color(255, 255, 255));
        gpuComputermsi.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        gpuComputermsi.setText("With GPU:");
        MsiTab.add(gpuComputermsi, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 390, 170, 40));

        standardComputerProducedMSI.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        standardComputerProducedMSI.setForeground(new java.awt.Color(255, 255, 255));
        standardComputerProducedMSI.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        standardComputerProducedMSI.setText("Standard:");
        MsiTab.add(standardComputerProducedMSI, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 350, 100, 40));

        gpuComputerMSI.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        gpuComputerMSI.setForeground(new java.awt.Color(255, 255, 255));
        gpuComputerMSI.setText("0");
        MsiTab.add(gpuComputerMSI, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 390, 40, 40));

        standarComputermsi.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        standarComputermsi.setForeground(new java.awt.Color(255, 255, 255));
        standarComputermsi.setText("0");
        MsiTab.add(standarComputermsi, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 350, 40, 40));

        ComputerProducedmsi.setFont(new java.awt.Font("Microsoft YaHei", 1, 24)); // NOI18N
        ComputerProducedmsi.setForeground(new java.awt.Color(238, 35, 43));
        ComputerProducedmsi.setText("Computers PRODUCED");
        MsiTab.add(ComputerProducedmsi, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 310, 340, 40));

        DaysLeftmsi.setFont(new java.awt.Font("Microsoft YaHei", 1, 24)); // NOI18N
        DaysLeftmsi.setForeground(new java.awt.Color(238, 35, 43));
        DaysLeftmsi.setText("DAYS LEFT:");
        MsiTab.add(DaysLeftmsi, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 450, 150, 40));

        daysLeftmsi.setFont(new java.awt.Font("Microsoft YaHei", 1, 24)); // NOI18N
        daysLeftmsi.setForeground(new java.awt.Color(255, 255, 255));
        daysLeftmsi.setText("0");
        MsiTab.add(daysLeftmsi, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 450, 50, 40));

        earningsmsi.setFont(new java.awt.Font("Microsoft YaHei", 1, 24)); // NOI18N
        earningsmsi.setForeground(new java.awt.Color(255, 255, 255));
        earningsmsi.setText("0");
        MsiTab.add(earningsmsi, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 460, 210, 40));

        costsmsi.setFont(new java.awt.Font("Microsoft YaHei", 1, 24)); // NOI18N
        costsmsi.setForeground(new java.awt.Color(255, 255, 255));
        costsmsi.setText("0");
        MsiTab.add(costsmsi, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 510, 220, 40));

        utilitymsi.setFont(new java.awt.Font("Microsoft YaHei", 1, 24)); // NOI18N
        utilitymsi.setForeground(new java.awt.Color(255, 255, 255));
        utilitymsi.setText("0");
        MsiTab.add(utilitymsi, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 500, 220, 50));

        utilitymsi_label.setEditable(false);
        utilitymsi_label.setBackground(new java.awt.Color(238, 35, 43));
        utilitymsi_label.setForeground(new java.awt.Color(255, 255, 255));
        utilitymsi_label.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        utilitymsi_label.setText("TOTAL PROFIT:");
        utilitymsi_label.setFont(new java.awt.Font("Microsoft YaHei", 1, 24)); // NOI18N
        utilitymsi_label.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                utilitymsi_labelActionPerformed(evt);
            }
        });
        MsiTab.add(utilitymsi_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 500, 210, 50));

        qty_cpu_msi.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        qty_cpu_msi.setForeground(new java.awt.Color(255, 255, 255));
        qty_cpu_msi.setText("0");
        MsiTab.add(qty_cpu_msi, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 120, 40, 40));

        Ram_Memory_msi.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        Ram_Memory_msi.setForeground(new java.awt.Color(255, 255, 255));
        Ram_Memory_msi.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Ram_Memory_msi.setText("RAM Memory Producers");
        MsiTab.add(Ram_Memory_msi, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 160, 130, 40));

        power_supply_msi.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        power_supply_msi.setForeground(new java.awt.Color(255, 255, 255));
        power_supply_msi.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        power_supply_msi.setText("Power Supply Producers");
        MsiTab.add(power_supply_msi, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 200, 130, 40));

        qty_powerS_msi.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        qty_powerS_msi.setForeground(new java.awt.Color(255, 255, 255));
        qty_powerS_msi.setText("0");
        MsiTab.add(qty_powerS_msi, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 200, 40, 40));

        gpu_producer_msi.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        gpu_producer_msi.setForeground(new java.awt.Color(255, 255, 255));
        gpu_producer_msi.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        gpu_producer_msi.setText("GPU Producers");
        MsiTab.add(gpu_producer_msi, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 240, 170, 40));

        qty_gpu_msi.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        qty_gpu_msi.setForeground(new java.awt.Color(255, 255, 255));
        qty_gpu_msi.setText("0");
        MsiTab.add(qty_gpu_msi, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 240, 40, 40));

        qty_MotherBoard_msi.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        qty_MotherBoard_msi.setForeground(new java.awt.Color(255, 255, 255));
        qty_MotherBoard_msi.setText("0");
        MsiTab.add(qty_MotherBoard_msi, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 80, 40, 40));

        assemblersmsi_Label.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        assemblersmsi_Label.setForeground(new java.awt.Color(255, 255, 255));
        assemblersmsi_Label.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        assemblersmsi_Label.setText("Assembling producers");
        MsiTab.add(assemblersmsi_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 280, 230, 40));

        qty_assembli_msi.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        qty_assembli_msi.setForeground(new java.awt.Color(255, 255, 255));
        qty_assembli_msi.setText("0");
        MsiTab.add(qty_assembli_msi, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 280, 40, 40));

        worker_msi.setFont(new java.awt.Font("Microsoft YaHei", 1, 24)); // NOI18N
        worker_msi.setForeground(new java.awt.Color(238, 35, 43));
        worker_msi.setText("WORKERS");
        MsiTab.add(worker_msi, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 20, 140, 40));

        screenMotherBoard_msi_Label.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        screenMotherBoard_msi_Label.setForeground(new java.awt.Color(255, 255, 255));
        screenMotherBoard_msi_Label.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        screenMotherBoard_msi_Label.setText(" Motherboard Producers");
        MsiTab.add(screenMotherBoard_msi_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 80, 160, 40));

        cpu_label_Label.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        cpu_label_Label.setForeground(new java.awt.Color(255, 255, 255));
        cpu_label_Label.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        cpu_label_Label.setText("CPU Producers");
        MsiTab.add(cpu_label_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 120, 190, 40));

        qty_ram_msi.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        qty_ram_msi.setForeground(new java.awt.Color(255, 255, 255));
        qty_ram_msi.setText("0");
        MsiTab.add(qty_ram_msi, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 160, 40, 40));

        motherboard_plus_msi.setBackground(new java.awt.Color(238, 35, 43));
        motherboard_plus_msi.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        motherboard_plus_msi.setForeground(new java.awt.Color(255, 255, 255));
        motherboard_plus_msi.setText("+");
        motherboard_plus_msi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                motherboard_plus_msiActionPerformed(evt);
            }
        });
        MsiTab.add(motherboard_plus_msi, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 90, 50, 20));

        motherboard_Min_msi.setBackground(new java.awt.Color(238, 35, 43));
        motherboard_Min_msi.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        motherboard_Min_msi.setForeground(new java.awt.Color(255, 255, 255));
        motherboard_Min_msi.setText("-");
        motherboard_Min_msi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                motherboard_Min_msiActionPerformed(evt);
            }
        });
        MsiTab.add(motherboard_Min_msi, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 90, 50, 20));

        cpu_plus_msi.setBackground(new java.awt.Color(238, 35, 43));
        cpu_plus_msi.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        cpu_plus_msi.setForeground(new java.awt.Color(255, 255, 255));
        cpu_plus_msi.setText("+");
        cpu_plus_msi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cpu_plus_msiActionPerformed(evt);
            }
        });
        MsiTab.add(cpu_plus_msi, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 130, 50, 20));

        ram_plus_msi.setBackground(new java.awt.Color(238, 35, 43));
        ram_plus_msi.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        ram_plus_msi.setForeground(new java.awt.Color(255, 255, 255));
        ram_plus_msi.setText("+");
        ram_plus_msi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ram_plus_msiActionPerformed(evt);
            }
        });
        MsiTab.add(ram_plus_msi, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 170, 50, 20));

        cpu_Min_msi.setBackground(new java.awt.Color(238, 35, 43));
        cpu_Min_msi.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        cpu_Min_msi.setForeground(new java.awt.Color(255, 255, 255));
        cpu_Min_msi.setText("-");
        cpu_Min_msi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cpu_Min_msiActionPerformed(evt);
            }
        });
        MsiTab.add(cpu_Min_msi, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 130, 50, 20));

        tam_Min_msi.setBackground(new java.awt.Color(238, 35, 43));
        tam_Min_msi.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        tam_Min_msi.setForeground(new java.awt.Color(255, 255, 255));
        tam_Min_msi.setText("-");
        tam_Min_msi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tam_Min_msiActionPerformed(evt);
            }
        });
        MsiTab.add(tam_Min_msi, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 170, 50, 20));

        power_supply_plus_msi.setBackground(new java.awt.Color(238, 35, 43));
        power_supply_plus_msi.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        power_supply_plus_msi.setForeground(new java.awt.Color(255, 255, 255));
        power_supply_plus_msi.setText("+");
        power_supply_plus_msi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                power_supply_plus_msiActionPerformed(evt);
            }
        });
        MsiTab.add(power_supply_plus_msi, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 210, 50, 20));

        powerSupply_Min_msi.setBackground(new java.awt.Color(238, 35, 43));
        powerSupply_Min_msi.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        powerSupply_Min_msi.setForeground(new java.awt.Color(255, 255, 255));
        powerSupply_Min_msi.setText("-");
        powerSupply_Min_msi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                powerSupply_Min_msiActionPerformed(evt);
            }
        });
        MsiTab.add(powerSupply_Min_msi, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 210, 50, 20));

        gpu_Min_msi.setBackground(new java.awt.Color(238, 35, 43));
        gpu_Min_msi.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        gpu_Min_msi.setForeground(new java.awt.Color(255, 255, 255));
        gpu_Min_msi.setText("-");
        gpu_Min_msi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gpu_Min_msiActionPerformed(evt);
            }
        });
        MsiTab.add(gpu_Min_msi, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 250, 50, 20));

        gpu_plus_msi.setBackground(new java.awt.Color(238, 35, 43));
        gpu_plus_msi.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        gpu_plus_msi.setForeground(new java.awt.Color(255, 255, 255));
        gpu_plus_msi.setText("+");
        gpu_plus_msi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gpu_plus_msiActionPerformed(evt);
            }
        });
        MsiTab.add(gpu_plus_msi, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 250, 50, 20));

        assembling_Min_msi.setBackground(new java.awt.Color(238, 35, 43));
        assembling_Min_msi.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        assembling_Min_msi.setForeground(new java.awt.Color(255, 255, 255));
        assembling_Min_msi.setText("-");
        assembling_Min_msi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                assembling_Min_msiActionPerformed(evt);
            }
        });
        MsiTab.add(assembling_Min_msi, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 290, 50, 20));

        assemblin_plus_msi.setBackground(new java.awt.Color(238, 35, 43));
        assemblin_plus_msi.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        assemblin_plus_msi.setForeground(new java.awt.Color(255, 255, 255));
        assemblin_plus_msi.setText("+");
        assemblin_plus_msi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                assemblin_plus_msiActionPerformed(evt);
            }
        });
        MsiTab.add(assemblin_plus_msi, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 290, 50, 20));

        earningsmsi_label.setFont(new java.awt.Font("Microsoft YaHei", 1, 24)); // NOI18N
        earningsmsi_label.setForeground(new java.awt.Color(238, 35, 43));
        earningsmsi_label.setText("EARNINGS: ");
        MsiTab.add(earningsmsi_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 460, 150, 40));

        costsmsi_label.setFont(new java.awt.Font("Microsoft YaHei", 1, 24)); // NOI18N
        costsmsi_label.setForeground(new java.awt.Color(238, 35, 43));
        costsmsi_label.setText("COSTS:");
        MsiTab.add(costsmsi_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 510, 100, 40));

        msi_Label.setFont(new java.awt.Font("Microsoft YaHei", 1, 36)); // NOI18N
        msi_Label.setForeground(new java.awt.Color(238, 35, 43));
        msi_Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/pngwing.com (1).png"))); // NOI18N
        MsiTab.add(msi_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, 140, 50));

        Tabs.addTab("MSI", MsiTab);

        statisticsTab.setBackground(new java.awt.Color(51, 51, 51));
        statisticsTab.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        chartPanel.setLayout(new java.awt.BorderLayout());
        statisticsTab.add(chartPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, 820, 370));

        Tabs.addTab("Statistics", statisticsTab);

        getContentPane().add(Tabs, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 1020, 640));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void utilitymsi_labelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_utilitymsi_labelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_utilitymsi_labelActionPerformed

    private void startSimulationActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_startSimulationActionPerformed
        getDell().start();
        getMsi().start();

        toggleStartSimulationButton(false);
        toggleWorkersQuantityButtons(true);
    }// GEN-LAST:event_startSimulationActionPerformed

    private void setConfigurationActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_setConfigurationActionPerformed
       String configString = "General\ndayDuration\n";

    try {
        int dayDuration = Integer.parseInt(dayDurationInput.getText()) * 1000;
        int deliveryDays = Integer.parseInt(deliveryDaysInput.getText());

        configString += Integer.toString(dayDuration) + "\ndeliveryDays\n" + Integer.toString(deliveryDays) + "\n";

        // Dell workers
        Integer motherboardProducersDell = (Integer) productores_placa_base_dell.getValue();
        Integer cpuProducersDell = (Integer) productores_cpu_dell.getValue();
        Integer ramProducersDell = (Integer) productores_memoria_ram_dell.getValue();
        Integer powerSupplyProducersDell = (Integer) productores_fuente_alimentacion_dell.getValue();
        Integer gpuProducersDell = (Integer) productores_gpu_dell.getValue();
        Integer assemblersDell = (Integer) productores_ensambladores_dell.getValue();

        int totalWorkersDell = motherboardProducersDell + cpuProducersDell + ramProducersDell
                + powerSupplyProducersDell + gpuProducersDell + assemblersDell;

        // MSI Workers
        Integer motherboardProducersMSI = (Integer) productores_placa_base_msi.getValue();
        Integer cpuProducersMSI = (Integer) productores_cpu_msi.getValue();
        Integer ramProducersMSI = (Integer) productores_memoria_ram_msi.getValue();
        Integer powerSupplyProducersMSI = (Integer) productores_fuente_alimentacion_msi.getValue();
        Integer gpuProducersMSI = (Integer) productores_gpu_msi.getValue();
        Integer assemblersMSI = (Integer) productores_ensambladores_msi.getValue();

        int totalWorkersMSI = motherboardProducersMSI + cpuProducersMSI + ramProducersMSI
                + powerSupplyProducersMSI + gpuProducersMSI + assemblersMSI;

        if ((totalWorkersDell > 20) || (totalWorkersMSI > 12)) {
            throw new Exception();
        }

        configString = getFunctions().generateConfigString("DELL", configString, motherboardProducersDell,
                cpuProducersDell, ramProducersDell, powerSupplyProducersDell, gpuProducersDell,
                assemblersDell);
        configString = getFunctions().generateConfigString("MSI", configString,
                motherboardProducersMSI, cpuProducersMSI, ramProducersMSI,
                powerSupplyProducersMSI, gpuProducersMSI, assemblersMSI);

        ReadFile file = new ReadFile();
        file.printTxt(configString);

        String newConfig = file.readTxt();
        file.readConfig(newConfig, config);

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "You have to enter a number");
    }

    }// GEN-LAST:event_setConfigurationActionPerformed
    
    
    
    
    
    

    private void dell_motherboard_plusActionPerformed(java.awt.event.ActionEvent evt) {
        functions.increaseWorkersByType(getDell(), 0);
    }

    private void dell_MotherBoard_minActionPerformed(java.awt.event.ActionEvent evt) {
        functions.reduceWorkersByType(getDell(), 0);

    }

    private void dell_cpu_plusActionPerformed(java.awt.event.ActionEvent evt) {
        getDell().changeWorkerType(-1, 1);
    }

    private void dell_cpu_minActionPerformed(java.awt.event.ActionEvent evt) {
        functions.reduceWorkersByType(getDell(), 1);

    }

    private void dell_ram_plusActionPerformed(java.awt.event.ActionEvent evt) {
        getDell().changeWorkerType(-1, 2);

    }

    private void dell_ram_minActionPerformed(java.awt.event.ActionEvent evt) {
        functions.reduceWorkersByType(getDell(), 2);

    }

    private void dell_power_supply_plusActionPerformed(java.awt.event.ActionEvent evt) {
        functions.increaseWorkersByType(getDell(), 3);

    }

    private void dell_powerSupply_minActionPerformed(java.awt.event.ActionEvent evt) {
        functions.reduceWorkersByType(getDell(), 3);

    }

    private void dell_gpu_minActionPerformed(java.awt.event.ActionEvent evt) {
        functions.reduceWorkersByType(getDell(), 4);

    }

    private void dell_gpu_plusActionPerformed(java.awt.event.ActionEvent evt) {
        functions.increaseWorkersByType(getDell(), 4);

    }

    private void dell_assembly_minaActionPerformed(java.awt.event.ActionEvent evt) {
        functions.reduceWorkersByType(getDell(), 5);

    }

    private void dell_assembly_plusActionPerformed(java.awt.event.ActionEvent evt) {
        functions.increaseWorkersByType(getDell(), 5);

    }

    private void motherboard_plus_msiActionPerformed(java.awt.event.ActionEvent evt) {
        functions.increaseWorkersByType(getMsi(), 0);

    }

    private void cpu_plus_msiActionPerformed(java.awt.event.ActionEvent evt) {
        functions.increaseWorkersByType(getMsi(), 1);

    }

    private void ram_plus_msiActionPerformed(java.awt.event.ActionEvent evt) {
        functions.increaseWorkersByType(getMsi(), 2);

    }

    private void motherboard_Min_msiActionPerformed(java.awt.event.ActionEvent evt) {
        functions.reduceWorkersByType(getMsi(), 0);

    }

    private void cpu_Min_msiActionPerformed(java.awt.event.ActionEvent evt) {
        functions.reduceWorkersByType(getMsi(), 1);

    }

    private void tam_Min_msiActionPerformed(java.awt.event.ActionEvent evt) {
        functions.reduceWorkersByType(getMsi(), 2);

    }

    private void power_supply_plus_msiActionPerformed(java.awt.event.ActionEvent evt) {
        functions.increaseWorkersByType(getMsi(), 3);

    }

    private void powerSupply_Min_msiActionPerformed(java.awt.event.ActionEvent evt) {
        functions.reduceWorkersByType(getMsi(), 3);

    }

    private void gpu_Min_msiActionPerformed(java.awt.event.ActionEvent evt) {
        functions.reduceWorkersByType(getMsi(), 4);

    }

    private void gpu_plus_msiActionPerformed(java.awt.event.ActionEvent evt) {
        functions.increaseWorkersByType(getMsi(), 4);

    }

    private void assembling_Min_msiActionPerformed(java.awt.event.ActionEvent evt) {
        functions.reduceWorkersByType(getMsi(), 5);

    }

    private void assemblin_plus_msiActionPerformed(java.awt.event.ActionEvent evt) {
        functions.increaseWorkersByType(getMsi(), 5);

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default
         * look and feel.
         * For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel CNMaximumWorkers_label;
    private javax.swing.JLabel CPU_label;
    private javax.swing.JLabel ComputerProduced_del;
    private javax.swing.JLabel ComputerProducedmsi;
    private javax.swing.JLabel Computer_parts_dell_label;
    private javax.swing.JLabel Config_label;
    private javax.swing.JPanel ConfigurationTab;
    private javax.swing.JLabel DaysLeftmsi;
    private javax.swing.JLabel DellConfig_label;
    private javax.swing.JPanel DellTab;
    private javax.swing.JLabel DelllMinWorker_label;
    private javax.swing.JLabel General_label;
    private javax.swing.JLabel MSIConfig_label;
    private javax.swing.JLabel MSI_script_cpu;
    private javax.swing.JLabel MSI_script_gpu;
    private javax.swing.JLabel MSI_script_power_supply;
    private javax.swing.JLabel MSI_script_ram;
    private javax.swing.JLabel MSI_scritpt_motherboard;
    private javax.swing.JLabel MotherBoard_label;
    private javax.swing.JLabel MotherBoard_tab;
    private javax.swing.JPanel MsiTab;
    private javax.swing.JLabel PowerSupply_label;
    private javax.swing.JLabel PowerSupply_label1;
    private javax.swing.JLabel Ram_Memory_msi;
    private javax.swing.JLabel Ram_label_msi;
    private javax.swing.JLabel Status_pm_dell;
    private javax.swing.JTabbedPane Tabs;
    private javax.swing.JLabel animationsCN_label;
    private javax.swing.JLabel assemblers_label;
    private javax.swing.JLabel assemblers_label2;
    private javax.swing.JLabel assemblersmsi_Label;
    private javax.swing.JButton assemblin_plus_msi;
    private javax.swing.JButton assembling_Min_msi;
    private javax.swing.JPanel chartPanel;
    private javax.swing.JLabel chas13;
    private javax.swing.JLabel chas14;
    private javax.swing.JLabel chas15;
    private javax.swing.JLabel chas16;
    private javax.swing.JLabel chas17;
    private javax.swing.JLabel chas18;
    private javax.swing.JLabel computer_parts_title_dell;
    private javax.swing.JLabel costsdell;
    private javax.swing.JLabel costsdell_label;
    private javax.swing.JLabel costsmsi;
    private javax.swing.JLabel costsmsi_label;
    private javax.swing.JButton cpu_Min_msi;
    private javax.swing.JLabel cpu_dell;
    private javax.swing.JLabel cpu_label1;
    private javax.swing.JLabel cpu_label_Label;
    private javax.swing.JButton cpu_plus_msi;
    private javax.swing.JTextPane dayDurationInput;
    private javax.swing.JLabel dayDuration_label;
    private javax.swing.JLabel daysLeftdell;
    private javax.swing.JLabel daysLeftmsi;
    private javax.swing.JLabel daysleftDell;
    private javax.swing.JTextPane deliveryDaysInput;
    private javax.swing.JLabel deliveryDays_label;
    private javax.swing.JButton dell_MotherBoard_min;
    private javax.swing.JLabel dell_assembly;
    private javax.swing.JButton dell_assembly_mina;
    private javax.swing.JButton dell_assembly_plus;
    private javax.swing.JLabel dell_cpu;
    private javax.swing.JButton dell_cpu_min;
    private javax.swing.JButton dell_cpu_plus;
    private javax.swing.JLabel dell_gpu;
    private javax.swing.JButton dell_gpu_min;
    private javax.swing.JButton dell_gpu_plus;
    private javax.swing.JLabel dell_label_cpu;
    private javax.swing.JLabel dell_label_gpu;
    private javax.swing.JLabel dell_label_mb;
    private javax.swing.JLabel dell_label_power_supply;
    private javax.swing.JLabel dell_label_ram;
    private javax.swing.JLabel dell_motherBoard;
    private javax.swing.JLabel dell_motherboard;
    private javax.swing.JButton dell_motherboard_plus;
    private javax.swing.JButton dell_powerSupply_min;
    private javax.swing.JLabel dell_power_supply;
    private javax.swing.JButton dell_power_supply_plus;
    private javax.swing.JLabel dell_ram;
    private javax.swing.JLabel dell_ram_label;
    private javax.swing.JButton dell_ram_min;
    private javax.swing.JButton dell_ram_plus;
    private javax.swing.JLabel directorMSI_title;
    private javax.swing.JLabel directorStatusmsi;
    private javax.swing.JLabel directorStatusmsi_label;
    private javax.swing.JLabel director_status_dell;
    private javax.swing.JLabel directordell_title;
    private javax.swing.JLabel directori_status_label;
    private javax.swing.JLabel disccounted_dell_label;
    private javax.swing.JLabel dubsCN_label;
    private javax.swing.JLabel earningsdell;
    private javax.swing.JLabel earningsdell_label;
    private javax.swing.JLabel earningsmsi;
    private javax.swing.JLabel earningsmsi_label;
    private javax.swing.JLabel faultsQtymsi;
    private javax.swing.JLabel fuente_poder_dell;
    private javax.swing.JLabel gpuComputerMSI;
    private javax.swing.JLabel gpuComputer_dell_qty;
    private javax.swing.JLabel gpuComputermsi;
    private javax.swing.JButton gpu_Min_msi;
    private javax.swing.JLabel gpu_dell;
    private javax.swing.JLabel gpu_label;
    private javax.swing.JLabel gpu_label2;
    private javax.swing.JButton gpu_plus_msi;
    private javax.swing.JLabel gpu_producer_msi;
    private javax.swing.JLabel infoLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel managerDiscountedmsi;
    private javax.swing.JLabel managerFaultsmsi_label;
    private javax.swing.JLabel managerSalaryDiscountmsi;
    private javax.swing.JLabel managerStatusmsi;
    private javax.swing.JLabel managerStatusmsi_label;
    private javax.swing.JLabel manager_discounted_qty_dekk;
    private javax.swing.JLabel manager_fault_label_dell;
    private javax.swing.JLabel manager_status_dell;
    private javax.swing.JLabel maxMotherBoard;
    private javax.swing.JLabel max_dell_cpu;
    private javax.swing.JLabel max_dell_gpu;
    private javax.swing.JLabel max_dell_powersupply;
    private javax.swing.JLabel max_dell_ram;
    private javax.swing.JLabel max_mother_board;
    private javax.swing.JLabel max_msi_cpu;
    private javax.swing.JLabel max_msi_gpu;
    private javax.swing.JLabel max_msi_power_supply;
    private javax.swing.JLabel max_msi_ram;
    private javax.swing.JButton motherboard_Min_msi;
    private javax.swing.JLabel motherboard_label1;
    private javax.swing.JButton motherboard_plus_msi;
    private javax.swing.JLabel msi_Label;
    private javax.swing.JLabel plotTwistsCN_label;
    private javax.swing.JButton powerSupply_Min_msi;
    private javax.swing.JLabel power_supply_msi;
    private javax.swing.JButton power_supply_plus_msi;
    private javax.swing.JSpinner productores_cpu_dell;
    private javax.swing.JSpinner productores_cpu_msi;
    private javax.swing.JSpinner productores_ensambladores_dell;
    private javax.swing.JSpinner productores_ensambladores_msi;
    private javax.swing.JSpinner productores_fuente_alimentacion_dell;
    private javax.swing.JSpinner productores_fuente_alimentacion_msi;
    private javax.swing.JSpinner productores_gpu_dell;
    private javax.swing.JSpinner productores_gpu_msi;
    private javax.swing.JSpinner productores_memoria_ram_dell;
    private javax.swing.JSpinner productores_memoria_ram_msi;
    private javax.swing.JSpinner productores_placa_base_dell;
    private javax.swing.JSpinner productores_placa_base_msi;
    private javax.swing.JLabel projectManager_dell;
    private javax.swing.JLabel projectManagermsi_title;
    private javax.swing.JLabel qty_MotherBoard_msi;
    private javax.swing.JLabel qty_assembli_msi;
    private javax.swing.JLabel qty_cpu_msi;
    private javax.swing.JLabel qty_faults_dell;
    private javax.swing.JLabel qty_gpu_msi;
    private javax.swing.JLabel qty_powerS_msi;
    private javax.swing.JLabel qty_ram_msi;
    private javax.swing.JLabel ram_dell;
    private javax.swing.JButton ram_plus_msi;
    private javax.swing.JLabel scenariosCN_label;
    private javax.swing.JLabel screenMotherBoard_msi_Label;
    private javax.swing.JButton setConfiguration;
    private javax.swing.JLabel standarComputermsi;
    private javax.swing.JLabel standardComputerProducedMSI;
    private javax.swing.JLabel standardComputer_dell_label;
    private javax.swing.JLabel standardComputer_dell_qty;
    private javax.swing.JLabel standardDell_Computer;
    private javax.swing.JButton startSimulation;
    private javax.swing.JPanel statisticsTab;
    private javax.swing.JButton tam_Min_msi;
    private javax.swing.JFormattedTextField utility_dell_days;
    private javax.swing.JLabel utilitydell;
    private javax.swing.JLabel utilitymsi;
    private javax.swing.JFormattedTextField utilitymsi_label;
    private javax.swing.JLabel wm_label5;
    private javax.swing.JLabel worker_msi;
    // End of variables declaration//GEN-END:variables
}

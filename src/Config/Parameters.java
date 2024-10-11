package Config;

import Classes.ComputerSpecs;

/**
 *
 * @author Felipe
 */



/**
 * Clase que maneja los parámetros para diferentes tipos de trabajadores en la simulación de la compañía de computadoras.
 */
public class Parameters {

    private WorkerParams motherboardParams;
    private WorkerParams cpuParams;
    private WorkerParams ramParams;
    private WorkerParams powerSupplyParams;
    private WorkerParams gpuParams;
    private WorkerParams assemblersParams;
    private ComputerSpecs computerSpecs;


    /**
     * Constructor por defecto que inicializa todos los parámetros de trabajadores con valores predeterminados.
     */
    public Parameters() {
         // Inicialización con valores por defecto
        this.motherboardParams = new WorkerParams(1, 0, "", 0);
        this.cpuParams = new WorkerParams(1, 0, "", 0);
        this.ramParams = new WorkerParams(1, 0, "", 0);
        this.powerSupplyParams = new WorkerParams(1, 0, "", 0);
        this.gpuParams = new WorkerParams(1, 0, "", 0);
        this.assemblersParams = new WorkerParams(1, 0, "", 0);
    }

      /**
     * Constructor que permite inicializar todos los parámetros de trabajadores y especificaciones de computadoras.
     */
    public Parameters(WorkerParams screenwriters, WorkerParams scenarioDesigners, WorkerParams animators,
            WorkerParams voiceActors, WorkerParams plotTwistWriters,
            WorkerParams assemblers, ComputerSpecs specs) {
        this.motherboardParams = screenwriters;
        this.cpuParams = scenarioDesigners;
        this.ramParams = animators;
        this.powerSupplyParams = voiceActors;
        this.gpuParams = plotTwistWriters;
        this.assemblersParams = assemblers;
        this.computerSpecs = specs;
    }
    
    
       /**
     * Obtiene los parámetros del trabajador según su tipo.

     */

    public WorkerParams getWorkerParamsByType(int workerType) {
        return switch (workerType) {
            case 0 ->
                getmotherboardParams();
            case 1 ->
                getcpuParams();
            case 2 ->
                getramParams();
            case 3 ->
                getpowerSupplyParams();
            case 4 ->
                getgpuParams();
            case 5 ->
                getAssemblersParams();
            default ->
                null;
        };
    }
    
    
  /**
     * Método alternativo para obtener los parámetros del trabajador según su tipo.
     * @param workerType El tipo de trabajador (0-5).
     * @return Los parámetros del trabajador correspondiente o parámetros no asignados si el tipo no es válido.
     */
    public WorkerParams getParamsByWorkerType(int workerType) {
        switch (workerType) {

            case 0 -> {
                return getmotherboardParams();
            }

            case 1 -> {
                return getcpuParams();
            }

            case 2 -> {
                return getramParams();
            }

            case 3 -> {
                return getpowerSupplyParams();
            }

            case 4 -> {
                return getgpuParams();
            }

            case 5 -> {
                return getAssemblersParams();
            }

            default -> {
                WorkerParams unassignedParams = new WorkerParams(0, 0, "Unassigned", 0);
                return unassignedParams;
            }
        }
    }
    
       /**
     * Establece la cantidad de trabajadores para un tipo específico.
     * @param workerType El tipo de trabajador (0-5).
     * @param newQuantity La nueva cantidad de trabajadores.
     */

    public void setParamsQuantityByWorkerType(int workerType, int newQuantity) {
        switch (workerType) {

            case 0 -> {
                getmotherboardParams().setQuantity(newQuantity);
            }

            case 1 -> {
                getcpuParams().setQuantity(newQuantity);
            }

            case 2 -> {
                getramParams().setQuantity(newQuantity);
            }

            case 3 -> {
                getpowerSupplyParams().setQuantity(newQuantity);
            }

            case 4 -> {
                getgpuParams().setQuantity(newQuantity);
            }

            case 5 -> {
                getAssemblersParams().setQuantity(newQuantity);
            }

            default -> {
            }
        }
    }

    // Getters and Setters
    public WorkerParams getmotherboardParams() {
        return motherboardParams;
    }

    public void setmotherboardParams(WorkerParams motherboardParams) {
        this.motherboardParams = motherboardParams;
    }

    public WorkerParams getcpuParams() {
        return cpuParams;
    }

    public void setcpuParams(WorkerParams cpuParams) {
        this.cpuParams = cpuParams;
    }

    public WorkerParams getramParams() {
        return ramParams;
    }

    public void setramParams(WorkerParams ramParams) {
        this.ramParams = ramParams;
    }

    public WorkerParams getpowerSupplyParams() {
        return powerSupplyParams;
    }

    public void setpowerSupplyParams(WorkerParams powerSupplyParams) {
        this.powerSupplyParams = powerSupplyParams;
    }

    public WorkerParams getgpuParams() {
        return gpuParams;
    }

    public void setgpuParams(WorkerParams gpuParams) {
        this.gpuParams = gpuParams;
    }

    public WorkerParams getAssemblersParams() {
        return assemblersParams;
    }

    public void setAssemblersParams(WorkerParams assemblersParams) {
        this.assemblersParams = assemblersParams;
    }

    public ComputerSpecs getcomputerSpecs() {
        return computerSpecs;
    }

    public void setcomputerSpecs(ComputerSpecs specs) {
        this.computerSpecs = specs;
    }

}

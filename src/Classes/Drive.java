package Classes;

import UserInterface.MainUI;

/**
 *
 * @author Felipe
 */


/**
 * Representa el almacén de componentes y gestiona el ensamblaje de computadoras.
 */
public class Drive {

    private int companyInt;
    private int[] componentElements;
    private int[] maxComponentElements;
    private int standardComputersCounter;
    private int graphicsComputersCounter;
    private int nextGraphicsComputer;
    private MainUI userInterface;
    private ComputerSpecs specs;
    
    
       /**
     * Constructor que inicializa el almacén con capacidades máximas para cada componente.
     */

    public Drive(int companyInt, int maxScripts, int maxScenarios, int maxAnimations, int maxVoices,
            int maxPlotTwists, ComputerSpecs specs, MainUI userInterface) {
        this.companyInt = companyInt;
        this.componentElements = new int[]{0, 0, 0, 0, 0};
        this.maxComponentElements = new int[]{maxScripts, maxScenarios, maxAnimations, maxVoices, maxPlotTwists};
        this.standardComputersCounter = 0;
        this.graphicsComputersCounter = 0;
        this.userInterface = userInterface;
        this.specs = specs;
        this.nextGraphicsComputer = specs.getpolicyForHighPerformance();

        // nextGraphicsComputer
        // iniciara siendo igual a la cantidad de la politica e ira disminuyendo hasta llegar a 0.
        // cuando llegue a cero, se debe crear capPlotTwist y resetear la cuenta.
    }



 /**
     * Añade una cantidad de un tipo específico de componente al almacén.
     */
   public void addElement(int typeInt, int elementQuantity) {
        if (this.isNotFull(typeInt)) {
            
            this.addComponentQuantity(typeInt, elementQuantity);
        }
        this.getUserInterface().changeDriveElements(getcompanyInt(), typeInt, getcomponentElements());
    }

    
 /**
     * Incrementa el contador de computadoras por tipo (estándar o con gráficos).
     */
    public void addChapterByType(int chapterType) {
        switch (chapterType) {
            case 0:
                this.setstandardComputersCounter(this.getstandardComputersCounter() + 1);
                this.setnextGraphicsComputer(this.getnextGraphicsComputer() - 1);
                this.getUserInterface().changeChapterQuantity(this.getcompanyInt(), 0, this.getstandardComputersCounter());
                break;
            case 1:
                this.setgraphicsComputersCounter(this.getgraphicsComputersCounter() + 1);
                this.resetHighPerformanceComputerCounter();
                this.getUserInterface().changeChapterQuantity(this.getcompanyInt(), 1, this.getgraphicsComputersCounter());
                break;
            default:
                break;
        }
        
    }
    /**
     * Verifica si es momento de ensamblar una computadora con gráficos avanzados.
     */
private boolean isTimeToAssembleHighPerformanceComputer() {
    return this.getnextGraphicsComputer() == 0;
}

  

private void assembleHighPerformanceComputer() {
    this.subtractcomponentElements(this.getSpecs().gethighPerformanceSpecs());
    //this.graphicsComputersCounter++; // creo debo sumar desde worker
    this.resetHighPerformanceComputerCounter();
}

    private void subtractcomponentElements(int[] specificChapterSpecs) {
        for (int i = 0; i < getcomponentElements().length; i++) {
            getcomponentElements()[i] = getcomponentElements()[i] - specificChapterSpecs[i];
        }
    }

    public void subtractcomponentElements(int chapterType) {
        switch (chapterType) {
            case 0:
                this.subtractcomponentElements(this.getSpecs().getstandardSpecs());
                this.getUserInterface().changeDriveElements(getcompanyInt(), 5, getcomponentElements());
                break;
            case 1:
                this.subtractcomponentElements(this.getSpecs().gethighPerformanceSpecs());
                this.getUserInterface().changeDriveElements(getcompanyInt(), 5, getcomponentElements());
                break;
            default:
                break;

        }

    }

  private void resetHighPerformanceComputerCounter() {
    this.setnextGraphicsComputer(this.getSpecs().getpolicyForHighPerformance());
}

   private boolean isTimeToAssembleStandardComputer() {
    return !this.isTimeToAssembleHighPerformanceComputer();
}

  public boolean hasComponentsForStandardComputer() {
    return this.getSpecs().checkStandardSpecs(getcomponentElements());
}

 public boolean hasComponentsForHighPerformanceComputer() {
    return this.getSpecs().checkStandardSpecs(getcomponentElements());
}



    public int determineComputerTypeToAssemble() {
    int computerType = -1;
    if (this.canAssembleStandardComputerWithSufficientComponents()) {
        computerType = 0;
    } else if (this.canAssembleHighPerformanceComputerWithSufficientComponents()) {
        computerType = 1;
    }
    return computerType;}


  public boolean canAssembleStandardComputerWithSufficientComponents() {
    return this.isTimeToAssembleStandardComputer() && this.hasComponentsForStandardComputer();
}

  public boolean canAssembleHighPerformanceComputerWithSufficientComponents() {
    return this.isTimeToAssembleHighPerformanceComputer() && this.hasComponentsForHighPerformanceComputer();
}
   private int getMaxComponentCapacity(int componentIndex) {
    return this.getMaxComponentElements()[componentIndex];
}


    private int getMaxByWorkerTypeIndex(int index) {
        return this.getMaxComponentElements()[index];
    }

    private boolean isNotFull(int index) {
        // Corregimos esta línea para que tenga sentido
        return this.getCurrentComponentQuantity(index) < this.getMaxComponentCapacity(index);
    }

    private int getCurrentComponentQuantity(int componentIndex) {
        return this.getcomponentElements()[componentIndex];
    }
    

   private void addComponentQuantity(int componentType, int quantityToAdd) {
    int currentQuantity = this.getcomponentElements()[componentType];
    int maxCapacity = this.getMaxComponentElements()[componentType];
    if ((currentQuantity + quantityToAdd) > maxCapacity) {
        this.getcomponentElements()[componentType] = maxCapacity;
    } else {
        this.getcomponentElements()[componentType] += quantityToAdd;
    }
}

    //Getters and Setters
    /**
     * @return the componentElements
     */
    public int[] getcomponentElements() {
        return componentElements;
    }

    /**
     * @param componentElements the componentElements to set
     */
    public void setcomponentElements(int[] componentElements) {
        this.setcomponentElements( componentElements);
    }

    public int getcompanyInt() {
        return companyInt;
    }

    public void setcompanyInt(int companyInt) {
        this.companyInt = companyInt;
    }

    /**
     * @return the specs
     */
    public ComputerSpecs getSpecs() {
        return specs;
    }

    /**
     * @return the maxComponentElements
     */
    public int[] getMaxComponentElements() {
        return maxComponentElements;
    }

    /**
     * @param maxComponentElements the maxComponentElements to set
     */
    public void setMaxComponentElements(int[] maxComponentElements) {
        this.maxComponentElements = maxComponentElements;
    }

    /**
     * @return the standardComputersCounter
     */
    public int getstandardComputersCounter() {
        return standardComputersCounter;
    }

    /**
     * @param standardComputersCounter the standardComputersCounter to set
     */
    public void setstandardComputersCounter(int standardComputersCounter) {
        this.standardComputersCounter = standardComputersCounter;
    }

    /**
     * @return the graphicsComputersCounter
     */
    public int getgraphicsComputersCounter() {
        return graphicsComputersCounter;
    }

    /**
     * @param graphicsComputersCounter the graphicsComputersCounter to set
     */
    public void setgraphicsComputersCounter(int graphicsComputersCounter) {
        this.graphicsComputersCounter = graphicsComputersCounter;
    }

    /**
     * @return the nextGraphicsComputer
     */
    public int getnextGraphicsComputer() {
        return nextGraphicsComputer;
    }

    /**
     * @param nextGraphicsComputer the nextGraphicsComputer to set
     */
    public void setnextGraphicsComputer(int nextGraphicsComputer) {
        this.nextGraphicsComputer = nextGraphicsComputer;
    }

    /**
     * @return the userInterface
     */
    public MainUI getUserInterface() {
        return userInterface;
    }

    /**
     * @param userInterface the userInterface to set
     */
    public void setUserInterface(MainUI userInterface) {
        this.userInterface = userInterface;
    }

    /**
     * @param specs the specs to set
     */
    public void setSpecs(ComputerSpecs specs) {
        this.specs = specs;
    }
    
 

}

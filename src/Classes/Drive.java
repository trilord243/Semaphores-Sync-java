package Classes;

import UserInterface.MainUI;

/**
 *
 * @author Felipe
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
     * This is the actual addElement function.
     *
     * @param typeInt
     * @param elementQuantity
     */
    public void addElement(int typeInt, int elementQuantity) {
        if (this.isNotFull(typeInt)) {
            this.increaseChapterElement(typeInt, elementQuantity);
        }
        this.getUserInterface().changeDriveElements(getcompanyInt(), typeInt, getcomponentElements());

    }

    

    public void addChapterByType(int chapterType) {
        switch (chapterType) {
            case 0:
                this.setstandardComputersCounter(this.getstandardComputersCounter() + 1);
                this.setnextGraphicsComputer(this.getnextGraphicsComputer() - 1);
                this.getUserInterface().changeChapterQuantity(this.getcompanyInt(), 0, this.getstandardComputersCounter());
                break;
            case 1:
                this.setgraphicsComputersCounter(this.getgraphicsComputersCounter() + 1);
                this.resetnextGraphicsComputer();
                this.getUserInterface().changeChapterQuantity(this.getcompanyInt(), 1, this.getgraphicsComputersCounter());
                break;
            default:
                break;
        }
        
    }

    private boolean isTimeToPlotTwistChapter() {
        return this.getnextGraphicsComputer() == 0;
    }

  

    private void addPlotTwistChapter() {
        this.subtractcomponentElements(this.getSpecs().gethighPerformanceSpecs());
        //this.graphicsComputersCounter++; // creo debo sumar desde worker
        this.resetnextGraphicsComputer();
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

    private void resetnextGraphicsComputer() {
        this.setnextGraphicsComputer(this.getSpecs().getpolicyForHighPerformance());
    }

    private boolean isTimeToStandardChapter() {
        return !this.isTimeToPlotTwistChapter();
    }

    public boolean canAssembleStandardChapter() {
        return this.getSpecs().checkStandardSpecs(getcomponentElements());
    }

    public boolean canAssemblePlotTwistChapter() {
        return this.getSpecs().checkStandardSpecs(getcomponentElements());
    }


    public int decideWhichChapterToAssemble() {
        int chapterType = -1;
        if (this.canAssembleStandardAndEnoughElements()) {
            chapterType = 0;
        } else if (this.canAssemblePlotTwistAndEnoughElements()) {
            chapterType = 1;
        }
        return chapterType;
    }

    public boolean canAssembleStandardAndEnoughElements() {
        return this.isTimeToStandardChapter() && this.canAssembleStandardChapter();
    }

    public boolean canAssemblePlotTwistAndEnoughElements() {
        return this.isTimeToPlotTwistChapter() && this.canAssemblePlotTwistChapter();
    }

    private int getAmountByWorkerTypeIndex(int index) {
        return this.getcomponentElements()[index];
    }

    private int getMaxByWorkerTypeIndex(int index) {
        return this.getMaxComponentElements()[index];
    }

    private boolean isNotFull(int index) {
        return this.getAmountByWorkerTypeIndex(index) < this.getMaxByWorkerTypeIndex(index);
    }

    

    private void increaseChapterElement(int workerType, int elementQuantity) {
        int currentElementQuantity = this.getcomponentElements()[workerType];
        int maxChapterElement = this.getMaxComponentElements()[workerType];
        if ((currentElementQuantity + elementQuantity) > maxChapterElement) {
            this.getcomponentElements()[workerType] = maxChapterElement;
        } else {
            this.getcomponentElements()[workerType] += elementQuantity;

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

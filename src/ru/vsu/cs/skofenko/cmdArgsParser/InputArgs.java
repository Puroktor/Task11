package ru.vsu.cs.skofenko.cmdArgsParser;

public class InputArgs {
    private String inputFile;
    private String outputFile;
    private boolean helpNeeded = false;

    public boolean isHelpNeeded() {
        return helpNeeded;
    }

    public void setHelpNeeded(boolean helpNeeded) {
        this.helpNeeded = helpNeeded;
    }

    public String getInputFile() {
        return inputFile;
    }

    public void setInputFile(String inputFile) {
        this.inputFile = inputFile;
    }

    public String getOutputFile() {
        return outputFile;
    }

    public void setOutputFile(String outputFile) {
        this.outputFile = outputFile;
    }
}
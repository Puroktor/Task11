package ru.vsu.cs.skofenko.cmdArgsParser;

public class DoublePathException extends Exception {
    private final FileType fileType;

    public DoublePathException(FileType fileType) {
        this.fileType = fileType;
    }

    public FileType getFileType() {
        return fileType;
    }
}

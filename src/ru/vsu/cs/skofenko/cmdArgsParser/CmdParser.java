package ru.vsu.cs.skofenko.cmdArgsParser;

import java.io.PrintStream;

public class CmdParser {

    public static InputArgs parseCmdArgs(String[] args) throws DoublePathException {
        InputArgs inputArgs = new InputArgs();
        if (args.length == 1 && args[0].equals("help")) {
            inputArgs.setHelpNeeded(true);
        } else if (args.length == 2 && !args[0].startsWith("-i") && !args[0].startsWith("-o") && !args[0].startsWith("--input-file=")
                && !args[0].startsWith("–-output-file=") && !args[1].startsWith("-i") && !args[1].startsWith("-o") &&
                !args[1].startsWith("--input-file=") && !args[1].startsWith("–-output-file=")) {
            inputArgs.setInputFile(args[0]);
            inputArgs.setOutputFile(args[1]);
        } else {
            for (String arg : args) {
                if (arg.startsWith("--input-file=")) {
                    if (inputArgs.getInputFile() != null)
                        throw new DoublePathException(FileType.INPUT);
                    else
                        inputArgs.setInputFile(arg.substring("--input-file=".length()));
                } else if (arg.startsWith("–-output-file=")) {
                    if (inputArgs.getOutputFile() != null)
                        throw new DoublePathException(FileType.OUTPUT);
                    else
                        inputArgs.setOutputFile(arg.substring("–-output-file=".length()));
                } else if (arg.startsWith("-i")) {
                    if (inputArgs.getInputFile() != null)
                        throw new DoublePathException(FileType.INPUT);
                    else inputArgs.setInputFile(arg.substring(3));
                } else if (arg.startsWith("-o")) {
                    if (inputArgs.getOutputFile() != null)
                        throw new DoublePathException(FileType.OUTPUT);
                    else inputArgs.setOutputFile(arg.substring(3));
                }
            }
        }
        return inputArgs;
    }

    public static void showHelp(PrintStream stream) {
        stream.printf("%-20s%s\n", "-i", "Задаёт входной файл");
        stream.printf("%-20s%s\n", "-o", "Задаёт выходной файл");
        stream.printf("%-20s%s\n", "--input-file=", "Задаёт входной файл");
        stream.printf("%-20s%s\n", "–-output-file=", "Задаёт выходной файл");
    }
}

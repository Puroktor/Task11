package ru.vsu.cs.skofenko;

import ru.vsu.cs.skofenko.cmdArgsParser.*;

import java.io.FileNotFoundException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        try {
            InputArgs inputArgs = CmdParser.parseCmdArgs(args);
            if (inputArgs.isHelpNeeded()) {
                CmdParser.showHelp(System.out);
            } else {
                List<String> input = InOutClass.readListFromFile(inputArgs.getInputFile());
                List<String> isDate = Logic.findDates(input);
                InOutClass.writeListToFile(inputArgs.getOutputFile(), isDate);
            }
        } catch (DoublePathException e) {
            System.err.println("Путь к " + e.getFileType() + " введён два раза");
            System.exit(-1);
        } catch (FileNotFoundException e) {
            System.err.println("Файл ввода вывода не найден");
            System.exit(-2);
        } catch (NullPointerException e) {
            CmdParser.showHelp(System.err);
            System.exit(-3);
        }
    }
}

package service;

import lombok.Data;
import model.output.OutputFloats;
import model.output.OutputIntegers;
import model.output.OutputStrings;
import parser.Parser;
import service.doubles.ServiceDoubles;
import service.integers.ServiceIntegers;
import service.strings.ServiceStrings;
import writer.Writer;

import java.util.List;

@Data
public class Service {
    private final Parser parser = new Parser();
    private final Writer writer = new Writer();
    private final OutputIntegers integers = new OutputIntegers();
    private final OutputFloats doubles = new OutputFloats();
    private final OutputStrings strings = new OutputStrings();
    private final ServiceIntegers serInt = new ServiceIntegers(integers);
    private final ServiceDoubles serDoubles = new ServiceDoubles(doubles);
    private final ServiceStrings serStrings = new ServiceStrings(strings);

    public void processService2 (String directoryPath, String prefix, boolean isRecoding,
                                boolean shotStatistic, boolean fullStatistic) {
        serInt.writeIntegersToFile(directoryPath, prefix, isRecoding);
        serDoubles.writeFloatsToFile(directoryPath, prefix, isRecoding);
        serStrings.writeStringsToFile(directoryPath, prefix, isRecoding);
        shotStatistic2(shotStatistic);
        fullStatistic2(fullStatistic);
    }

    public void filter2 (String path) {
        List<String> lines = parser.getInputFile(path).getLines();
        for (String line : lines) {
            if (isDigit(line)) {
                integers.getIntegers().add(Long.valueOf(line));
            } else if (isDouble(line)) {
                doubles.getDoubles().add(Double.valueOf(line));
            } else {
                strings.getStrings().add(line);
            }
        }
    }

    private boolean isDigit(String s) throws NumberFormatException {
        try {
            Long.parseLong(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isDouble(String s) throws NumberFormatException {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void shotStatistic2 (boolean shotStatistic) {
        if (shotStatistic) {
            System.out.println("Краткая статичстика:");
            System.out.println("Количество целочисленных элементов = " + integers.getIntegers().size());
            System.out.println("Количество вещественных элементов = " + doubles.getDoubles().size());
            System.out.println("Количество строковых элементов = " + strings.getStrings().size());
        }
    }

    private void fullStatistic2 (boolean fullStatistic) {
        if (fullStatistic) {
            System.out.println("Полная статистика:");
            serInt.fullIntegersStatistic();
            serDoubles.fullDoublesStatistic();
            serStrings.fullStringsStatistic();
        }
    }
}

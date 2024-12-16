package service;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import model.output.OutputFloats;
import model.output.OutputIntegers;
import model.output.OutputStrings;
import parser.Parser;
import service.doubles.ServiceDoubles;
import service.integers.ServiceIntegers;
import service.strings.ServiceStrings;
import writer.Writer;

import java.util.List;

@Slf4j
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

    public void processService(String directoryPath, String prefix, boolean isRecoding,
                               boolean shotStatistic, boolean fullStatistic) {
        writer.toWrite(integers.getIntegers(), doubles.getDoubles(), strings.getStrings(),
                directoryPath, prefix, isRecoding);
        shotStatistic(shotStatistic);
        fullStatistic(fullStatistic);
    }

    public void filter(String path) {
        List<String> lines = parser.getInputFile(path).getLines();
        for (String line : lines) {
            if (isDigit(line)) {
                integers.getIntegers().add(Long.valueOf(line));
            } else if (isDouble(line)) {
                doubles.getDoubles().add(Double.valueOf(line));
            } else {
                log.info("Строка является символьной строкой");
                strings.getStrings().add(line);
            }
        }
    }

    private boolean isDigit(String s) throws NumberFormatException {
        try {
            Long.parseLong(s);
            log.info("Строка является целочисленным числом");
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isDouble(String s) throws NumberFormatException {
        try {
            Double.parseDouble(s);
            log.info("Строка является вещественным числом");
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void shotStatistic(boolean shotStatistic) {
        if (shotStatistic) {
            System.out.println("Краткая статистика:");
            System.out.println("Количество целочисленных элементов = " + integers.getIntegers().size());
            System.out.println("Количество вещественных элементов = " + doubles.getDoubles().size());
            System.out.println("Количество строковых элементов = " + strings.getStrings().size());
        }
    }

    private void fullStatistic(boolean fullStatistic) {
        if (fullStatistic) {
            System.out.println("Полная статистика:");
            serInt.fullIntegersStatistic();
            serDoubles.fullDoublesStatistic();
            serStrings.fullStringsStatistic();
        }
    }
}

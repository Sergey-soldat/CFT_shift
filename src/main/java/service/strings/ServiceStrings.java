package service.strings;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import model.output.OutputStrings;
import writer.Writer;

import java.util.List;

@Data
@Slf4j
public class ServiceStrings {
    private final OutputStrings strings;
    private final Writer writer = new Writer();

    public ServiceStrings(OutputStrings strings) {
        this.strings = strings;
    }

    public void fullStringsStatistic () {
        if (strings.getStrings().isEmpty()) {
            log.info("Строковых элементов нет");
            System.out.println("Строковых жлементов нет");
        } else {
            log.info("Печать полной статистики для строковых элементов");
            int shortString = findShortString(strings.getStrings());
            int longString = findLongString(strings.getStrings());
            System.out.println("Количество строковых элементов = " + strings.getStrings().size());
            System.out.println("Самая короткая строка длиной " + shortString);
            System.out.println("Самая длинная строка длиной " + longString);
        }
    }

    private int findLongString(List<String> strings) {
        int longLength = Integer.MIN_VALUE;
        for (String str : strings) {
            if (str.length() > longLength) {
                longLength = str.length();
            }
        }
        log.info("Длина самой длинной строки: " + longLength);
        return longLength;
    }

    private int findShortString(List<String> strings) {
        int shortestLength = Integer.MAX_VALUE;
        for (String str : strings) {
            if (str.length() < shortestLength) {
                shortestLength = str.length();
            }
        }
        log.info("Длина самой короткой строки: " + shortestLength);
        return shortestLength;
    }
}

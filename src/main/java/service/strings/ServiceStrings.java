package service.strings;

import lombok.Data;
import model.output.OutputStrings;
import writer.Writer;

import java.util.List;

@Data
public class ServiceStrings {
    private OutputStrings strings;
    private final Writer writer = new Writer();

    public ServiceStrings(OutputStrings strings) {
        this.strings = strings;
    }

    public void writeStringsToFile(String directoryPath, String prefix, boolean isRecoding) {
        if (!strings.getStrings().isEmpty()) {
            writer.toWriteOfStrings(strings.getStrings(), directoryPath, prefix, isRecoding);
        }
    }

    public void fullStringsStatistic () {
        int shortString = findShortString(strings.getStrings());
        int longString = findLongString(strings.getStrings());
        System.out.println("Количество строковых элементов = " + strings.getStrings().size());
        System.out.println("Самая короткая строка длиной " + shortString);
        System.out.println("Самая длинная строка длиной " + longString);
    }

    private int findLongString(List<String> strings) {
        int longLength = Integer.MIN_VALUE;
        for (String str : strings) {
            if (str.length() > longLength) {
                longLength = str.length();
            }
        }
        return longLength;
    }

    private int findShortString(List<String> strings) {
        int shortestLength = Integer.MAX_VALUE;
        for (String str : strings) {
            if (str.length() < shortestLength) {
                shortestLength = str.length();
            }
        }
        return shortestLength;
    }
}

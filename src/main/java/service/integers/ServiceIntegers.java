package service.integers;

import lombok.Data;
import model.output.OutputIntegers;
import writer.Writer;

import java.util.List;

@Data
public class ServiceIntegers {
    private OutputIntegers integers;
    private final Writer writer = new Writer();

    public ServiceIntegers(OutputIntegers integers) {
        this.integers = integers;
    }

    public void writeIntegersToFile (String directoryPath, String prefix, boolean isRecoding) {
        if (!integers.getIntegers().isEmpty()) {
            writer.toWriteOfIntegers(integers.getIntegers(), directoryPath, prefix, isRecoding);
        }
    }

    public void fullIntegersStatistic () {
        long min = findMinIntegers(integers.getIntegers());
        long max = findMaxIntegers(integers.getIntegers());
        long sum = findSumIntegers(integers.getIntegers());
        double avg = findAvgIntegers (integers.getIntegers(), sum);
        System.out.println("Количество целочисленных элементов = " + integers.getIntegers().size());
        System.out.println("Минимальное целочисленное значение = " + min);
        System.out.println("Максимальное целочисленное значение = " + max);
        System.out.println("Сумма всех элементов = " + sum);
        System.out.println("Среднее значение = " + avg);
    }

    private long findSumIntegers(List<Long> integers) {
        long sum = 0;
        for (long integer : integers) {
            sum += integer;
        }
        return sum;
    }

    private double findAvgIntegers(List<Long> integers, long sum) {
        return integers.isEmpty() ? 0 : (double) sum / integers.size();
    }

    private long findMinIntegers(List<Long> integers) {
        return integers.stream()
                .mapToLong(Long::longValue)
                .min()
                .orElse(Long.MAX_VALUE);
    }

    private long findMaxIntegers(List<Long> integers) {
        return integers.stream()
                .mapToLong(Long::longValue)
                .max()
                .orElse(Long.MIN_VALUE);
    }
}

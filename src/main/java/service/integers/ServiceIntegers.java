package service.integers;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import model.output.OutputIntegers;
import writer.Writer;

import java.util.List;

@Data
@Slf4j
public class ServiceIntegers {
    private final OutputIntegers integers;
    private final Writer writer = new Writer();

    public ServiceIntegers(OutputIntegers integers) {
        this.integers = integers;
    }

    public void fullIntegersStatistic () {
        if (integers.getIntegers().isEmpty()) {
            log.info("Целочисленныох элементво нет");
            System.out.println("Целочисленных жлементов нет");
        } else {
            log.info("Печать Полной статистики целочисленных элементтов");
            long min = findMinIntegers(integers.getIntegers());
            long max = findMaxIntegers(integers.getIntegers());
            long sum = findSumIntegers(integers.getIntegers());
            double avg = findAvgIntegers (integers.getIntegers(), sum);
            System.out.println("Количество целочисленных элементов = " + integers.getIntegers().size());
            System.out.println("Минимальное целочисленное значение = " + min);
            System.out.println("Максимальное целочисленное значение = " + max);
            System.out.println("Сумма всех целочисленных элементов = " + sum);
            System.out.println("Среднее значение целочисленных элементов = " + avg);
        }
    }

    private long findSumIntegers(List<Long> integers) {
        long sum = 0;
        for (long integer : integers) {
            sum += integer;
        }
        log.info("Сумма всех целочисленных элементов" + sum);
        return sum;
    }

    private double findAvgIntegers(List<Long> integers, long sum) {
        log.info("Среднее значение целочисленных элементов");
        return integers.isEmpty() ? 0 : (double) sum / integers.size();
    }

    private long findMinIntegers(List<Long> integers) {
        log.info("Минимальное значение целочисленных элементов");
        return integers.stream()
                .mapToLong(Long::longValue)
                .min()
                .orElse(Long.MAX_VALUE);
    }

    private long findMaxIntegers(List<Long> integers) {
        log.info("Максимальное значение целосисленных элементов");
        return integers.stream()
                .mapToLong(Long::longValue)
                .max()
                .orElse(Long.MIN_VALUE);
    }
}

package service.doubles;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import model.output.OutputFloats;
import writer.Writer;

import java.util.List;

@Data
@Slf4j
public class ServiceDoubles {
    private final OutputFloats doubles;
    private final Writer writer = new Writer();

    public ServiceDoubles(OutputFloats doubles) {
        this.doubles = doubles;
    }

    public void fullDoublesStatistic() {
        if (doubles.getDoubles().isEmpty()) {
            log.info("Вещественных элементов нет");
            System.out.println("Вещественных жлементов нет");
        } else {
            log.info("Печать полной статистики вещественных элементов");
            double min = findMinDoubles(doubles.getDoubles());
            double max = findMaxDoubles(doubles.getDoubles());
            double sum = findSumDoubles(doubles.getDoubles());
            double avg = findAvgDoubles(doubles.getDoubles(), sum);
            System.out.println("Количество вещественных элементов = " + doubles.getDoubles().size());
            System.out.println("Минимальное значение = " + min);
            System.out.println("Максиммальное значение = " + max);
            System.out.println("Сумма = " + sum);
            System.out.println("Среднее значение = " + avg);
        }
    }

    private double findAvgDoubles(List<Double> doubles, double sum) {
        try {
            log.info("Среднее арифметическое значение вещественных элементов");
            return sum / doubles.size();
        } catch (ArithmeticException e) {
            log.debug("Арифметическая ошибка при поиске среднего значение вещественных элементов");
            throw new ArithmeticException(e.getMessage());
        } catch (NullPointerException e) {
            log.debug("Передан(ы) объекты null");
            throw new NullPointerException(e.getMessage());
        }
    }

    private double findSumDoubles(List<Double> doubles) {
        double sum = 0;
        for (Double aDouble : doubles) {
            sum +=aDouble;
        }
        log.info("Сумма всех вещественных элементов");
        return sum;
    }

    private double findMaxDoubles(List<Double> doubles) {
        log.info("Максимальное значение вещественных элементов");
        return doubles.stream()
                .mapToDouble(Double::doubleValue)
                .max()
                .orElse(Double.MIN_VALUE);
    }

    private double findMinDoubles(List<Double> doubles) {
        log.info("Минимальное значение вещественных элементов");
        return doubles.stream()
                .mapToDouble(Double::doubleValue)
                .min()
                .orElse(Double.MAX_VALUE);
    }
}

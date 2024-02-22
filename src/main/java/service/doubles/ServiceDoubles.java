package service.doubles;

import lombok.Data;
import model.output.OutputFloats;
import writer.Writer;

import java.util.List;

@Data
public class ServiceDoubles {
    private OutputFloats doubles;
    private final Writer writer = new Writer();

    public ServiceDoubles(OutputFloats doubles) {
        this.doubles = doubles;
    }

    public void writeFloatsToFile(String directoryPath, String prefix, boolean isRecoding) {
        if (!doubles.getDoubles().isEmpty()) {
            writer.toWriteOfDoubles(doubles.getDoubles(), directoryPath, prefix, isRecoding);
        }
    }

    public void fullDoublesStatistic() {
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

    private double findAvgDoubles(List<Double> doubles, double sum) {
        return sum / doubles.size();
    }

    private double findSumDoubles(List<Double> doubles) {
        double sum = 0;
        for (Double aDouble : doubles) {
            sum +=aDouble;
        }
        return sum;
    }

    private double findMaxDoubles(List<Double> doubles) {
        return doubles.stream()
                .mapToDouble(Double::doubleValue)
                .max()
                .orElse(Double.MIN_VALUE);
    }

    private double findMinDoubles(List<Double> doubles) {
        return doubles.stream()
                .mapToDouble(Double::doubleValue)
                .min()
                .orElse(Double.MAX_VALUE);
    }
}

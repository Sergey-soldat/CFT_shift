package model.output;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OutputFloats {
    private List<Double> doubles = new ArrayList<>();
}

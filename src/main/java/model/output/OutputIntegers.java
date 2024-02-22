package model.output;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OutputIntegers {
    private List<Long> integers = new ArrayList<>();
}

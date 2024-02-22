package parser;

import model.input.InputFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Parser {

    private List<String> readFileToList(String fileName) {
        try {
            return Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public InputFile getInputFile(String fileMame) {
        List<String> list = readFileToList(fileMame);
        return new InputFile(list);
    }
}

package parser;

import lombok.extern.slf4j.Slf4j;
import model.input.InputFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Slf4j
public class Parser {

    private List<String> readFileToList(String fileName) {
        try {
            log.info("Сохренения в строки файла" + fileName);
            return Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        log.info("Что-то не так с именем файла");
        return null;
    }

    public InputFile getInputFile(String fileMame) {
        List<String> list = readFileToList(fileMame);
        log.info("Чтение файла" + fileMame);
        return new InputFile(list);
    }
}

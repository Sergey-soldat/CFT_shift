package writer.strings;

import lombok.extern.slf4j.Slf4j;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Slf4j
public class StringsWriter {

    public String fileNameWithPrefix (String prefix) {
        String FILENAME = "Strings.txt";
        log.info("Соединили префикс и имя файла");
        return prefix + FILENAME;
    }

    public void toWriteOfIntegers(List<String> strings, FileWriter fileWriter) {
        log.info("Запсь целочисленных элементов в файл");
        try (FileWriter fileWriter1 = fileWriter) {
            for (String s : strings) {
                assert fileWriter1 != null;
                fileWriter1.write(s);
                fileWriter1.write("\n");
            }
            assert fileWriter1 != null;
            fileWriter1.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Что-то не так с путём");
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }
}

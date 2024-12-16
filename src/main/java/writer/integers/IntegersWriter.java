package writer.integers;

import lombok.extern.slf4j.Slf4j;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Slf4j
public class IntegersWriter {

    public String fileNameWithPrefix (String prefix) {
        String FILENAME = "integers.txt";
        log.info("Соединили префикс и имя файла");
        return prefix + FILENAME;
    }

    public void toWriteOfIntegers(List<Long> integers, FileWriter fileWriter) {
        log.info("Запсь целочисленных элементов в файл");
        try(FileWriter fileWriter1 = fileWriter) {
            for (Long i : integers){
                assert fileWriter1 != null;
                fileWriter1.write(String.valueOf(i));
                fileWriter1.write("\n");
            }
            assert fileWriter1 != null;
            fileWriter1.flush();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
            System.out.println("Что-то не так с путём");
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }
}

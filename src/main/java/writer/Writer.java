package writer;

//import com.sun.tools.classfile.ConstantPool;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import writer.doubles.DoublesWriter;
import writer.integers.IntegersWriter;
import writer.strings.StringsWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Slf4j
@Data
public class Writer {
    private File outputFileInt;
    private File outputFileDoubles;
    private File outputFileStrings;
    private FileWriter fileWriterInt;
    private FileWriter fileWriterDoubles;
    private FileWriter fileWriterStrings;
    private final IntegersWriter integersWriter = new IntegersWriter();
    private final DoublesWriter doublesWriter = new DoublesWriter();
    private final StringsWriter stringsWriter = new StringsWriter();

    public void toWrite(List<Long> integers, List<Double> doubles, List<String> strings,
                        String directoryPath, String prefix, boolean isRecording) {
        if (!integers.isEmpty()) {
            String fileNameInt = integersWriter.fileNameWithPrefix(prefix);
            outputFileInt = createDirectoryPath(directoryPath, fileNameInt);
            fileWriterInt = isRecoding(outputFileInt, isRecording);
            integersWriter.toWriteOfIntegers(integers, fileWriterInt);
        }
        if (!doubles.isEmpty()) {
            String fileNameDoubles = doublesWriter.fileNameWithPrefix(prefix);
            outputFileDoubles = createDirectoryPath(directoryPath, fileNameDoubles);
            fileWriterDoubles = isRecoding(outputFileDoubles, isRecording);
            doublesWriter.toWriteOfIntegers(doubles, fileWriterDoubles);
        }
        if (!strings.isEmpty()) {
            String fileNameStrings = stringsWriter.fileNameWithPrefix(prefix);
            outputFileStrings = createDirectoryPath(directoryPath, fileNameStrings);
            fileWriterStrings = isRecoding(outputFileStrings, isRecording);
            stringsWriter.toWriteOfIntegers(strings, fileWriterStrings);
        }
    }

    private FileWriter isRecoding (File outputFile, boolean isRecoding) {
        if (isRecoding) {
            try{
                log.info("Включена перезапись в файл");
                return new FileWriter(outputFile, true);
            } catch(IOException ex){
                ex.printStackTrace();
                log.debug("Произошла ошибка");
                throw new RuntimeException(ex);
            }
        } else {
            try{
                log.info("Перезапись в файл выключена");
                return new FileWriter(outputFile, false);
            } catch (IOException e) {
                log.debug("Произошла ошибка");
                throw new RuntimeException(e);
            }
        }
    }

    public File createDirectoryPath(String directoryParh, String fileName) {
        if (!directoryParh.isEmpty()) {
            File allDirectoryes = new File(directoryParh);
            if (allDirectoryes.mkdirs()) {
                log.info("Создана новая директория");
                return new File(allDirectoryes, fileName);
            } else {
                log.info("Создана новая директория");
                return new File(allDirectoryes, fileName);
            }
        } else {
            log.info("Используем директорию по умолчанию");
            return new File(fileName);
        }
    }
}

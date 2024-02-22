import org.apache.commons.cli.*;
import service.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class CommandLineArguments {
    public static void main(String[] args) throws ParseException {
        Service service = new Service();
        Options options = new Options();
        String DirectoryPath = "";
        String filePrefix = "";
        boolean isRecording = false;
        boolean shotStatistic = false;
        boolean fullStatistic = false;

        options.addOption("o", true, "Указать путь для результатов");
        options.addOption("p", true,"Указать префикс имен выходных файлов");
        options.addOption("a", false, "Включить добавления в файл и выключить перезапись");
        options.addOption("s", false, "Показать краткую статистику");
        options.addOption("f", false, "Показать полную статистику");

        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);
            if (cmd.hasOption("o")) {
                DirectoryPath = cmd.getOptionValue("o");
            }
            if (cmd.hasOption("p")) {
                filePrefix = cmd.getOptionValue("p");
            }
            if (cmd.hasOption("a")) {
                isRecording = true;
            }
            if (cmd.hasOption("s")) {
                shotStatistic = true;
            }
            if (cmd.hasOption("f")) {
                fullStatistic = true;
            }
            List<String> files = cmd.getArgList();
            for (String file : files) {
                processFile(service, file);
            }
            processDefault(service, DirectoryPath, filePrefix, isRecording, shotStatistic, fullStatistic);
        } catch (ParseException e) {
            System.err.println("Ошибка при разборе параметров командной строки: " + e.getMessage());
            String exp = "Ошибка при разборе параметров командной строки: " + e.getMessage();
            throw new ParseException(exp);
        }
    }

    private static void processFile(Service service, String filePath) {
        System.out.println("Чтение файла: " + filePath);
        service.filter2(filePath);
    }

    private static void processDefault(Service service, String directoryPath, String prefix,
                                       boolean isRecording, boolean shotStat, boolean fullStat) {
        service.processService2(directoryPath, prefix, isRecording, shotStat, fullStat);
    }
}

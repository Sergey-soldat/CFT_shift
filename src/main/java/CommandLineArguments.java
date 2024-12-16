import lombok.extern.slf4j.Slf4j;
import org.apache.commons.cli.*;
import parser.ExtendedParserDefault;
import service.Service;

import java.io.File;
import java.util.List;
import java.util.Map;

@Slf4j
public class CommandLineArguments {
    public static void main(String[] args) throws ParseException {
        Service service = new Service();
        Options options = new Options();
        String DirectoryPath = "";
        String filePrefix = "";
        boolean isRecording = false;
        boolean shotStatistic = false;
        boolean fullStatistic = false;
        boolean validatetOption = false;

        options.addOption("o", true, "Указать путь для результатов");
        options.addOption("p", true,"Указать префикс имен выходных файлов");
        options.addOption("a", false, "Включить добавления в файл и выключить перезапись");
        options.addOption("s", false, "Показать краткую статистику");
        options.addOption("f", false, "Показать полную статистику");
        options.addOption("v", false, "Проверка опций командной строки");

        ExtendedParserDefault parser = new ExtendedParserDefault();
        try {
            CommandLine cmd = parser.parse(options, args, validatetOption);
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
            if (cmd.hasOption('v')) {
                validatetOption = true;
            }
            List<String> files = cmd.getArgList();
            validateFiles(files, service);
            processDefault(service, DirectoryPath, filePrefix, isRecording, shotStatistic, fullStatistic);
            printNotParsedPotions(parser);
        }
        catch (ParseException e) {
            System.err.println("Ошибка при разборе параметров командной строки: " + e.getMessage());
            String exp = "Ошибка при разборе параметров командной строки: " + e.getMessage();
            printHelp();
            log.error("Ошибка при разборе параметров командной строки" + e.getMessage());
            throw new ParseException(exp);
        }
    }

    private static void processFile(Service service, String filePath) {
        log.info("Чтение файла" + filePath);
        service.filter(filePath);
    }

    private static void processDefault(Service service, String directoryPath, String prefix,
                                       boolean isRecording, boolean shotStat, boolean fullStat) {
        service.processService(directoryPath, prefix, isRecording, shotStat, fullStat);
    }

    private static boolean isTxtFile(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            log.debug("Имя файла неинициализировано или пусто" );
            return false;
        }
        log.info("Проверка, является файл txt");
        return fileName.toLowerCase().endsWith(".txt");
    }

    private static void validateFiles(List<String> files, Service service) {
        if (!files.isEmpty()) {
            int counter = 0;
            for (String file : files) {
                File currentFile = new File(file);
                if (!currentFile.exists()) {
                    System.out.println("Файла " + file + " не найдено. ");
                    log.info("файла" + file + " не найдено.");
                    counter++;
                    continue;
                }
                if (isTxtFile(file)) {
                    log.info("Обработка файлов");
                    processFile(service, file);
                } else {
                    counter ++;
                    System.out.println("Файл " + file + " не является формата .txt");
                    if (counter == files.size()) {
                        System.out.println("Нет файлов для обработки");
                        log.info("Нет файлов для обработки");
                        return;
                    }
                }
            }
        } else {
            System.out.println("Нет файлов для обработки.");
            log.info("Нет файлов для обрработки");
            return;
        }
    }

    private static void printHelp(){
        System.out.println("Допустимые опции:");
        System.out.println("o - указать путь для результатов");
        System.out.println("p - указать префикс имен выходных файлов");
        System.out.println("a - включить добавления в файл и выключить перезапись");
        System.out.println("s - показать краткую статистику");
        System.out.println("f - показать полную статистику");
        System.out.println("Пример ввода:");
        System.out.println("java -jar CFT_shift-1.0-SNAPSHOT-jar-with-dependencies.jar" +
                " -s -f -p sample_ -o some/path in1.txt in2.txt");
    }

    private static void printNotParsedPotions (ExtendedParserDefault parser) {
        if (! parser.getNotParsedArgs().isEmpty()){
            log.error("Ввод недобавленной опции");
            parser.getNotParsedArgs().forEach(s -> System.out.println("Введена недобавленная опция " + s));
        }
    }
}

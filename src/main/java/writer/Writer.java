package writer;

import service.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Writer {

    public void toWriteOfIntegers(List<Long> integers, String directoryPath, String prefix, boolean isRecoding) {
        String fileName = prefix + "integers.txt";
        File outputFile = createDirectoryPath(directoryPath, fileName);
        try(FileWriter writer = isRecoding(outputFile, isRecoding)) {
            for (Long i : integers){
                assert writer != null;
                writer.write(String.valueOf(i));
                writer.write("\n");
            }
            assert writer != null;
            writer.flush();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
            System.out.println("Что-то не так с путём");
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    public void toWriteOfDoubles(List<Double> doubles, String directoryPath, String prefix, boolean isRecoding) {
        String fileName = prefix + "floats.txt";
        File outputFile = createDirectoryPath(directoryPath, fileName);
        try(FileWriter writer = isRecoding(outputFile, isRecoding)) {
            for (Double d : doubles){
                assert writer != null;
                writer.write(String.valueOf(d));
                writer.write("\n");
            }
            assert writer != null;
            writer.flush();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
            System.out.println("Что-то не так с путём");
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    public void toWriteOfStrings(List<String> list, String directoryPath, String prefix, boolean isRecoding) {
        String fileName = prefix + "strings.txt";
        File outputFile = createDirectoryPath(directoryPath, fileName);
        try(FileWriter writer = isRecoding(outputFile, isRecoding)) {
            for (String s : list){
                assert writer != null;
                writer.write(s);
                writer.write("\n");
            }
            assert writer != null;
            writer.flush();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
            System.out.println("Что-то не так с путём");
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    private FileWriter isRecoding (File outputFile, boolean isRecoding) {
        if (isRecoding) {
            try{
                return new FileWriter(outputFile, true);
            } catch(IOException ex){
                ex.printStackTrace();
                throw new RuntimeException(ex);
            }
        } else {
            try{
                return new FileWriter(outputFile, false);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private File createDirectoryPath(String directoryParh, String fileName) {
        if (!directoryParh.isEmpty()) {
            File allDirectoryes = new File(directoryParh);
            if (allDirectoryes.mkdirs()) {
                return new File(allDirectoryes, fileName);
            } else {
                return new File(allDirectoryes, fileName);
            }
        } else {
            return new File(fileName);
        }
    }
}

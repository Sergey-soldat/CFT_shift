package parser;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import java.util.ArrayList;
import java.util.List;

@Data
@Slf4j
public class ExtendedParserDefault extends DefaultParser {
    private final List<String> notParsedArgs = new ArrayList<>();

    @Override
    public CommandLine parse(Options options, String[] arguments, boolean stopAtNonOption) throws ParseException {
        if(stopAtNonOption) {
            log.info("Включена строгая валидация опций");
            return parse(options, arguments);
        }
        List<String> knownArguments = new ArrayList<>();
        notParsedArgs.clear();
        boolean nextArgument = false;
        for (String arg : arguments) {
            if (options.hasOption(arg) || nextArgument || !arg.startsWith("-")) {
                knownArguments.add(arg);
            } else {
                notParsedArgs.add(arg);
            }
            nextArgument = options.hasOption(arg) && options.getOption(arg).hasArg();
        }
        log.info("Обрабатываем известные опции и показываем недобавленную опцию");
        return super.parse(options, knownArguments.toArray(new String[knownArguments.size()]));
    }
}

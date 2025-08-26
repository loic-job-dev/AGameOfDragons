package fr.campus.loic.agameofdragons.tools;

import java.util.logging.*;

/**
 * This class is the logger created to display the messages. It can be called with the console version or the graphic version
 */
public class GameLogger {

    public static final Logger LOGGER = Logger.getLogger("AGameOfDragons");

    static {
        //delete all the existing handlers
        LOGGER.setUseParentHandlers(false);

        //creation of a console handler
        ConsoleHandler handler = new ConsoleHandler();
        handler.setLevel(Level.ALL); // To display all the levels

        handler.setFormatter(new Formatter() {
            @Override
            public String format(LogRecord record) {
                return record.getMessage() + "\n";
            }
        });

        LOGGER.addHandler(handler);
        LOGGER.setLevel(Level.ALL);
    }
}

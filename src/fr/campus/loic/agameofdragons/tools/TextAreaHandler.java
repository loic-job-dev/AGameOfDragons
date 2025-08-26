package fr.campus.loic.agameofdragons.tools;

import javafx.application.Platform;
import javafx.scene.control.TextArea;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * This class is the handler for the graphic version
 */
public class TextAreaHandler extends Handler {

    private final TextArea textArea;

    public TextAreaHandler(TextArea textArea) {
        this.textArea = textArea;
    }

    @Override
    public void publish(LogRecord record) {
        if (record == null) return;
        Platform.runLater(() -> {
            // Delete the ANSI codes
            String msg = record.getMessage().replaceAll("\u001B\\[[;\\d]*m", "");
            textArea.appendText(msg + "\n");
        });
    }

    @Override
    public void flush() { }

    @Override
    public void close() throws SecurityException { }
}
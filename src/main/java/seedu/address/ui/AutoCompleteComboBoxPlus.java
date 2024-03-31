package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.ui.CommandBox.CommandExecutor;

/**
 * Represents a utility class to enable auto-complete for ComboBox.
 */
public class AutoCompleteComboBoxPlus {
    private static final Logger logger = LogsCenter.getLogger(AutoCompleteComboBoxPlus.class);

    /**
     * Represents a interface that can compare the typed text with the object to compare.
     */
    public interface AutoCompleteComparator<T> {
        boolean matches(String typedText, T objectToCompare);
    }

    /**
     * Configures the ComboBox to enable auto-complete.
     */
    public static<T> void config(ComboBox<T> comboBox, AutoCompleteComparator<T> comparatorMethod,
            CommandExecutor commandExecutor) {
        ObservableList<T> commandHistory = comboBox.getItems();

        comboBox.getEditor().focusedProperty().addListener(observable -> {
            if (comboBox.getSelectionModel().getSelectedIndex() < 0) {
                comboBox.getEditor().setText("");
            }
        });
        comboBox.addEventHandler(KeyEvent.KEY_PRESSED, t -> comboBox.hide());
        comboBox.addEventHandler(KeyEvent.KEY_RELEASED,
                                 createHandler(comboBox, comparatorMethod, commandHistory, commandExecutor));
    }

    /**
     * Creates a EventHandler for the ComboBox.
     */
    private static <T> EventHandler<KeyEvent> createHandler(ComboBox<T> comboBox,
            AutoCompleteComparator<T> comparatorMethod, ObservableList<T> commandHistory,
            CommandExecutor commandExecutor) {
        return new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.UP) {
                    processArrowKeys(event, comboBox);
                    return;
                } else if (event.getCode() == KeyCode.DOWN) {
                    processArrowKeys(event, comboBox);
                    return;
                } else if (event.getCode() == KeyCode.BACK_SPACE) {
                    processBackspace(comboBox);
                } else if (event.getCode() == KeyCode.DELETE) {
                    processDelete(comboBox);
                } else if (event.getCode() == KeyCode.ENTER) {
                    processEnter(comboBox, commandHistory, commandExecutor);
                    return;
                }

                comboBox.hide(); // reset the filter size

                // Override the default behaviour of the ComboBox
                if (event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.LEFT
                        || event.getCode().equals(KeyCode.SHIFT)
                        || event.getCode().equals(KeyCode.CONTROL)
                        || event.isControlDown() || event.getCode() == KeyCode.HOME
                        || event.getCode() == KeyCode.END || event.getCode() == KeyCode.TAB) {
                    return;
                }

                processTextInput(comboBox, commandHistory, comparatorMethod);
            }
        };
    }

    /**
     * Returns the value of the ComboBox.
     */
    public static<T> T getComboBoxValue(ComboBox<T> comboBox) {
        if (comboBox.getSelectionModel().getSelectedIndex() < 0) {
            return null;
        } else {
            return comboBox.getItems().get(comboBox.getSelectionModel().getSelectedIndex());
        }
    }

    /**
     * Moves the caret to the specified position.
     */
    private static <T> void moveCaret(ComboBox<T> comboBox, int caretPos) {
        comboBox.getEditor().positionCaret(caretPos);
    }

    private static <T> void processArrowKeys(KeyEvent event, ComboBox<T> comboBox) {
        logger.info("Arrow key pressed");
        if (event.getCode() == KeyCode.UP) {
            if (comboBox.getEditor().getText() != null) {
                moveCaret(comboBox, comboBox.getEditor().getText().length());
            }
            return;
        } else if (event.getCode() == KeyCode.DOWN) {
            if (!comboBox.isShowing()) {
                comboBox.show();
            }
            if (comboBox.getEditor().getText() != null) {
                moveCaret(comboBox, comboBox.getEditor().getText().length());
            }
            return;
        }
    }

    private static <T> int processBackspace(ComboBox<T> comboBox) {
        logger.info("Backspace key pressed");
        int caretPos = -1;
        if (comboBox.getEditor().getText() != null) {
            caretPos = comboBox.getEditor().getCaretPosition();
        }
        return caretPos;
    }

    private static <T> int processDelete(ComboBox<T> comboBox) {
        logger.info("Delete key pressed");
        int caretPos = -1;
        if (comboBox.getEditor().getText() != null) {
            caretPos = comboBox.getEditor().getCaretPosition();
        }
        return caretPos;
    }

    @SuppressWarnings("unchecked")
    private static <T> void processEnter(ComboBox<T> comboBox, ObservableList<T> commandHistory,
            CommandExecutor commandExecutor) {
        String commandText = comboBox.getEditor().getText();
        if (commandText.equals("")) {
            return;
        }
        try {
            commandExecutor.execute(commandText);
            comboBox.getEditor().setText("");
            commandText = commandText.trim();
            if (commandHistory.contains(commandText)) {
                commandHistory.remove(commandText);
            }
            commandHistory.add(0, (T) commandText);
            comboBox.setItems(commandHistory);
            comboBox.hide();
            return;
        } catch (CommandException | ParseException e) {
            setStyleToIndicateCommandFailure(comboBox);
            return;
        }
    }

    private static <T> void processTextInput(ComboBox<T> comboBox, ObservableList<T> commandHistory,
            AutoCompleteComparator<T> comparatorMethod) {
        setStyleToDefault(comboBox);
        ObservableList<T> list = FXCollections.observableArrayList();
        for (T aData : commandHistory) {
            if (aData != null && comboBox.getEditor().getText() != null
                && comparatorMethod.matches(comboBox.getEditor().getText(), aData)) {
                list.add(aData);
            }
        }
        String t = "";
        if (comboBox.getEditor().getText() != null) {
            t = comboBox.getEditor().getText();
        }

        comboBox.setItems(list);
        comboBox.getEditor().setText(t);
        moveCaret(comboBox, t.length());
        if (!list.isEmpty()) {
            comboBox.show();
        }
    }

    /**
     * Sets the command box style to indicate a failed command.
     */
    public static<T> void setStyleToIndicateCommandFailure(ComboBox<T> comboBox) {
        comboBox.setStyle("-fx-border-color: red;");
        comboBox.getEditor().setStyle("-fx-text-fill: red; -fx-background-color: #FFD2D2;");
    }

    /**
     * Sets the command box style to use the default style.
     */
    public static<T> void setStyleToDefault(ComboBox<T> comboBox) {
        comboBox.setStyle(null);
        comboBox.getEditor().setStyle(null);
    }
}

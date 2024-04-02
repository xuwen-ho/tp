package seedu.address.ui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Region;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * The UI component that is responsible for receiving user command inputs.
 */
public class CommandBox extends UiPart<Region> {

    public static final String ERROR_STYLE_CLASS = "error";
    private static final String FXML = "CommandBox.fxml";

    private final CommandExecutor commandExecutor;

    @FXML
    private ComboBox<String> commandTextField;
    /**
     * Creates a {@code CommandBox} with the given {@code CommandExecutor}.
     */
    public CommandBox(CommandExecutor commandExecutor) {
        super(FXML);
        this.commandExecutor = commandExecutor;

        commandTextField.setEditable(true);
        commandTextField.setVisibleRowCount(8);
        commandTextField.setItems(
            FXCollections.observableArrayList("add", "addavail", "assign", "clear", "copy", "delete", "edit", "exit",
                    "find", "help", "list", "lista", "removeavail", "removeassign"));
        // Set the command box to be auto-completable
        AutoCompleteComboBoxPlus.config(commandTextField, (typedText, itemToCompare) ->
                itemToCompare.toLowerCase().contains(typedText.toLowerCase())
                || itemToCompare.toString().equals(typedText), commandExecutor);
    }

    /**
     * Represents a function that can execute commands.
     */
    @FunctionalInterface
    public interface CommandExecutor {
        /**
         * Executes the command and returns the result.
         *
         * @see seedu.address.logic.Logic#execute(String, boolean)
         */
        CommandResult execute(String commandText) throws CommandException, ParseException;
    }
}

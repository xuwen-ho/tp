package seedu.address.ui;

import java.util.List;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.skin.ComboBoxListViewSkin;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import javafx.util.Callback;
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
        commandTextField.setItems(FXCollections.observableArrayList("add", "clear", "delete", "edit", "exit", "find", "help", "list"));
        // Set the command box to be auto-completable
        AutoCompleteComboBoxPlus.autoCompleteComboBoxPlus(commandTextField, (typedText, itemToCompare) -> itemToCompare.toLowerCase().contains(typedText.toLowerCase()) || itemToCompare.toString().equals(typedText), commandExecutor);
    
        // calls #setStyleToDefault() whenever there is a change to the text of the command box.
        // commandTextField.valueProperty().addListener((unused1, unused2, unused3) -> setStyleToDefault());
        // commandTextField.addEventHandler(KeyEvent.KEY_PRESSED, event -> handleUpDownKeyPressed(event));
    }

    /**
     * Handles the Enter button pressed event.
     */
    @FXML
    private void handleCommandEntered() {
        String commandText = commandTextField.getValue();
        if (commandText.equals("")) {
            return;
        }

        try {
            commandExecutor.execute(commandText);
            commandTextField.setValue("");
        } catch (CommandException | ParseException e) {
            setStyleToIndicateCommandFailure();
        }
    }

    /**
     * Sets the command box style to use the default style.
     */
    private void setStyleToDefault() {
        commandTextField.getStyleClass().remove(ERROR_STYLE_CLASS);
    }

    /**
     * Sets the command box style to indicate a failed command.
     */
    public void setStyleToIndicateCommandFailure() {
        ObservableList<String> styleClass = commandTextField.getStyleClass();

        if (styleClass.contains(ERROR_STYLE_CLASS)) {
            return;
        }

        styleClass.add(ERROR_STYLE_CLASS);
    }

    /**
     * Handles the up and down key pressed event.
     */
    // private void handleUpDownKeyPressed(KeyEvent event) {
    //     if (event.getCode().equals(KeyCode.UP)) {
    //         commandTextField.setValue("up key pressed");
    //     } else if (event.getCode().equals(KeyCode.DOWN)) {
    //         commandTextField.setValue("down key pressed");
    //     }
    // }

    @FXML
    private void handleSelection() {
        commandTextField.getSelectionModel().select(0);
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

// class AutoShowComboBoxHelper {
//     public AutoShowComboBoxHelper(final ComboBox<String> comboBox, final Callback<String, String> textBuilder) {
//         ComboBoxListViewSkin<String> comboBoxListViewSkin = new ComboBoxListViewSkin<>(comboBox);
//         comboBoxListViewSkin.getPopupContent().addEventFilter(KeyEvent.ANY, (event) -> {
//             if( event.getCode() == KeyCode.SPACE ) {
//                 event.consume();
//             }
//         });
//         comboBox.setSkin(comboBoxListViewSkin);

//         final ObservableList<String> items = FXCollections.observableArrayList(comboBox.getItems());

//         comboBox.getEditor().textProperty().addListener((ov, o, n) -> {
//             if (n.equals(comboBox.getSelectionModel().getSelectedItem())) {
//                 return;
//             }
            
//             Platform.runLater(() -> comboBox.hide());
//             final FilteredList<String> filtered = items.filtered(s -> textBuilder.call(s).toLowerCase().contains(n.toLowerCase()));
//             if (filtered.isEmpty()) {
//                 Platform.runLater(() -> comboBox.getItems().setAll(items));
//             } else {
//                 Platform.runLater(() -> {
//                     comboBox.getItems().setAll(filtered);
//                     comboBox.show();
//                 });
//             }
//         });
//     }
// }

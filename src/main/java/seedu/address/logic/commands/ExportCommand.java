package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.storage.JsonSerializableAddressBook.ASSIGNMENTS_PROPERTY;
import static seedu.address.storage.JsonSerializableAddressBook.PERSONS_PROPERTY;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONObject;
import seedu.address.commons.util.FileUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Export all the information of people and assignments in the entire address book as comma-separated values files.
 * The files are located in <location of jar file>\data directory.
 */
public class ExportCommand extends Command {

    public static final String COMMAND_WORD = "export";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Export the information of all the people and activities in the application to a CSV file.\n";

    public static final String MESSAGE_COPY_SUCCESS = "Data successfully exported.";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Path addressBookFilePath = model.getAddressBookFilePath();

        try {
            exportCsv(addressBookFilePath);
        } catch (IOException | AssertionError e) {
            throw new CommandException("Unable to locate data directory.");
        }
        return new CommandResult(String.format(MESSAGE_COPY_SUCCESS));
    }

    /**
     * Exports persons and assignment information into 2 separate comma-separated values file by taking in the
     * path to the address book.
     *
     * @param addressBookFilePath A path to the address book file.
     */
    public void exportCsv(Path addressBookFilePath) throws IOException {
        assert addressBookFilePath != null;

        String jsonString = new String(Files.readAllBytes(addressBookFilePath));
        JSONObject jsonObject = new JSONObject(jsonString);

        Path addressBookDirectoryPath = addressBookFilePath.getParent();

        JSONArray jsonArrayPersons = jsonObject.getJSONArray(JSON_KEY_PERSONS);
        JSONArray jsonArrayAssignments = jsonObject.getJSONArray(JSON_KEY_ASSIGNMENTS);

        Path csvFilePathPersons = Path.of(addressBookDirectoryPath + "/" + PERSONS_PROPERTY + ".csv");
        Path csvFilePathAssignments = Path.of(addressBookDirectoryPath + "/" + ASSIGNMENTS_PROPERTY + ".csv");

        String csvStringPersons = CDL.toString(jsonArrayPersons);
        String csvStringAssignments = CDL.toString(jsonArrayAssignments);

        if (csvStringPersons == null) {
            csvStringPersons = "";
        }
        if (csvStringAssignments == null) {
            csvStringAssignments = "";
        }

        FileUtil.writeToFile(csvFilePathPersons, csvStringPersons);
        FileUtil.writeToFile(csvFilePathAssignments, csvStringAssignments);
    }
}

package seedu.address.model.assignment.exceptions;

/**
 * Signals that the operation would result in a duplicate assignment
 */
public class DuplicateAssignmentException extends RuntimeException {
    public DuplicateAssignmentException() {
        super("Operation would result in duplicate assignments");
    }
}

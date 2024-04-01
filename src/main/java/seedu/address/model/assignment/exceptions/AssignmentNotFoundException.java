package seedu.address.model.assignment.exceptions;

/**
 * Signals that the operation is not able to find the specified assignment.
 */
public class AssignmentNotFoundException extends RuntimeException {
    public AssignmentNotFoundException() {
        super("Assignment not found, cannot delete assignments not found in list");
    }
}

package seedu.address.model.assignment.exceptions;

public class AssignmentNotFoundException extends RuntimeException{
    public AssignmentNotFoundException() {
        super("Assignment not found, cannot delete assignments not found in list");
    }
}

package seedu.address.model.assignment;

/**
 * Represents the details of the assignment
 */
public class AssignmentDetails {
    private String details;


    public AssignmentDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return details;
    }
}

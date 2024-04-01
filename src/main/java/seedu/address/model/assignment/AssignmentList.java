package seedu.address.model.assignment;

import static java.util.Objects.requireNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.assignment.exceptions.AssignmentNotFoundException;
import seedu.address.model.assignment.exceptions.DuplicateAssignmentException;

/**
 * A list of assignments that does not allow nulls.
 */
public class AssignmentList implements Iterable<Assignment> {

    private final ObservableList<Assignment> internalList = FXCollections.observableArrayList();
    private final ObservableList<Assignment> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Adds a person to the list.
     * The person must not already exist in the list.
     */
    public void add(Assignment toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateAssignmentException();
        }
        internalList.add(toAdd);
    }

    /**
     * Checks if assignment is in the list
     */
    public boolean contains(Assignment toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isAlreadyAssigned);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Assignment> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }


    /**
     * Sets the given list of assignments to the current list
     */
    public void setAssignments(List<Assignment> assignments) {
        requireNonNull(assignments);
        internalList.setAll((assignments));
    }

    /**
     * Sets the given list of assignments to the current list
     */
    public void setAssignments(AssignmentList assignments) {
        requireNonNull(assignments);
        internalList.setAll((assignments.internalList));
    }

    /**
     * Removes the given assignment from the current list.
     * {@code toRemove} must exist inthe list.
     */
    public void remove(Assignment toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new AssignmentNotFoundException();
        }
    }

    @Override
    public Iterator<Assignment> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AssignmentList)) {
            return false;
        }

        AssignmentList otherAssignmentList = (AssignmentList) other;
        return internalList.equals(otherAssignmentList.internalList);
    }
}

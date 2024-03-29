package seedu.address.model.assignment;

import static java.util.Objects.requireNonNull;

import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
        internalList.add(toAdd);
    }

    /**
     * Checks if assignment is in the list
     */
    public boolean contains(Assignment toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
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

    @Override
    public Iterator<Assignment> iterator() {
        return null;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return false;
        }

        // instanceof handles nulls
        if (!(other instanceof AssignmentList)) {
            return false;
        }

        AssignmentList otherAssignmentList = (AssignmentList) other;
        return internalList.equals(otherAssignmentList.internalList);
    }
}

package seedu.address.model.assignment;

import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import static java.util.Objects.requireNonNull;

public class UniqueAssignmentList implements Iterable<Assignment> {

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
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Assignment> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }


    @Override
    public Iterator<Assignment> iterator() {
        return internalList.iterator();
    }

    @Override
    public void forEach(Consumer<? super Assignment> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<Assignment> spliterator() {
        return Iterable.super.spliterator();
    }

    public void setAssignments(List<Assignment> assignments) {
        requireNonNull(assignments);
        internalList.setAll((assignments));
    }
}

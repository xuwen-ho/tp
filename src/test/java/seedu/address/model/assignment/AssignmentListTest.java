package seedu.address.model.assignment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAssignments.FOODDONATION;
import static seedu.address.testutil.TypicalAssignments.TUTORING;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import seedu.address.model.AddressBook;
import seedu.address.model.assignment.exceptions.AssignmentNotFoundException;
import seedu.address.model.assignment.exceptions.DuplicateAssignmentException;

public class AssignmentListTest {

    private final AssignmentList assignmentList = new AssignmentList();
//    private final AddressBook sampleAddressBook = getTypicalAddressBook();

    @Test
    public void contains_nullAssignment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> assignmentList.contains(null));
    }

    @Test
    public void contains_assignmentNotInList_returnsFalse() {
        assertFalse(assignmentList.contains(FOODDONATION));
    }

    @Test
    public void add_nullAssignment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> assignmentList.add(null));
    }

    @Test
    public void add_validAssignmentTest() {
        assignmentList.add(FOODDONATION);
        assertTrue(assignmentList.contains(FOODDONATION));
    }

    @Test
    public void add_duplicateAssignmentTest_throwsDuplicateAssignmentException() {
        assignmentList.add(FOODDONATION);
        assertThrows(DuplicateAssignmentException.class, () -> assignmentList.add(FOODDONATION));
    }

    @Test
    public void remove_existingAssignment_removesAssignment() {
        assignmentList.add(FOODDONATION);
        assignmentList.remove(FOODDONATION);
        AssignmentList expectedAssignmentList = new AssignmentList();
        assertEquals(expectedAssignmentList, assignmentList);
    }

    @Test
    public void remove_nullAssignment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> assignmentList.remove(null));
    }

    @Test
    public void remove_assignmentDoesNotExist_throwsAssignmentNotFoundException() {
        assertThrows(AssignmentNotFoundException.class, () -> assignmentList.remove(FOODDONATION));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> assignmentList.asUnmodifiableObservableList().add(FOODDONATION));
    }

    @Test
    public void setAssignments_nullAssignmentList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, ()
                -> assignmentList.setAssignments((AssignmentList) null));
    }

    @Test
    public void setAssignments_replacesOwnListWithProvidedAssignmentList() {
        assignmentList.add(FOODDONATION);
        AssignmentList expectedAssignmentList = new AssignmentList();
        expectedAssignmentList.add(TUTORING);
        assignmentList.setAssignments(expectedAssignmentList);
        assertEquals(expectedAssignmentList, assignmentList);
    }

    @Test
    public void setAssignments_replacesOwnListWithProvidedList() {
        assignmentList.add(FOODDONATION);
        List<Assignment> assignmentListTutoring = Collections.singletonList(TUTORING);
        assignmentList.setAssignments(assignmentListTutoring);
        AssignmentList expectedAssignmentList = new AssignmentList();
        expectedAssignmentList.add(TUTORING);
        assertEquals(expectedAssignmentList, assignmentList);
    }

    @Test
    public void equalsTest() {
        // same object -> true
        assertTrue(assignmentList.equals(assignmentList));
        // different type -> false
        assertFalse(assignmentList.equals(1));
    }
}

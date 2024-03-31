package seedu.address.model.assignment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAssignments.FOODDONATION;
import static seedu.address.testutil.TypicalAssignments.TUTORING;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

public class AssignmentListTest {

    private final AssignmentList assignmentList = new AssignmentList();

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
}

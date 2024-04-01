package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.assignment.AssignmentDetails;
import seedu.address.model.person.Availability;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                getAvailabilitySet("01/03/2024", "02/03/2024", "03/03/2024"),
                getTagSet("Food Bank", "Teaching")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                getAvailabilitySet("20/03/2024", "22/03/2024"),
                getTagSet("Elderly Care", "Teaching")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                getAvailabilitySet("01/03/2024"),
                getTagSet("Environment")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                getAvailabilitySet("28/02/2024"),
                getTagSet("Healthcare")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                getAvailabilitySet("05/04/2024"),
                getTagSet("Animal Welfare")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                getAvailabilitySet("03/05/2024"),
                getTagSet("Food Bank"))
        };
    }

    public static Assignment[] getSampleAssignments() {
        Person[] samplePersons = getSamplePersons();
        Person alex = samplePersons[0];
        Person bernice = samplePersons[1];
        Person charlotte = samplePersons[2];
        return new Assignment[] {
                new Assignment(alex, new AssignmentDetails("Willing Hearts"), new Availability("01/03/2024")),
                new Assignment(bernice, new AssignmentDetails("Tutoring"), new Availability("20/03/2024")),
                new Assignment(charlotte, new AssignmentDetails("Elderly Care"), new Availability("01/03/2024"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }

        for (Assignment sampleAssignment : getSampleAssignments()) {
            sampleAb.addAssignment(sampleAssignment);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns an availability set containing the list of strings given.
     */
    public static Set<Availability> getAvailabilitySet(String... strings) {
        return Arrays.stream(strings)
                .map(Availability::new)
                .collect(Collectors.toSet());
    }
}

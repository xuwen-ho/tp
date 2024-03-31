package seedu.address.model.person;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;


/**
 * Descriptor class for editing a person. Each non-null field in this descriptor represents a field to edit.
 */
public class EditPersonDescriptor {
    private Name name;
    private Phone phone;
    private Email email;
    private Set<Availability> availabilities;
    private Set<Tag> tags;

    public EditPersonDescriptor() {}

    /**
     * Copy constructor.
     * A defensive copy of {@code tags} is used internally.
     */
    public EditPersonDescriptor(EditPersonDescriptor toCopy) {
        setName(toCopy.name);
        setPhone(toCopy.phone);
        setEmail(toCopy.email);
        setAvailabilities(toCopy.availabilities);
        setTags(toCopy.tags);
    }

    /**
     * Returns true if at least one field is edited.
     */
    public boolean isAnyFieldEdited() {
        return CollectionUtil.isAnyNonNull(name, phone, email, availabilities, tags);
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Optional<Name> getName() {
        return Optional.ofNullable(name);
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public Optional<Phone> getPhone() {
        return Optional.ofNullable(phone);
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public Optional<Email> getEmail() {
        return Optional.ofNullable(email);
    }

    public void setAvailabilities(Set<Availability> availabilities) {
        this.availabilities = availabilities;
    }

    public Optional<Set<Availability>> getAvailabilities() {
        return (availabilities != null)
            ? Optional.of(Collections.unmodifiableSet(availabilities))
            : Optional.empty();
    }

    /**
     * Sets {@code tags} to this object's {@code tags}.
     * A defensive copy of {@code tags} is used internally.
     */
    public void setTags(Set<Tag> tags) {
        this.tags = (tags != null) ? new HashSet<>(tags) : null;
    }

    /**
     * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     * Returns {@code Optional#empty()} if {@code tags} is null.
     */
    public Optional<Set<Tag>> getTags() {
        return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditPersonDescriptor)) {
            return false;
        }

        EditPersonDescriptor otherEditPersonDescriptor = (EditPersonDescriptor) other;
        return Objects.equals(name, otherEditPersonDescriptor.name)
            && Objects.equals(phone, otherEditPersonDescriptor.phone)
            && Objects.equals(email, otherEditPersonDescriptor.email)
            && Objects.equals(availabilities, otherEditPersonDescriptor.availabilities)
            && Objects.equals(tags, otherEditPersonDescriptor.tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .add("name", name)
            .add("phone", phone)
            .add("email", email)
            .add("availabilities", availabilities)
            .add("tags", tags)
            .toString();
    }
}

package edu.noia.myoffice.person.domain.util.sanitizer;

import edu.noia.myoffice.common.util.sanitizer.Sanitizer;
import edu.noia.myoffice.person.domain.vo.Person;

import java.util.Optional;

public interface PersonSanitizer {

    Sanitizer<String> name = na ->
            Optional.ofNullable(na)
                    .map(n -> n.substring(0, 1).toUpperCase() + n.substring(1).toLowerCase());

    Sanitizer<Person> person = p -> Optional.of(Person.of(
            name.sanitize(p.getFirstName()).orElse(p.getFirstName()),
            name.sanitize(p.getLastName()).orElse(p.getLastName()),
            p.getGender(),
            p.getSalutation(),
            p.getBirthDate()));
}

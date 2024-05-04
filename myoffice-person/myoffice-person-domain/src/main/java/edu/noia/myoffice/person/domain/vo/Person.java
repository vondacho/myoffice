package edu.noia.myoffice.person.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Optional;

@Accessors(chain = true)
@Setter
@Getter
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class Person {

    String firstName;
    @NotNull
    String lastName;
    Gender gender;
    String salutation;
    LocalDate birthDate;

    public String getFullname() {
        return Optional.ofNullable(getFirstName())
                .map(fn -> getLastName() + " " + fn)
                .orElseGet(this::getLastName);
    }

}

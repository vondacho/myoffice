package edu.noia.myoffice.person.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
@Getter
@NoArgsConstructor
public class GenderDto {

    @JsonProperty(value = "last_name")
    String lastName;
    @JsonProperty(value = "first_name")
    String firstName;
    String gender;
}

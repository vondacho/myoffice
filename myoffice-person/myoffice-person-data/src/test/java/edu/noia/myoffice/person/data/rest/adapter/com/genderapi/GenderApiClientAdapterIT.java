package edu.noia.myoffice.person.data.rest.adapter.com.genderapi;

import edu.noia.myoffice.person.rest.client.GenderApiClient;
import edu.noia.myoffice.person.rest.dto.GenderDto;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import lombok.extern.slf4j.Slf4j;
import org.junit.ClassRule;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;

import static org.assertj.core.api.Assertions.assertThat;

@Ignore
@Slf4j
@SpringBootTest(classes = GenderApiClientConfiguration.class)
@RunWith(JUnitParamsRunner.class)
public class GenderApiClientAdapterIT {

    @ClassRule
    public static final SpringClassRule SPRING_CLASS_RULE = new SpringClassRule();
    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();
    @Autowired
    GenderApiClient client;

    @Test
    @Parameters(method = "testValues")
    public void should_return_the_gender_associated_to_a_full_name(String fullName,
                                                                   String expectedFirstName,
                                                                   String expectedLastName,
                                                                   String expectedGender) {
        // when
        GenderDto gender = client.split(fullName);
        // then
        assertThat(gender.getFirstName()).isEqualTo(expectedFirstName);
        assertThat(gender.getLastName()).isEqualTo(expectedLastName);
        assertThat(gender.getGender()).isEqualTo(expectedGender);
    }

    private Object[] testValues() {
        return new Object[]{
                new Object[]{"john doe", "John", "Doe", "male"},
                new Object[]{"doe john", "John", "Doe", "male"},
                new Object[]{"john van doe", "John", "van Doe", "male"},
        };
    }
}

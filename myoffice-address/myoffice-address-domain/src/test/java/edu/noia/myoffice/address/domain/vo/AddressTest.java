package edu.noia.myoffice.address.domain.vo;

import org.junit.Test;

import javax.validation.Validation;
import javax.validation.Validator;

import static org.assertj.core.api.Assertions.assertThat;

public class AddressTest {

    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    public void should_create_postal_address() {
        Address address = new PostalAddress()
                .setStreet("one street")
                .setHouseNbr("one house")
                .setZip("one zip")
                .setCity("one city")
                .setCountry("one country");
        assertThat(address.getType()).isEqualTo(AddressType.POSTAL);
        assertThat(validator.validate(address)).isEmpty();
    }

    @Test
    public void should_create_email_address() {
        Address address = new EmailAddress().setEmail("john.doe@seven.com");
        assertThat(address.getType()).isEqualTo(AddressType.EMAIL);
        assertThat(validator.validate(address)).isEmpty();
    }

    @Test
    public void should_create_phone_address() {
        Address address = new PhoneAddress().setNumber("909090909");
        assertThat(address.getType()).isEqualTo(AddressType.PHONE);
        assertThat(validator.validate(address)).isEmpty();
    }

    @Test
    public void should_create_web_address() {
        Address address = new WebAddress().setUrl("http://john.doe.com");
        assertThat(address.getType()).isEqualTo(AddressType.WEB);
        assertThat(validator.validate(address)).isEmpty();
    }

    @Test
    public void should_invalidate_postal_address() {
        Address address = new PostalAddress().setStreet("one street").setHouseNbr("one house");
        assertThat(address.getType()).isEqualTo(AddressType.POSTAL);
        assertThat(validator.validate(address)).isNotEmpty();
    }

    @Test
    public void should_invalidate_email_address() {
        Address address = new EmailAddress();
        assertThat(address.getType()).isEqualTo(AddressType.EMAIL);
        assertThat(validator.validate(address)).isNotEmpty();
    }

    @Test
    public void should_invalidate_phone_address() {
        Address address = new PhoneAddress();
        assertThat(address.getType()).isEqualTo(AddressType.PHONE);
        assertThat(validator.validate(address)).isNotEmpty();
    }

    @Test
    public void should_invalidate_web_address() {
        Address address = new WebAddress();
        assertThat(address.getType()).isEqualTo(AddressType.WEB);
        assertThat(validator.validate(address)).isNotEmpty();
    }
}

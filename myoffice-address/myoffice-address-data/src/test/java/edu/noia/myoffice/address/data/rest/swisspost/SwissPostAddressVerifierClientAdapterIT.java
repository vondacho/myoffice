package edu.noia.myoffice.address.data.rest.swisspost;

import edu.noia.myoffice.address.data.rest.adapter.swisspost.SwissPostAddressVerifierClientAdapter;
import edu.noia.myoffice.address.data.rest.adapter.swisspost.SwissPostAddressVerifierClientConfiguration;
import edu.noia.myoffice.address.domain.vo.PostalAddress;
import lombok.extern.slf4j.Slf4j;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@Ignore
@Slf4j
@SpringBootTest(classes = SwissPostAddressVerifierClientConfiguration.class)
@RunWith(SpringRunner.class)
public class SwissPostAddressVerifierClientAdapterIT {

    @Autowired
    SwissPostAddressVerifierClientAdapter client;

    @Test
    public void should_find_paradeplatz_street_in_zurich() {
        assertThat(client.findStreets("Paradeplatz", "", "", null))
                .usingElementComparatorOnFields("city")
                .containsExactly(new PostalAddress().setCity("Zürich"));
    }

    @Test
    public void should_find_precossy_street_in_nyon() {
        assertThat(client.findStreets("Precossy", null, null, null))
                .usingElementComparatorOnFields("city")
                .containsExactly(new PostalAddress().setCity("Nyon"));
    }

    @Test
    public void should_find_address_of_swiss_post_in_neuchatel() {
        assertThat(client.findAddresses("Numa-Droz", "1", "2000", null, null))
                .usingElementComparatorOnFields("city")
                .containsExactly(new PostalAddress().setCity("Neuchâtel"));
    }
}

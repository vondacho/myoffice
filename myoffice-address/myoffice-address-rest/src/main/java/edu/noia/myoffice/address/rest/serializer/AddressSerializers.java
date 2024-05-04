package edu.noia.myoffice.address.rest.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import edu.noia.myoffice.address.domain.vo.*;
import io.vavr.CheckedConsumer;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AddressSerializers {

    public static Module getModule() {
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Address.class, new AddressDeserializer());
        module.addSerializer(Address.class, new AddressSerializer());
        return module;
    }

    private static <T> void ifPresent(T value, CheckedConsumer<T> action) {
        if (!StringUtils.isEmpty(value)) {
            try {
                action.accept(value);
            } catch (Throwable t) {
                throw new IllegalArgumentException(t);
            }
        }
    }

    private static Optional<String> getNodeFieldValue(JsonNode node, String fieldName) {
        return Optional.ofNullable(node.get(fieldName)).map(JsonNode::asText);
    }

    public static class AddressSerializer extends JsonSerializer<Address> {
        @Override
        public void serialize(Address address, JsonGenerator jgen, SerializerProvider serializers) throws IOException {
            if (address != null) {
                jgen.writeStartObject();
                switch (address.getType()) {
                    case POSTAL:
                        PostalAddress postalAddress = (PostalAddress) address;
                        ifPresent(postalAddress.getStreet(), v -> jgen.writeStringField("street", v));
                        ifPresent(postalAddress.getHouseNbr(), v -> jgen.writeStringField("houseNbr", v));
                        ifPresent(postalAddress.getZip(), v -> jgen.writeStringField("zip", v));
                        ifPresent(postalAddress.getCity(), v -> jgen.writeStringField("city", v));
                        ifPresent(postalAddress.getRegion(), v -> jgen.writeStringField("region", v));
                        ifPresent(postalAddress.getCountry(), v -> jgen.writeStringField("country", v));
                        break;
                    case PHONE:
                        PhoneAddress phoneAddress = (PhoneAddress) address;
                        ifPresent(phoneAddress.getNumber(), v -> jgen.writeStringField("number", v));
                        break;
                    case EMAIL:
                        EmailAddress emailAddress = (EmailAddress) address;
                        ifPresent(emailAddress.getEmail(), v -> jgen.writeStringField("email", v));
                        break;
                    case WEB:
                        WebAddress webAddress = (WebAddress) address;
                        ifPresent(webAddress.getUrl(), v -> jgen.writeStringField("url", v));
                        break;
                }
                jgen.writeStringField("type", address.getType().name());
                ifPresent(address.getTags(), v -> jgen.writeStringField("tags", v));
                jgen.writeEndObject();
            }
        }
    }

    public static class AddressDeserializer extends JsonDeserializer<Address> {
        @Override
        public Address deserialize(JsonParser jp, DeserializationContext ctx) throws IOException {
            JsonNode node = jp.getCodec().readTree(jp);

            return getNodeFieldValue(node, "type")
                    .map(AddressType::valueOf)
                    .flatMap(addressType -> {
                        switch (addressType) {
                            case POSTAL:
                                return Optional.of(new PostalAddress())
                                        .map(a -> getNodeFieldValue(node, "street").map(a::setStreet).orElse(a))
                                        .map(a -> getNodeFieldValue(node, "houseNbr").map(a::setHouseNbr).orElse(a))
                                        .map(a -> getNodeFieldValue(node, "zip").map(a::setZip).orElse(a))
                                        .map(a -> getNodeFieldValue(node, "city").map(a::setCity).orElse(a))
                                        .map(a -> getNodeFieldValue(node, "region").map(a::setRegion).orElse(a))
                                        .map(a -> getNodeFieldValue(node, "country").map(a::setCountry).orElse(a))
                                        .map(a -> getNodeFieldValue(node, "tags").map(a::setTags).orElse(a));
                            case PHONE:
                                return Optional.of(new PhoneAddress())
                                        .map(a -> getNodeFieldValue(node, "number").map(a::setNumber).orElse(a))
                                        .map(a -> getNodeFieldValue(node, "tags").map(a::setTags).orElse(a));
                            case EMAIL:
                                return Optional.of(new EmailAddress())
                                        .map(a -> getNodeFieldValue(node, "email").map(a::setEmail).orElse(a))
                                        .map(a -> getNodeFieldValue(node, "tags").map(a::setTags).orElse(a));
                            case WEB:
                                return Optional.of(new WebAddress())
                                        .map(a -> getNodeFieldValue(node, "url").map(a::setUrl).orElse(a))
                                        .map(a -> getNodeFieldValue(node, "tags").map(a::setTags).orElse(a));
                            default:
                                return Optional.empty();
                        }
                    }).orElse(null);
        }
    }
}

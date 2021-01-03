package org.niks.repository;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.niks.AccessRoles;
import org.niks.entity.User;

import java.io.IOException;
import java.util.Map;

public class Deserializer extends JsonDeserializer {
    @Override
    public User deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        ObjectCodec objectCodec = jsonParser.getCodec();
        JsonNode node = objectCodec.readTree(jsonParser);
        AccessRoles roles = AccessRoles.valueOf(node.get("accessRoles").asText());
        long ID = node.get("userID").asLong();
        String userName = node.get("UserID").asText();
        String passwordHash = node.get("passwordHash").asText();

        return new User(roles, ID, userName, passwordHash);
    }
}

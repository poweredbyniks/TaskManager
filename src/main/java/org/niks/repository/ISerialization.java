package org.niks.repository;

import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.niks.AccessRoles;
import org.niks.entity.User;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface ISerialization<T> {

    default void writeJSON(Map<String, T> map) throws IOException {
        List<T> list = new ArrayList<>(map.values());
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File("/Users/elupokniks/Desktop/ProjectsData.json"), list);
    }

    default Map<String, T> readJSON() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File("/Users/elupokniks/Desktop/ProjectsData.json"),
                new TypeReference<Map<String, T>>() {
                });
    }
}


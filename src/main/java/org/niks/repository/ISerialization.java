package org.niks.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.niks.entity.Project;
import org.niks.entity.Task;
import org.niks.entity.User;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface ISerialization<T> {

    default void writeJSON(Map<String, T> map, String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        List<T> list = new ArrayList<>(map.values());
        mapper.writeValue(new File(filePath), list);
    }

    default User[] readUserJSON(String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(filePath), User[].class);
    }

    default Project[] readProjectJSON(String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(filePath), Project[].class);
    }
    default Task[] readTaskJSON(String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(filePath), Task[].class);
    }
}


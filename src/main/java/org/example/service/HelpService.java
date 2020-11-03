package org.example.service;

import org.example.Bootstrap;
import org.example.repository.HelpRepo;

import java.util.Map;

public class HelpService {
    private HelpRepo helpRepo;

    public HelpService(HelpRepo helpRepo) {
        this.helpRepo = helpRepo;
    }

    public void getCommands() {

        for (Map.Entry<String, String> commandsMap : helpRepo.showAll().entrySet()) {
            System.out.println(commandsMap.getKey() + " : " + commandsMap.getValue());
        }
    }
}

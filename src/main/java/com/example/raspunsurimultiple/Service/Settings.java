package com.example.raspunsurimultiple.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Settings {

    private static Settings instance;

    private final String repositoryType;
    private final String produseFile;
    private Settings(String repositoryType, String produseFile) {
        this.repositoryType = repositoryType;
        this.produseFile = produseFile;
    }

    public String getRepositoryType() {
        return repositoryType;
    }

    public String getProduseFile() {
        return produseFile;
    }
    private static Properties loadSettings() {
        try (FileReader fr = new FileReader("C:\\Users\\Maria\\IdeaProjects\\RaspunsuriMultiple\\src\\main\\java\\settings.properties")) {
            Properties settings = new Properties();
            settings.load(fr);
            return settings;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static synchronized Settings getInstance() {
        if (instance == null) {
            Properties properties = loadSettings();
            instance = new Settings(
                    properties.getProperty("Repository"),
                    properties.getProperty("Intrebari")
            );
        }
        return instance;
    }
}

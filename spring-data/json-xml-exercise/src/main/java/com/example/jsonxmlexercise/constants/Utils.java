package com.example.jsonxmlexercise.constants;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public enum Utils {
    ;

    public static final ModelMapper MODEL_MAPPER = new ModelMapper();

    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static <T> void writeIntoJsonFile(List<T> objects, Path path) throws IOException {
        final FileWriter fileWriter = new FileWriter(path.toFile());

        GSON.toJson(objects, fileWriter);

        fileWriter.flush();
        fileWriter.close();
    }

    public static <T> void writeIntoJsonFile(T object, Path path) throws IOException {
        final FileWriter fileWriter = new FileWriter(path.toFile());

        GSON.toJson(object, fileWriter);

        fileWriter.flush();
        fileWriter.close();
    }

}

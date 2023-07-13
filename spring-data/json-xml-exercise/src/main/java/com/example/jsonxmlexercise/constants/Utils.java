package com.example.jsonxmlexercise.constants;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import org.modelmapper.ModelMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static com.example.jsonxmlexercise.constants.Paths.FIRST_XML_PATH;

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

    public static <T> void writeIntoXmlFile(T wrapper, Path path) throws JAXBException {
        final File file = path.toFile();

        final JAXBContext context = JAXBContext.newInstance(wrapper.getClass());
        final Marshaller marshaller = context.createMarshaller();

        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        marshaller.marshal(wrapper, file);
    }

}

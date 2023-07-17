package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.MechanicImportDto;
import softuni.exam.models.entity.Mechanic;
import softuni.exam.repository.MechanicsRepository;
import softuni.exam.service.MechanicsService;
import softuni.exam.util.ValidationUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static softuni.exam.models.Constants.*;

@Service
public class MechanicsServiceImpl implements MechanicsService {
    private static final String MECHANIC_FILE_PATH = "src/main/resources/files/json/mechanics.json";

    private final MechanicsRepository mechanicsRepository;
    private final ValidationUtils validationUtils;
    private final ModelMapper modelMapper;
    private final Gson gson;

    @Autowired
    public MechanicsServiceImpl(MechanicsRepository mechanicsRepository, ValidationUtils validationUtils, ModelMapper modelMapper, Gson gson) {
        this.mechanicsRepository = mechanicsRepository;
        this.validationUtils = validationUtils;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }

    @Override
    public boolean areImported() {
        return this.mechanicsRepository.count() > 0;
    }

    @Override
    public String readMechanicsFromFile() throws IOException {
        return Files.readString(Path.of(MECHANIC_FILE_PATH));
    }

    @Override
    public String importMechanics() throws IOException {
        final StringBuilder stringBuilder = new StringBuilder();

        final List<MechanicImportDto> mechanics =
                Arrays.stream(gson.fromJson(readMechanicsFromFile(), MechanicImportDto[].class))
                        .collect(Collectors.toList());

        for (MechanicImportDto mechanic : mechanics) {
            stringBuilder.append(System.lineSeparator());

            if (this.mechanicsRepository.findFirstByEmail(mechanic.getEmail()).isPresent() ||
                    this.mechanicsRepository.findFirstByFirstName(mechanic.getFirstName()).isPresent() ||
                    !this.validationUtils.isValid(mechanic)) {
                stringBuilder.append(String.format(INVALID_FORMAT, MECHANIC));
                continue;
            }

            this.mechanicsRepository.save(this.modelMapper.map(mechanic, Mechanic.class));

            stringBuilder.append(String.format(SUCCESSFUL_FORMAT,
                    MECHANIC,
                    mechanic.getFirstName(),
                    mechanic.getLastName()));
        }

        return stringBuilder.toString().trim();
    }
}

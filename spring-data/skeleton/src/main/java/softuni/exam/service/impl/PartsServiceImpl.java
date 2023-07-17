package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.PartImportDto;
import softuni.exam.models.entity.Part;
import softuni.exam.repository.PartsRepository;
import softuni.exam.service.PartsService;
import softuni.exam.util.ValidationUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static softuni.exam.models.Constants.*;

@Service
public class PartsServiceImpl implements PartsService {
    private static final String PARTS_FILE_PATH = "src/main/resources/files/json/parts.json";

    private final PartsRepository partsRepository;
    private final ValidationUtils validationUtils;
    private final ModelMapper modelMapper;
    private final Gson gson;

    @Autowired
    public PartsServiceImpl(PartsRepository partsRepository,
                            ValidationUtils validationUtils,
                            ModelMapper modelMapper,
                            Gson gson) {
        this.partsRepository = partsRepository;
        this.validationUtils = validationUtils;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }

    @Override
    public boolean areImported() {
        return this.partsRepository.count() > 0;
    }

    @Override
    public String readPartsFileContent() throws IOException {
        return Files.readString(Path.of(PARTS_FILE_PATH));
    }

    @Override
    public String importParts() throws IOException {
        final StringBuilder stringBuilder = new StringBuilder();

        final List<PartImportDto> parts =
                Arrays.stream(this.gson.fromJson(readPartsFileContent(), PartImportDto[].class))
                        .collect(Collectors.toList());

        for (PartImportDto part : parts) {
            stringBuilder.append(System.lineSeparator());

            if (this.partsRepository.findFirstByPartName(part.getPartName()).isPresent() ||
                    !this.validationUtils.isValid(part)) {
                stringBuilder.append(String.format(INVALID_FORMAT, PART));
                continue;
            }

//          Successfully imported part Performance Battery - 455.38
            this.partsRepository.save(this.modelMapper.map(part, Part.class));

            stringBuilder.append(String.format(SUCCESSFUL_FORMAT,
                    PART,
                    part.getPartName() + " -",
                    part.getPrice()));
        }


        return stringBuilder.toString().trim();
    }
}

package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.PartDto;
import softuni.exam.models.entity.Part;
import softuni.exam.repository.PartsRepository;
import softuni.exam.service.PartsService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class PartsServiceImpl implements PartsService {

    private static String PARTS_FILE_PATH = "src/main/resources/files/json/parts.json";

    private PartsRepository partsRepository;
    private ValidationUtil validationUtil;
    private ModelMapper modelMapper;
    private Gson gson;


    public PartsServiceImpl(PartsRepository partsRepository, ValidationUtil validationUtil, ModelMapper modelMapper, Gson gson) {
        this.partsRepository = partsRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }


    @Override
    public boolean areImported() {
        return partsRepository.count() > 0;
    }

    @Override
    public String readPartsFileContent() throws IOException {
        return Files.readString(Path.of(PARTS_FILE_PATH));
    }

    @Override
    public String importParts() throws IOException {

        String fileInput = readPartsFileContent();

        List<PartDto> partDtos = Arrays.stream(gson.fromJson(fileInput, PartDto[].class))
                .collect(Collectors.toList());

        StringBuilder sb = new StringBuilder();

        for (PartDto partDto : partDtos) {
            Optional<Part> partInDb = partsRepository.findFirstByPartName(partDto.getPartName());

            if(!validationUtil.isValid(partDto) || partInDb.isPresent()){
                sb.append("Invalid part").append(System.lineSeparator());
                continue;
            }

            Part part = modelMapper.map(partDto, Part.class);
            partsRepository.save(part);

            sb.append(String.format("Successfully imported part %s - %s",
                    part.getPartName(), part.getPrice())).append(System.lineSeparator());

        }

        return sb.toString().trim();
    }
}

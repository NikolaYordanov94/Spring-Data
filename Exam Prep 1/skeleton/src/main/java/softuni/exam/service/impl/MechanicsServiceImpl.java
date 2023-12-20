package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.MechanicDto;
import softuni.exam.models.entity.Mechanic;
import softuni.exam.repository.MechanicsRepository;
import softuni.exam.service.MechanicsService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MechanicsServiceImpl implements MechanicsService {

    private static String MECHANICS_FILE_PATH = "src/main/resources/files/json/mechanics.json";
    private MechanicsRepository mechanicsRepository;
    private ValidationUtil validationUtil;
    private ModelMapper modelMapper;
    private Gson gson;


    public MechanicsServiceImpl(MechanicsRepository mechanicsRepository, ValidationUtil validationUtil, ModelMapper modelMapper, Gson gson) {
        this.mechanicsRepository = mechanicsRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }


    @Override
    public boolean areImported() {
        return mechanicsRepository.count() > 0;
    }

    @Override
    public String readMechanicsFromFile() throws IOException {
        return Files.readString(Path.of(MECHANICS_FILE_PATH));
    }

    @Override
    public String importMechanics() throws IOException {
        String fileMechanicsInput = readMechanicsFromFile();

        List<MechanicDto> mechanicDtoList = Arrays.stream(gson.fromJson(fileMechanicsInput, MechanicDto[].class))
                .collect(Collectors.toList());

        StringBuilder sb = new StringBuilder();

        for (MechanicDto mechanicDto : mechanicDtoList) {


            if(!validationUtil.isValid(mechanicDto) || mechanicsRepository.findFirstByEmail(mechanicDto.getEmail()).isPresent()
            || mechanicsRepository.findFirstByPhone(mechanicDto.getPhone()).isPresent()
                    || mechanicsRepository.findFirstByFirstName(mechanicDto.getFirstName()).isPresent()){
                sb.append("Invalid mechanic").append(System.lineSeparator());
                continue;
            }

            Mechanic mechanic = modelMapper.map(mechanicDto, Mechanic.class);
            mechanicsRepository.save(mechanic);

            sb.append(String.format("Successfully imported mechanic %s %s",
                    mechanic.getFirstName(), mechanic.getLastName())).append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}

package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.PicturesSeedJsonDto;
import softuni.exam.models.entity.Car;
import softuni.exam.models.entity.Picture;
import softuni.exam.repository.CarRepository;
import softuni.exam.repository.PictureRepository;
import softuni.exam.service.PictureService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PictureServiceImpl implements PictureService {

    private String PICTURES_FILE_PATH = "src/main/resources/files/json/pictures.json";

    private PictureRepository pictureRepository;
    private Gson gson;
    private ModelMapper modelMapper;
    private ValidationUtil validationUtil;

    private CarRepository carRepository;


    public PictureServiceImpl(PictureRepository pictureRepository, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil, CarRepository carRepository) {
        this.pictureRepository = pictureRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.carRepository = carRepository;
    }

    @Override
    public boolean areImported() {
        return pictureRepository.count() > 0;
    }

    @Override
    public String readPicturesFromFile() throws IOException {
        return Files.readString(Path.of(PICTURES_FILE_PATH));
    }

    @Override
    public String importPictures() throws IOException {
        String filePath = readPicturesFromFile();

        List<PicturesSeedJsonDto> picturesDto = Arrays.stream(gson.fromJson(filePath, PicturesSeedJsonDto[].class))
                .collect(Collectors.toList());

        StringBuilder sb = new StringBuilder();

        for (PicturesSeedJsonDto pictureDto : picturesDto) {
            if(!validationUtil.isValid(pictureDto)){
                sb.append("Invalid picture").append(System.lineSeparator());
                continue;
            }

            Car car = carRepository.findById(pictureDto.getCar())
                    .orElse(null);
            Picture picture = modelMapper.map(pictureDto, Picture.class);

            picture.setCar(car);

            pictureRepository.save(picture);

            sb.append(String.format("Successfully import picture - %s",
                    picture.getName())).append(System.lineSeparator());

        }

        return sb.toString().trim();
    }
}

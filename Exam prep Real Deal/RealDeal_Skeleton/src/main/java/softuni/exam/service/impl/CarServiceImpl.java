package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.CarsSeedJsonDto;
import softuni.exam.models.entity.Car;
import softuni.exam.repository.CarRepository;
import softuni.exam.service.CarService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {

    private String CARS_FILE_PATH = "src/main/resources/files/json/cars.json";

    private CarRepository carRepository;
    private Gson gson;
    private ModelMapper modelMapper;
    private ValidationUtil validationUtil;

    public CarServiceImpl(CarRepository carRepository, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.carRepository = carRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return carRepository.count() > 0;
    }

    @Override
    public String readCarsFileContent() throws IOException {
        return Files.readString(Path.of(CARS_FILE_PATH));
    }

    @Override
    public String importCars() throws IOException {

        String filePath = readCarsFileContent();

        List<CarsSeedJsonDto>  carsDto = Arrays.stream(gson.fromJson(filePath, CarsSeedJsonDto[].class))
                .collect(Collectors.toList());

        StringBuilder sb = new StringBuilder();

        for (CarsSeedJsonDto carDto : carsDto) {

            if(!validationUtil.isValid(carDto)){
                sb.append("Invalid car").append(System.lineSeparator());
                continue;
            }

            Car car = modelMapper.map(carDto, Car.class);
            carRepository.save(car);

            sb.append(String.format("Successfully imported car - %s - %s",
                    car.getMake(), car.getModel())).append(System.lineSeparator());
        }

        return sb.toString().trim();
    }

    @Override
    public String getCarsOrderByPicturesCountThenByMake() {
        return null;
    }
}

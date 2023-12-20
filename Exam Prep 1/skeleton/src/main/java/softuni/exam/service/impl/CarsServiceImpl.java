package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.CarDto;
import softuni.exam.models.dto.CarRootDto;
import softuni.exam.models.entity.Car;
import softuni.exam.repository.CarsRepository;
import softuni.exam.service.CarsService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class CarsServiceImpl implements CarsService {
    private CarsRepository carsRepository;
    private ModelMapper modelMapper;
    private ValidationUtil validationUtil;
    private XmlParser xmlParser;

    private static String CARS_FILE_PATH = "src/main/resources/files/xml/cars.xml";


    public CarsServiceImpl(CarsRepository carsRepository, ModelMapper modelMapper, ValidationUtil validationUtil, XmlParser xmlParser) {
        this.carsRepository = carsRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.xmlParser = xmlParser;
    }

    @Override
    public boolean areImported() {
        return carsRepository.count() > 0;
    }

    @Override
    public String readCarsFromFile() throws IOException {
        return Files.readString(Path.of(CARS_FILE_PATH));
    }

    @Override
    public String importCars() throws IOException, JAXBException {

        List<CarDto> carDtoList = xmlParser.fromFile(CARS_FILE_PATH, CarRootDto.class)
                .getCars();

        StringBuilder sb = new StringBuilder();

        for (CarDto carDto : carDtoList) {
            if(!validationUtil.isValid(carDto) ||
                    carsRepository.findFirstByPlateNumber(carDto.getPlateNumber()).isPresent()){
                sb.append("Invalid car").append(System.lineSeparator());
                continue;
            }

            Car car = modelMapper.map(carDto, Car.class);
            carsRepository.save(car);

            sb.append(String.format("Successfully imported car %s - %s",
                    car.getCarMake(), car.getCarModel())).append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}

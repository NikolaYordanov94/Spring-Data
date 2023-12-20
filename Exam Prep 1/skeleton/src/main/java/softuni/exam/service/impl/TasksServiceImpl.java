package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.TaskDto;
import softuni.exam.models.dto.TaskInfoDto;
import softuni.exam.models.dto.TaskRootDto;
import softuni.exam.models.entity.*;
import softuni.exam.repository.CarsRepository;
import softuni.exam.repository.MechanicsRepository;
import softuni.exam.repository.PartsRepository;
import softuni.exam.repository.TasksRepository;
import softuni.exam.service.TasksService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TasksServiceImpl implements TasksService {

    private TasksRepository tasksRepository;
    private ValidationUtil validationUtil;
    private ModelMapper modelMapper;
    private XmlParser xmlParser;
    private CarsRepository carsRepository;
    private MechanicsRepository mechanicsRepository;
    private PartsRepository partsRepository;

    private static String TASKS_FILE_PATH = "src/main/resources/files/xml/tasks.xml";

    public TasksServiceImpl(TasksRepository tasksRepository, ValidationUtil validationUtil, ModelMapper modelMapper, XmlParser xmlParser, CarsRepository carsRepository, MechanicsRepository mechanicsRepository, PartsRepository partsRepository) {
        this.tasksRepository = tasksRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.carsRepository = carsRepository;
        this.mechanicsRepository = mechanicsRepository;
        this.partsRepository = partsRepository;
    }


    @Override
    public boolean areImported() {
        return tasksRepository.count() > 0;
    }

    @Override
    public String readTasksFileContent() throws IOException {
        return Files.readString(Path.of(TASKS_FILE_PATH));
    }

    @Override
    public String importTasks() throws IOException, JAXBException {
        List<TaskDto> taskDtoList = xmlParser.fromFile(TASKS_FILE_PATH, TaskRootDto.class)
                .getTaskDtoList();

        StringBuilder sb = new StringBuilder();

        for (TaskDto taskDto : taskDtoList) {

            Optional<Car> car = carsRepository.findById(taskDto.getPart().getId());
            Optional<Mechanic> mechanic = mechanicsRepository.findFirstByFirstName(taskDto.getMechanic().getFirstName());
            Optional<Part> part = partsRepository.findById(taskDto.getPart().getId());

            if(validationUtil.isValid(taskDto) && car.isPresent() && mechanic.isPresent() && part.isPresent()){

                Task task = modelMapper.map(taskDto, Task.class);
                task.setCar(car.get());
                task.setMechanic(mechanic.get());
                task.setPart(part.get());

                tasksRepository.save(task);

                sb.append(String.format("Successfully imported task %.2f", task.getPrice()))
                        .append(System.lineSeparator());

            }else{
                sb.append("Invalid task").append(System.lineSeparator());
            }

        }

        return sb.toString().trim();
    }

    @Override
    public String getCoupeCarTasksOrderByPrice() {
        List<Task> cars = tasksRepository.findAllByCar_CarTypeOrderByPriceDesc(CarType.coupe);

        return cars
                .stream()
                .map(task -> modelMapper.map(task, TaskInfoDto.class))
                .map(TaskInfoDto::toString)
                .collect(Collectors.joining())
                .trim();
    }
}

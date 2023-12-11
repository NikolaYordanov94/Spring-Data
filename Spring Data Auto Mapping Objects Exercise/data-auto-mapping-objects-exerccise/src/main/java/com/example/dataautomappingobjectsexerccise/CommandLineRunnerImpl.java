package com.example.dataautomappingobjectsexerccise;

import com.example.dataautomappingobjectsexerccise.model.dto.GameAddDto;
import com.example.dataautomappingobjectsexerccise.model.dto.UserLoginDto;
import com.example.dataautomappingobjectsexerccise.model.dto.UserRegisterDto;
import com.example.dataautomappingobjectsexerccise.service.GameService;
import com.example.dataautomappingobjectsexerccise.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    private final BufferedReader bufferedReader;
    private final UserService userService;
    private final GameService gameService;

    public CommandLineRunnerImpl(UserService userService, GameService gameService) {
        this.userService = userService;
        this.gameService = gameService;
        this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Enter your commands: / Or write End to stop");
        String input = bufferedReader.readLine();

        while (!input.equals("End")){
            String[] commands = input.split("\\|");

            switch (commands[0]){
                case "RegisterUser" -> userService
                        .registerUser(new UserRegisterDto(commands[1], commands[2], commands[3], commands[4]));
                case "LoginUser" -> userService
                        .loginUser(new UserLoginDto(commands[1], commands[2]));
                case "Logout" -> userService
                        .logout();
                case "AddGame" -> gameService
                        .addGame(new GameAddDto(commands[1], new BigDecimal(commands[2]),
                                Double.parseDouble(commands[3]), commands[4], commands[5], commands[6]
                                , LocalDate.parse(commands[7], DateTimeFormatter.ofPattern("dd-MM-yyyy"))));
                case "EditGame" -> gameService
                        .editGame(Long.parseLong(commands[1]),
                                new BigDecimal(commands[2].substring(6)),
                                Double.parseDouble(commands[3].substring(5)));
                case "DeleteGame" -> gameService
                        .deleteGame(Long.parseLong(commands[1]));
                case "AllGames" -> gameService
                        .allGames()
                        .forEach(System.out::println);
                case "DetailGame" -> gameService
                        .detailsGame(commands[1])
                        .forEach(System.out::println);

                default -> System.out.println("Wrong command");

            }

            System.out.println("Enter your commands: / Or write End to stop");

            input = bufferedReader.readLine();
        }
    }
}

package com.example.dataautomappingobjectsexerccise.service.impl;
import com.example.dataautomappingobjectsexerccise.model.dto.GameAddDto;
import com.example.dataautomappingobjectsexerccise.model.dto.GameDetailsDto;
import com.example.dataautomappingobjectsexerccise.model.dto.GamesViewAllDto;
import com.example.dataautomappingobjectsexerccise.model.entity.Game;
import com.example.dataautomappingobjectsexerccise.repository.GameRepository;
import com.example.dataautomappingobjectsexerccise.service.GameService;
import com.example.dataautomappingobjectsexerccise.service.UserService;
import com.example.dataautomappingobjectsexerccise.util.ValidationUtil;
import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final UserService userService;

    public GameServiceImpl(GameRepository gameRepository, ModelMapper modelMapper, ValidationUtil validationUtil, UserService userService) {
        this.gameRepository = gameRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.userService = userService;
    }

    @Override
    public void addGame(GameAddDto gameAddDto) {

        Set<ConstraintViolation<GameAddDto>> violations =
                validationUtil.violations(gameAddDto);

        if(!violations.isEmpty()){
            violations
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);

            return;
        }

        Game game = modelMapper.map(gameAddDto, Game.class);

        gameRepository.save(game);

        System.out.printf("Added %s%n", game.getTitle());

    }

    @Override
    public void editGame(Long gameId, BigDecimal price, Double size) {
        Game game = gameRepository
                .findById(gameId)
                .orElse(null);

        if(game == null){
            System.out.println("Invalid game id");
            return;
        }

        game.setPrice(price);
        game.setSize(size);

        gameRepository.save(game);
        System.out.printf("Edited %s%n", game.getTitle());
    }

    @Override
    public void deleteGame(long gameId) {
        Game game = gameRepository
                .findById(gameId)
                .orElse(null);

        if(game == null){
            System.out.println("Invalid game id");
            return;
        }

        gameRepository.delete(game);
        System.out.printf("Deleted %s%n", game.getTitle());
    }

    @Override
    public List<String> allGames() {
        return gameRepository.findAll()
                .stream()
                .map(game -> {
            GamesViewAllDto gamesViewAllDto =
                    modelMapper.map(game, GamesViewAllDto.class);
            return String.format("%s %.2f", gamesViewAllDto.getTitle(), gamesViewAllDto.getPrice());
        }).collect(Collectors.toList());
    }

    @Override
    public List<String> detailsGame(String title) {
        return gameRepository
                .findByTitle(title)
                .stream()
                .map(game -> {
                    GameDetailsDto gameDetailsDto = modelMapper.map(game, GameDetailsDto.class);
                    return String.format("Title: %s%nPrice: %.2f%nDescription: %s%nRelease date: %s",
                            gameDetailsDto.getTitle(), gameDetailsDto.getPrice(), gameDetailsDto.getDescription(),
                            gameDetailsDto.getReleaseDate());
                }).collect(Collectors.toList());
    }
}

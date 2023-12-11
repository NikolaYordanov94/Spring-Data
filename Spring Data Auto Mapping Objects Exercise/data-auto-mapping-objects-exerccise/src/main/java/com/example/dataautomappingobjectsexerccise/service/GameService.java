package com.example.dataautomappingobjectsexerccise.service;

import com.example.dataautomappingobjectsexerccise.model.dto.GameAddDto;

import java.math.BigDecimal;
import java.util.List;

public interface GameService {
    void addGame(GameAddDto gameAddDto);

    void editGame(Long gameId, BigDecimal price, Double size);

    void deleteGame(long gameId);

    List<String> allGames();

    List<String> detailsGame(String title);
}

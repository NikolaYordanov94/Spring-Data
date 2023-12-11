package com.example.dataautomappingobjectsexerccise.repository;

import com.example.dataautomappingobjectsexerccise.model.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {

    List<Game> findByTitle(String title);
}

package com.example.dataautomappingobjectsexerccise.repository;

import com.example.dataautomappingobjectsexerccise.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}

package com.example.pract6.repository;

import com.example.pract6.model.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuyerRepository extends JpaRepository<Buyer, Long> {

    List<Buyer> findByLastNameContainingIgnoreCase(String lastName);

    List<Buyer> findByGender(String gender);
}
package com.example.pract6.service;

import com.example.pract6.model.Buyer;
import com.example.pract6.model.dto.BuyerRequestDto;
import com.example.pract6.repository.BuyerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BuyerService {

    private final BuyerRepository buyerRepository;

    @Autowired
    public BuyerService(BuyerRepository buyerRepository) {
        this.buyerRepository = buyerRepository;
    }

    public List<Buyer> getAllBuyers() {
        return buyerRepository.findAll();
    }

    public Optional<Buyer> getBuyerById(Long id) {
        return buyerRepository.findById(id);
    }

    public Buyer createBuyer(BuyerRequestDto dto) {
        Buyer buyer = toEntity(dto);
        return buyerRepository.save(buyer);
    }

    public Buyer updateBuyer(Long id, BuyerRequestDto dto) {
        Buyer existingBuyer = buyerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Покупатель с ID " + id + " не найден"));

        existingBuyer.setLastName(dto.getLastName());
        existingBuyer.setFirstName(dto.getFirstName());
        existingBuyer.setPatronymic(dto.getPatronymic());
        existingBuyer.setGender(dto.getGender());
        existingBuyer.setNationality(dto.getNationality());
        existingBuyer.setHeight(dto.getHeight());
        existingBuyer.setWeight(dto.getWeight());
        existingBuyer.setBirthDate(dto.getBirthDate());
        existingBuyer.setPhone(dto.getPhone());
        existingBuyer.setAddress(dto.getAddress());
        existingBuyer.setCreditCardNumber(dto.getCreditCardNumber());
        existingBuyer.setBankAccount(dto.getBankAccount());

        return buyerRepository.save(existingBuyer);
    }

    public boolean deleteBuyer(Long id) {
        if (buyerRepository.existsById(id)) {
            buyerRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean existsById(Long id) {
        return buyerRepository.existsById(id);
    }

    public List<Buyer> searchByLastName(String lastName) {
        return buyerRepository.findByLastNameContainingIgnoreCase(lastName);
    }

    public List<Buyer> searchByGender(String gender) {
        return buyerRepository.findByGender(gender);
    }

    private Buyer toEntity(BuyerRequestDto dto) {
        return new Buyer(
                dto.getLastName(),
                dto.getFirstName(),
                dto.getPatronymic(),
                dto.getGender(),
                dto.getNationality(),
                dto.getHeight(),
                dto.getWeight(),
                dto.getBirthDate(),
                dto.getPhone(),
                dto.getAddress(),
                dto.getCreditCardNumber(),
                dto.getBankAccount()
        );
    }
}
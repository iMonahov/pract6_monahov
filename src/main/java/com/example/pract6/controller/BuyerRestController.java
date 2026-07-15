package com.example.pract6.controller;

import com.example.pract6.model.Buyer;
import com.example.pract6.model.dto.BuyerRequestDto;
import com.example.pract6.model.dto.BuyerResponseDto;
import com.example.pract6.service.BuyerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/buyers")
@Tag(name = "Покупатели", description = "REST API для управления покупателями")
public class BuyerRestController {

    private final BuyerService buyerService;

    @Autowired
    public BuyerRestController(BuyerService buyerService) {
        this.buyerService = buyerService;
    }

    @GetMapping
    @Operation(summary = "Получить всех покупателей", description = "Возвращает список всех покупателей")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно получены",
                    content = @Content(schema = @Schema(implementation = BuyerResponseDto.class)))
    })
    public ResponseEntity<List<BuyerResponseDto>> getAllBuyers() {
        List<Buyer> buyers = buyerService.getAllBuyers();
        List<BuyerResponseDto> response = buyers.stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить покупателя по ID", description = "Возвращает покупателя по указанному идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Покупатель найден",
                    content = @Content(schema = @Schema(implementation = BuyerResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Покупатель не найден")
    })
    public ResponseEntity<BuyerResponseDto> getBuyerById(
            @Parameter(description = "ID покупателя", example = "1", required = true)
            @PathVariable("id") Long id) {
        return buyerService.getBuyerById(id)
                .map(this::toResponseDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Создать покупателя", description = "Создаёт нового покупателя на основе переданных данных")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Покупатель создан",
                    content = @Content(schema = @Schema(implementation = BuyerResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Некорректные данные")
    })
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<BuyerResponseDto> createBuyer(
            @Valid @RequestBody BuyerRequestDto requestDto) {
        Buyer savedBuyer = buyerService.createBuyer(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponseDto(savedBuyer));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновить покупателя", description = "Обновляет данные существующего покупателя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Покупатель обновлён",
                    content = @Content(schema = @Schema(implementation = BuyerResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Покупатель не найден"),
            @ApiResponse(responseCode = "400", description = "Некорректные данные")
    })
    public ResponseEntity<BuyerResponseDto> updateBuyer(
            @Parameter(description = "ID покупателя", example = "1", required = true)
            @PathVariable("id") Long id,
            @Valid @RequestBody BuyerRequestDto requestDto) {
        try {
            Buyer updatedBuyer = buyerService.updateBuyer(id, requestDto);
            return ResponseEntity.ok(toResponseDto(updatedBuyer));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить покупателя", description = "Удаляет покупателя по указанному ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Покупатель удалён"),
            @ApiResponse(responseCode = "404", description = "Покупатель не найден")
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteBuyer(
            @Parameter(description = "ID покупателя", example = "1", required = true)
            @PathVariable("id") Long id) {
        if (buyerService.deleteBuyer(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/search")
    @Operation(summary = "Поиск покупателей", description = "Ищет покупателей по фамилии или полу")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Результаты поиска",
                    content = @Content(schema = @Schema(implementation = BuyerResponseDto.class)))
    })
    public ResponseEntity<List<BuyerResponseDto>> searchBuyers(
            @Parameter(description = "Фамилия (частичное совпадение)", example = "Иванов")
            @RequestParam(required = false) String lastName,
            @Parameter(description = "Пол (Мужской или Женский)", example = "Мужской")
            @RequestParam(required = false) String gender) {

        List<Buyer> buyers;
        if (lastName != null && !lastName.isEmpty()) {
            buyers = buyerService.searchByLastName(lastName);
        } else if (gender != null && !gender.isEmpty()) {
            buyers = buyerService.searchByGender(gender);
        } else {
            buyers = buyerService.getAllBuyers();
        }

        List<BuyerResponseDto> response = buyers.stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    private BuyerResponseDto toResponseDto(Buyer buyer) {
        return new BuyerResponseDto(
                buyer.getId(),
                buyer.getLastName(),
                buyer.getFirstName(),
                buyer.getPatronymic(),
                buyer.getGender(),
                buyer.getNationality(),
                buyer.getHeight(),
                buyer.getWeight(),
                buyer.getBirthDate(),
                buyer.getPhone(),
                buyer.getAddress(),
                buyer.getCreditCardNumber(),
                buyer.getBankAccount()
        );
    }
}
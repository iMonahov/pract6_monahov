package com.example.pract6.model.dto;

import com.example.pract6.model.Address;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Schema(description = "Запрос на создание/обновление покупателя")
public class BuyerRequestDto {

    @Schema(description = "Фамилия покупателя", example = "Иванов", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "Фамилия не может быть пустой")
    @Size(min = 2, max = 50, message = "Фамилия должна быть от 2 до 50 символов")
    private String lastName;

    @Schema(description = "Имя покупателя", example = "Михаил", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "Имя не может быть пустым")
    @Size(min = 2, max = 50, message = "Имя должно быть от 2 до 50 символов")
    private String firstName;

    @Schema(description = "Отчество покупателя", example = "Петрович")
    @Size(max = 50, message = "Отчество не может быть длиннее 50 символов")
    private String patronymic;

    @Schema(description = "Пол покупателя", example = "Мужской", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "Пол не может быть пустым")
    @Pattern(regexp = "^(Мужской|Женский)$", message = "Пол должен быть 'Мужской' или 'Женский'")
    private String gender;

    @Schema(description = "Национальность покупателя", example = "Русский", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "Национальность не может быть пустой")
    @Size(min = 2, max = 50, message = "Национальность должна быть от 2 до 50 символов")
    private String nationality;

    @Schema(description = "Рост покупателя в сантиметрах", example = "180", minimum = "50", maximum = "250")
    @NotNull(message = "Рост обязателен")
    @Min(value = 50, message = "Рост должен быть не менее 50 см")
    @Max(value = 250, message = "Рост должен быть не более 250 см")
    private Integer height;

    @Schema(description = "Вес покупателя в килограммах", example = "75", minimum = "10", maximum = "300")
    @NotNull(message = "Вес обязателен")
    @Min(value = 10, message = "Вес должен быть не менее 10 кг")
    @Max(value = 300, message = "Вес должен быть не более 300 кг")
    private Integer weight;

    @Schema(description = "Дата рождения", example = "2000-05-15", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "Дата рождения обязательна")
    @Past(message = "Дата рождения должна быть в прошлом")
    private LocalDate birthDate;

    @Schema(description = "Номер телефона", example = "+7-999-123-45-67", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "Номер телефона не может быть пустым")
    @Pattern(regexp = "^\\+?[0-9\\-\\s]{10,15}$", message = "Некорректный формат телефона")
    private String phone;

    @Schema(description = "Домашний адрес")
    @Valid
    private Address address;

    @Schema(description = "Номер кредитной карты (16 цифр)", example = "1234567890123456", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "Номер кредитной карты не может быть пустым")
    @Pattern(regexp = "^[0-9]{16}$", message = "Номер карты должен содержать 16 цифр")
    private String creditCardNumber;

    @Schema(description = "Номер банковского счёта (20 цифр)", example = "12345678901234567890", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "Номер банковского счёта не может быть пустым")
    @Pattern(regexp = "^[0-9]{20}$", message = "Номер счёта должен содержать 20 цифр")
    private String bankAccount;

    // Геттеры и сеттеры
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getPatronymic() { return patronymic; }
    public void setPatronymic(String patronymic) { this.patronymic = patronymic; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getNationality() { return nationality; }
    public void setNationality(String nationality) { this.nationality = nationality; }

    public Integer getHeight() { return height; }
    public void setHeight(Integer height) { this.height = height; }

    public Integer getWeight() { return weight; }
    public void setWeight(Integer weight) { this.weight = weight; }

    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public Address getAddress() { return address; }
    public void setAddress(Address address) { this.address = address; }

    public String getCreditCardNumber() { return creditCardNumber; }
    public void setCreditCardNumber(String creditCardNumber) { this.creditCardNumber = creditCardNumber; }

    public String getBankAccount() { return bankAccount; }
    public void setBankAccount(String bankAccount) { this.bankAccount = bankAccount; }
}
package com.example.pract6.model.dto;

import com.example.pract6.model.Address;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;

@Schema(description = "Ответ с данными покупателя")
public class BuyerResponseDto {

    @Schema(description = "ID покупателя", example = "1")
    private Long id;

    @Schema(description = "Фамилия", example = "Иванов")
    private String lastName;

    @Schema(description = "Имя", example = "Михаил")
    private String firstName;

    @Schema(description = "Отчество", example = "Петрович")
    private String patronymic;

    @Schema(description = "Пол", example = "Мужской")
    private String gender;

    @Schema(description = "Национальность", example = "Русский")
    private String nationality;

    @Schema(description = "Рост", example = "180")
    private Integer height;

    @Schema(description = "Вес", example = "75")
    private Integer weight;

    @Schema(description = "Дата рождения", example = "2000-05-15")
    private LocalDate birthDate;

    @Schema(description = "Телефон", example = "+7-999-123-45-67")
    private String phone;

    @Schema(description = "Адрес")
    private Address address;

    @Schema(description = "Номер кредитной карты", example = "1234567890123456")
    private String creditCardNumber;

    @Schema(description = "Номер банковского счёта", example = "12345678901234567890")
    private String bankAccount;

    public BuyerResponseDto() {}

    public BuyerResponseDto(Long id, String lastName, String firstName, String patronymic,
                            String gender, String nationality, Integer height, Integer weight,
                            LocalDate birthDate, String phone, Address address,
                            String creditCardNumber, String bankAccount) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.gender = gender;
        this.nationality = nationality;
        this.height = height;
        this.weight = weight;
        this.birthDate = birthDate;
        this.phone = phone;
        this.address = address;
        this.creditCardNumber = creditCardNumber;
        this.bankAccount = bankAccount;
    }

    // Геттеры и сеттеры
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

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

    public String getFullName() {
        return lastName + " " + firstName + (patronymic != null ? " " + patronymic : "");
    }
}
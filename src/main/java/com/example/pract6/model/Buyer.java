package com.example.pract6.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Schema(description = "Сущность покупателя (Вариант 4)")
@Entity
@Table(name = "buyers")
public class Buyer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Фамилия не может быть пустой")
    @Size(min = 2, max = 50, message = "Фамилия должна быть от 2 до 50 символов")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotBlank(message = "Имя не может быть пустым")
    @Size(min = 2, max = 50, message = "Имя должно быть от 2 до 50 символов")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Size(max = 50, message = "Отчество не может быть длиннее 50 символов")
    @Column(name = "patronymic")
    private String patronymic;

    @NotBlank(message = "Пол не может быть пустым")
    @Pattern(regexp = "^(Мужской|Женский)$", message = "Пол должен быть 'Мужской' или 'Женский'")
    @Column(name = "gender", nullable = false)
    private String gender;

    @NotBlank(message = "Национальность не может быть пустой")
    @Size(min = 2, max = 50, message = "Национальность должна быть от 2 до 50 символов")
    @Column(name = "nationality", nullable = false)
    private String nationality;

    @NotNull(message = "Рост обязателен")
    @Min(value = 50, message = "Рост должен быть не менее 50 см")
    @Max(value = 250, message = "Рост должен быть не более 250 см")
    @Column(name = "height")
    private Integer height;

    @NotNull(message = "Вес обязателен")
    @Min(value = 10, message = "Вес должен быть не менее 10 кг")
    @Max(value = 300, message = "Вес должен быть не более 300 кг")
    @Column(name = "weight")
    private Integer weight;

    @NotNull(message = "Дата рождения обязательна")
    @Past(message = "Дата рождения должна быть в прошлом")
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @NotBlank(message = "Номер телефона не может быть пустым")
    @Pattern(regexp = "^\\+?[0-9\\-\\s]{10,15}$", message = "Некорректный формат телефона")
    @Column(name = "phone", nullable = false)
    private String phone;

    @Embedded
    private Address address;

    @NotBlank(message = "Номер кредитной карты не может быть пустым")
    @Pattern(regexp = "^[0-9]{16}$", message = "Номер карты должен содержать 16 цифр")
    @Column(name = "credit_card_number", nullable = false)
    private String creditCardNumber;

    @NotBlank(message = "Номер банковского счёта не может быть пустым")
    @Pattern(regexp = "^[0-9]{20}$", message = "Номер счёта должен содержать 20 цифр")
    @Column(name = "bank_account", nullable = false)
    private String bankAccount;

    public Buyer() {}

    public Buyer(String lastName, String firstName, String patronymic,
                 String gender, String nationality, Integer height, Integer weight,
                 LocalDate birthDate, String phone, Address address,
                 String creditCardNumber, String bankAccount) {
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

    public int getAge() {
        if (birthDate == null) return 0;
        return LocalDate.now().getYear() - birthDate.getYear();
    }
}
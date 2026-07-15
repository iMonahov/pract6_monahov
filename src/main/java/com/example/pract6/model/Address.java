package com.example.pract6.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Size;

@Schema(description = "Адрес покупателя")
@Embeddable
public class Address {

    @Schema(description = "Почтовый индекс", example = "101000")
    @Size(max = 10, message = "Индекс не может быть длиннее 10 символов")
    private String postalCode;

    @Schema(description = "Страна", example = "Россия")
    @Size(max = 50, message = "Страна не может быть длиннее 50 символов")
    private String country;

    @Schema(description = "Область", example = "Московская")
    @Size(max = 50, message = "Область не может быть длиннее 50 символов")
    private String region;

    @Schema(description = "Район", example = "Центральный")
    @Size(max = 50, message = "Район не может быть длиннее 50 символов")
    private String district;

    @Schema(description = "Город", example = "Москва")
    @Size(max = 50, message = "Город не может быть длиннее 50 символов")
    private String city;

    @Schema(description = "Улица", example = "Ленина")
    @Size(max = 50, message = "Улица не может быть длиннее 50 символов")
    private String street;

    @Schema(description = "Номер дома", example = "10")
    @Size(max = 10, message = "Номер дома не может быть длиннее 10 символов")
    private String houseNumber;

    @Schema(description = "Номер квартиры", example = "5")
    @Size(max = 10, message = "Номер квартиры не может быть длиннее 10 символов")
    private String apartmentNumber;

    public Address() {}

    public Address(String postalCode, String country, String region,
                   String district, String city, String street,
                   String houseNumber, String apartmentNumber) {
        this.postalCode = postalCode;
        this.country = country;
        this.region = region;
        this.district = district;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
        this.apartmentNumber = apartmentNumber;
    }

    // Геттеры и сеттеры
    public String getPostalCode() { return postalCode; }
    public void setPostalCode(String postalCode) { this.postalCode = postalCode; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public String getRegion() { return region; }
    public void setRegion(String region) { this.region = region; }

    public String getDistrict() { return district; }
    public void setDistrict(String district) { this.district = district; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }

    public String getHouseNumber() { return houseNumber; }
    public void setHouseNumber(String houseNumber) { this.houseNumber = houseNumber; }

    public String getApartmentNumber() { return apartmentNumber; }
    public void setApartmentNumber(String apartmentNumber) { this.apartmentNumber = apartmentNumber; }

    public String getFullAddress() {
        return (postalCode != null ? postalCode + ", " : "") +
                (country != null ? country + ", " : "") +
                (region != null ? region + ", " : "") +
                (district != null ? district + ", " : "") +
                (city != null ? city + ", " : "") +
                (street != null ? street + ", " : "") +
                (houseNumber != null ? "д." + houseNumber : "") +
                (apartmentNumber != null ? ", кв." + apartmentNumber : "");
    }
}
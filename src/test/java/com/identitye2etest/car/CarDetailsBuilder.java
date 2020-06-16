package com.identitye2etest.car;

public class CarDetailsBuilder {
    private String registration = "";
    private String make = "";
    private String model = "";
    private String colour = "";
    private String year = "";

    public CarDetailsBuilder withRegistration(String registration) {
        this.registration = registration;
        return this;
    }

    public CarDetailsBuilder withMake(String make) {
        this.make = make;
        return this;
    }

    public CarDetailsBuilder withModel(String model) {
        this.model = model;
        return this;
    }

    public CarDetailsBuilder withColour(String colour) {
        this.colour = colour;
        return this;
    }

    public CarDetailsBuilder withYear(String year) {
        this.year = year;
        return this;
    }

    public CarDetails build() {
        return new CarDetails(registration, make, model, colour, year);
    }
}

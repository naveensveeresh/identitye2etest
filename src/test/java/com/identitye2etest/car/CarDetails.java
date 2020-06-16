package com.identitye2etest.car;

public class CarDetails {
    private String registration;
    private String make;
    private String model;
    private String colour;
    private String year;

    public CarDetails(
            String registration,
            String make,
            String model,
            String colour,
            String year) {
        this.registration = registration;
        this.make = make;
        this.model = model;
        this.colour = colour;
        this.year = year;
    }

    public String getRegistration() {
        return registration;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getColour() {
        return colour;
    }

    public String getYear() {
        return year;
    }

    public Boolean detailsMatch(CarDetails carDetails) {
        if(this.registration.equals(carDetails.getRegistration()) &&
                this.make.equals(carDetails.getMake()) &&
                this.model.equals(carDetails.getModel()) &&
                this.colour.equals(carDetails.getColour()) &&
                this.year.equals(carDetails.getYear())
                ) {
            return true;
        }
        else {
            return false;
        }
    }
}

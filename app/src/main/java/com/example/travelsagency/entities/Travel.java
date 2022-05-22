package com.example.travelsagency.entities;

public class Travel {
    private Integer id, quantity, agency_id_fk, vehicle_id_fk;
    private String name,description, destination, location_exit, location_arrive, start_travel, hour_exit, end_travel;
    private Float rating, price;

    public Travel(Integer id, Integer quantity, Integer agency_id_fk, Integer vehicle_id_fk, String name, String description, String destination, String location_exit, String location_arrive, String start_travel, String hour_exit, String end_travel, Float rating, Float price) {
        this.id = id;
        this.quantity = quantity;
        this.agency_id_fk = agency_id_fk;
        this.vehicle_id_fk = vehicle_id_fk;
        this.name = name;
        this.description = description;
        this.destination = destination;
        this.location_exit = location_exit;
        this.location_arrive = location_arrive;
        this.start_travel = start_travel;
        this.hour_exit = hour_exit;
        this.end_travel = end_travel;
        this.rating = rating;
        this.price = price;
    }

    public Travel() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getAgency_id_fk() {
        return agency_id_fk;
    }

    public void setAgency_id_fk(Integer agency_id_fk) {
        this.agency_id_fk = agency_id_fk;
    }

    public Integer getVehicle_id_fk() {
        return vehicle_id_fk;
    }

    public void setVehicle_id_fk(Integer vehicle_id_fk) {
        this.vehicle_id_fk = vehicle_id_fk;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getLocation_exit() {
        return location_exit;
    }

    public void setLocation_exit(String location_exit) {
        this.location_exit = location_exit;
    }

    public String getLocation_arrive() {
        return location_arrive;
    }

    public void setLocation_arrive(String location_arrive) {
        this.location_arrive = location_arrive;
    }

    public String getStart_travel() {
        return start_travel;
    }

    public void setStart_travel(String start_travel) {
        this.start_travel = start_travel;
    }

    public String getHour_exit() {
        return hour_exit;
    }

    public void setHour_exit(String hour_exit) {
        this.hour_exit = hour_exit;
    }

    public String getEnd_travel() {
        return end_travel;
    }

    public void setEnd_travel(String end_travel) {
        this.end_travel = end_travel;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}

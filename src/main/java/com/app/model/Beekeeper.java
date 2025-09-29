package com.app.model;

public class Beekeeper {
    private int id;
    private String firstName;
    private String lastName;
    private String address;
    private String hobby;

    public Beekeeper(int id, String firstName, String lastName, String address, String hobby) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.hobby = hobby;
    }

    public int getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getAddress() { return address; }
    public String getHobby() { return hobby; }
}

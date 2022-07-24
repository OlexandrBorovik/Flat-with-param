package jpa1;

import javax.persistence.*;

@Entity
@Table(name="Flat")
public class Flat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private long id;

    @Column(nullable = false)
    private String city;
    private String street;
    private int number;
    private int room;
    private int square;
    private int cost;

    public Flat() {
    }

    public Flat( String city, String street, int number, int room, int square, int cost) {

        this.city = city;
        this.street = street;
        this.number = number;
        this.room = room;
        this.square = square;
        this.cost = cost;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public int getSquare() {
        return square;
    }

    public void setSquare(int square) {
        this.square = square;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }



    @Override
    public String toString() {
        return "Flat{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", number=" + number +
                ", room=" + room +
                ", square=" + square +
                ", cost=" + cost +
                '}';
    }
}

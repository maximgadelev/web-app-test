package com.gadelev.model;

import javax.persistence.*;

import java.time.LocalDateTime;


@Entity
public class Weather {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne(mappedBy = "weather",cascade = CascadeType.ALL)
    private Request request;

    private String humidity;

    private String city;


    private String email;

    private LocalDateTime time;

    public Weather() {
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Weather(String humidity, String city, String email, LocalDateTime time) {
        this.humidity = humidity;
        this.city = city;
        this.email = email;
        this.time = time;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}

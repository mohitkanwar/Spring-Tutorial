package com.mk.spring.demo;

import org.springframework.stereotype.Repository;


public class BookedPerson {
    private String name;
    private String bookingType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBookingType() {
        return bookingType;
    }

    public void setBookingType(String bookingType) {
        this.bookingType = bookingType;
    }
}

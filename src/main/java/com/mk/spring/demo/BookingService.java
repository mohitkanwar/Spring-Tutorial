package com.mk.spring.demo;

import java.util.List;

public interface BookingService {
    void book(List<String> names);

    List<String> findAllBookings();
}

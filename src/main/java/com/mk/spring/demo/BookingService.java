package com.mk.spring.demo;

import java.util.List;

public interface BookingService {
    void book(String... names);

    List<BookedPerson> findAllBookings();
}

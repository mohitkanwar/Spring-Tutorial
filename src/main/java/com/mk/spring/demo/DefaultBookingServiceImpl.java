package com.mk.spring.demo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Primary
public class DefaultBookingServiceImpl implements BookingService{

    private final static Logger logger = LoggerFactory.getLogger(DefaultBookingServiceImpl.class);

    private final JdbcTemplate jdbcTemplate;

    public DefaultBookingServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public void book(String... persons) {
        for (String person : persons) {
            if(!person.equals("")){
                logger.info("Booking " + person + " in a default seat...");
                jdbcTemplate.update("insert into BOOKINGS(FIRST_NAME,BOOKING_TYPE) values (?,?)", person,"DEFAULT");

            }
        }
    }

    public List<BookedPerson> findAllBookings() {
        return jdbcTemplate.query("select FIRST_NAME,BOOKING_TYPE from BOOKINGS",
                (rs, rowNum) -> {
            BookedPerson person = new BookedPerson();
                    person.setBookingType(rs.getString("BOOKING_TYPE"));
                    person.setName(rs.getString("FIRST_NAME"));
                    return person;

        });
    }

}
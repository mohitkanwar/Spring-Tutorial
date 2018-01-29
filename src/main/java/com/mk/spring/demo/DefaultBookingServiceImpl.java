package com.mk.spring.demo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DefaultBookingServiceImpl implements BookingService{

    private final static Logger logger = LoggerFactory.getLogger(DefaultBookingServiceImpl.class);

    private final JdbcTemplate jdbcTemplate;

    public DefaultBookingServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public void book(List<String> persons) {
        for (String person : persons) {
            if(!person.equals("")){
                logger.info("Booking " + person + " in a seat...");
                jdbcTemplate.update("insert into BOOKINGS(FIRST_NAME) values (?)", person);

            }
        }
    }

    public List<String> findAllBookings() {
        logger.info("Inside FindAll Bookings!!!");
        return jdbcTemplate.query("select FIRST_NAME from BOOKINGS",
                (rs, rowNum) -> rs.getString("FIRST_NAME"));
    }

}
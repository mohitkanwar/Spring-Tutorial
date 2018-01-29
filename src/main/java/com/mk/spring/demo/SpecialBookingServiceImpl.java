package com.mk.spring.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Qualifier("special")
public class SpecialBookingServiceImpl implements BookingService{

    private final static Logger logger = LoggerFactory.getLogger(SpecialBookingServiceImpl.class);

    private final JdbcTemplate jdbcTemplate;

    public SpecialBookingServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public void book(String... persons) {
        for (String person : persons) {
            if(!person.equals("")){
                logger.info("**** This is a Special Service ****");
                logger.info("Booking " + person + " in a special seat...");
                jdbcTemplate.update("insert into BOOKINGS(FIRST_NAME,BOOKING_TYPE) values (?,?)", person,"SPECIAL");
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
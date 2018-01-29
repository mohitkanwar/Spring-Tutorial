package com.mk.spring.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Component
class AppRunner implements CommandLineRunner {

    private final static Logger logger = LoggerFactory.getLogger(AppRunner.class);

    private final BookingService bookingService;

    public AppRunner(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @Override
    public void run(String... args) throws Exception {
        List<String> test = new ArrayList();
        test.add("Amit");
        test.add("Bobby");
        test.add("Charu");
        bookingService.book(test);
        Assert.isTrue(bookingService.findAllBookings().size() == 3,
                "First booking should work with no problem");
        logger.info("Amit, Bobby and Charu have been booked");
        try {
            List<String> test1 = new ArrayList();
            test1.add("Ajay");
            test1.add("Suneel");

            bookingService.book(test1);

        } catch (RuntimeException e) {
            logger.info("v--- The following exception is expected because 'Suneel' is too " +
                    "big for the DB ---v");
            logger.error(e.getMessage());
        }

        for (String person : bookingService.findAllBookings()) {
            logger.info("So far, " + person + " is booked.");
        }
        logger.info("You shouldn't see Ajay or Suneel. Suneel violated DB constraints, " +
                "and Ajay was rolled back in the same TX");
        Assert.isTrue(bookingService.findAllBookings().size() == 3, "'Suneel' should have triggered a rollback");

        try {
            List<String> test1 = new ArrayList();
            test1.add("Buddy");
            test1.add(null);

            bookingService.book(test1);
        } catch (RuntimeException e) {
            logger.info("v--- The following exception is expect because null is not " +
                    "valid for the DB ---v");
            logger.error(e.getMessage());
        }

        for (String person : bookingService.findAllBookings()) {
            logger.info("So far, " + person + " is booked.");
        }
        logger.info("You shouldn't see Buddy or null. null violated DB constraints, and " +
                "Buddy was rolled back in the same TX");
        Assert.isTrue(bookingService.findAllBookings().size() == 3, "'null' should have triggered a rollback");
    }

}
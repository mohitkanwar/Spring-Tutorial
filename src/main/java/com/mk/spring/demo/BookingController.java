package com.mk.spring.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by mkanwar on 06/09/17.
 */
@Controller
public class BookingController {
    @Autowired
    private BookingService service;
    @RequestMapping("/book")
    public String bookSeat(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        service.book(name.split(","));
        model.addAttribute("bookings", String.join(",",service.findAllBookings()));
        return "booking";
    }

    @RequestMapping("/bookedseates")
    public  String books(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {

        model.addAttribute("bookings", String.join(",",service.findAllBookings()));
        return "booking";
    }
}

package com.mk.spring.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mkanwar on 06/09/17.
 */
@Controller
public class BookingController {
    private final static Logger logger = LoggerFactory.getLogger(BookingController.class);

    @Autowired
    private BookingService service;

    @Autowired
    @Qualifier("special")
    private BookingService specialService;
    @RequestMapping(value = "/book" , method = { RequestMethod.POST  })
    public ModelAndView bookSeat(@RequestParam(value="name", required=false) String name,
                                 @RequestParam(value="isSpecial", required=false) String isSpecial,
                                 Model model) {
        String errorMessage=null;
        try {
            if("on".equalsIgnoreCase(isSpecial)){
                specialService.book(name);
            }
            else{
                service.book(name);
            }

        }
        catch (RuntimeException e){
            e.printStackTrace();
            logger.error(e.getMessage());

            if(e.getMessage().contains("Value too long for column")){
                errorMessage = "One of the names is too long, hence booking is not made!";
            }
            else{
                errorMessage = "Some unexpected exception occurred!";
            }

        }
        return new ModelAndView("redirect:/","errorMessage",errorMessage);

    }

    private void addNameIfNotEmpty(List<String>list, String name) {

        if (!name.equals("")){
            list.add(name);
        }

    }

    @RequestMapping("/")
    public  String books(@RequestParam(value="errorMessage", required=false) String errorMessage, Model model) {
        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("bookings", service.findAllBookings());
        return "booking";
    }
}

package com.web.finalproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TradeController {

    @GetMapping("/trade")
    public ModelAndView tradeList() {
        ModelAndView mav = new ModelAndView();

        return mav;
    }

}

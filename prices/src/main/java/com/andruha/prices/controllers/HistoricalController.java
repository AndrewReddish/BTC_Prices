package com.andruha.prices.controllers;

import com.andruha.prices.services.DataRequesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

//TODO change to jersey
//List of dependencies:
// 1)Spring -> no spring boot -> learn how context is created -> xml, java config and autowired
// 2)jersey
// 3)jackson
// 2)spring simple webapp -> helloworld
// 3)deploy on tomcat
@Controller
public class HistoricalController {

    @Autowired
    private DataRequesterService dataRequesterService;

    @GetMapping(value = "historical/{currency}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Double getHistoricalPrice(@PathVariable("currency") String currency) {
        return dataRequesterService.getMinimumHistoricalRate(currency);

    }
}

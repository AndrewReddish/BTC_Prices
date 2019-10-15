package com.andruha.prices;


import com.andruha.prices.dto.Bpi;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class Test {

    @org.junit.Test
    public void test() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Double> rates = new HashMap<>();
        rates.put("eur", 12.1212d);
        rates.put("eur2", 12.1223d);
        rates.put("eur3", 12.1225d);

        Bpi bpi = new Bpi();
        bpi.setBpi(rates);

        System.out.println(objectMapper.writeValueAsString(bpi));


    }
}

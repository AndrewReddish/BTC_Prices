package com.andruha.prices.services;

import com.andruha.prices.dto.Bpi;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
//TODO create dedicated generic service to fetch data; call it from here
public class DataRequesterService {

    //TODO move to config
    private static final String API_URL = "https://api.coindesk.com/v1/bpi/historical/close.json";
    private static final String CURRENCY_PARAM = "currency";

    //TODO learn how to properly register new converter -> do very last
    public class JavaScriptMessageConverter extends AbstractJackson2HttpMessageConverter {
        //Add a new mediatype converter
        private JavaScriptMessageConverter() {
            super(Jackson2ObjectMapperBuilder.json().build(), new MediaType("application", "javascript"));
        }
    }

    //TODO replace String with enum value
    public Double getMinimumHistoricalRate(String currency) {
        Map<String, String> variables = new HashMap<>();
        variables.put(CURRENCY_PARAM, currency);

        ///////////////////////////////////////TODO create dedicated generic service to fetch data; call it from here
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new JavaScriptMessageConverter());
        Bpi bpiResponse = restTemplate.getForObject(API_URL, Bpi.class, variables);
        /////////////////////////////////////
        return getMinimalHistoricalPriceFromBpi(bpiResponse);
    }
    public Double getMaxHistoricalRate(String currency) {
        Map<String, String> variables = new HashMap<>();
        variables.put(CURRENCY_PARAM, currency);

        ///////////////////////////////////////TODO create dedicated generic service to fetch data; call it from here
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new JavaScriptMessageConverter());
        Bpi bpiResponse = restTemplate.getForObject(API_URL, Bpi.class, variables);
        /////////////////////////////////////
        return getMaxHistoricalPriceFromBpi(bpiResponse);
    }

    private Double getMinimalHistoricalPriceFromBpi(Bpi bpi) { //TODO stream API
        return bpi.getBpi()
                .values()
                .stream()
                .min(Double::compareTo)
                .orElseThrow(() -> new IllegalArgumentException("Empty list ob bpis"));

    }
    private Double getMaxHistoricalPriceFromBpi(Bpi bpi) { //TODO stream API
        return bpi.getBpi()
                .values()
                .stream()
                .max(Double::compareTo)
                .orElseThrow(() -> new IllegalArgumentException("Empty list ob bpis"));

    }
}

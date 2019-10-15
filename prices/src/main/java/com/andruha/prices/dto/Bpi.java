package com.andruha.prices.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;

import java.util.Map;

@Data
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)  //TODO lombok
public class Bpi {
    private Map<String, Double> bpi;
}

package com.teanetworks.springjwt.models;

import lombok.Data;

@Data
public class WeatherDataFilter {
    Boolean isAll;
    String filterType;
    String filterVal;
}

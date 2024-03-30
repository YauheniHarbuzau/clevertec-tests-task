package ru.clevertec.product.constants;

import java.time.LocalDateTime;

import static java.time.Month.OCTOBER;

public class Constants {

    public static final LocalDateTime CREATION_DATE_TIME = LocalDateTime.of(2023, OCTOBER, 30, 12, 0, 0);

    public static final String PRODUCT_NAME_PATTERN = "^[А-яЁё\\s]{5,10}$";
    public static final String PRODUCT_DESCRIPTION_PATTERN = "^[А-яЁё\\s]{10,30}$";
}

package ru.clevertec.product.constants;

import java.time.LocalDateTime;
import java.util.UUID;

import static java.time.Month.OCTOBER;

public class Constants {

    public static final UUID UUID_1 = UUID.fromString("cb7f2552-a60d-4831-a5f2-ab1cb1262def");
    public static final UUID UUID_2 = UUID.fromString("165830f5-6fa0-483d-8487-49f718be5630");
    public static final UUID UUID_3 = UUID.fromString("8ad6415a-7175-46fd-8ff3-7f9ee459578c");
    public static final UUID UUID_4 = UUID.fromString("0fcaec59-033c-4039-8b90-1e9fbbbe2033");
    public static final UUID UUID_5 = UUID.fromString("f154378c-721d-499b-b760-333797ce3854");

    public static final LocalDateTime CREATION_DATE_TIME = LocalDateTime.of(2023, OCTOBER, 30, 12, 0, 0);

    public static final String PRODUCT_NAME_PATTERN = "^[А-яЁё\\s]{5,10}$";
    public static final String PRODUCT_DESCRIPTION_PATTERN = "^[А-яЁё\\s]{10,30}$";
}

package com.emaflores.price.controller;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
public class PriceControllerTests {

    @Autowired
    private MockMvc mockMvc;

    private static final String BASE_URL = "/getPrice";
    private static final String DATE_1 = "2020-06-14-10.00.00";
    private static final String DATE_2 = "2020-06-14-16.00.00";
    private static final String DATE_3 = "2020-06-14-21.00.00";
    private static final String DATE_4 = "2020-06-15-10.00.00";
    private static final String DATE_5 = "2020-06-16-21.00.00";

    private static final long BRAND_ID = 1;
    private static final long PRODUCT_ID = 35455;

    private static final String START_DATE_1 = "2020-06-14T00:00:00";
    private static final String END_DATE_1 = "2020-12-31T23:59:59";

    private static final String START_DATE_2 = "2020-06-14T15:00:00";
    private static final String END_DATE_2 = "2020-06-14T18:30:00";

    private static final String START_DATE_3 = "2020-06-15T00:00:00";
    private static final String END_DATE_3 = "2020-06-15T11:00:00";

    private static final String START_DATE_4 = "2020-06-15T16:00:00";
    private static final String END_DATE_4 = "2020-12-31T23:59:59";


    private static final double EXPECTED_PRICE_1 = 35.50;
    private static final double EXPECTED_PRICE_2 = 25.45;
    private static final double EXPECTED_PRICE_3 = 30.50;
    private static final double EXPECTED_PRICE_4 = 38.95;

    private static final long PRICE_LIST_ID_1 = 1;
    private static final long PRICE_LIST_ID_2 = 2;
    private static final long PRICE_LIST_ID_3 = 3;
    private static final long PRICE_LIST_ID_4 = 4;


    @Test
    public void shouldReturnPriceForValidDateAndProductId() throws Exception {
        testGetPrice(DATE_1, BRAND_ID, PRODUCT_ID, START_DATE_1, END_DATE_1, PRICE_LIST_ID_1, EXPECTED_PRICE_1);
        testGetPrice(DATE_2, BRAND_ID, PRODUCT_ID, START_DATE_2, END_DATE_2, PRICE_LIST_ID_2, EXPECTED_PRICE_2);
        testGetPrice(DATE_3, BRAND_ID, PRODUCT_ID, START_DATE_1, END_DATE_1, PRICE_LIST_ID_1, EXPECTED_PRICE_1);
        testGetPrice(DATE_4, BRAND_ID, PRODUCT_ID, START_DATE_3, END_DATE_3, PRICE_LIST_ID_3, EXPECTED_PRICE_3);
        testGetPrice(DATE_5, BRAND_ID, PRODUCT_ID, START_DATE_4, END_DATE_4, PRICE_LIST_ID_4, EXPECTED_PRICE_4);
    }

    private void testGetPrice(String date, long brandId, long productId, String startDate, String endDate, long priceList, double expectedPrice) throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL)
                        .param("date", date)
                        .param("productId", String.valueOf(productId))
                        .param("brandId", String.valueOf(brandId))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String jsonResponse = result.getResponse().getContentAsString();

        JSONObject jsonObject = new JSONObject(jsonResponse);
        assertEquals(brandId, jsonObject.getLong("brandId"));
        assertEquals(productId, jsonObject.getLong("productId"));
        assertEquals(startDate, jsonObject.getString("startDate"));
        assertEquals(endDate, jsonObject.getString("endDate"));
        assertEquals(priceList, jsonObject.getLong("priceList"));
        assertEquals(expectedPrice, jsonObject.getDouble("price"), 0.01);
    }
}


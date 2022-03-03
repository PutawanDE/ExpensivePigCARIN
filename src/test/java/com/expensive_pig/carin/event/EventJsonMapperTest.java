package com.expensive_pig.carin.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

public class EventJsonMapperTest {
    @Test
    public void MoveEventJsonMapTest_ShouldPass() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = "{\"eventType\":\"input\", \"action\":\"move\"," +
                "\"oldPos\": [50,50], \"newPos\":[1,5]}";
        InputMoveEvent actual = objectMapper.readValue(json, InputMoveEvent.class);
        System.out.println();
    }

    @Test
    public void BuyEventJsonMapTest_ShouldPass() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = "{\"kind\": 1,\"pos\": [50,50]}";
        BuyEvent actual = objectMapper.readValue(json, BuyEvent.class);
        System.out.println();
    }
}

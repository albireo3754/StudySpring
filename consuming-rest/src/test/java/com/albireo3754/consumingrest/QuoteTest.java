package com.albireo3754.consumingrest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import static org.junit.jupiter.api.Assertions.*;

class QuoteTest {
    // ObjectMapper -> DefaultDeserialzationContext -> BeanDeserializer(체크 1) -> Base -> DeserializationContext(context 체크2)

    public static class Quote3 {
        public String type;
        public Value value;

        public Quote3() {
        }

        public Quote3(String type, Value value) {
            this.type = type;
            this.value = value;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Value getValue() {
            return value;
        }

        public void setValue(Value value) {
            this.value = value;
        }
    }
    @Test
    public void convertObjectMapperWithJsonString() throws JsonProcessingException {
        String json = "{\"type\":\"success\",\"value3\":{\"id\":10,\"quote\":\"Really loving Spring Boot, makes stand alone Spring apps easy.\"}, \"unknown\":\"Unknown\"}";
        var quoteResult = new Jackson2ObjectMapperBuilder().failOnUnknownProperties(false).build().readValue(json,  Quote3.class);
        var a = new Jackson2ObjectMapperBuilder().build();

        assertEquals("success", quoteResult.type);

        Quote3 quoteResult3 = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).readValue(json, Quote3.class);
        assertEquals("success", quoteResult3.type);

        // 이건 에러가 남
//        Quote quoteResult2 = new ObjectMapper().readValue(json, Quote.class);
//        assertEquals("success", quoteResult2.type);

    }
}
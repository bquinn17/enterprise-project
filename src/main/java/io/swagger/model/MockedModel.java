package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MockedModel {
    @JsonProperty("model")
    private String name;

    @JsonProperty("quantity")
    private int quantity;

    @JsonProperty("description")
    private String description;

    public MockedModel(String name, int quantity, String description){
        this.name = name;
        this.quantity = quantity;
        this.description = description;
    }
}

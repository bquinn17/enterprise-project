package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MockedCredentials {

    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;

    public MockedCredentials(String username, String password){
        this.username = username;
        this.password = password;
    }
}

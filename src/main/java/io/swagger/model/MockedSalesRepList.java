package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Response wrapper for the SalesRep data coming to Sales UI from HR.
 * This wraps the list of SalesReps in an array called "data".
 *
 * @author Brendan Jones, bpj1651@rit.edu, GitHub: brendanjones44
 */
public class MockedSalesRepList {

    @JsonProperty("data")
    private List<MockedSalesRep> reps;

    /**
     * Constructor for MockedSalesRepList.
     * @param reps - the List<MockedSalesRep> to wrap
     */
    public MockedSalesRepList(List<MockedSalesRep> reps){
        this.reps = reps;
    }
}

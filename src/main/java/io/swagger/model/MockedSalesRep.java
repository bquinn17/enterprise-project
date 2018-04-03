package io.swagger.model;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * MockedSalesRep represents the model that gets sent to the Sales UI from HR.
 *
 * @author Brendan Jones, bpj1651@rit.edu, GitHub: brendanjones44
 */
public class MockedSalesRep {

    @JsonProperty("id")
    private int id;

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("dateOfBirth")
    private Date dateOfBirth;

    @JsonProperty("regionName")
    private String regionName;

    @JsonProperty("roleName")
    private String roleName;

    @JsonProperty("departmentName")
    private String departmentName;

    @JsonProperty("positionName")
    private String positionName;

    /**
     * Constructor for created MockedSalesReps
     * @param id - String to set the id to.
     * @param firstName - String to set the firstName to.
     * @param lastName - String to set the lastName to.
     * @param regionName - String to set the regionName to.
     */
    public MockedSalesRep(int id, String firstName, String lastName, String regionName){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = new Date();
        this.regionName = regionName;
        this.roleName = "Employee";
        this.departmentName = "Sales";
        this.positionName = "SalesRep";
    }
}

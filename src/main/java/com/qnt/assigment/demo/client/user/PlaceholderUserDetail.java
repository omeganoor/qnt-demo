package com.qnt.assigment.demo.client.user;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PlaceholderUserDetail {
    public int id;
    public String firstname;
    public String lastname;
    public String email;
    public String birthDate;
    public Login login;
    public Address address;
    public String phone;
    public String website;
    public Company company;
    public long executionTime;
    private long concurrentApiExecutionTime;

    public void setConcurrentApiExecutionTime (long concurrentApiExecutionTime) {
        this.concurrentApiExecutionTime = concurrentApiExecutionTime;
    }

    public long getConcurrentApiExecutionTime () {
        return concurrentApiExecutionTime;
    }
}
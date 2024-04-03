package com.qnt.assigment.demo.client.user;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Address {

  public String street;
  public String suite;
  public String city;
  public String zipcode;
  public Geo geo;
}
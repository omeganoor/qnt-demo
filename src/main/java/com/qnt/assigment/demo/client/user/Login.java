package com.qnt.assigment.demo.client.user;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class Login {
  public String uuid;
  public String username;
  public String password;
  public String md5;
  public String sha1;
  public Date registered;
}
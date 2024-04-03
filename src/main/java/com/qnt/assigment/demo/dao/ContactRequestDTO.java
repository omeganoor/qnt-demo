package com.qnt.assigment.demo.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactRequestDTO {

    private Long id;

    private String name;

    private String email;

    private String phone;

    private String address;

}
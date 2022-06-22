package com.nttdata.apirestcustomers.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "customers")
public class Customer {

    @Id
    private String id;
    @Field(name = "name")
    private String name;
    @Field(name = "address")
    private String address;
    @Field(name = "email")
    private String email;
    @Field(name = "phone")
    private String phone;
    @Field(name = "numberDocument")
    private String numberDocument;
    @Field(name = "customerType")
    private String customerType;
}

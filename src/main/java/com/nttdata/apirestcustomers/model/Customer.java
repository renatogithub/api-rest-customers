/**
 * Bean Stores Customer Information
 *
 * @author Renato Ponce
 * @version 1.0
 * @since 2022-06-24
 */

package com.nttdata.apirestcustomers.model;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @Field(name = "dateBirthDay")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private String dateBirthDay;
}

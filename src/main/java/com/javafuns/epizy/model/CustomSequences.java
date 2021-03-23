package com.javafuns.epizy.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "customSequences")
@Data
public class CustomSequences {
    @Id
    private String id;
    private int seq;
    //https://stackoverflow.com/questions/36448921/how-can-we-create-auto-generated-field-for-mongodb-using-spring-boot
}
package com.practice.springbootmysql.entity;

import jakarta.persistence.Entity;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Entity
public class Provider {

    @Id
    private String ncpdpProviderId;
    private String npi;
    private String name;

}

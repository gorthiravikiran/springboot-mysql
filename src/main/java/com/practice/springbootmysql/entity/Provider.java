package com.practice.springbootmysql.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;

@Data
@Entity
public class Provider {

    @Id
    private String ncpdpProviderId;
    private String npi;
    private String name;

}

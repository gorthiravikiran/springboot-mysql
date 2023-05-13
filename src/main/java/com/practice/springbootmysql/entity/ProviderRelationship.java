package com.practice.springbootmysql.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.IdClass;

@Data
@Entity
@IdClass(ProviderRelationshipId.class)
public class ProviderRelationship {

    private ProviderRelationshipId providerRelationshipId;
    private String npi;
    private String name;
    private String effectiveStartDate;
    private String effectiveEndDate;

}

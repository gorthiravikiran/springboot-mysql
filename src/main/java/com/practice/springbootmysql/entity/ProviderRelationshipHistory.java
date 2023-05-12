package com.practice.springbootmysql.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.IdClass;
import lombok.Data;

@Data
@Entity
@IdClass(ProviderRelationshipId.class)
public class ProviderRelationshipHistory {

    private ProviderRelationshipId providerRelationshipId;
    private String npi;
    private String name;
    private String effectiveStartDate;
    private String effectiveEndDate;

}

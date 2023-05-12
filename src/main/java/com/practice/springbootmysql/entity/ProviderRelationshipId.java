package com.practice.springbootmysql.entity;

import lombok.Data;

@Data
public class ProviderRelationshipId {

    private String ncpdpProviderId;
    private String relationshipId;
    private String paymentCenterId;
    private String remitReconciliationId;

}

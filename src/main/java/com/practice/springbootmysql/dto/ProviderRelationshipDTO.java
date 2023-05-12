package com.practice.springbootmysql.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProviderRelationshipDTO {

    private String ncpdpProviderId;
    private String relationshipId;
    private String paymentCenterId;
    private String remitReconciliationId;
    private String npi;
    private String name;
    private String effectiveStartDate;
    private String effectiveEndDate;

}

package com.practice.springbootmysql.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProviderDTO {

    private String ncpdpProviderId;
    private String npi;
    private String name;

}

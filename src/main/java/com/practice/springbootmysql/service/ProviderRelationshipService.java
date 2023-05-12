package com.practice.springbootmysql.service;

import com.practice.springbootmysql.dto.ProviderRelationshipDTO;
import com.practice.springbootmysql.entity.ProviderRelationship;
import com.practice.springbootmysql.entity.ProviderRelationshipHistory;
import com.practice.springbootmysql.entity.ProviderRelationshipId;
import com.practice.springbootmysql.repository.ProviderRelationshipHistoryRepository;
import com.practice.springbootmysql.repository.ProviderRelationshipRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProviderRelationshipService {

    @Autowired
    private ProviderRelationshipRepository providerRelationshipRepository;

    @Autowired
    private ProviderRelationshipHistoryRepository providerRelationshipHistoryRepository;

    public List<ProviderRelationshipDTO> getAllProviderRelationships(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return getMappedProviderList(providerRelationshipRepository.findAll(pageRequest));
    }

    public List<ProviderRelationshipDTO> getProviderRelationshipsByRelationshipId(String relationshipId,
                                                                                  int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        Page<ProviderRelationship> providerRelationships = providerRelationshipRepository.getProviderRelationshipsByRelationshipId(relationshipId, pageRequest);
        return getMappedProviderList(providerRelationships);
    }

    private List<ProviderRelationshipDTO> getMappedProviderList(Page<ProviderRelationship> providerRelationships) {
        List<ProviderRelationshipDTO> providerRelationshipDTOS = new ArrayList<>();
        for (ProviderRelationship providerRelationship : providerRelationships) {
            ProviderRelationshipDTO providerRelationshipDTO = ProviderRelationshipDTO.builder()
                    .ncpdpProviderId(providerRelationship.getProviderRelationshipId().getNcpdpProviderId())
                    .npi(providerRelationship.getNpi())
                    .name(providerRelationship.getName())
                    .relationshipId(providerRelationship.getProviderRelationshipId().getRelationshipId())
                    .remitReconciliationId(providerRelationship.getProviderRelationshipId().getRemitReconciliationId())
                    .paymentCenterId(providerRelationship.getProviderRelationshipId().getPaymentCenterId())
                    .effectiveStartDate(providerRelationship.getEffectiveStartDate())
                    .effectiveEndDate(providerRelationship.getEffectiveEndDate())
                    .build();
            providerRelationshipDTOS.add(providerRelationshipDTO);
        }
        return providerRelationshipDTOS;
    }

    public String addOrUpdateNewProviderRelationship(ProviderRelationshipDTO providerRelationshipDTO) {
        ProviderRelationshipId providerRelationshipId = new ProviderRelationshipId();
        providerRelationshipId.setRelationshipId(providerRelationshipDTO.getRelationshipId());
        providerRelationshipId.setNcpdpProviderId(providerRelationshipDTO.getNcpdpProviderId());
        providerRelationshipId.setPaymentCenterId(providerRelationshipDTO.getPaymentCenterId());
        providerRelationshipId.setRemitReconciliationId(providerRelationshipDTO.getRemitReconciliationId());

        ProviderRelationship incomingItem = new ProviderRelationship();
        incomingItem.setProviderRelationshipId(providerRelationshipId);
        incomingItem.setNpi(providerRelationshipDTO.getNpi());
        incomingItem.setName(providerRelationshipDTO.getName());
        incomingItem.setEffectiveStartDate(providerRelationshipDTO.getEffectiveStartDate());
        incomingItem.setEffectiveEndDate(providerRelationshipDTO.getEffectiveEndDate());
        ProviderRelationship existingItem = providerRelationshipRepository.findProviderRelationshipDTOById(providerRelationshipDTO.getRelationshipId());
        String result = null;
        try {
            if (!existingItem.equals(incomingItem)) {
                providerRelationshipRepository.save(incomingItem);
                providerRelationshipHistoryRepository.save(existingItem);
            }
            result = "Added New ProviderRelationship Successfully.";
        } catch (Exception e) {
            result = String.format("Failed to Add New ProviderRelationship.  Exception: %s", e);
        }
        return result;
    }

    public List<ProviderRelationshipDTO> getAllProviderRelationshipHistoryList(String relationshipId) {
        return getMappedProviderHistoryList(providerRelationshipHistoryRepository.getProviderRelationshipHistoriesByRelationshipId(relationshipId));
    }

    private List<ProviderRelationshipDTO> getMappedProviderHistoryList(List<ProviderRelationshipHistory> providerRelationshipHistoryList) {
        List<ProviderRelationshipDTO> providerRelationshipDTOS = new ArrayList<>();
        for (ProviderRelationshipHistory providerRelationship : providerRelationshipHistoryList) {
            ProviderRelationshipDTO providerRelationshipDTO = ProviderRelationshipDTO.builder()
                    .ncpdpProviderId(providerRelationship.getProviderRelationshipId().getNcpdpProviderId())
                    .npi(providerRelationship.getNpi())
                    .name(providerRelationship.getName())
                    .relationshipId(providerRelationship.getProviderRelationshipId().getRelationshipId())
                    .remitReconciliationId(providerRelationship.getProviderRelationshipId().getRemitReconciliationId())
                    .paymentCenterId(providerRelationship.getProviderRelationshipId().getPaymentCenterId())
                    .effectiveStartDate(providerRelationship.getEffectiveStartDate())
                    .effectiveEndDate(providerRelationship.getEffectiveEndDate())
                    .build();
            providerRelationshipDTOS.add(providerRelationshipDTO);
        }
        return providerRelationshipDTOS;
    }
}

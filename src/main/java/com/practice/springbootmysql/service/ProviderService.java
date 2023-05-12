package com.practice.springbootmysql.service;

import com.practice.springbootmysql.dto.ProviderDTO;
import com.practice.springbootmysql.entity.Provider;
import com.practice.springbootmysql.repository.ProviderRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProviderService {

    @Autowired
    private ProviderRepository providerRepository;

    public List<ProviderDTO> getAllProviders() {
        return getMappedProviderList(providerRepository.findAll());
    }

    public List<ProviderDTO> getProviderByCriteria(String searchCriteria, String searchValue) {
        if (searchCriteria.equalsIgnoreCase("ncpdpProviderId")) {
            return getProviderByNcpdpId(searchValue);
        }
        return getProviderByNpi(searchValue);
    }

    private List<ProviderDTO> getProviderByNcpdpId(String ncpdpProviderId) {
        List<Provider> providers = new ArrayList<>();
        Optional<Provider> optional = providerRepository.findById(ncpdpProviderId);
        optional.ifPresent(providers::add);
        return getMappedProviderList(providers);
    }

    private List<ProviderDTO> getProviderByNpi(String npi) {
        List<Provider> providers = new ArrayList<>();
        Provider provider = providerRepository.findByNpi(npi);
        if (provider != null) {
            providers.add(provider);
        }
        return getMappedProviderList(providers);
    }

    private List<ProviderDTO> getMappedProviderList(List<Provider> providers) {
        List<ProviderDTO> providerDTOS = new ArrayList<>();
        for (Provider provider : providers) {
            ProviderDTO providerDTO = ProviderDTO.builder()
                    .ncpdpProviderId(provider.getNcpdpProviderId())
                    .npi(provider.getNpi())
                    .name(provider.getName())
                    .build();
            providerDTOS.add(providerDTO);
        }
        return providerDTOS;
    }

}

package com.practice.springbootmysql.controller;

import com.practice.springbootmysql.dto.ProviderDTO;
import com.practice.springbootmysql.service.ProviderService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@RequestMapping("/provider")
public class ProviderController {

    @Autowired
    private ProviderService providerService;

    @GetMapping(value = "/all")
    public List<ProviderDTO> getAllProviders() {
        return providerService.getAllProviders();
    }

    @GetMapping(value = "/{searchCriteria}/{searchValue}")
    public List<ProviderDTO> getAllProviders(@PathVariable String searchCriteria, @PathVariable String searchValue) {
        return providerService.getProviderByCriteria(searchCriteria, searchValue);
    }
}

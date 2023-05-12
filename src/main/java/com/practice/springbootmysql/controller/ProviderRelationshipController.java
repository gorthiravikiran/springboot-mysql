package com.practice.springbootmysql.controller;

import com.practice.springbootmysql.dto.ProviderRelationshipDTO;
import com.practice.springbootmysql.service.ProviderRelationshipService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@RequestMapping("/provider-relationship")
public class ProviderRelationshipController {

    @Autowired
    private ProviderRelationshipService providerRelationshipService;

    @GetMapping(value = "/all")
    public List<ProviderRelationshipDTO> getAllProviderRelationships(@RequestParam int pageNumber,
                                                                     @RequestParam int pageSize) {
        return providerRelationshipService.getAllProviderRelationships(pageNumber, pageSize);
    }

    @GetMapping(value = "/relationshipId/{searchValue}")
    public List<ProviderRelationshipDTO> getProviderRelationshipsByRelationshipId(@PathVariable String searchValue,
                                             @RequestParam int pageNumber,
                                             @RequestParam int pageSize) {
        return providerRelationshipService.getProviderRelationshipsByRelationshipId(searchValue, pageNumber, pageSize);
    }

    @PostMapping(value = "/add")
    public String addNewProviderRelationship(@RequestBody ProviderRelationshipDTO providerRelationshipDTO) {
        return providerRelationshipService.addOrUpdateNewProviderRelationship(providerRelationshipDTO);
    }

    @GetMapping(value = "/history/{relationshipId}")
    public List<ProviderRelationshipDTO> getAllProviderRelationships(@PathVariable String relationshipId) {
        return providerRelationshipService.getAllProviderRelationshipHistoryList(relationshipId);
    }

}

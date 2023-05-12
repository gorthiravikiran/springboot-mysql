package com.practice.springbootmysql.repository;

import com.practice.springbootmysql.entity.ProviderRelationship;
import com.practice.springbootmysql.entity.ProviderRelationshipId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderRelationshipRepository extends JpaRepository<ProviderRelationship, ProviderRelationshipId>,
        CrudRepository<ProviderRelationship, ProviderRelationshipId>, PagingAndSortingRepository<ProviderRelationship, ProviderRelationshipId> {

    @Query(value = "SELECT * FROM provider_relationship pr WHERE (str_to_date(effective_end_date, '%m%d%Y') " +
            "or effective_end_date is null or effective_end_date = '' or effective_end_date = '00000000') and " +
            "relationship_id = %?1", nativeQuery = true)
    Page<ProviderRelationship> getProviderRelationshipsByRelationshipId(String relationshipId, PageRequest pageRequest);

    @Query("SELECT * FROM providerRelationship pr WHERE relationshipId = %?1")
    ProviderRelationship findProviderRelationshipDTOById(String relationshipId);

}

package com.practice.springbootmysql.repository;

import com.practice.springbootmysql.entity.ProviderRelationship;
import com.practice.springbootmysql.entity.ProviderRelationshipHistory;
import com.practice.springbootmysql.entity.ProviderRelationshipId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProviderRelationshipHistoryRepository extends JpaRepository<ProviderRelationship, ProviderRelationshipId> {

    @Query("SELECT * FROM providerRelationshipHistory pr WHERE relationshipId = %?1")
    List<ProviderRelationshipHistory> getProviderRelationshipHistoriesByRelationshipId(String relationshipId);

}

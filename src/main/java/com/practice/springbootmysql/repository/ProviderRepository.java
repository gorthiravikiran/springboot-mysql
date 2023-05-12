package com.practice.springbootmysql.repository;

import com.practice.springbootmysql.entity.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, String> {

    @Query("select p from providerRepository p where p.npi=%?1")
    Provider findByNpi(String npi);
}

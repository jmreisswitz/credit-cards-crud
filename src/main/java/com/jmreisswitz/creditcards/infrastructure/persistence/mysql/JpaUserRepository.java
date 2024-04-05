package com.jmreisswitz.creditcards.infrastructure.persistence.mysql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaUserRepository extends JpaRepository<JpaUser, Integer> {

    JpaUser findByUsername(String username);

}

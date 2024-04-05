package com.jmreisswitz.creditcards.infrastructure.persistence.jpa.jparepository;

import com.jmreisswitz.creditcards.infrastructure.persistence.jpa.entity.JpaUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaUserRepository extends JpaRepository<JpaUser, Integer> {

    JpaUser findByUsername(String username);

}

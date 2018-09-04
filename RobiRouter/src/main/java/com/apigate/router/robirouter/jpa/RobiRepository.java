package com.apigate.router.robirouter.jpa;

import com.apigate.router.robirouter.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface RobiRepository extends JpaRepository<Profile, Long> {

    @Query(value = "SELECT * FROM PROFILE WHERE USERNAME = ?1", nativeQuery = true)
    Profile findByUsername(String username);

}

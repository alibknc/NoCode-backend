package com.kou.bitirme.data.repository;

import com.kou.bitirme.data.entity.Endpoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EndpointRepository extends JpaRepository<Endpoint, Long> {

    @Query("select e from Endpoint e where e.projectId = ?1 and e.url = ?2")
    Endpoint getEndpointByProjectIdAndKey(String projectId, String url);

}

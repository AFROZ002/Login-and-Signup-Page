


package com.useraccess.repository;

import com.useraccess.entity.AccessRequest;
import com.useraccess.entity.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AccessRequestRepository extends JpaRepository<AccessRequest, Long> {
    List<AccessRequest> findByStatus(RequestStatus status);
    List<AccessRequest> findByUserId(Long userId);
}
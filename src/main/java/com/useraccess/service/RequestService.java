package com.useraccess.service;

import com.useraccess.dto.RequestDto;
import com.useraccess.entity.AccessRequest;
import com.useraccess.entity.RequestStatus;
import com.useraccess.entity.Software;
import com.useraccess.entity.User;
import com.useraccess.repository.AccessRequestRepository;
import com.useraccess.repository.SoftwareRepository;
import com.useraccess.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestService {
    private final AccessRequestRepository requestRepository;
    private final UserRepository userRepository;
    private final SoftwareRepository softwareRepository;

    public AccessRequest createRequest(RequestDto requestDto, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Software software = softwareRepository.findById(requestDto.getSoftwareId())
                .orElseThrow(() -> new RuntimeException("Software not found"));

        AccessRequest request = new AccessRequest();
        request.setUser(user);
        request.setSoftware(software);
        request.setAccessType(requestDto.getAccessType());
        request.setReason(requestDto.getReason());
        request.setStatus(RequestStatus.PENDING);

        return requestRepository.save(request);
    }

    public List<AccessRequest> getPendingRequests() {
        return requestRepository.findByStatus(RequestStatus.PENDING);
    }

    public AccessRequest updateRequestStatus(Long requestId, RequestStatus status) {
        AccessRequest request = requestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));
        request.setStatus(status);
        return requestRepository.save(request);
    }
}
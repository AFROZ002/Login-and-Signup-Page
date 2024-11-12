package com.useraccess.service;

import com.useraccess.dto.SoftwareDto;
import com.useraccess.entity.Software;
import com.useraccess.repository.SoftwareRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SoftwareService {
    private final SoftwareRepository softwareRepository;

    public Software createSoftware(SoftwareDto softwareDto) {
        Software software = new Software();
        software.setName(softwareDto.getName());
        software.setDescription(softwareDto.getDescription());
        software.setAccessLevels(String.join(",", softwareDto.getAccessLevels()));

        return softwareRepository.save(software);
    }

    public List<Software> getAllSoftware() {
        return softwareRepository.findAll();
    }
}
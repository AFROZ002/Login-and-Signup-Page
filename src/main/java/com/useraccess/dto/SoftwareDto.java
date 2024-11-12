package com.useraccess.dto;
import lombok.Data;

@Data
public class SoftwareDto {
    private String name;
    private String description;
    private String[] accessLevels;
}
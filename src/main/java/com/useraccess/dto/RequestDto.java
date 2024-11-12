package com.useraccess.dto;
import lombok.Data;
@Data
public class RequestDto {
    private Long softwareId;
    private String accessType;
    private String reason;
}
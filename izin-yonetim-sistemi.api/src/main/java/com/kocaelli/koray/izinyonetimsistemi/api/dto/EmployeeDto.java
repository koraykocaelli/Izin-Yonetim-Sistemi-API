package com.kocaelli.koray.izinyonetimsistemi.api.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class EmployeeDto {
    private String firstName;
    private String lastName;
    private String email;
    private String department;
    private int dayOff = 15; // Default/Kalan izin günleri

    private int usedDayOff; // Kullanılan izin günleri
}

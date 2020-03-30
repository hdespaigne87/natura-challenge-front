package com.natura.challenge.front.nurseapp.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patient {

    private String id;
    private String dni;
    private String name;
    private Integer age;
}

package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Airplane {
    private int id;
    private String model;
    private String serialNumber;
    private int seats;
}

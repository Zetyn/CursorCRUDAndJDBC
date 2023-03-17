package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Pilot {
    private int id;
    private String name;
    private short age;
}

package com.example.redismessagingsubscriber.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Cliente {

    private String id;
    private String nombre;
    private String apellidos;

}

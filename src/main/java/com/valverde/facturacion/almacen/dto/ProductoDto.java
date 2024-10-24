package com.valverde.facturacion.almacen.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductoDto {
    private int id;
    private String nombre;
    private String despricion;
    private int stock;
    private double precio;
}

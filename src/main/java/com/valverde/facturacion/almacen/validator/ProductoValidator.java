package com.valverde.facturacion.almacen.validator;

import com.valverde.facturacion.almacen.entity.Producto;
import com.valverde.facturacion.almacen.exception.ValidateException;

public class ProductoValidator {
    public static void save(Producto producto) {
        if (producto.getNombre() == null || producto.getNombre().isEmpty()) {
            throw new ValidateException("El nombre del producto no puede ser nulo o vacío.");
        }
        if (producto.getDescripcion() == null || producto.getDescripcion().isEmpty()) {
            throw new ValidateException("La descripción del producto no puede ser nula o vacía.");
        }

        if (producto.getPrecio()<=0) {
            throw new ValidateException("El precio del producto no puede ser menor a 0.");
        }
        if (producto.getStock()<=0) {
            throw new ValidateException("El stock del producto no puede ser menor a 0.");
            
        }
        if (producto.getNombre().length() >70) {
            throw new ValidateException("El nombre no debe exceder los 70 caracteres");
            
        }
    }
}

package com.valverde.facturacion.almacen.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.valverde.facturacion.almacen.entity.Producto;
import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer>{
    List<Producto> findByNombreContaining(String nombre, Pageable pageable);

}

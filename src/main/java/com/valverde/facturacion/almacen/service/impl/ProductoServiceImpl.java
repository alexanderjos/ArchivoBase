package com.valverde.facturacion.almacen.service.impl;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.valverde.facturacion.almacen.entity.Producto;
import com.valverde.facturacion.almacen.exception.GeneralException;
import com.valverde.facturacion.almacen.exception.NoDataFoundException;
import com.valverde.facturacion.almacen.exception.ValidateException;
import com.valverde.facturacion.almacen.repository.ProductoRepository;
import com.valverde.facturacion.almacen.service.ProductoService;
import com.valverde.facturacion.almacen.validator.ProductoValidator;

@Service
public class ProductoServiceImpl  implements ProductoService{

    @Autowired
    private ProductoRepository repository;
    
    @Override
    @Transactional(readOnly = true)
    public List<Producto> findAll(Pageable page) {
        try {
            List<Producto> registros = repository.findAll(page).toList();
            return registros;
        } catch (ValidateException | NoDataFoundException e) {
            throw e;
        } catch (GeneralException e) {
            throw new GeneralException("Error del servidorr");
        }
    }

    @Override
    public List<Producto> findAll() {
        try {
            List<Producto> registros = repository.findAll();
            return registros;
        } catch (ValidateException | NoDataFoundException e) {
            throw e;
        } catch (GeneralException e) {
            throw new GeneralException("Error del servidorr");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Producto> findByNombre(String nombre, Pageable page) {
        try {
            List<Producto> registros = repository.findByNombreContaining(nombre, page);
            return registros;
        } catch (ValidateException | NoDataFoundException e) {
            throw e;
        } catch (GeneralException e) {
            throw new GeneralException("Error del servidor");
        }
    }

    @Override
    public Producto findById(int id) {
        try {
            Producto registro = repository.findById(id)
                    .orElseThrow(() -> new NoDataFoundException("No existe un registro como ese id"));
            return registro;
        } catch (ValidateException | NoDataFoundException e) {
            throw e;
        } catch (GeneralException e) {
            throw new GeneralException("Error del servidor");
        }
    }

    @Override
    @Transactional
    public Producto save(Producto producto) {
        try {
            ProductoValidator.save(producto);

            if(producto.getId() == 0) {
                Producto nuevo = repository.save(producto);
                return nuevo;
            }

            Producto registro = repository.findById(producto.getId())
                    .orElseThrow(() -> new NoDataFoundException("No existe un registro como ese id"));
            registro.setNombre(producto.getNombre());
            registro.setDescripcion(producto.getDescripcion());
            registro.setPrecio(producto.getPrecio());
            registro.setStock(producto.getStock());
            repository.save(registro);

            return registro;
        } catch (ValidateException | NoDataFoundException e) {
            throw e;
        } catch (GeneralException e) {
            throw new GeneralException("Error del servidor");
        }
    }

    @Override
    @Transactional
    public void delete(int id) {
        try {
            Producto registro = repository.findById(id)
                    .orElseThrow(() -> new NoDataFoundException("No existe un registro como ese id"));
            repository.delete(registro);
        } catch (ValidateException | NoDataFoundException e) {
            throw e;
        } catch (GeneralException e) {
            throw new GeneralException("Error del servidor");
        }
    }

}

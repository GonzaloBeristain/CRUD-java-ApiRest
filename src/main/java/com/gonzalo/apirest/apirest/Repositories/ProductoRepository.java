package com.gonzalo.apirest.apirest.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gonzalo.apirest.apirest.Entities.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {  //Se agrega Long pq es el tipo de dato de la ID.

}

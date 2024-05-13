package com.gonzalo.apirest.apirest.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gonzalo.apirest.apirest.Repositories.ProductoRepository;
import com.gonzalo.apirest.apirest.Entities.Producto;

@RestController //Estrutura para CRUD
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository; //Conecta con la base de datos

    @GetMapping
    public List<Producto> getAllProductos(){
        return productoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Producto getProductoById(@PathVariable Long id){
        return productoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontró el producto de ID: " + id)); //La función flecha se hace con un guión.
    }

    @PostMapping
    public Producto createProducto(@RequestBody Producto producto){
        return productoRepository.save(producto); //Save es para grabar el producto
    }

    @PutMapping("/{id}")
    public Producto updateProducto(@PathVariable Long id, @RequestBody Producto productoUpdate){
        Producto producto =  productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el producto de ID: " + id));

        producto.setNombre(productoUpdate.getNombre());
        producto.setPrecio(productoUpdate.getPrecio());

        return productoRepository.save(producto);
    }

    @DeleteMapping("/{id}")
    public String deleteProducto(@PathVariable Long id){
        Producto producto =  productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el producto de ID: " + id)); //Buscar producto

        productoRepository.delete(producto);
        return "El producto con ID: " + id + " fue eliminado correctamente";
    }

}

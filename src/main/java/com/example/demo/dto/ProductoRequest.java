package com.example.demo.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;

public class ProductoRequest {
    @NotBlank(message = "no debe estar vacio")
    private String nombre;

    @NotBlank(message = "no debe estar vacio")
    private String categoria;

    @Min(value = 0, message = "debe ser mayor o igual a 0")
    private Integer stock;

    @DecimalMin(value = "0.01", message = "debe ser mayor o igual a 0.01")
    private BigDecimal precio;

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }

    public BigDecimal getPrecio() { return precio; }
    public void setPrecio(BigDecimal precio) { this.precio = precio; }
}

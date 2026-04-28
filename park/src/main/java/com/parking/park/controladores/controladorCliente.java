package com.parking.park.controladores;

import com.parking.park.modelos.Vehiculo;
import com.parking.park.repositorios.VehiculoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cliente/vehiculos")
@RequiredArgsConstructor
@Tag(name = "Cliente", description = "Consulta de vehículos")
public class controladorCliente {

    private final VehiculoRepository vehiculoRepo;

    @Operation(summary = "Ver lista de vehículos")
    @GetMapping
    public List<Vehiculo> listar() {
        return vehiculoRepo.findAll();
    }

    @Operation(summary = "Ver detalle de vehículo")
    @GetMapping("/{id}")
    public ResponseEntity<Vehiculo> obtener(@PathVariable Long id) {
        return vehiculoRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

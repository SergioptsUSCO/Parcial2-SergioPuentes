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
@RequestMapping("/acomodador/vehiculos")
@RequiredArgsConstructor
@Tag(name = "Acomodador", description = "Gestión de ubicaciones")
public class controladorAcomodador {

    private final VehiculoRepository vehiculoRepo;

    @Operation(summary = "Ver vehículos")
    @GetMapping
    public List<Vehiculo> listar() {
        return vehiculoRepo.findAll();
    }

    @Operation(summary = "Actualizar ubicación del vehículo")
    @PatchMapping("/{id}/ubicacion")
    public ResponseEntity<Vehiculo> actualizarUbicacion(
            @PathVariable Long id,
            @RequestBody String ubicacion) {

        return vehiculoRepo.findById(id).map(v -> {
            v.setUbicacion(ubicacion);
            return ResponseEntity.ok(vehiculoRepo.save(v));
        }).orElse(ResponseEntity.notFound().build());
    }
}

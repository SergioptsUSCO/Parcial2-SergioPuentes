package com.parking.park.controladores;

import com.parking.park.modelos.Vehiculo;
import com.parking.park.repositorios.TipoVehiculoRepository;
import com.parking.park.repositorios.VehiculoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/vehiculos")
@RequiredArgsConstructor
@Tag(name = "Administrador", description = "Gestión completa de vehículos")
public class controladorAdministrador {

    private final VehiculoRepository vehiculoRepo;
    @SuppressWarnings("unused")
    private final TipoVehiculoRepository tipoRepo;

    @Operation(summary = "Listar todos los vehículos")
    @GetMapping
    public List<Vehiculo> listar() {
        return vehiculoRepo.findAll();
    }

    @Operation(summary = "Obtener vehículo por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Vehiculo> obtener(@PathVariable Long id) {
        return vehiculoRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Registrar entrada de vehículo")
    @PostMapping
    public ResponseEntity<Vehiculo> crear(@RequestBody Vehiculo v) {

        if (v.getPlaca().length() > 6) {
            return ResponseEntity.badRequest().build();
        }

        v.setHoraEntrada(LocalDateTime.now());
        v.setHoraSalida(null);

        return ResponseEntity.ok(vehiculoRepo.save(v));
    }

    @Operation(summary = "Actualizar vehículo")
    @PutMapping("/{id}")
    public ResponseEntity<Vehiculo> actualizar(@PathVariable Long id, @RequestBody Vehiculo v) {

        return vehiculoRepo.findById(id).map(existing -> {

            existing.setPlaca(v.getPlaca());
            existing.setUbicacion(v.getUbicacion());
            existing.setTipoVehiculo(v.getTipoVehiculo());

            return ResponseEntity.ok(vehiculoRepo.save(existing));

        }).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Registrar salida del vehículo")
    @PatchMapping("/{id}/salida")
    public ResponseEntity<Vehiculo> registrarSalida(@PathVariable Long id) {

        return vehiculoRepo.findById(id).map(v -> {
            v.setHoraSalida(LocalDateTime.now());
            return ResponseEntity.ok(vehiculoRepo.save(v));
        }).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar vehículo")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {

        if (!vehiculoRepo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        vehiculoRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
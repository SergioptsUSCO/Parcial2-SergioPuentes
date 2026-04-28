package com.parking.park.controladores;

import com.parking.park.modelos.TipoVehiculo;
import com.parking.park.repositorios.TipoVehiculoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import java.util.List;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/tipos")
@RequiredArgsConstructor
@Tag(name = "Tipos de Vehículo")
public class TipoVehiculoController {

    private final TipoVehiculoRepository tipoRepo;

    @Operation(summary = "Listar tipos de vehículo")
    @GetMapping
    public List<TipoVehiculo> listar() {
        return tipoRepo.findAll();
    }

    @Operation(summary = "Crear tipo de vehículo")
    @PostMapping
    public TipoVehiculo crear(@RequestBody TipoVehiculo t) {
        return tipoRepo.save(t);
    }

    @Operation(summary = "Eliminar tipo de vehículo")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {

        if (!tipoRepo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        tipoRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
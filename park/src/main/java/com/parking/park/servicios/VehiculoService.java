package com.parking.park.servicios;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parking.park.modelos.Vehiculo;
import com.parking.park.modelos.VehiculoDTO;
import com.parking.park.modelos.VehiculoMapper;
import com.parking.park.repositorios.VehiculoRepository;

@Service
public class VehiculoService {

    @Autowired
    private VehiculoRepository repo;

    private static final double TARIFA_HORA = 2000;

    public Double calcularTotal(Vehiculo v) {

        if (v.getHoraSalida() == null) return 0.0;

        long horas = Duration.between(
                v.getHoraEntrada(),
                v.getHoraSalida()
        ).toHours();

        if (horas == 0) horas = 1;

        return horas * TARIFA_HORA;
    }

    public List<VehiculoDTO> listarDTO() {
        return repo.findAll().stream()
                .map(v -> VehiculoMapper.toDTO(v, calcularTotal(v)))
                .toList();
    }

    public Vehiculo guardar(Vehiculo v) {
        v.setHoraEntrada(LocalDateTime.now());
        return repo.save(v);
    }

    public Vehiculo registrarSalida(Long id) {
        Vehiculo v = repo.findById(id).orElseThrow();
        v.setHoraSalida(LocalDateTime.now());
        return repo.save(v);
    }

    public Vehiculo actualizarUbicacion(Long id, String ubicacion) {
        Vehiculo v = repo.findById(id).orElseThrow();
        v.setUbicacion(ubicacion);
        return repo.save(v);
    }
}

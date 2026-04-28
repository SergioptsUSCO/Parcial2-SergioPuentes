package com.parking.park.modelos;

public class VehiculoMapper {

    public static VehiculoDTO toDTO(Vehiculo v, Double total) {
        return new VehiculoDTO(
                v.getId(),
                v.getPlaca(),
                v.getTipoVehiculo().getNombre(),
                v.getUbicacion(),
                v.getHoraEntrada(),
                v.getHoraSalida(),
                total
        );
    }
}

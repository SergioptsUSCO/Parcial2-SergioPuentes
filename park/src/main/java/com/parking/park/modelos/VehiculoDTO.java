package com.parking.park.modelos;

import lombok.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehiculoDTO {

    private Long id;
    private String placa;
    private String tipo;
    private String ubicacion;
    private LocalDateTime horaEntrada;
    private LocalDateTime horaSalida;
    private Double totalPagar;
}

package com.parking.park.modelos;

public class Vehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 6, nullable = false)
    private String placa;

    private LocalDateTime horaEntrada;
    private LocalDateTime horaSalida;

    private String ubicacion;

    @ManyToOne
    @JoinColumn(name = "tipo_vehiculo_id")
    private TipoVehiculo tipoVehiculo;

}

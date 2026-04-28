package com.parking.park.controladores;

import com.parking.park.modelos.Usuario;
import com.parking.park.repositorios.UsuarioRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/usuarios")
@RequiredArgsConstructor
@Tag(name = "Usuarios")
public class UsuarioController {

    private final UsuarioRepository usuarioRepo;

    @Operation(summary = "Listar usuarios")
    @GetMapping
    public List<Usuario> listar() {
        return usuarioRepo.findAll();
    }

    @Operation(summary = "Crear usuario")
    @PostMapping
    public Usuario crear(@RequestBody Usuario u) {
        return usuarioRepo.save(u);
    }

}
package com.Perfulandia.perfulandia;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.Perfulandia.perfulandia.model.Envio;
import com.Perfulandia.perfulandia.model.Pedido;
import com.Perfulandia.perfulandia.model.Producto;
import com.Perfulandia.perfulandia.model.Sucursal;
import com.Perfulandia.perfulandia.model.Usuario;
import com.Perfulandia.perfulandia.repository.EnvioRepository;
import com.Perfulandia.perfulandia.repository.PedidoRepository;
import com.Perfulandia.perfulandia.repository.ProductoRepository;
import com.Perfulandia.perfulandia.repository.SucursalRepository;
import com.Perfulandia.perfulandia.repository.UsuarioRepository;

import net.datafaker.Faker;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired private ProductoRepository productoRepository;
    @Autowired private PedidoRepository pedidoRepository;
    @Autowired private EnvioRepository envioRepository;
    @Autowired private SucursalRepository sucursalRepository;
    @Autowired private UsuarioRepository usuarioRepository;

    @Override
    public void run(String... args) {
        Faker faker = new Faker();
        Random random = new Random();

        // Productos
        for (int i = 0; i < 20; i++) {
            Producto producto = new Producto();
            producto.setNombre(faker.commerce().productName());
            producto.setDescripcion(faker.lorem().sentence());
            producto.setStock(random.nextInt(100));
            producto.setPrecio(new BigDecimal(faker.commerce().price()));
            productoRepository.save(producto);
        }

        // Sucursales
        for (int i = 0; i < 5; i++) {
            Sucursal sucursal = new Sucursal();
            sucursal.setNombre(faker.company().name());
            sucursal.setDireccion(faker.address().streetAddress());
            sucursal.setCiudad(faker.address().city());
            sucursalRepository.save(sucursal);
        }

        // Usuarios
        for (int i = 0; i < 5; i++) {
            Usuario usuario = new Usuario();
            usuario.setUsername(faker.name().username());
            usuario.setPassword("1234");
            usuario.setRol("USER");
            usuarioRepository.save(usuario);
        }

        // Pedidos
        for (int i = 0; i < 10; i++) {
            Pedido pedido = new Pedido();
            pedido.setClienteNombre(faker.name().fullName());
            pedido.setFecha(LocalDate.now().minusDays(random.nextInt(30)));
            pedido.setTotal(new BigDecimal(faker.commerce().price()));
            pedido.setEstado("PENDIENTE");
            pedidoRepository.save(pedido);
        }

        // Envios
        for (int i = 0; i < 10; i++) {
            Envio envio = new Envio();
            envio.setFechaSalida(LocalDate.now().minusDays(random.nextInt(20)));
            envio.setFechaLlegada(LocalDate.now().plusDays(random.nextInt(10)));
            envio.setEstado("EN PROCESO");
            envio.setDireccionDestino(faker.address().fullAddress());
            envioRepository.save(envio);
        }
    }
}

package com.tipyme.solaris_api.data;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tipyme.solaris_api.products.Product;
import com.tipyme.solaris_api.products.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Component
@Order(2)
@RequiredArgsConstructor
public class ProductDataLoader implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(ProductDataLoader.class);

    private final ProductRepository productRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        if (productRepository.count() == 0) {
            List<Product> products = createSampleProducts();
            productRepository.saveAll(products);
            logger.info("Se han cargado {} productos de ejemplo", products.size());
        } else {
            logger.info("Los productos ya existen en la base de datos. Omitiendo carga de datos.");
        }
    }

    private List<Product> createSampleProducts() {
        List<Product> products = new ArrayList<>();

        Product product1 = new Product();
        product1.setCode("LAPTOP-001");
        product1.setName("Laptop HP Pavilion");
        product1.setDescription("Laptop HP Pavilion 15.6\", Intel Core i5, 8GB RAM, 256GB SSD");
        product1.setPrice(12500.00);
        products.add(product1);

        Product product2 = new Product();
        product2.setCode("MOUSE-001");
        product2.setName("Mouse Logitech MX Master 3");
        product2.setDescription("Mouse inalámbrico ergonómico con sensor de alta precisión");
        product2.setPrice(1899.00);
        products.add(product2);

        Product product3 = new Product();
        product3.setCode("KEYBOARD-001");
        product3.setName("Teclado Mecánico Keychron K2");
        product3.setDescription("Teclado mecánico inalámbrico, switches Gateron Brown, retroiluminado RGB");
        product3.setPrice(2499.00);
        products.add(product3);

        Product product4 = new Product();
        product4.setCode("MONITOR-001");
        product4.setName("Monitor LG UltraWide 29\"");
        product4.setDescription("Monitor LG 29\" 21:9 UltraWide, Full HD, IPS, 75Hz");
        product4.setPrice(5499.00);
        products.add(product4);

        Product product5 = new Product();
        product5.setCode("HEADSET-001");
        product5.setName("Audífonos Sony WH-1000XM4");
        product5.setDescription("Audífonos inalámbricos con cancelación de ruido activa");
        product5.setPrice(6299.00);
        products.add(product5);

        Product product6 = new Product();
        product6.setCode("WEBCAM-001");
        product6.setName("Webcam Logitech C920");
        product6.setDescription("Cámara web Full HD 1080p con micrófono estéreo");
        product6.setPrice(1599.00);
        products.add(product6);

        Product product7 = new Product();
        product7.setCode("DESK-001");
        product7.setName("Escritorio Ajustable Standing Desk");
        product7.setDescription("Escritorio de altura ajustable eléctrico, 120x60cm");
        product7.setPrice(8999.00);
        products.add(product7);

        Product product8 = new Product();
        product8.setCode("CHAIR-001");
        product8.setName("Silla Ergonómica Herman Miller Aeron");
        product8.setDescription("Silla de oficina ergonómica con soporte lumbar ajustable");
        product8.setPrice(18500.00);
        products.add(product8);

        Product product9 = new Product();
        product9.setCode("SSD-001");
        product9.setName("SSD Samsung 970 EVO Plus 1TB");
        product9.setDescription("Disco de estado sólido NVMe M.2, velocidad de lectura 3500 MB/s");
        product9.setPrice(2299.00);
        products.add(product9);

        Product product10 = new Product();
        product10.setCode("TABLET-001");
        product10.setName("Tablet iPad Pro 11\"");
        product10.setDescription("iPad Pro 11\" con chip M2, 128GB, Wi-Fi");
        product10.setPrice(17999.00);
        products.add(product10);

        return products;
    }
}

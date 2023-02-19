package com.elr.elr;

import com.elr.elr.repositories.BookRepository;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
/*@DataJpaTest
*Anotación para una prueba de JPA que se enfoca solo en los componentes de JPA.
*El uso de esta anotación deshabilitará la configuración automática completa y, en su lugar,
*aplicará solo la configuración relevante para las pruebas de JPA.
*De forma predeterminada, las pruebas anotadas con @DataJpaTest son transaccionales y retroceden al
*final de cada prueba. También utilizan una base de datos incrustada en la memoria (reemplazando cualquier
*DataSource explícito o generalmente autoconfigurado).
* La anotación @AutoConfigureTestDatabase se puede usar para anular esta configuración.
*Las consultas SQL se registran de forma predeterminada al establecer la propiedad spring.jpa.show-sql en
*verdadero. Esto se puede deshabilitar usando el atributo showSql.
*Si desea cargar la configuración completa de su aplicación, pero usa una base de datos integrada, debe
*considerar @SpringBootTest combinado con @AutoConfigureTestDatabase en lugar de esta anotación.
*@ActiveProfiles("local")
*Activamos
*@ComponentScan(basePackages = {"guru.springframework.sdjpaintro.bootstrap"})
* con esta cargamos Datainitializer
* */
@ActiveProfiles("local")
@DataJpaTest
@ComponentScan(basePackages = {"guru.springframework.sdjpaintro.bootstrap"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) //anula la cong de db en memoria de @DataJpaTest
public class MySQLIntegrationTest {

    @Autowired
    BookRepository bookRepository;

    @Test
    void testMySQL() {
        long countBefore = bookRepository.count();
        assertThat(countBefore).isEqualTo(2);

    }

}
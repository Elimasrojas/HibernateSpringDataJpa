package com.elr.elr;

import com.elr.elr.domain.AuthorUuid;
import com.elr.elr.domain.BookNatural;
import com.elr.elr.domain.BookUuid;
import com.elr.elr.domain.composite.AuthorComposite;
import com.elr.elr.domain.composite.AuthorEmbedded;
import com.elr.elr.domain.composite.NameId;
import com.elr.elr.repositories.*;
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
@ComponentScan(basePackages = {"com.elr.elr.bootstrap"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MySQLIntegrationTest {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorUuidRepository authorUuidRepository;

    @Autowired
    BookUuidRepository bookUuidRepository;

    @Autowired
    BookNaturalRepository bookNaturalRepository;

    @Autowired
    AuthorCompositeRepository authorCompositeRepository;

    @Autowired
    AuthorEmbeddedRepository authorEmbeddedRepository;

    @Test
    void authorEmbeddedTest() {
        NameId nameId = new NameId("John", "T");
        AuthorEmbedded authorEmbedded = new AuthorEmbedded(nameId);

        AuthorEmbedded saved = authorEmbeddedRepository.save(authorEmbedded);
        assertThat(saved).isNotNull();

        AuthorEmbedded fetched = authorEmbeddedRepository.getById(nameId);
        assertThat(fetched).isNotNull();
    }

    @Test
    void authorCompositeTest() {
        NameId nameId = new NameId("John", "T");
        AuthorComposite authorComposite = new AuthorComposite();
        authorComposite.setFirstName(nameId.getFirstName());
        authorComposite.setLastName(nameId.getLastName());
        authorComposite.setCountry("US");

        AuthorComposite saved = authorCompositeRepository.save(authorComposite);
        assertThat(saved).isNotNull();

        AuthorComposite fetched = authorCompositeRepository.getById(nameId);
        assertThat(fetched).isNotNull();
    }

    @Test
    void bookNaturalTest() {
        BookNatural bookNatural = new BookNatural();
        bookNatural.setTitle("My Book");
        BookNatural saved = bookNaturalRepository.save(bookNatural);

        BookNatural fetched = bookNaturalRepository.getById(saved.getTitle());
        assertThat(fetched).isNotNull();
    }

    @Test
    void testBookUuid() {
        BookUuid bookUuid = bookUuidRepository.save(new BookUuid());
        assertThat(bookUuid).isNotNull();
        assertThat(bookUuid.getId());

        BookUuid fetched = bookUuidRepository.getById(bookUuid.getId());
        assertThat(fetched).isNotNull();
    }

    @Test
    void testAuthorUuid() {
        AuthorUuid authorUuid = authorUuidRepository.save(new AuthorUuid());
        assertThat(authorUuid).isNotNull();
        assertThat(authorUuid.getId()).isNotNull();

        AuthorUuid fetched = authorUuidRepository.getById(authorUuid.getId());
        assertThat(fetched).isNotNull();
    }


    @Test
    void testMySQL() {
        long countBefore = bookRepository.count();
        assertThat(countBefore).isEqualTo(2);

    }

}
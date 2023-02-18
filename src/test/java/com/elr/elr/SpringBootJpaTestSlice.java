package com.elr.elr;

import com.elr.elr.domain.Book;
import com.elr.elr.repositories.BookRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.Commit;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;



/*
 * el test @DataJpaTest __SI__toma los valores bootstrap.DataInitializer hace el insert de los dos registros de
 * ejemplo por esto @ComponentScan(basePackages = {"com.elr.elr.bootstrap"})
 * */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) //ejecuta los    @Order(1) y    @Order(2)
@DataJpaTest
@ComponentScan(basePackages = {"com.elr.elr.bootstrap"})
public class SpringBootJpaTestSlice {

    @Autowired
    BookRepository bookRepository;

    @Commit
    @Order(1)
    @Test
    void testJpaTestSplice() {
        long countBefore = bookRepository.count();
        assertThat(countBefore).isEqualTo(2);

        bookRepository.save(new Book("My Book", "1235555", "Self"));

        long countAfter = bookRepository.count();

        assertThat(countBefore).isLessThan(countAfter);
    }

    @Order(2)
    @Test
    void testJpaTestSpliceTransaction() {
        long countBefore = bookRepository.count();
        assertThat(countBefore).isEqualTo(3);

    }
}
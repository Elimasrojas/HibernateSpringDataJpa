package com.elr.elr;

import com.elr.elr.domain.Book;
import com.elr.elr.repositories.BookRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Commit;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;



/*
 * el test @DataJpaTest __No__toma los valores bootstrap.DataInitializer hace el insert de los dos registros de
 * ejemplo
 * */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) //ejecuta los    @Order(1) y    @Order(2)
@DataJpaTest
public class SpringBootJpaTestSlice {

    @Autowired
    BookRepository bookRepository;

    //@Commit Para que tome los valores del testJpaTestSplice en el testJpaTestSpliceTransaction
    //de lo contrario cada test se ejecuta independientemente
    @Commit
    @Order(1)
    @Test
    void testJpaTestSplice() {
        long countBefore = bookRepository.count();
        assertThat(countBefore).isEqualTo(0);

        bookRepository.save(new Book("My Book", "1235555", "Self"));

        long countAfter = bookRepository.count();

        assertThat(countBefore).isLessThan(countAfter);
    }

    @Order(2)
    @Test
    void testJpaTestSpliceTransaction() {
        long countBefore = bookRepository.count();
        assertThat(countBefore).isEqualTo(1);
    }
}
package com.elr.elr.repositories;


import com.elr.elr.domain.BookUuid;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface BookUuidRepository extends JpaRepository<BookUuid, UUID> {
}
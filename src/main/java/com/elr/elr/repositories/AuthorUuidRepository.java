package com.elr.elr.repositories;

import com.elr.elr.domain.AuthorUuid;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface AuthorUuidRepository extends JpaRepository<AuthorUuid, UUID> {
}
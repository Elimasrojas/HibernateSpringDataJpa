package com.elr.elr.repositories;

import com.elr.elr.domain.composite.AuthorEmbedded;
import com.elr.elr.domain.composite.NameId;

import org.springframework.data.jpa.repository.JpaRepository;


public interface AuthorEmbeddedRepository extends JpaRepository<AuthorEmbedded, NameId> {
}
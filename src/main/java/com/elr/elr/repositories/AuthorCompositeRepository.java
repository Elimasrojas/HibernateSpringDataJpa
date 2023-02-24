package com.elr.elr.repositories;


import com.elr.elr.domain.composite.AuthorComposite;
import com.elr.elr.domain.composite.NameId;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AuthorCompositeRepository extends JpaRepository<AuthorComposite, NameId> {
}
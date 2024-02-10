package com.example.DSCatalog.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.DSCatalog.domain.entities.PasswordRecover;

public interface PasswordRecoverRepository extends JpaRepository<PasswordRecover, Long>{

}

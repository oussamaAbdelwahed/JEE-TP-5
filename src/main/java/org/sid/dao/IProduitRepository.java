package org.sid.dao;

import org.sid.entities.Produit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IProduitRepository extends JpaRepository<Produit , Long> {
    @Query("select p from Produit p where p.designation like :x")
     Page<Produit> findByMc(@Param("x")String motCle,Pageable p);

     List<Produit> findByDesignation(String des);
     Page<Produit> findByDesignation(String des,Pageable p);

}

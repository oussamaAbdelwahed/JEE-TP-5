package org.sid.entities;


import javax.persistence.*;

@Entity
@Table(name = "PRODUITS")
public class Produit {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long reference;

    private String designation;

    private double prix;

    public Long getRef() {
        return reference;
    }

    public void setRef(Long ref) {
        this.reference = ref;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
}

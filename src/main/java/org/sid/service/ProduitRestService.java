package org.sid.service;


import org.sid.dao.IProduitRepository;
import org.sid.entities.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class ProduitRestService {
    @Autowired
    private IProduitRepository produitRepository;

    @GetMapping("/test")
    public List<Produit> test() {
        Page<Produit> page = this.produitRepository.findAll(PageRequest.of(0,2));
        return page.getContent();
    }

    @PostMapping(value = "/save")
    public Produit saveProduit(@RequestBody Produit p){
        produitRepository.save(p);
        return p;
    }

    @GetMapping("/all")
    public List<Produit> getProduits(){
        return produitRepository.findAll();
    }



    @GetMapping("/produits/{page}/{size}")
    public ResponseEntity<List<Produit>> getProduits(@PathVariable("page") int page,@PathVariable("size") int size){
        Page<Produit> p =  produitRepository.findAll(PageRequest.of(page, size));
        return new ResponseEntity<List<Produit>>(p.getContent(), HttpStatus.OK);
    }

    @PostMapping("/produitsParMc")
    public ResponseEntity<List<Produit>> getProduits(@RequestBody Map<String,String> body){
       String mc = body.get("mc");
       int page=0;
       page =Integer.parseInt(body.get("page"));
       Page<Produit> produits =  produitRepository.findByMc("%"+mc+"%",PageRequest.of(page,5));
       return  new ResponseEntity<List<Produit>>(produits.getContent(),HttpStatus.OK);
    }

  //here
    @GetMapping("/get/{ref}")
    public Optional<Produit> getProduit(@PathVariable  Long ref){
        return produitRepository.findById(ref);
    }

    @GetMapping (value = "/delete/{ref}")
    public boolean delete(@PathVariable Long ref){
        produitRepository.deleteById(ref);
        return true;
    }

    @PostMapping(value = "/update")
    public Produit update(@RequestBody  Produit p){
        produitRepository.saveAndFlush(p);
        return p;
    }
}

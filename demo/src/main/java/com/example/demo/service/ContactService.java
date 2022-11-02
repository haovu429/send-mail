package com.example.demo.service;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Contact;

@Repository
public interface ContactService extends JpaRepository<Contact, Long>{
    //todo
}
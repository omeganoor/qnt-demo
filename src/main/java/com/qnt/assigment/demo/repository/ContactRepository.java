package com.qnt.assigment.demo.repository;

import com.qnt.assigment.demo.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

  Contact findOneById (Long id);
}
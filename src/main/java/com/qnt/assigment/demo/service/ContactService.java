package com.qnt.assigment.demo.service;

import com.qnt.assigment.demo.dao.ContactRequestDTO;
import com.qnt.assigment.demo.dao.ContactResponseDTO;
import com.qnt.assigment.demo.entity.Contact;
import com.qnt.assigment.demo.repository.ContactRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactService {

    private ContactRepository contactRepository;

    public ContactService (ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public Page<ContactResponseDTO> findAll(Pageable pageable) {
        List<ContactResponseDTO> contactData = new ArrayList<>();
        Page<Contact> contact = contactRepository.findAll(pageable);
        for (Contact cnt : contact.getContent()) {
            contactData.add(ContactResponseDTO.builder()
                    .id(cnt.getId())
                    .address(cnt.getAddress()).name(cnt.getName())
                    .email(cnt.getEmail()).phone(cnt.getPhone())
                .build());
        }
        Page<ContactResponseDTO> resultPage = new PageImpl<>(contactData, pageable, contact.getTotalElements());
        return resultPage;
    }

    public ContactResponseDTO save(ContactRequestDTO contactRequestDTO) {
        Contact contact;
        if (contactRequestDTO.getId() != null){
            contact = contactRepository.findOneById(contactRequestDTO.getId());
            if (contact == null){
                return null;
            }
            contact.setName(contactRequestDTO.getName() == null ? contact.getName() : contactRequestDTO.getName());
            contact.setAddress(contactRequestDTO.getAddress() == null ? contact.getAddress() : contactRequestDTO.getAddress());
            contact.setEmail(contactRequestDTO.getEmail() == null ? contact.getEmail() : contactRequestDTO.getEmail());
            contact.setPhone(contactRequestDTO.getPhone() == null ? contact.getPhone() : contactRequestDTO.getPhone());

        }else{
            contact = Contact.builder()
                    .name(contactRequestDTO.getName())
                    .email(contactRequestDTO.getEmail())
                    .address(contactRequestDTO.getAddress())
                    .phone(contactRequestDTO.getPhone())
                    .build();
        }

        Contact result = contactRepository.save(contact);
        return ContactResponseDTO.builder()
            .id(result.getId())
            .name(result.getName())
            .email(result.getEmail())
            .address(result.getAddress())
            .phone(result.getPhone())
            .build();
    }


    public ContactResponseDTO findOneById(long id) {
        Contact contact = contactRepository.findOneById(id);
        if(contact == null){
            return null;
        }
        return ContactResponseDTO.builder()
            .id(contact.getId())
            .name(contact.getName())
            .email(contact.getEmail())
            .address(contact.getAddress())
            .phone(contact.getPhone())
            .build();
    }

    public void delete(long id) {
        contactRepository.deleteById(id);
    }

}
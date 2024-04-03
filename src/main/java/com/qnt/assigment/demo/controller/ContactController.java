package com.qnt.assigment.demo.controller;

import com.qnt.assigment.demo.dao.ContactRequestDTO;
import com.qnt.assigment.demo.dao.ContactResponseDTO;
import com.qnt.assigment.demo.dao.constans.ApiPath;
import com.qnt.assigment.demo.service.ContactService;
import com.qnt.assigment.demo.service.RateLimiterConfiguration;
import com.qnt.assigment.demo.utility.PageableHelper;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.id.GUIDGenerator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

@RestController
@RequestMapping(ApiPath.CONTACT)
@Slf4j
public class ContactController {

    private ContactService contactService;
    private RateLimiterConfiguration rateLimiterConfiguration;

    public ContactController (ContactService contactService, RateLimiterConfiguration rateLimiterConfiguration) {
        this.contactService = contactService;
        this.rateLimiterConfiguration = rateLimiterConfiguration;
    }

    @GetMapping
    public ResponseEntity<Page<ContactResponseDTO>> getContactData( @RequestParam(required = false) Integer size,
           @RequestParam(required = false) Integer pageNumber,
           @RequestParam(required = false) Sort.Direction direction,
           @RequestParam(required = false) String sortedField
    ) {
        UUID reqId = UUID.randomUUID();
        if(rateLimiterConfiguration.isLimitAvailable(reqId.toString())){
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS.value()).build();
        }

        Pageable pageable = PageableHelper.setPageable(pageNumber, size, direction, sortedField);
        Page<ContactResponseDTO> contactPage = this.contactService.findAll(pageable);
        return ResponseEntity.ok(contactPage);
    }

    @PostMapping
    public ResponseEntity<ContactResponseDTO> createContact(@RequestBody ContactRequestDTO contactRequestDTO)
            throws URISyntaxException {
        log.debug("REST request to Insert Contact : {}");
        UUID reqId = UUID.randomUUID();
        if (rateLimiterConfiguration.isLimitAvailable(reqId.toString())) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS.value()).build();
        }

        if (contactRequestDTO.getId() != null){
            return ResponseEntity.badRequest().build();
        }
        ContactResponseDTO result = contactService.save(contactRequestDTO);
        return ResponseEntity.created(new URI(ApiPath.CONTACT + result.getId())).body(result);
    }

    @PutMapping(ApiPath.ID)
    public ResponseEntity<ContactResponseDTO> updateContact(@PathVariable long id, @RequestBody ContactRequestDTO contactRequestDTO)
            throws URISyntaxException {
        log.debug("REST request to Update Contact by Id : {}, {}",id , contactRequestDTO);
        UUID reqId = UUID.randomUUID();
        if (rateLimiterConfiguration.isLimitAvailable(reqId.toString())) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS.value()).build();
        }

        contactRequestDTO.setId(id);
        ContactResponseDTO result = contactService.save(contactRequestDTO);
        if (result == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(result);
    }

    @GetMapping(ApiPath.ID)
    public ResponseEntity<ContactResponseDTO> getContactById(@PathVariable long id) {
        log.debug("REST request to get Contact By Id : {}", id);
        UUID reqId = UUID.randomUUID();
        if (rateLimiterConfiguration.isLimitAvailable(reqId.toString())) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS.value()).build();
        }

        ContactResponseDTO contactResponseDTO = contactService.findOneById(id);
        if (contactResponseDTO == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(contactResponseDTO);
    }

    @DeleteMapping(ApiPath.ID)
    public ResponseEntity<Void> deleteContactById(@PathVariable long id) {
        log.debug("REST request to delete Contact by Id : {}", id);
        UUID reqId = UUID.randomUUID();
        if (rateLimiterConfiguration.isLimitAvailable(reqId.toString())) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS.value()).build();
        }

        contactService.delete(id);
        return ResponseEntity.ok().build();
    }
}
package com.example.demo.api;

import java.lang.reflect.Method;
import java.util.List;

import javax.validation.Valid;

import com.example.demo.send_mail.SendMail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Contact;
import com.example.demo.service.ContactService;
import java.util.Random;

@RestController
@RequestMapping("/api")
public class RestApiController {
    public static Logger logger = LoggerFactory.getLogger(RestApiController.class);

    @Autowired
    ContactService contactService;

    @RequestMapping(value = "/contact/", method = RequestMethod.GET)
    public ResponseEntity<List<Contact>> listAllContact(){
        List<Contact> listContact= contactService.findAll();
        if(listContact.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Contact>>(listContact, HttpStatus.OK);
    }

    @RequestMapping(value = "/vietnamStatus/", method = RequestMethod.GET)
    public ResponseEntity<String>  getVietnamStatus(){
        List<Contact> listContact= contactService.findAll();
        if(listContact.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        //return new ResponseEntity<List<Contact>>(listContact, HttpStatus.OK);
        if (getRandomBoolean()) {
            return ResponseEntity.ok("VietnamSuspended");
        } else {
            return ResponseEntity.ok("Vietnamopen");
        }
    }

    @RequestMapping(value = "/contact/{id}", method = RequestMethod.GET)
    public Contact findContact(@PathVariable("id") long id) {
        Contact contact= contactService.getOne(id);
        if(contact == null) {
            ResponseEntity.notFound().build();
        }
        return contact;
    }

    @RequestMapping(value = "/contact/", method = RequestMethod.POST)
    public Contact saveContact(@Valid @RequestBody Contact contact) {
        return contactService.save(contact);
    }

    @RequestMapping(value = "/contact/", method = RequestMethod.PUT)
    public ResponseEntity<Contact> updateContact(
                                                 @Valid @RequestBody Contact contactForm) {
        long id = contactForm.getId();
        boolean exists = contactService.existsById(contactForm.getId());
        if(!exists) {
            return ResponseEntity.notFound().build();
        }
        Contact contact = new Contact();
        contact.setId(contactForm.getId());
        contact.setName(contactForm.getName());
        contact.setAge(contactForm.getAge());
        contact.setEmail(contactForm.getEmail());
        contact.setDob(contactForm.getDob());
        contact.setActive(true);

        Contact updatedContact = contactService.save(contact);
        return ResponseEntity.ok(updatedContact);
    }

    @RequestMapping(value = "/contact/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Contact> deleteContact(@PathVariable(value = "id") Long id) {
        Contact contact = contactService.getOne(id);
        if(contact == null) {
            return ResponseEntity.notFound().build();
        }

        contactService.delete(contact);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/sendmail/{recieverMail}", method = RequestMethod.GET)
    public ResponseEntity<String> sendMail(@PathVariable(value = "recieverMail") String recieverEmail) {
        //reference https://www.baeldung.com/spring-requestmapping
        SendMail.sendMail(recieverEmail);
        return ResponseEntity.ok().build();
    }



    public boolean getRandomBoolean() {
        Random random = new Random();
        return random.nextBoolean();
    }
}

package com.rmit.sept.turtorial.demo.web;

import com.rmit.sept.turtorial.demo.model.Person;
import com.rmit.sept.turtorial.demo.services.PersonService;
import com.rmit.sept.turtorial.demo.services.MapValidationErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/person")
@CrossOrigin
public class PersonController {

    @Autowired
    private PersonService personService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;


    @PostMapping("")
    public ResponseEntity<?> createNewPerson(@Valid @RequestBody Person person, BindingResult result){

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap!=null) return errorMap;

        Person project1 = personService.saveOrUpdatePerson(person);
        return new ResponseEntity<Person>(project1, HttpStatus.CREATED);
    }


    @GetMapping("/{personId}")
    public ResponseEntity<?> getPersonById(@PathVariable String personId


    ){

        Person person = personService.findByPersonIdentifier(personId);

        return new ResponseEntity<Person>(person, HttpStatus.OK);
    }


    @GetMapping("/all")
    public Iterable<Person> getAllPersons(){return

            personService.findAllPersons();}


    @DeleteMapping("/{personId}")
    public ResponseEntity<?> deleteProject(@PathVariable String personId){
        personService.deletePersonByIdentifier(personId);

        return new ResponseEntity<String>("Person with ID: '"+personId+"' was deleted", HttpStatus.OK);
    }
}
// final Person Controller
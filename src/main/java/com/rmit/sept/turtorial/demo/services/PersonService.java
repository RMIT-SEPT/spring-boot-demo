package com.rmit.sept.turtorial.demo.services;


import com.rmit.sept.turtorial.demo.Repositories.PersonRepository;
import com.rmit.sept.turtorial.demo.exceptions.PersonException;
import com.rmit.sept.turtorial.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;
    public Person saveOrUpdatePerson(Person person) {

        try{
            person.setPersonIdentifier(person.getPersonIdentifier().toUpperCase());
            return personRepository.save(person);
        }catch (Exception e){
            throw new PersonException("Person ID '"+person.getPersonIdentifier().toUpperCase()+"' already exists");
        }

    }


    public Person findByPersonIdentifier(String personId){

        Person person = personRepository.findByPersonIdentifier(personId.toUpperCase());

        if(person == null){
            throw new PersonException("Person ID '"+personId+"' does not exist");

        }


        return person;
    }

    public Iterable<Person> findAllPersons(){
        return personRepository.findAll();
    }


    public void deletePersonByIdentifier(String personId){
        Person person = personRepository.findByPersonIdentifier(personId.toUpperCase());

        if(person == null){
            throw  new  PersonException("Cannot Person with ID '"+personId+"'. This person does not exist");
        }

        personRepository.delete(person);
    }
}

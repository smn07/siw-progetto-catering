package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Amministratore;
import com.example.demo.repository.AmministratoreRepository;

/**
 * The UserService handles logic for Users.
 */
@Service
public class AmministratoreService {

    @Autowired
    protected AmministratoreRepository amministratoreRepository;

    /**
     * This method retrieves a User from the DB based on its ID.
     * @param id the id of the User to retrieve from the DB
     * @return the retrieved User, or null if no User with the passed ID could be found in the DB
     */
    @Transactional
    public Amministratore getAmministratore(Long id) {
        Optional<Amministratore> result = this.amministratoreRepository.findById(id);
        return result.orElse(null);
    }

    /**
     * This method saves a User in the DB.
     * @param user the User to save into the DB
     * @return the saved User
     * @throws DataIntegrityViolationException if a User with the same username
     *                              as the passed User already exists in the DB
     */
    @Transactional
    public Amministratore saveAmministratore(Amministratore amministratore) {
        return this.amministratoreRepository.save(amministratore);
    }

    /**
     * This method retrieves all Users from the DB.
     * @return a List with all the retrieved Users
     */
    @Transactional
    public List<Amministratore> getAllAmministratori() {
        List<Amministratore> result = new ArrayList<>();
        Iterable<Amministratore> iterable = this.amministratoreRepository.findAll();
        for(Amministratore amministratore : iterable)
            result.add(amministratore);
        return result;
    }
}

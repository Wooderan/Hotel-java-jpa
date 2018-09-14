package com.example.hotel_jpa.services.impls;

import com.example.hotel_jpa.models.Client;
import com.example.hotel_jpa.repositories.IClientRepository;
import com.example.hotel_jpa.services.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements IClientService {

    @Autowired
    private IClientRepository repository;

    @Override
    public Client addClient(Client client) {
        if (repository.findByPassport(client.getPassport()) == null)
            return repository.saveAndFlush(client);
        return null;
    }

    @Override
    public void delete(long id) {
        repository.deleteById((int) id);
    }

    @Override
    public Client getByLastName(String name) {
        return repository.findByLastName(name);
    }

    @Override
    public Client editClient(Client client) {
        return repository.saveAndFlush(client);
    }

    @Override
    public List<Client> getAll() {
        return repository.findAll();
    }
}

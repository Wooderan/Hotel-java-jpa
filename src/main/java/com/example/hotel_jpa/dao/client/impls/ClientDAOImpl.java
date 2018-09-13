package com.example.hotel_jpa.dao.client.impls;

import com.example.hotel_jpa.dao.client.interfaces.IClientDAO;
import com.example.hotel_jpa.exceptions.NoSuchClientException;
import com.example.hotel_jpa.models.Client;
import com.example.hotel_jpa.models.datasources.DataSourceClients;

import java.util.ArrayList;
import java.util.List;

public class ClientDAOImpl implements IClientDAO {
    DataSourceClients dataSourceClients = new DataSourceClients();

    @Override
    public Client create(Client client) {
        return dataSourceClients.add(client);
    }

    @Override
    public List<Client> getAll() {
        return dataSourceClients.getClientsList();
    }

    @Override
    public Client getClient(int id) throws NoSuchClientException {
        if (dataSourceClients.getTopId() > id)
            throw new NoSuchClientException("Wrong ID");
        for (Client client: this.getAll()){
            if (client.getId() == id)
                return client;
        }
        throw new NoSuchClientException("This exception should be never throws. Check your code dummy!");
    }

    @Override
    public List<Client> getClient(String lastName) throws NoSuchClientException {
        List<Client> retList = new ArrayList<>();
        for (Client client: this.getAll()){
            if (client.getLastName() == lastName)
                retList.add(client);
        }
        if (retList.isEmpty())
            throw new NoSuchClientException("List is empty");

        return retList;
    }

    @Override
    public Client update(Client client) throws NoSuchClientException {
        Client old = this.getClient(client.getId());
        old = client;
        return old;
    }

    @Override
    public boolean delete(Client client) {
        return dataSourceClients.remove(client);
    }
}

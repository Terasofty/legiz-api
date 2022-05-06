package com.terasofty.legiz.api.domain.service;


import com.terasofty.legiz.api.domain.models.Client;

import java.util.List;

public interface ClientsService {
    List<Client> getClients();
    Client getClient(String username);
    Client createClient(Client client);
}

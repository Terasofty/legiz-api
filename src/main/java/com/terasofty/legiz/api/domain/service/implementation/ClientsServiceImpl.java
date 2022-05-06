package com.terasofty.legiz.api.domain.service.implementation;

import com.terasofty.legiz.api.domain.models.Client;
import com.terasofty.legiz.api.domain.persistence.ClientRepository;
import com.terasofty.legiz.api.domain.service.ClientsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ClientsServiceImpl implements ClientsService {
    private final ClientRepository clientRepository;
    @Override
    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client getClient(String username) {
        return clientRepository.getByUserUsername(username);
    }

    @Override
    public Client createClient(Client client) {
        return clientRepository.save(client);
    }
}

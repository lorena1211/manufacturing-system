package com.system.billing.backend.client;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service 
public class ClientService {
    
    private final ClientRepository repository;

    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    // Having two data bases, there are two transaction managers: INdicates which one to use
    @Transactional ("postgresTransactionManager")
    public ClientResponse create(ClientRequest req) {
        Client c = new Client();
        apply(c, req);
        c.setActive(true);
        c.setCreationDate(LocalDateTime.now());
        return ClientResponse.from(repository.save(c));
    }

    @Transactional(value = "postgresTransactionManager", readOnly = true)
    public ClientResponse getById(Long id) {
        return ClientResponse.from(find(id));
    }

    @Transactional(value = "postgresTransactionManager", readOnly = true)
    public List<ClientResponse> list() {
        return repository.findAll().stream().map(ClientResponse::from).toList();
    }

    @Transactional("postgresTransactionManager")
    public ClientResponse update(Long id, ClientRequest req) {
        Client c = find(id);
        apply(c, req);
        c.setUpdateDate(LocalDateTime.now());
        return ClientResponse.from(repository.save(c));
    }

    // Logical erase: The client becomes inactive, it is not deleted
    @Transactional("postgresTransactionManager")
    public void deactivate(Long id) {
        Client c = find(id);
        c.setActive(false);
        c.setUpdateDate(LocalDateTime.now());
        repository.save(c);
    }

    private Client find(Long id) {
        return repository.findById(id).orElseThrow(() -> new ClientNotFoundException(id));
    }

    private void apply(Client c, ClientRequest req) {
        c.setName(req.name());
        c.setNit(req.nit());
        c.setEmail(req.email());
        c.setTel(req.tel());
        c.setAddress(req.address());
    }
}

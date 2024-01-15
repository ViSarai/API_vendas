package com.example.rest.controllers;

import com.example.domain.entity.Client;
import com.example.domain.repo.ClientRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClientController {

    private ClientRepository repo;

    public ClientController(ClientRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/{id}")
    public Client getClientById(@PathVariable("id") Integer id) throws Exception {
        return repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Client save(@RequestBody @Valid Client client) {
        return repo.save(client);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        repo.findById(id)
                .map(existingClient -> {
                    repo.delete(existingClient);
                    return existingClient;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id, @RequestBody @Valid Client client) {
        repo.findById(id)
                .map(existingClient -> {
                    client.setId(existingClient.getId());
                    repo.save(client);
                    return existingClient;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public List<Client> find(Client filter) throws Exception {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example<Client> example = Example.of(filter, matcher);
        List<Client> resultList = repo.findAll(example);
        if (resultList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Clients not found");
        }
        return resultList;

    }

}

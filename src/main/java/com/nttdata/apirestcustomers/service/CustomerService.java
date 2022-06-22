package com.nttdata.apirestcustomers.service;

import com.nttdata.apirestcustomers.model.Customer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerService {

    Mono<Customer> create(Customer customer);

    Mono<Customer> update(Customer customer);

    Flux<Customer> listAll();

    Mono<Customer> getById(String id);

    Mono<Void> deleteById(String id);

    Mono<Customer> getByNumberDocument(String numberDocument);

    Flux<Customer> getByCustomerType(String customerType);

}

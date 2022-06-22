package com.nttdata.apirestcustomers.repository;

import com.nttdata.apirestcustomers.model.Customer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerRepository extends ReactiveMongoRepository<Customer, String> {

    Mono<Customer> findByNumberDocument(String numberDocument);

    Flux<Customer> findByCustomerType(String customerType);
}

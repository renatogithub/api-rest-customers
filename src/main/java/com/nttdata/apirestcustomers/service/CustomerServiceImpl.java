/**
 * Implementation Interface Service Customer
 *
 * @author Renato Ponce
 * @version 1.0
 * @since 2022-06-24
 */

package com.nttdata.apirestcustomers.service;

import com.nttdata.apirestcustomers.model.Customer;
import com.nttdata.apirestcustomers.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository repository;

    @Override
    public Mono<Customer> create(Customer customer) {
        return repository.save(customer);
    }

    @Override
    public Mono<Customer> update(Customer customer) {
        return repository.save(customer);
    }

    @Override
    public Flux<Customer> listAll() {
        return repository.findAll();
    }

    @Override
    public Mono<Customer> getById(String id) {
        return repository.findById(id);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return repository.deleteById(id);
    }

    @Override
    public Mono<Customer> getByNumberDocument(String numberDocument) {
        return repository.findByNumberDocument(numberDocument);
    }

    @Override
    public Flux<Customer> getByCustomerType(String customerType) {
        return repository.findByCustomerType(customerType);
    }
}

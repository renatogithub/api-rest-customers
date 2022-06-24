/**
 * Controller that receives the requests
 *
 * @author Renato Ponce
 * @version 1.0
 * @since 2022-06-24
 */

package com.nttdata.apirestcustomers.controller;

import com.nttdata.apirestcustomers.model.Customer;
import com.nttdata.apirestcustomers.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService service;

    @GetMapping
    public Mono<ResponseEntity<Flux<Customer>>> list() {
        Flux<Customer> fxCustomers = service.listAll();

        return Mono.just(ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fxCustomers));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Customer>> getForId(@PathVariable("id") String id) {
        logger.info("Se obtendra el cliente por Id");
        return service.getById(id) //Mono<Customer>->Mono<ResponseEntity<Customer>>
                .map(p -> ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(p)
                ); //Mono<ResponseEntity<Customer>>
    }

    @PostMapping
    public Mono<ResponseEntity<Customer>> register(@RequestBody Customer customer, final ServerHttpRequest req) {
        //201 | localhost:8080/customers/123
        return service.create(customer)
                .map(p -> ResponseEntity.created(URI.create(req.getURI().toString().concat("/").concat(p.getId())))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(p)
                );
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Customer>> update(@PathVariable("id") String id, @RequestBody Customer customer) {

        Mono<Customer> monoBody = Mono.just(customer);
        Mono<Customer> monoBD = service.getById(id);

        return monoBD
                .zipWith(monoBody, (bd, c) -> {
                    bd.setId(id);
                    bd.setName(c.getName());
                    bd.setAddress(c.getAddress());
                    bd.setEmail(c.getEmail());
                    bd.setPhone(c.getPhone());
                    bd.setNumberDocument(c.getNumberDocument());
                    bd.setCustomerType(c.getCustomerType());
                    return bd;
                })
                .flatMap(service::update) //bd->service.update(bd)
                .map(pc -> ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(pc))
                .defaultIfEmpty(new ResponseEntity<Customer>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable("id") String id) {
        return service.getById(id)
                .flatMap(c -> {
                    return service.deleteById(c.getId())
                            .then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)));
                })
                .defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/numberDocument/{numberDocument}")
    public Mono<ResponseEntity<Customer>> getByNumberDocument(@PathVariable("numberDocument") String numberDocument) {
        return service.getByNumberDocument(numberDocument) //Mono<Customer>->Mono<ResponseEntity<Customer>>
                .map(c -> ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(c)
                ); //Mono<ResponseEntity<Customer>>
    }

    @GetMapping("/customerType/{customerType}")
    public Mono<ResponseEntity<Flux<Customer>>> getByCustomerType(@PathVariable("customerType") String customerType) {

        Flux<Customer> fxCustomers = service.getByCustomerType(customerType);

        return Mono.just(ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fxCustomers));

    }

}

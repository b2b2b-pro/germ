package pro.b2b2b.germ.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.b2b2b.germ.model.Organization;
import pro.b2b2b.germ.repository.OrganizationRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
public class OrganizationController {
    private final OrganizationRepository repository;

    public OrganizationController(OrganizationRepository repository) {
        this.repository = repository;
    }
    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/organizations")
    List<Organization> all() {
        List<Organization> result = new ArrayList<>();
        repository.findAll().forEach(result::add);
        return result;
    }
    // end::get-aggregate-root[]
}

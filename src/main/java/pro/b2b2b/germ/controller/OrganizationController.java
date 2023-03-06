package pro.b2b2b.germ.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;


import org.springframework.web.bind.annotation.*;
import pro.b2b2b.germ.exception.OrganizationNotFoundException;
import pro.b2b2b.germ.model.Organization;
import pro.b2b2b.germ.repository.OrganizationRepository;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

// TODO разобраться с уникальностью ИНН+КПП

@RestController
public class OrganizationController {
    private final OrganizationRepository repository;
    private final OrganizationModelAssembler assembler;

    public OrganizationController(OrganizationRepository repository, OrganizationModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
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

    @GetMapping("/organizations/{id}")
    EntityModel<Organization> one(@PathVariable Long id) {

        Organization organization = repository.findById(id) //
                .orElseThrow(() -> new OrganizationNotFoundException(id));

        return assembler.toModel(organization);
    }

    @PostMapping("/organizations")
    Organization newOrganization(@RequestBody Organization newOrganization) {
        return repository.save(newOrganization);
    }

    @PutMapping("/organizations/{id}")
   Organization replaceOrganization(@RequestBody Organization newOrganization, @PathVariable Long id) {

    return repository.findById(id)
      .map(organization -> {
        organization.setFullName(newOrganization.getFullName() != null ? newOrganization.getFullName() : organization.getFullName());
        organization.setShortName(newOrganization.getShortName() != null ? newOrganization.getShortName() : organization.getShortName());
        organization.setInn(newOrganization.getInn() != null ? newOrganization.getInn() : organization.getInn());
        organization.setKpp(newOrganization.getKpp() != null ? newOrganization.getKpp() : organization.getKpp());
        return repository.save(organization);
      })
      .orElseGet(() -> {
        newOrganization.setId(id);
        return repository.save(newOrganization);
      });
  }


    @DeleteMapping("/organizations/{id}")
    void deleteOrganization(@PathVariable Long id) {
        repository.deleteById(id);
    }
}

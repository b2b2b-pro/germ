package pro.b2b2b.germ.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger log = LoggerFactory.getLogger(ObligationController.class);

    public OrganizationController(OrganizationRepository repository, OrganizationModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/organizations/{id}")
    EntityModel<Organization> one(@PathVariable Long id) {

        log.info("get organization " + id);

        Organization organization = repository.findById(id) //
                .orElseThrow(() -> new OrganizationNotFoundException(id));

        return assembler.toModel(organization);
    }

    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/organizations")
    CollectionModel<EntityModel<Organization>> all() {

        log.info("get organizations list");

        List<EntityModel<Organization>> organizations = repository.findAll().stream() //
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(organizations, linkTo(methodOn(OrganizationController.class).all()).withSelfRel());
    }
    // end::get-aggregate-root[]


    @PostMapping("/organizations")
    ResponseEntity<?> newOrganization(@RequestBody Organization newOrganization) {

        log.info("post organization " + newOrganization);

        EntityModel<Organization> entityModel = assembler.toModel(repository.save(newOrganization));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

   @PutMapping("/organizations/{id}")
   ResponseEntity<?> replaceOrganization(@RequestBody Organization newOrganization, @PathVariable Long id) {

       log.info("put organization " + id + " " + newOrganization);

       Organization updatedOrganization = repository.findById(id)
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

      EntityModel<Organization> entityModel = assembler.toModel(updatedOrganization);

      return ResponseEntity //
              .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
              .body(entityModel);
   }

    @DeleteMapping("/organizations/{id}")
    ResponseEntity<?> deleteOrganization(@PathVariable Long id) {

        log.info("delete organization " + id);

        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}

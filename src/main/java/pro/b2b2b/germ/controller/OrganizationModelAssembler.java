package pro.b2b2b.germ.controller;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import pro.b2b2b.germ.model.Organization;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
class OrganizationModelAssembler implements RepresentationModelAssembler<Organization, EntityModel<Organization>> {

    @Override
    public EntityModel<Organization> toModel(Organization organization) {

        return EntityModel.of(organization, //
                linkTo(methodOn(OrganizationController.class).one(organization.getId())).withSelfRel(),
                linkTo(methodOn(OrganizationController.class).all()).withRel("employees"));
    }
}

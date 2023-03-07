package pro.b2b2b.germ.controller;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import pro.b2b2b.germ.model.Obligation;
import pro.b2b2b.germ.model.ObligationStatus;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Component
class ObligationModelAssembler implements RepresentationModelAssembler<Obligation, EntityModel<Obligation>> {

    @Override
    public EntityModel<Obligation> toModel(Obligation obligation) {

        EntityModel<Obligation> orderModel = EntityModel.of(obligation,
                linkTo(methodOn(ObligationController.class).one(obligation.getId())).withSelfRel(),
                linkTo(methodOn(ObligationController.class).all()).withRel("obligations"));

        // Conditional links based on state of the order

        if (obligation.getStatus() == ObligationStatus.CONTRACT_IS_CONCLUDED) {
            orderModel.add(linkTo(methodOn(ObligationController.class).cancel(obligation.getId())).withRel("cancel"));
//            orderModel.add(linkTo(methodOn(ObligationController.class).activate(obligation.getId())).withRel("activate"));
        }

        return orderModel;
    }
}

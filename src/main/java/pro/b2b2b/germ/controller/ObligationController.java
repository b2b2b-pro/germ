package pro.b2b2b.germ.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.mediatype.problem.Problem;

import org.springframework.web.bind.annotation.*;
import pro.b2b2b.germ.exception.ObligationNotFoundException;

import pro.b2b2b.germ.model.Obligation;
import pro.b2b2b.germ.model.ObligationStatus;
import pro.b2b2b.germ.repository.ObligationRepository;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class ObligationController {
    private final ObligationRepository obligationRepository;
    private final ObligationModelAssembler assembler;
    private static final Logger log = LoggerFactory.getLogger(ObligationController.class);

    public ObligationController(ObligationRepository obligationRepository, ObligationModelAssembler assembler) {
        this.obligationRepository = obligationRepository;
        this.assembler = assembler;
    }

    @GetMapping("/obligations")
    CollectionModel<EntityModel<Obligation>> all() {

        log.info("get obligations list");
        List<EntityModel<Obligation>> obligations = obligationRepository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(obligations, //
                linkTo(methodOn(ObligationController.class).all()).withSelfRel());
    }

    @GetMapping("/obligations/{id}")
    EntityModel<Obligation> one(@PathVariable Long id) {
        log.info("get obligation " + id);
        Obligation obligation = obligationRepository.findById(id) //
                .orElseThrow(() -> new ObligationNotFoundException(id));

        return assembler.toModel(obligation);
    }

    @PostMapping("/obligations")
    ResponseEntity<EntityModel<Obligation>> newObligation(@RequestBody Obligation newobligation) {

        log.info("post obligation " + newobligation);

        newobligation.setStatus(ObligationStatus.CONTRACT_IS_CONCLUDED);
        Obligation newObligation = obligationRepository.save(newobligation);

        return ResponseEntity //
                .created(linkTo(methodOn(ObligationController.class).one(newObligation.getId())).toUri()) //
                .body(assembler.toModel(newObligation));
    }

    @DeleteMapping("/obligations/{id}/cancel")
    ResponseEntity<?> cancel(@PathVariable Long id) {

        log.info("delete obligation " + id);

        Obligation obligation =obligationRepository.findById(id) //
                .orElseThrow(() -> new ObligationNotFoundException(id));

        if (obligation.getStatus() == ObligationStatus.CONTRACT_IS_CONCLUDED) {
            obligation.setStatus(ObligationStatus.CANCELLED);
            return ResponseEntity.ok(assembler.toModel(obligationRepository.save(obligation)));
        }

        return ResponseEntity //
                .status(HttpStatus.METHOD_NOT_ALLOWED) //
                .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE) //
                .body(Problem.create() //
                        .withTitle("Method not allowed") //
                        .withDetail("You can't cancel an obligation that is in the " + obligation.getStatus() + " status"));
    }
}

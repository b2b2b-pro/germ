package pro.b2b2b.germ.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.b2b2b.germ.model.Organization;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
}

package pro.b2b2b.germ.exception;

public class OrganizationNotFoundException extends RuntimeException{
    public OrganizationNotFoundException(Long id) {
        super("Could not find organization " + id);
    }
}

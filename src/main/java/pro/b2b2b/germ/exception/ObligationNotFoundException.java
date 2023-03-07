package pro.b2b2b.germ.exception;

public class ObligationNotFoundException extends RuntimeException{
    public ObligationNotFoundException(Long id) {
        super("Could not find obligation " + id);
    }
}

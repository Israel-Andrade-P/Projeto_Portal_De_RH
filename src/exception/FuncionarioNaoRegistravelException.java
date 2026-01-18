package exception;

public class FuncionarioNaoRegistravelException extends RuntimeException {
    public FuncionarioNaoRegistravelException(String message) {
        super(message);
    }
}

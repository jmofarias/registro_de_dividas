package br.ufal.sd.exceptions;

public class RegistroNaoEncontradoException extends RuntimeException {
    
    public RegistroNaoEncontradoException(long id) {
        super("Não foi possível encontrar pessoa com o id: " + id);
    }
}

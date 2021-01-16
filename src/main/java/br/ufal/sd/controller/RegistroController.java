package br.ufal.sd.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import br.ufal.sd.entities.Entidade;
import br.ufal.sd.exceptions.RegistroNaoEncontradoException;

@RestController
@RequestMapping("/registros")
public class RegistroController {
    private final List<Entidade> registro = new ArrayList<>();
    private final AtomicLong contador = new AtomicLong();

    @GetMapping
    public List<Entidade> obterTodasPessoas(){
        return this.registro;
    }

    @GetMapping("/{id}")
    public Entidade obterEntidade(@PathVariable long id){
        for (Entidade p: this.registro){
            if (p.getId() == id){
                return p;
            }
        }
        throw new RegistroNaoEncontradoException(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Entidade adicionarEntidade(@RequestBody Entidade p){
        Entidade n = new Entidade(contador.incrementAndGet(), p.getNome(), p.getValor(), p.getStatus());
        this.registro.add(n);
        return n;
    }

    @PutMapping("/{id}")
    public Entidade atualizarEntidade(@RequestBody Entidade entidade, @PathVariable long id){
        for (Entidade p: this.registro){
            if (p.getId() == id){
                p.setNome(entidade.getNome());
                p.setValor(entidade.getValor());
                p.setStatus(entidade.getStatus());
                return p;
            }
        }
        throw new RegistroNaoEncontradoException(id);
    }

    @DeleteMapping("/{id}")
    public void excluirEntidade(@PathVariable long id){
        if (!this.registro.removeIf(p-> p.getId()==id)){
            throw new RegistroNaoEncontradoException(id);
        }
    }

    @ControllerAdvice
    class RegistroNaoEncontrado {
        @ResponseBody
        @ExceptionHandler(RegistroNaoEncontradoException.class)
        @ResponseStatus(HttpStatus.NOT_FOUND)
        String registroNaoEncontrado(RegistroNaoEncontradoException p){
            return p.getMessage();
        }
    }
}

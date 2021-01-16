package br.ufal.sd.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.concurrent.atomic.AtomicLong;
import br.ufal.sd.resources.Saudacao;

@RestController
public class SaudacaoController {
    private static final String MENSAGEM = "Olá, %s!";
    private final AtomicLong contador = new AtomicLong();

    @GetMapping("/saudacao")
    public Saudacao saudacao(@RequestParam(value = "nome", defaultValue = "seja bem-vindo(a) ao registro de dívidas") String nome) {
        return new Saudacao(contador.incrementAndGet(), String.format(MENSAGEM, nome));
    }

}
package br.com.isa.screenmatch.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.isa.screenmatch.domain.filme.DadosCadastroFilme;
import br.com.isa.screenmatch.domain.filme.Filme;

@Controller
@RequestMapping("/filmes")
public class FilmesController {

    private List<Filme> filmes = new ArrayList();

    @GetMapping
    public String carregaPaginaFormulario(){
        // a ideia é devolver a página html
        // devolvemos apenas o caminho da página
        return "filmes/formulario";
    }
    
    @PostMapping
    public String cadastraFilme(DadosCadastroFilme dados){
        var filme = new Filme(dados);
        filmes.add(filme);

        System.out.println(filmes);
        // vamos cadastrar o filme em memoria por enquanto
        return "filmes/formulario";
    }
}

package br.com.isa.screenmatch.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String carregaPaginaListgem(Model model){
        model.addAttribute("lista", filmes);
        return "filmes/listagem";
    }

    @GetMapping("/formulario")
    public String carregaPaginaFormulario(){
        return "filmes/formulario";
    }
    
    @PostMapping
    public String cadastraFilme(DadosCadastroFilme dados){
        var filme = new Filme(dados);
        filmes.add(filme);
        return "redirect:/filmes";
    }
}

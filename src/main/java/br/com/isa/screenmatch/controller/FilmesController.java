package br.com.isa.screenmatch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.isa.screenmatch.domain.filme.DadosCadastroFilme;
import br.com.isa.screenmatch.domain.filme.Filme;
import br.com.isa.screenmatch.domain.filme.FilmeRepository;

@Controller
@RequestMapping("/filmes")
public class FilmesController {

    // colocamos o autowired para que o proprio spring procure em nosso projeto essa repository e consiga gerenciar 
    @Autowired
    private FilmeRepository repository;

    @GetMapping
    public String carregaPaginaListgem(Model model){
        model.addAttribute("lista", repository.findAll());
        return "filmes/listagem";
    }

    @GetMapping("/formulario")
    public String carregaPaginaFormulario(Long id, Model model){
        // esse parâmetro só será preenchido se o id nao for null
        if(id != null){
            var filme = repository.getReferenceById(id);
            model.addAttribute("filme", filme);
        }
        return "filmes/formulario";
    }
    
    @PostMapping
    public String cadastraFilme(DadosCadastroFilme dados){
        var filme = new Filme(dados);
        repository.save(filme);
        return "redirect:/filmes";
    }

    @DeleteMapping
    public String removeFilme(Long id) {
            repository.deleteById(id);
            return "redirect:/filmes";
    }

}

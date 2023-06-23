package br.com.isa.screenmatch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.isa.screenmatch.domain.filme.DadosCadastroFilme;
import br.com.isa.screenmatch.domain.filme.DadosEdicaoFilme;
import br.com.isa.screenmatch.domain.filme.Filme;
import br.com.isa.screenmatch.domain.filme.FilmeRepository;
import jakarta.transaction.Transactional;

@Controller
@RequestMapping("/filmes")
public class FilmesController {

    @Autowired
    private FilmeRepository repository;

    @GetMapping
    public String carregaPaginaListgem(Model model){
        model.addAttribute("lista", repository.findAll());
        return "filmes/listagem";
    }

    @GetMapping("/formulario")
    public String carregaPaginaFormulario(Long id, Model model){
        if(id != null){
            var filme = repository.getReferenceById(id);
            model.addAttribute("filme", filme);
        }
        return "filmes/formulario";
    }
    
    @PostMapping
    @Transactional
    public String cadastraFilme(DadosCadastroFilme dados){
        var filme = new Filme(dados);
        repository.save(filme);
        return "redirect:/filmes";
    }

    @PutMapping
    @Transactional
    // iniciar uma transação no banco de dados 
    public String atualizaFilme(DadosEdicaoFilme dados){
        var filme = repository.getReferenceById(dados.id());
        filme.atualizaDados(dados);
        return "redirect:/filmes";
    }

    @DeleteMapping
    @Transactional
    public String removeFilme(Long id) {
            repository.deleteById(id);
            return "redirect:/filmes";
    }

}

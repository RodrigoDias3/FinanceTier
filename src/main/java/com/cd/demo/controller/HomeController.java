package com.cd.demo.controller;

import com.cd.demo.dao.Poupanca;
import com.cd.demo.dao.Transacao;
import com.cd.demo.dao.User;
import com.cd.demo.form.PoupancaForm;
import com.cd.demo.form.TransacaoForm;
import com.cd.demo.repository.PoupancaRepository;
import com.cd.demo.repository.TransacaoRepository;
import com.cd.demo.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TransacaoRepository transacaoRepository;
    @Autowired
    private PoupancaRepository poupancaRepository;

    public HomeController() throws RemoteException {
    }

    public Transacao registerTransacao(String data, String categoria, Float valor, boolean eReceita, Long id_user) {
        Transacao transacao = new Transacao();
        transacao.setData(data);
        transacao.setCategoria(categoria);
        transacao.seteReceita(eReceita);
        transacao.setQuantidade(valor);
        transacao.setUser(id_user);
        return transacaoRepository.save(transacao);
    }

    public Poupanca registerPoupanca(Float quantidade, Float rate, Integer prazo, Long id_user) {
        Poupanca poupanca = new Poupanca();
        poupanca.setQuantidade(quantidade);
        poupanca.setRate(rate);
        poupanca.setUser(id_user);
        poupanca.setPrazo(prazo);
        return poupancaRepository.save(poupanca);
    }

    @GetMapping("/")
    String home(ModelMap model, HttpSession session){
        model.addAttribute("session", session);
        return "pagina-inicio";
    }

    @GetMapping("/simulations")
    String simulations(ModelMap model){
        return "simu-goals";
    }

    @GetMapping("/transactions")
    String transactions(ModelMap model, HttpSession session){

        String nome = (String) session.getAttribute("name");
        String email = (String) session.getAttribute("email");

        if (nome == null) {
            return "redirect:/login";
        }

        Optional<User> user = userRepository.findByNameAndEmail(nome,email);

        if (user.isEmpty()){
            return "redirect:/login";
        }

        Float saldo = user.get().getSaldo();
        List<Transacao> transacoes = transacaoRepository.findByUserOrderByDataDesc(user.get().getId());

        model.addAttribute("saldo", saldo);
        model.addAttribute("transacoes", transacoes);
        model.addAttribute("transacaoForm", new TransacaoForm());
        return "transaction";
    }

    @PostMapping("/transactions")
    String validar_transacao(ModelMap model, @Valid @ModelAttribute("transacaoForm") TransacaoForm transacaoForm, BindingResult bindingResult, RedirectAttributes redirectAttributes, HttpSession session){
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", "Transacao falhada");
            return "redirect:/transactions";
        }

        String nome = (String) session.getAttribute("name");
        String email = (String) session.getAttribute("email");

        if (nome == null) {
            redirectAttributes.addFlashAttribute("message", "Sem loggin feito");
            return "redirect:/login";
        }

        Optional<User> user = userRepository.findByNameAndEmail(nome,email);

        if (user.isEmpty()){
            redirectAttributes.addFlashAttribute("message", "Utilizador não encontrado");
            return "redirect:/login";
        }

        if(transacaoForm.iseReceita()){
            user.get().deposita(transacaoForm.getValor());
            userRepository.save(user.get());
        } else {
            user.get().levanta(transacaoForm.getValor());
            userRepository.save(user.get());
        }

        Transacao transacao = registerTransacao(transacaoForm.data, transacaoForm.categoria, transacaoForm.valor, transacaoForm.eReceita, user.get().getId());

        return "redirect:/transactions";
    }

    @GetMapping("/savings")
    public String savings(ModelMap model, HttpSession session, RedirectAttributes redirectAttributes){
        String nome = (String) session.getAttribute("name");
        String email = (String) session.getAttribute("email");

        if (nome == null) {
            redirectAttributes.addFlashAttribute("message", "Sem loggin feito");
            return "redirect:/login";
        }

        Optional<User> user = userRepository.findByNameAndEmail(nome,email);

        if (user.isEmpty()){
            redirectAttributes.addFlashAttribute("message", "Utilizador não encontrado");
            return "redirect:/login";
        }

        List<Poupanca> poupancas = poupancaRepository.findByUser(user.get().getId());

        model.addAttribute("savings",poupancas);
        model.addAttribute("poupancaForm", new PoupancaForm());

        return "savings";
    }

    @PostMapping("/savings/{id}")
    public String receberJuros(ModelMap model, @PathVariable Long id, HttpSession session,  RedirectAttributes redirectAttributes){
        String nome = (String) session.getAttribute("name");
        String email = (String) session.getAttribute("email");

        if (nome == null) {
            redirectAttributes.addFlashAttribute("message", "Sem loggin feito");
            return "redirect:/login";
        }

        Optional<User> user = userRepository.findByNameAndEmail(nome,email);

        if (user.isEmpty()){
            redirectAttributes.addFlashAttribute("message", "Utilizador não encontrado");
            return "redirect:/login";
        }

        Optional<Poupanca> poupanca = poupancaRepository.findById(id);

        if (poupanca.isEmpty()){
            return "redirect:/savings";
        }

        poupanca.get().recebeJuros();
        poupancaRepository.save(poupanca.get());

        return "redirect:/savings";
    }

    @PostMapping("/savings/new")
    public String crearPoupanca(ModelMap model, @Valid @ModelAttribute("poupancaForm") PoupancaForm poupancaForm, BindingResult bindingResult, RedirectAttributes redirectAttributes, HttpSession session){
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", "Falha na creação da conta poupança");
            return "redirect:/savings";
        }

        String nome = (String) session.getAttribute("name");
        String email = (String) session.getAttribute("email");

        if (nome == null) {
            redirectAttributes.addFlashAttribute("message", "Sem loggin feito");
            return "redirect:/login";
        }

        Optional<User> user = userRepository.findByNameAndEmail(nome,email);

        if (user.isEmpty()){
            redirectAttributes.addFlashAttribute("message", "Utilizador não encontrado");
            return "redirect:/login";
        }

        Poupanca poupanca = registerPoupanca(poupancaForm.getQuantidade(), poupancaForm.getRate(), poupancaForm.getPrazo(), user.get().getId());

        return "redirect:/savings";
    }
}

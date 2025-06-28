package com.example.demo.controller;

import com.example.demo.entity.Utilisateur;
import com.example.demo.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
  private final UserRepository repo;

  public UserController(UserRepository repo) {
    this.repo = repo;
  }

  @GetMapping
  public List<Utilisateur> all() {
    return repo.findAll();
  }

  @PostMapping
  public Utilisateur create(@RequestBody Utilisateur user) {
    return repo.save(user);
  }

  @GetMapping("/")
  public String accueil() {
    return "Bienvenue dans le backend utilisateur !";
  }
}

package com.binubalan.pluginarchi.plugin.api.controller;

import com.binubalan.pluginarchi.plugin.api.entities.AdministratorUser;
import com.binubalan.pluginarchi.plugin.api.services.AdministratorUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin-user")
@SuppressWarnings("unused")
public class AdministratorUserController {
    private final AdministratorUserService administratorUserService;

    public AdministratorUserController(AdministratorUserService administratorUserService) {
        this.administratorUserService = administratorUserService;
    }

    @GetMapping("/getAll")
    @ResponseBody
    public List<AdministratorUser> getAll() {
        return this.administratorUserService.getAll(AdministratorUser.class);
    }

    @GetMapping("/getById/{id}")
    @ResponseBody
    public AdministratorUser getById(@PathVariable("id") String id) throws Exception {
        Optional<AdministratorUser> optional = this.administratorUserService.getById(id, AdministratorUser.class);
        if (optional.isEmpty()) {
            throw new Exception("Not Found");
        }
        return optional.get();
    }

    @PostMapping("/create")
    @ResponseBody
    public ResponseEntity<Object> create(@RequestBody AdministratorUser administrator) {
        this.administratorUserService.create(administrator);
        return new ResponseEntity<>("User created", HttpStatus.OK);
    }

    @PostMapping("/remove")
    @ResponseBody
    public ResponseEntity<Object> remove(@RequestBody String id) throws Exception {
        this.administratorUserService.removeById(id, AdministratorUser.class);
        return new ResponseEntity<>("User removed", HttpStatus.OK);
    }

}

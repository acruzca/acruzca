package com.acruzca.pradonavas.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.acruzca.pradonavas.model.PradoNava;
import com.acruzca.pradonavas.service.PradoNavaService;

import jakarta.validation.Valid;

@Controller
public class PradoNavaController {

    private final PradoNavaService pradoNavaService;

    public PradoNavaController(PradoNavaService pradoNavaService) {
        this.pradoNavaService = pradoNavaService;
    }

    @GetMapping({"/", "/pradonavas"})
    public String listarPradoNavas(Model model,
                                   @RequestParam(name = "success", required = false) String success) {
        List<PradoNava> pradoNavas = pradoNavaService.listarTodos();
        model.addAttribute("pradoNavas", pradoNavas);
        model.addAttribute("pageTitle", "Gestión de Prado Navas");
        model.addAttribute("successMessage", success);
        return "pradonavas/list";
    }

    @GetMapping("/pradonavas/nuevo")
    public String mostrarFormularioCreacion(Model model) {
        PradoNava pradoNava = new PradoNava();
        model.addAttribute("pradoNava", pradoNava);
        model.addAttribute("pageTitle", "Registrar Prado Nava");
        model.addAttribute("isEdit", false);
        return "pradonavas/form";
    }

    @GetMapping("/pradonavas/{id}/editar")
    public String mostrarFormularioEdicion(@PathVariable Long id, Model model) {
        PradoNava pradoNava = pradoNavaService.buscarPorId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Prado Nava no encontrado"));
        model.addAttribute("pradoNava", pradoNava);
        model.addAttribute("pageTitle", "Editar Prado Nava");
        model.addAttribute("isEdit", true);
        return "pradonavas/form";
    }

    @PostMapping("/pradonavas/guardar")
    public String guardarPradoNava(@Valid @ModelAttribute("pradoNava") PradoNava pradoNava,
                                   BindingResult bindingResult,
                                   RedirectAttributes redirectAttributes,
                                   Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("pageTitle", pradoNava.getId() == null ? "Registrar Prado Nava" : "Editar Prado Nava");
            model.addAttribute("isEdit", pradoNava.getId() != null);
            return "pradonavas/form";
        }

        pradoNavaService.guardar(pradoNava);
        redirectAttributes.addAttribute("success", "La información del prado se guardó correctamente");
        return "redirect:/pradonavas";
    }

    @PostMapping("/pradonavas/{id}/eliminar")
    public String eliminarPradoNava(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        pradoNavaService.eliminarPorId(id);
        redirectAttributes.addAttribute("success", "El prado se eliminó correctamente");
        return "redirect:/pradonavas";
    }
}

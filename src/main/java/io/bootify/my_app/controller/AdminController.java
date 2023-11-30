package io.bootify.my_app.controller;

import io.bootify.my_app.form.BibliotecarioForm;
import io.bootify.my_app.form.LibroForm;
import io.bootify.my_app.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/agregarLibro")
    public String mostrarFormularioAgregarLibro(Model model) {
        // Aquí podrías agregar lógica si es necesario antes de mostrar el formulario
        model.addAttribute("libroForm", new LibroForm());
        return "admin/agregarLibro";
    }

    @PostMapping("/agregarLibro")
    public String agregarLibro(@ModelAttribute LibroForm libroForm) {
        adminService.agregarLibro(libroForm);
        // Puedes redirigir a una página de éxito o a otro formulario si es necesario
        return "redirect:/admin/agregarLibro";
    }

    @GetMapping("/eliminarLibro/{idLibro}")
    public String eliminarLibro(@PathVariable Integer idLibro) {
        adminService.eliminarLibro(idLibro);
        // Puedes redirigir a una página de éxito o a otra vista si es necesario
        return "redirect:/admin";
    }

    @GetMapping("/agregarBibliotecario")
    public String mostrarFormularioAgregarBibliotecario(Model model) {
        // Aquí podrías agregar lógica si es necesario antes de mostrar el formulario
        model.addAttribute("bibliotecarioForm", new BibliotecarioForm());
        return "admin/agregarBibliotecario";
    }

    @PostMapping("/agregarBibliotecario")
    public String agregarBibliotecario(@ModelAttribute BibliotecarioForm bibliotecarioForm) {
        adminService.agregarBibliotecario(bibliotecarioForm);
        // Puedes redirigir a una página de éxito o a otro formulario si es necesario
        return "redirect:/admin/agregarBibliotecario";
    }

    @GetMapping("/eliminarBibliotecario/{idBibliotecario}")
    public String eliminarBibliotecario(@PathVariable Integer idBibliotecario) {
        adminService.eliminarBibliotecario(idBibliotecario);
        // Puedes redirigir a una página de éxito o a otra vista si es necesario
        return "redirect:/admin";
    }
}


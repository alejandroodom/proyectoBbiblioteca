package io.bootify.my_app.form;

import java.time.LocalDate;

public class BibliotecarioForm {

    private LocalDate fechaContratacion;
    private Integer idUsuario;

    public LocalDate getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(LocalDate fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
}


package io.bootify.my_app.form;

public class LectorForm {

    private Integer idLector;
    private String direccion;
    private String telefono;
    private Integer librosPrestados;

    // Getters y Setters

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Integer getLibrosPrestados() {
        return librosPrestados;
    }

    public void setLibrosPrestados(Integer librosPrestados) {
        this.librosPrestados = librosPrestados;
    }

    public Integer getIdLector() {
        return idLector;
    }

    public void setIdLector(Integer idLector) {
        this.idLector = idLector;
    }
}


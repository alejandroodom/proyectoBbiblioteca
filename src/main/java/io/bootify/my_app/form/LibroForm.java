package io.bootify.my_app.form;

public class LibroForm {

    private String titulo;
    private String autor;
    private Integer anoPublicacion;
    private Integer cantidadDisponible;
    private Boolean danado;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Integer getAnoPublicacion() {
        return anoPublicacion;
    }

    public void setAnoPublicacion(Integer anoPublicacion) {
        this.anoPublicacion = anoPublicacion;
    }

    public Integer getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(Integer cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public Boolean getDanado() {
        return danado;
    }

    public void setDanado(Boolean danado) {
        this.danado = danado;
    }
}


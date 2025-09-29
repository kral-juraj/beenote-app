package com.app.model;

public class Taxacia {
    private int id;
    private String datum;
    private String ul;
    private String poznamky;

    public Taxacia(int id, String datum, String ul, String poznamky) {
        this.id = id;
        this.datum = datum;
        this.ul = ul;
        this.poznamky = poznamky;
    }

    public int getId() { return id; }
    public String getDatum() { return datum; }
    public String getUl() { return ul; }
    public String getPoznamky() { return poznamky; }
}

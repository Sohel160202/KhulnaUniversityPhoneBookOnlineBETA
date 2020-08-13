package com.example.khulnauniversityphonebookonlinebeta;

public class Discipline {
    public String discipline_name;

    public Discipline() {
    }

    public Discipline(String discipline_name) {
        this.discipline_name = discipline_name;
    }

    @Override
    public String toString() {
        return "Discipline{" +
                "discipline_name='" + discipline_name + '\'' +
                '}';
    }

    public String getDiscipline_name() {
        return discipline_name;
    }

    public void setDiscipline_name(String discipline_name) {
        this.discipline_name = discipline_name;
    }


}

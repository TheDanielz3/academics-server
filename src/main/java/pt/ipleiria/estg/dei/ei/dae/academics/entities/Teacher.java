package pt.ipleiria.estg.dei.ei.dae.academics.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Teacher extends User {
    private String office;

    @ManyToMany(mappedBy = "teachers")
    private List<Subject> subjects;

    public Teacher() {}

    public Teacher(String username, String password, String name, String email, String office) {
        super(username, password, name, email);
        this.office = office;
        this.subjects = new ArrayList<>();
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public void addSubject(Subject subject){
        this.subjects.add(subject);
    }
    public void removeSubject(Subject subject){
        boolean foundSubject =this.subjects.contains(subject);
        if (foundSubject)
            this.subjects.remove(subject);
    }
}

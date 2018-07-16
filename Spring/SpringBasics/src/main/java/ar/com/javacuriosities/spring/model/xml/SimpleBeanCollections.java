package ar.com.javacuriosities.spring.model.xml;

import java.util.List;
import java.util.Set;

public class SimpleBeanCollections {

    private List<String> students;

    private Set<String> courses;

    public List<String> getStudents() {
        return students;
    }

    public void setStudents(List<String> students) {
        this.students = students;
    }

    public Set<String> getCourses() {
        return courses;
    }

    public void setCourses(Set<String> courses) {
        this.courses = courses;
    }
}

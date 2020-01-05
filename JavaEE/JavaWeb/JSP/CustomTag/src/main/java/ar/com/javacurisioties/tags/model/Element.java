package ar.com.javacurisioties.tags.model;

import java.io.Serializable;

public interface Element extends Serializable {

    Long getId();

    void setId(Long id);

    String getName();

    void setName(String name);
}

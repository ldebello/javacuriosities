package ar.com.javacuriosities.hibernate.model.unidirectional_join_table;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "tasks_tasks_details",
            joinColumns = {
                    @JoinColumn(name = "task_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "tasks_details_id", unique = true)
            }
    )
    private TaskDetail details;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskDetail getDetails() {
        return details;
    }

    public void setDetails(TaskDetail details) {
        this.details = details;
    }
}

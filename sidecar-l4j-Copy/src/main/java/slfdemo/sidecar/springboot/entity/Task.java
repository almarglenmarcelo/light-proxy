package slfdemo.sidecar.springboot.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String task;
    @Column
    private String description;

    public Task(String task, String description) {
        this.task = task;
        this.description = description;
    }
}

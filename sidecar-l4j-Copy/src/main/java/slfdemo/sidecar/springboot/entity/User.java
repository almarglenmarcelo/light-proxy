package slfdemo.sidecar.springboot.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "users")
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String username;

    public User(String username) {
        this.username = username;
    }
}

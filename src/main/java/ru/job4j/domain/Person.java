package ru.job4j.domain;

import lombok.*;

import javax.persistence.*;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "person")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "person_id")
    private int id;
    @NonNull
    @Column(name = "person_login")
    private String login;
    @NonNull
    @Column(name = "person_password")
    private String password;
}

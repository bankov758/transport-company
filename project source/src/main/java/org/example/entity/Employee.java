package org.example.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "photos")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "family_name")
    private String familyName;

    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;

    @ManyToMany
    private Set<Skill> skills;

}

package com.psauer.match_eps.model;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@EqualsAndHashCode
@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_department")
    private Integer idDepartment;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

}

package com.psauer.match_eps.model;

import javax.persistence.*;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@EqualsAndHashCode
@Entity
public class Eps {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_eps")
    private Integer idEps;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "general_address")
    private String generalAddress;

    @Column(name = "id_department")
    private String idDepartment;

    @Column(name = "state")
    private Boolean state;

}
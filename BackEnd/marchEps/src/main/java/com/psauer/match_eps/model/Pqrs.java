package com.psauer.match_eps.model;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@EqualsAndHashCode
@Entity
public class Pqrs {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_pqrs")
    private Integer idPqrs;

    @Column(name = "id_month")
    private String idMonth;

    @Column(name = "channel")
    private String channel;

    @Column(name = "request_type")
    private String requestType;

    @Column(name = "affected_age")
    private String affectedAge;

    @OneToOne
    @JoinColumn(name = "id_regimen_type")
    private Regimen regimen;

    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "id_eps")
    private Eps eps;

    @Column(name = "reason_macro")
    private String reasonMacro;

    @Column(name = "general_reason")
    private String generalReason;

    @Column(name = "specific_reason")
    private String specificReason;


}

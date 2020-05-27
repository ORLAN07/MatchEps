package com.psauer.match_eps.model;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@EqualsAndHashCode
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comment")
    private Integer idComment;

    @Column(name = "description")
    private String description;

    @OneToOne
    @JoinColumn(name = "id_eps")
    private Eps eps;

    @Column(name = "author")
    private String author;

}

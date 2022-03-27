package org.jastka4.digitalgamesstore.model;

import lombok.*;
import org.jastka4.digitalgamesstore.configuration.AuthorityType;

import javax.persistence.*;

@Entity
@Table(name = "authorities")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private AuthorityType name;

    public Authority(AuthorityType name) {
        this.name = name;
    }
}

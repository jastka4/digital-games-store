package org.jastka4.digitalgamesstorebackoffice.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Product {

    @Id
    @Column(name = "code", length = 20, nullable = false)
    private String code;

    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "online_catalogue", length = 1, nullable = false)
    private boolean onlineCatalogue;

    @Lob
    @Column(name = "image", length = Integer.MAX_VALUE, nullable = true)
    private byte[] image;

    //    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", nullable = false)
    private LocalDate createDate;
}

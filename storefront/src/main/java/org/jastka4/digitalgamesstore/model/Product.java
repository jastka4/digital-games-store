package org.jastka4.digitalgamesstore.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "code", length = 20, nullable = false)
    private String code;

    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "online_catalogue", length = 1, nullable = false)
    private boolean onlineCatalogue;

    @Type(type = "org.hibernate.type.BinaryType")
    @Column(name = "image", length = Integer.MAX_VALUE, nullable = true)
    @Basic(fetch = FetchType.LAZY)
    private byte[] image;

    @Column(name = "create_date", nullable = false)
    private LocalDateTime createDate;
}

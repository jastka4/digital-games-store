package org.jastka4.digitalgamesstore.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.jastka4.digitalgamesstore.forms.ProductForm;

import javax.persistence.*;
import java.io.IOException;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    public Product(final ProductForm productForm) throws IOException {
        this.code = productForm.getCode();
        this.name = productForm.getName();
        this.price = productForm.getPrice();
        this.image = productForm.getFileData().getBytes();
        this.onlineCatalogue = false;
        this.createDate = LocalDateTime.now();
    }
}

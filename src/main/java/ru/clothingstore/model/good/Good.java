package ru.clothingstore.model.good;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.clothingstore.model.product.Product;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "Good")
@EqualsAndHashCode(exclude = {"id", "category", "cartProducts"})
@ToString(exclude = "cartProducts")
@NoArgsConstructor
public class Good {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(min = 1, max = 64)
    @Column(name = "title")
    private String title;

    @Column(name = "active", columnDefinition = "boolean default true")
    private Boolean active = true;

    @Size(min = 3)
    @Column(name = "description")
    private String description;

    @Column(name = "small_image_link")
    private String smallImageLink;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    @Digits(integer = 9, fraction = 2)
    private Double price;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @OneToMany(mappedBy = "good", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Product> cartProducts = new HashSet<>();
}
package ru.clothingstore.model.news;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Data
@Table(name = "News")
@EqualsAndHashCode(exclude={"id"})
@ToString(exclude = "newsImageLink")
@NoArgsConstructor
public class News {

    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(min = 3, max = 32)
    @Column(name = "title")
    private String title;

    @Size(min = 3)
    @Column(name = "description")
    private String description;

    @Column(name = "news_image_link")
    private String newsImageLink;

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "date")
    private Date date;

}
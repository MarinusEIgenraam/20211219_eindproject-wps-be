package com.willpowered.eindprojectwpsbe.model.elements;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.willpowered.eindprojectwpsbe.model.auth.User;
import com.willpowered.eindprojectwpsbe.model.elements.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "blogs")
public class Blog {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long blogId;

    @NotBlank(message = "Blog name can not be Null")
    private String blogName;

    @Nullable
    private String url;

    @Nullable
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private String description;

    private Instant startTime;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "blog_owner", referencedColumnName = "username")
    private User blogOwner;

}

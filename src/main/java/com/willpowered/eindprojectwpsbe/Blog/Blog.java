package com.willpowered.eindprojectwpsbe.Blog;


import com.sun.istack.Nullable;
import com.willpowered.eindprojectwpsbe.User.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
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
    private String imageUrl;

    @Nullable
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private String description;

    private Instant startTime;

    @ManyToOne(fetch = LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "blog_owner", referencedColumnName = "username")
    private User blogOwner;

}

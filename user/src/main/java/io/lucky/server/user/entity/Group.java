package io.lucky.server.user.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "Group")
public class Group extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;

    public static Group newInstance(String name){
        return new Group(null, name);
    }

    private Group(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}

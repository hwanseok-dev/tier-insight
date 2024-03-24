package io.lucky.server.user.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "Team")
public class Team extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "team_id")
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;

    public static Team newInstance(String name){
        return new Team(null, name);
    }

    private Team(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}

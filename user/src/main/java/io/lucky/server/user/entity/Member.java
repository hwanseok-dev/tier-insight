package io.lucky.server.user.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "Member")
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String nickname;
    @Column(nullable = false)
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    @OneToMany
    private Set<Tier> tierSet = new HashSet<>();

    public static Member newInstance(String email,
                                     String nickname,
                                     String password){
        return new Member(null, email, nickname, password);
    }

    private Member(Long id, String email, String nickname, String password) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
    }

    public void addTeam(Team team){
        Assert.notNull(team, "Group must be not null");
        this.team = team;
    }

    public void removeTeam(Team team) {
        Assert.isTrue(this.team == team, "Group must be equal");
        this.team = null;
    }

    public void addTier(Tier tier){
        Assert.isTrue(!tierSet.contains(tier), "You can only add tiers that have not been added");
        tierSet.add(tier);
    }

    public void removeTier(Tier tier){
        Assert.isTrue(tierSet.contains(tier), "You can only remove tiers that have been added");
        tierSet.remove(tier);
    }
}

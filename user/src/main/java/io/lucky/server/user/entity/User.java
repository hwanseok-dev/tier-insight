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
@Table(name = "User")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String nickname;
    @Column(nullable = false)
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;

    @OneToMany
    private Set<Tier> tierSet = new HashSet<>();

    public static User newInstance(String email,
                                   String nickname,
                                   String password){
        return new User(null, email, nickname, password);
    }

    private User(Long id, String email, String nickname, String password) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
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

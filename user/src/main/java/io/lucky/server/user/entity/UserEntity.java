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
public class UserEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String email;
    private String nickname;
    private String password;
    @OneToMany
    private Set<TierEntity> tierSet = new HashSet<>();

    public static UserEntity newInstance(String email,
                                         String nickname,
                                         String password){
        return new UserEntity(null, email, nickname, password);
    }

    private UserEntity(Long id, String email, String nickname, String password) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
    }

    public void addTier(TierEntity tier){
        Assert.isTrue(!tierSet.contains(tier), "You can only add tiers that have not been added");
        tierSet.add(tier);
    }

    public void removeTier(TierEntity tier){
        Assert.isTrue(tierSet.contains(tier), "You can only remove tiers that have been added");
        tierSet.remove(tier);
    }
}

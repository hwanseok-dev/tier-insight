package io.lucky.server.user.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "Tier", uniqueConstraints = {
        @UniqueConstraint(name = "UNIQUE_KEY_VALUE", columnNames = {"tierKey", "tierValue"})
})
public class TierEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(value = EnumType.STRING)
    private TierKey tierKey;
    @Enumerated(value = EnumType.STRING)
    private TierValue tierValue;

    public TierEntity(Long idOrNull,
                      TierKey tierKey,
                      TierValue tierValue) {
        this.id = idOrNull;
        this.tierKey = tierKey;
        this.tierValue = tierValue;
    }
}

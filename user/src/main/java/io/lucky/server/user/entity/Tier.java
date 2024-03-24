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
public class Tier extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tier_id")
    private Long id;

    @Enumerated(value = EnumType.STRING)
    @Column(columnDefinition = "varchar(255)")
    private TierKey tierKey;

    @Enumerated(value = EnumType.STRING)
    @Column(columnDefinition = "varchar(255)")
    private TierValue tierValue;

    public static Tier newInstance(TierKey key, TierValue value) {
        return new Tier(null, key, value);
    }

    private Tier(Long id, TierKey tierKey, TierValue tierValue) {
        this.id = id;
        this.tierKey = tierKey;
        this.tierValue = tierValue;
    }
}

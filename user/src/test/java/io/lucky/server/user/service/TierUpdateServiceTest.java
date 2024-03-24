package io.lucky.server.user.service;

import io.lucky.server.user.entity.Member;
import io.lucky.server.user.entity.Tier;
import io.lucky.server.user.entity.TierKey;
import io.lucky.server.user.entity.TierValue;
import io.lucky.server.user.service.dto.UserCreateForm;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class TierUpdateServiceTest {

    @Autowired
    private MemberSimpleQuery memberSimpleQuery;
    @Autowired
    private TierUpdateService tierUpdateService;
    @Autowired
    private MemberCreateService memberCreateService;
    @Autowired
    private TierSimpleQuery tierSimpleQuery;
    @Autowired
    private EntityManager em;

    @BeforeEach
    void init(){
        UserCreateForm form = getUserCreateForm();
        memberCreateService.create(form);
    }

    @Test
    void addTier() {
        // given
        Member member = memberSimpleQuery.findOneByEmailOrThrow(TestUtil.USER_EMAIL);

        // when
        tierUpdateService.addTier(member.getId(), TierKey.JAVA, TierValue.BRONZE);
        em.flush();
        em.clear();

        // then
        Member memberWithTier = memberSimpleQuery.findOneByEmailOrThrow(TestUtil.USER_EMAIL);
        Assertions.assertThat(memberWithTier.getTierSet().size()).isEqualTo(1);
    }

    @Test
    void removeTier() {
        // given
        Member member = memberSimpleQuery.findOneByEmailOrThrow(TestUtil.USER_EMAIL);
        tierUpdateService.addTier(member.getId(), TierKey.JAVA, TierValue.BRONZE);
        em.flush();
        em.clear();

        // when
        Member memberWithTier = memberSimpleQuery.findOneByEmailOrThrow(TestUtil.USER_EMAIL);
        Tier tier = tierSimpleQuery.findByIdOrThrow(TierKey.JAVA, TierValue.BRONZE);
        memberWithTier.removeTier(tier);
        em.flush();
        em.clear();

        // then
        memberWithTier = memberSimpleQuery.findOneByEmailOrThrow(TestUtil.USER_EMAIL);
        Assertions.assertThat(memberWithTier.getTierSet().size()).isEqualTo(0);
    }

    private UserCreateForm getUserCreateForm() {
        UserCreateForm form = new UserCreateForm();
        form.setEmail(TestUtil.USER_EMAIL);
        form.setNickname(TestUtil.USER_NICKNAME);
        form.setPassword(TestUtil.USER_PASSWORD);
        return form;
    }
}
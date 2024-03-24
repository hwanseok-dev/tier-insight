package io.lucky.server.user.service;

import io.lucky.server.user.entity.Tier;
import io.lucky.server.user.entity.TierKey;
import io.lucky.server.user.entity.TierValue;
import io.lucky.server.user.entity.User;
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
class UserUpdateServiceTest {

    @Autowired
    private UserCreateService userCreateService;
    @Autowired
    private UserSimpleQuery userSimpleQuery;
    @Autowired
    private UserUpdateService userUpdateService;
    @Autowired
    private TierCreateService tierCreateService;
    @Autowired
    private TierSimpleQuery tierSimpleQuery;
    @Autowired
    private EntityManager em;

    @BeforeEach
    void init(){
        UserCreateForm form = getUserCreateForm();
        userCreateService.create(form);
    }

    @Test
    void addTier() {
        // given
        User user = userSimpleQuery.findByEmailOrThrow(TestUtil.USER_EMAIL);

        // when
        userUpdateService.addTier(user.getId(), TierKey.JAVA, TierValue.BRONZE);
        em.flush();
        em.clear();

        // then
        User userWithTier = userSimpleQuery.findByEmailOrThrow(TestUtil.USER_EMAIL);
        Assertions.assertThat(userWithTier.getTierSet().size()).isEqualTo(1);
    }

    @Test
    void removeTier() {
        // given
        User user = userSimpleQuery.findByEmailOrThrow(TestUtil.USER_EMAIL);
        userUpdateService.addTier(user.getId(), TierKey.JAVA, TierValue.BRONZE);
        em.flush();
        em.clear();

        // when
        User userWithTier = userSimpleQuery.findByEmailOrThrow(TestUtil.USER_EMAIL);
        Tier tier = tierSimpleQuery.findByIdOrThrow(TierKey.JAVA, TierValue.BRONZE);
        userWithTier.removeTier(tier);
        em.flush();
        em.clear();

        // then
        userWithTier = userSimpleQuery.findByEmailOrThrow(TestUtil.USER_EMAIL);
        Assertions.assertThat(userWithTier.getTierSet().size()).isEqualTo(0);
    }

    private UserCreateForm getUserCreateForm() {
        UserCreateForm form = new UserCreateForm();
        form.setEmail(TestUtil.USER_EMAIL);
        form.setNickname(TestUtil.USER_NICKNAME);
        form.setPassword(TestUtil.USER_PASSWORD);
        return form;
    }
}
package io.lucky.server.user.service;

import io.lucky.server.user.entity.Member;
import io.lucky.server.user.exception.BusinessException;
import io.lucky.server.user.service.dto.UserCreateForm;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class MemberCreateServiceTest {
    @Autowired
    private MemberCreateService memberCreateService;
    @Autowired
    private MemberSimpleQuery memberSimpleQuery;

    @Test
    @DisplayName("유저 생성")
    void create() {
        // given
        UserCreateForm form = getUserCreateForm();

        // when
        Long savedId = memberCreateService.create(form);
        Member entity = memberSimpleQuery.findOneByIdOrThrow(savedId);

        // then
        assertThat(savedId).isNotNull();
        assertThat(entity.getId()).isNotNull();
        assertThat(entity.getEmail()).isEqualTo(TestUtil.USER_EMAIL);
        assertThat(entity.getNickname()).isEqualTo(TestUtil.USER_NICKNAME);
        assertThat(entity.getPassword()).isEqualTo(TestUtil.USER_PASSWORD);
        assertThat(entity.getCreateTime()).isNotNull();
        assertThat(entity.getLastModifiedTime()).isNotNull();
    }

    @Test
    @DisplayName("유저 이메일은 유니크하다")
    void uniqueEmail() {
        // given
        UserCreateForm form = getUserCreateForm();
        memberCreateService.create(form);

        // when then
        Assertions.assertThatThrownBy(() -> memberCreateService.create(form))
                .isInstanceOf(BusinessException.class);
    }

    private UserCreateForm getUserCreateForm() {
        UserCreateForm form = new UserCreateForm();
        form.setEmail(TestUtil.USER_EMAIL);
        form.setNickname(TestUtil.USER_NICKNAME);
        form.setPassword(TestUtil.USER_PASSWORD);
        return form;
    }
}
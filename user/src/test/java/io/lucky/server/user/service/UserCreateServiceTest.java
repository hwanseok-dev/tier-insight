package io.lucky.server.user.service;

import io.lucky.server.user.service.dto.UserCreateForm;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class UserCreateServiceTest {

    @Autowired
    private UserCreateService userCreateService;

    @Test
    @DisplayName("유저 생성")
    void create() {
        // given
        UserCreateForm form = getUserCreateForm();

        // when
        Long savedId = userCreateService.create(form);

        // then
        assertThat(savedId).isNotNull();
    }

    @Test
    @DisplayName("유저 이메일은 유니크하다")
    void uniqueEmail() {
        // given
        UserCreateForm form = getUserCreateForm();
        userCreateService.create(form);

        // when then
        Assertions.assertThatThrownBy(() -> userCreateService.create(form))
                .isInstanceOf(DataAccessException.class);
    }

    private UserCreateForm getUserCreateForm() {
        UserCreateForm form = new UserCreateForm();
        form.setEmail("test@gmail.com");
        form.setNickname("testNickName");
        form.setPassword("testPassword");
        return form;
    }
}
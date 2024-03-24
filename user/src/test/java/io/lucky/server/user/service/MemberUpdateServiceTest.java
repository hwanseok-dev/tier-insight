package io.lucky.server.user.service;

import io.lucky.server.user.service.dto.UserCreateForm;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class MemberUpdateServiceTest {

    @Autowired
    private MemberCreateService memberCreateService;
    @Autowired
    private MemberSimpleQuery memberSimpleQuery;
    @Autowired
    private MemberUpdateService memberUpdateService;
    @Autowired
    private TierCreateService tierCreateService;
    @Autowired
    private TierSimpleQuery tierSimpleQuery;
    @Autowired
    private EntityManager em;

    @BeforeEach
    void init(){
        UserCreateForm form = getUserCreateForm();
        memberCreateService.create(form);
    }

    private UserCreateForm getUserCreateForm() {
        UserCreateForm form = new UserCreateForm();
        form.setEmail(TestUtil.USER_EMAIL);
        form.setNickname(TestUtil.USER_NICKNAME);
        form.setPassword(TestUtil.USER_PASSWORD);
        return form;
    }
}
package io.lucky.server.user.service;

import io.lucky.server.user.service.dto.GroupCreateForm;
import io.lucky.server.user.service.dto.UserCreateForm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class TeamUpdateServiceTest {
    @Autowired
    private GroupCreateService groupCreateService;
    @Autowired
    private MemberCreateService memberCreateService;
    @Autowired
    private GroupUpdateService groupUpdateService;
    @Autowired
    private GroupSimpleQuery groupSimpleQuery;
    @Autowired
    private MemberSimpleQuery memberSimpleQuery;

    @BeforeEach
    void init(){
        groupCreateService.create(getGroupCreateForm());
        memberCreateService.create(getUserCreateForm());
    }

    @Test
    void addMember() {
        Long groupId = groupSimpleQuery.findIdByNameOrThrow(TestUtil.GROUP_NAME);
        Long memberId = memberSimpleQuery.findIdByEmailOrThrow(TestUtil.USER_EMAIL);
        groupUpdateService.addMember(groupId, memberId);
    }

    @Test
    void removeMember() {
    }

    private UserCreateForm getUserCreateForm() {
        UserCreateForm form = new UserCreateForm();
        form.setEmail(TestUtil.USER_EMAIL);
        form.setNickname(TestUtil.USER_NICKNAME);
        form.setPassword(TestUtil.USER_PASSWORD);
        return form;
    }

    private GroupCreateForm getGroupCreateForm(){
        GroupCreateForm groupCreateForm = new GroupCreateForm();
        groupCreateForm.setName(TestUtil.GROUP_NAME);
        return groupCreateForm;
    }
}
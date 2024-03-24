package io.lucky.server.user.service;

import io.lucky.server.user.entity.Member;
import io.lucky.server.user.entity.Team;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class GroupUpdateService {
    private final GroupSimpleQuery groupSimpleQuery;
    private final MemberSimpleQuery memberSimpleQuery;
    public boolean addMember(Long groupId, Long memberId) {
        Team team = groupSimpleQuery.findOneByIdOrThrow(groupId);
        Member member = memberSimpleQuery.findOneByIdOrThrow(memberId);
        member.addTeam(team);
        log.info("msg : {}, groupId : {}, memberId : {}", "add member", groupId, memberId);
        return true;
    }

    public boolean removeMember(Long groupId, Long memberId) {
        Team team = groupSimpleQuery.findOneByIdOrThrow(groupId);
        Member member = memberSimpleQuery.findOneByIdOrThrow(memberId);
        member.addTeam(team);
        log.info("msg : {}, groupId : {}, memberId : {}", "remove member", groupId, memberId);
        return true;
    }
}

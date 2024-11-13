package CRUD.example.CRUD.security;

import CRUD.example.CRUD.domain.Member;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

public class SecurityUser extends User {
    private Member member;

    public SecurityUser(Member member) {
        super(member.getId().toString(), member.getPassword(),
                AuthorityUtils.createAuthorityList("ROLE_" + member.getRole().toString()));
        this.member = member;
    }

    public Member getMember() {
        return member;
    }
}

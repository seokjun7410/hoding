package com.msa.member.domain.model;

import com.msa.member.domain.model.vo.*;
import lombok.*;
import org.apache.kafka.common.config.types.Password;
import javax.persistence.Access;
import javax.persistence.AccessType;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Access(AccessType.FIELD)
@ToString
@Builder(access = AccessLevel.PRIVATE)
public class Member {
    private Long MemberNo;
    private IDName idName;
    private Password password;
    private Email email;

    private List<Authority> authorites = new ArrayList<>();

    private Point point;

    public static Member registerMember(IDName idName, Password pwd, Email email) {
        Member member = Member.builder()
                .idName(idName)
                .password(pwd)
                .email(email)
                .point(Point.createPoint())
                .authorites(List.of(new Authority(UserRole.USER)))
                .build();
        return member;
    }

    private void addAuthority(Authority authority) {
        this.authorites.add(authority);
    }

    public long savePoint(long point) {
        return this.point.addPoint(point);
    }

    public long usePoint(long point) throws Exception {
        return this.point.removePoint(point);
    }

    public Member login(IDName idNname, Password password) {
        return this;
    }

    public void logout(IDName idName) {
    }
}

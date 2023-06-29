package com.emsys.emsyswebsitebackend.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@Entity
@Table(name = "user_account")
public class User extends AuditingFields {

    @Id
    private String studentId;

    @Column(nullable = false)
    private String password;

    private String nickname;

    @Column(nullable = false)
    private Boolean graduated;

    private String contact;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private Boolean isExecutive;

    private String githubId;

    private String baekjoonId;


    protected User() {
    }

    // 모든 필드를 초기화하는 생성자
    private User(String studentId, String password, String nickname, Boolean graduated, String contact,
                 String email, Boolean isExecutive,
                 String githubId, String baekjoonId) {
        this.studentId = studentId;
        this.password = password;
        this.nickname = nickname;
        this.graduated = graduated;
        this.contact = contact;
        this.email = email;
        this.isExecutive = isExecutive;
        this.githubId = githubId;
        this.baekjoonId = baekjoonId;
    }
//    이는 모든 필드를 초기화하는 생성자이다 private 으로 선언되었는데 이는 객체 생성을 정적 팩토리 메서드를 통해서만
//    가능하도록 제한하기 위함이다

    // 팩토리 메서드
    public static User of(String studentId, String password, String nickname, Boolean graduated, String contact,
                          String email, Boolean isExecutive, String githubId, String baekjoonId) {
        return new User(studentId, password, nickname, graduated, contact, email, isExecutive,
                githubId, baekjoonId);
    }
    //정적 팩토리 메서드이다. 이름을 가질 수 있어 생정자에 비해 가독성이 좋고 호출할 때마다 새로운 객체를 생성하지 않아도 된다.
    //또한 반환 타입의 하위 타입 객체를 반환할 수 있는 유연성을 가지고 있다.
    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        User user = (User) o;
        return getStudentId() != null && Objects.equals(getStudentId(), user.getStudentId());
    }
    //이 메서드는 객체 동일성을 판단하기 위해 오버리아드 된 'equals()' 메서드입니다. 여기서는 'userId'가 같은지를
    //비교하여 객체 동일성을 판단합니다. 이 메서들르 오버라이드 하지 않으면, 객체의 레퍼런스를 비교하는 기본
    //'equals()'메서드가 사용되어 의도치 않은 결과가 나올 수 있습니다.

    @Override
    public final int hashCode() {
        return getClass().hashCode();
    }
    // 이 메서드는 객체의 해시 코드를 생성하기 위해 오버라이드 된 메서드입니다.
    // 'equals()' 메서드가 'true' 를 반환하는 두 객체는 같은 해시 코드를 반환해야 합니다. 따라서
    // 'equals()' 를 오버라이드 하면 'hashCode()' 도 함께 오버라이드 해야합니다.
    // 그렇지 않으면 해시 기반의 컬렉션에서 의도치 않은 동작을 할 수 있습니다.
}
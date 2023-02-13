package offworkseekers.unnamed.db.entity;

import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@Getter
@NoArgsConstructor
public class User {

    @Id
    @Column(name = "user_id")
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private String userId;

    @Column(name = "email", length = 50)
    @Size(max = 50)
    private String email;

    @Column(name = "nickname", length = 20)
    @Size(max = 20)
    private String nickName;

    @Column(name = "picture")
    private String picture;

    @Column(name = "role_type")
    @Enumerated(EnumType.STRING)
        private RoleType roleType;

//    @OneToMany(mappedBy = "user")
//    private List<TeamMember> teamMemberIds = new ArrayList<>();
//
//    @OneToMany(mappedBy = "user")
//    private List<Film> films = new ArrayList<>();
//
//    @OneToMany(mappedBy = "user")
//    private List<Article> articles = new ArrayList<>();
//
//    @OneToMany(mappedBy = "user")
//    private List<Comment> comments = new ArrayList<>();

//    @OneToMany(mappedBy = "follower")
//    private List<Follow> followerIds = new ArrayList<>();
//
//    @OneToMany(mappedBy = "following")
//    private List<Follow> followingIds = new ArrayList<>();


    @Builder
    public User(
            @NotNull String userId,
            @NotNull @Size(max = 50) String email,
            @Size(max = 20) String nickName,
            String picture,
            RoleType roleType
    ){
        this.userId = userId;
        this.email = email;
        this.nickName = nickName;
        this.picture = picture;
        this.roleType = roleType;
    }


    public User update(String nickName, String picture){
        this.nickName = nickName;
        this.picture = picture;
        return this;
    }

    public String getRoleKey(){
        return this.roleType.getKey();
    }

    /**
     * UID를 암호화
     * @param passwordEncoder 암호화 할 인코더 클래스
     * @return 변경된 유저 Entity
     */
    public User hashUID(PasswordEncoder passwordEncoder) {
        this.userId = passwordEncoder.encode(this.userId);
        return this;
    }

    /**
     * 비밀번호 확인
     * @param plainUID 암호화 이전의 userID
     * @param passwordEncoder 암호화에 사용된 클래스
     * @return true | false
     */
    public boolean checkPassword(String plainUID, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(plainUID, this.userId);
    }
}
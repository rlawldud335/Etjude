package offworkseekers.unnamed.db.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RoleType {

    GUEST("ROLE_GUEST", "게스트"),
    USER("ROLE_USER", "일반 사용자"),
    ADMIN("ROLE_AMID", "관리자");

    private final String key;
    private final String roleName;
}
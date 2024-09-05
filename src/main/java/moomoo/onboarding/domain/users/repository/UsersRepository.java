package moomoo.onboarding.domain.users.repository;

import moomoo.onboarding.domain.users.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {

    boolean existsByUsername(String username);
}

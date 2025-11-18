package xyz.riannegreiros.spring_nextjs_start_kit.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.riannegreiros.spring_nextjs_start_kit.users.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}

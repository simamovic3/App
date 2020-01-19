package com.tim4.app.account.repositories;

import com.tim4.app.account.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Integer> {

    User findByUsernameAndRole(String username, Integer role);
    User findByUsername(String username);
    User findByRole(Integer role);
    Collection<User> findUsersByRole(Integer role);
}

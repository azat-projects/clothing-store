package ru.clothingstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.clothingstore.model.person.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByLastNameAndFirstName(String lastName, String firstName);

    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

    User findByActivationCode(String code);
}

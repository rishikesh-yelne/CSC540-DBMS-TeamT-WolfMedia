package edu.ncsu.csc540.s23.backend.repository;

import edu.ncsu.csc540.s23.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}

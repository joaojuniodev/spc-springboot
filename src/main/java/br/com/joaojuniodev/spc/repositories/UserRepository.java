package br.com.joaojuniodev.spc.repositories;

import br.com.joaojuniodev.spc.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.username =:username")
    User findByUserName(@Param("username") String username);

    @Query("SELECT u.fullName FROM User u WHERE u.username = :username")
    String findFullNameByUser(@Param("username") String username);
}

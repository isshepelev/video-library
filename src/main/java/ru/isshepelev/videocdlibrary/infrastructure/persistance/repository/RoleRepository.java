package ru.isshepelev.videocdlibrary.infrastructure.persistance.repository;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.isshepelev.videocdlibrary.infrastructure.persistance.entity.Role;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByRoleName(String roleName);


    @Query("SELECT r FROM Role r WHERE r.roleName IN :roleNames")
    List<Role> findByRoleNames(@Param("roleNames") List<String> roleNames);
}

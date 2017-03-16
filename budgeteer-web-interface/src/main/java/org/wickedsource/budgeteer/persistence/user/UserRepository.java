package org.wickedsource.budgeteer.persistence.user;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

    @Query("select u from UserEntity u where u.name = :name")
    public UserEntity findByName(@Param("name") String name);

    @Query("select u from UserEntity u where u.id not in (select u2.id from UserEntity u2 join u2.authorizedProjects p where p.id = :projectId)")
    public List<UserEntity> findNotInProject(@Param("projectId") long projectId);

    @Query("select u from UserEntity u join u.authorizedProjects p where p.id = :projectId")
    public List<UserEntity> findInProject(@Param("projectId") long projectId);

}

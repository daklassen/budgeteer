package org.wickedsource.budgeteer.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wickedsource.budgeteer.persistence.project.ProjectEntity;
import org.wickedsource.budgeteer.persistence.project.ProjectRepository;
import org.wickedsource.budgeteer.persistence.user.UserEntity;
import org.wickedsource.budgeteer.persistence.user.UserRepository;
import org.wickedsource.budgeteer.service.UnknownEntityException;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserMapper mapper;

    /**
     * Returns a list of all users that currently have access to the given project.
     *
     * @param projectId ID of the project whose users to load.
     * @return list of all users with access to the given project.
     */
    public List<User> getUsersInProject(long projectId) {
        return mapper.map(userRepository.findInProject(projectId));
    }

    /**
     * Returns a list of all users that currently DO NOT have access to the given project.
     *
     * @param projectId ID of the project
     * @return list of all users that currently DO NOT have access to the given project.
     */
    public List<User> getUsersNotInProject(long projectId) {
        return mapper.map(userRepository.findNotInProject(projectId));
    }

    /**
     * Removes the the access of the given user to the given project.
     *
     * @param projectId ID of the project for which to remove access
     * @param userId    ID of the user whose access to remove.
     */
    public void removeUserFromProject(long projectId, long userId) {
        ProjectEntity project = projectRepository.findOne(projectId);
        if (project == null) {
            throw new UnknownEntityException(ProjectEntity.class, projectId);
        }
        UserEntity user = userRepository.findOne(userId);
        if (user == null) {
            throw new UnknownEntityException(UserEntity.class, userId);
        }
        user.getAuthorizedProjects().remove(project);
        project.getAuthorizedUsers().remove(user);
    }

    /**
     * Adds the given user to the given project so that this user now has access to it.
     *
     * @param projectId ID of the project.
     * @param userId    ID of the user.
     */
    public void addUserToProject(long projectId, long userId) {
        ProjectEntity project = projectRepository.findOne(projectId);
        if (project == null) {
            throw new UnknownEntityException(ProjectEntity.class, projectId);
        }
        UserEntity user = userRepository.findOne(userId);
        if (user == null) {
            throw new UnknownEntityException(UserEntity.class, userId);
        }
        user.getAuthorizedProjects().add(project);
        project.getAuthorizedUsers().add(user);
    }

    public User login(String username) {
        UserEntity entity = userRepository.findByName(username);
        if (entity == null) {
            // register on first login
            registerUser(username);
            entity = userRepository.findByName(username);
        }

        return mapper.map(entity);
    }

    public void registerUser(String username){
        UserEntity user = new UserEntity();
        user.setName(username);
        userRepository.save(user);
    }
}

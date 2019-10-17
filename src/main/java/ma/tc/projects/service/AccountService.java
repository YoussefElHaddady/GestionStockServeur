package ma.tc.projects.service;

import ma.tc.projects.entity.AppRole;
import ma.tc.projects.entity.AppUser;

import java.util.List;

public interface AccountService {


    public AppUser saveUser(String username, String password, String confirmedPasssword);

    public AppRole save(AppRole role);

    public AppUser loadUserByUsername(String username);


    public void addRoleToUser(String username, String rolename);

    public List<AppUser> getUsers();

    public AppUser BloquerUser(String username);

    public AppUser DebloquerUser(String username);
}


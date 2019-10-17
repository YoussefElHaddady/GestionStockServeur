package ma.tc.projects.service;

import ma.tc.projects.entity.AppRole;
import ma.tc.projects.entity.AppUser;
import ma.tc.projects.repository.AppRoleRepository;
import ma.tc.projects.repository.AppUserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AcountServiceImpl implements AccountService {
    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    public AcountServiceImpl(AppUserRepository appUserRepository, AppRoleRepository appRoleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.appUserRepository = appUserRepository;
        this.appRoleRepository = appRoleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public AppUser saveUser(String username, String password, String confirmedPasssword) {

    if(appUserRepository.findByUsername(username).isPresent()==true) throw new RuntimeException("User already exisit");
    if(!password.equals(confirmedPasssword)) throw new RuntimeException("Your password of confirrmation is incorrect");

    AppUser appUser=new AppUser();
    appUser.setUsername(username);
    appUser.setPassword(bCryptPasswordEncoder.encode(password));
    appUser.setActived(true);
    appUserRepository.save(appUser);
    addRoleToUser(username,"USER");
    return appUser;
    }

    @Override
    public AppRole save(AppRole role) {
        return appRoleRepository.save(role);
    }

    @Override
    public AppUser loadUserByUsername(String username) {
        return appUserRepository.findByUsername(username).get();
    }

    @Override
    public void addRoleToUser(String username, String rolename) {
        appUserRepository.findByUsername(username).get().getRoles().add(appRoleRepository.findByRoleName(rolename));
    }


    @Override
    public List<AppUser> getUsers() {
        List<AppUser> users=new ArrayList<>();

        for (AppUser appUser : this.appUserRepository.findAll()) {
                appUser.getRoles().forEach(r->{
                    if(r.getRoleName().equals("USER") && appUser.getRoles().size()==1)
                        users.add(appUser);

                });
        }
        return users;
    }

    @Override
    public AppUser BloquerUser(String username) {

             AppUser u=appUserRepository.findByUsername(username).get();
             u.setActived(false);
             return appUserRepository.save(u);
    }

    @Override
    public AppUser DebloquerUser(String username) {

        AppUser u=appUserRepository.findByUsername(username).get();
        u.setActived(true);
        return appUserRepository.save(u);
    }
}

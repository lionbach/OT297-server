package com.alkemy.ong.auth.config.seeder;

import com.alkemy.ong.models.entity.RoleEntity;
import com.alkemy.ong.models.entity.UserEntity;
import com.alkemy.ong.repository.RoleRepository;
import com.alkemy.ong.repository.UserRepository;
import com.alkemy.ong.utils.RoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.lang.System.Logger.Level;
import java.sql.Timestamp;
import java.util.Set;



//me avisa que el UserSeeder nunca es usado
@Component
public class UserSeeder implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RoleRepository roleRepository;

    private static final System.Logger LOOGER = System.getLogger("Mi log");

    @Override
    public void run(String... args) throws Exception {
        this.loadSeedersUsers();
    }

    private void loadSeedersUsers(){
        LOOGER.log(Level.INFO, "iniciando seeder");
        Set<RoleEntity> roleAdmin = roleRepository.findByName(RoleEnum.ADMIN.getFullRoleName());
        Set<RoleEntity> roleUser = roleRepository.findByName(RoleEnum.USER.getFullRoleName());

        this.userRepository.save(new UserEntity("Guillermo","Pintos","pintos-email@gmail.com",passwordEncoder.encode("12345"),"stringPath",new Timestamp(System.currentTimeMillis()),roleAdmin));


        this.userRepository.save(new UserEntity("Ignacio","Fernan","ignacio-email@gmail.com",passwordEncoder.encode("12345"),"stringPath",new Timestamp(System.currentTimeMillis()),roleUser));
    }
}

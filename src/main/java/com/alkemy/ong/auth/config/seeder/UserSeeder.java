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


// me avisa que el UserSeeder nunca es usado
@Component
public class UserSeeder implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RoleRepository roleRepository;

    private static final System.Logger LOGGER = System.getLogger("Mi log");

    @Override
    public void run(String... args) throws Exception {
        this.loadSeedersUsers();
    }

    private void loadSeedersUsers() {
        LOGGER.log(Level.INFO, "Inicializing seeder");
        if (roleRepository.findAll().isEmpty() && userRepository.findAll().isEmpty()) {

            // Create Roles
            roleRepository.save(new RoleEntity(RoleEnum.USER.getFullRoleName(),null,new Timestamp(System.currentTimeMillis())));
            roleRepository.save(new RoleEntity(RoleEnum.ADMIN.getFullRoleName(),null,new Timestamp(System.currentTimeMillis())));

            Set<RoleEntity> roleAdmin = roleRepository.findByName(RoleEnum.ADMIN.getFullRoleName());
            Set<RoleEntity> roleUser = roleRepository.findByName(RoleEnum.USER.getFullRoleName());
            // Create Users
            this.userRepository.save(new UserEntity("Marta", "Sanchez", "marta-email@gmail.com", passwordEncoder.encode("12345"), "stringPath", new Timestamp(System.currentTimeMillis()), roleAdmin));
            this.userRepository.save(new UserEntity("Guillermo", "Pintos", "guillermo-email@gmail.com", passwordEncoder.encode("12345"), "stringPath", new Timestamp(System.currentTimeMillis()), roleAdmin));
            this.userRepository.save(new UserEntity("Sara", "Silva", "sara-email@gmail.com", passwordEncoder.encode("12345"), "stringPath", new Timestamp(System.currentTimeMillis()), roleAdmin));
            this.userRepository.save(new UserEntity("Fabian", "Mendoza", "fabian-email@gmail.com", passwordEncoder.encode("12345"), "stringPath", new Timestamp(System.currentTimeMillis()), roleAdmin));
            this.userRepository.save(new UserEntity("Esteban", "Pinto", "esteban-email@gmail.com", passwordEncoder.encode("12345"), "stringPath", new Timestamp(System.currentTimeMillis()), roleAdmin));
            this.userRepository.save(new UserEntity("Erika", "Strumbergert", "erica-email@gmail.com", passwordEncoder.encode("12345"), "stringPath", new Timestamp(System.currentTimeMillis()), roleAdmin));
            this.userRepository.save(new UserEntity("Florencia", "Bianqueri", "florencia-email@gmail.com", passwordEncoder.encode("12345"), "stringPath", new Timestamp(System.currentTimeMillis()), roleAdmin));
            this.userRepository.save(new UserEntity("Pablo", "Silva", "pablo-email@gmail.com", passwordEncoder.encode("12345"), "stringPath", new Timestamp(System.currentTimeMillis()), roleAdmin));
            this.userRepository.save(new UserEntity("Micaela", "Barragan", "micaela-email@gmail.com", passwordEncoder.encode("12345"), "stringPath", new Timestamp(System.currentTimeMillis()), roleAdmin));
            this.userRepository.save(new UserEntity("Isaac", "Silva", "isaac-email@gmail.com", passwordEncoder.encode("12345"), "stringPath", new Timestamp(System.currentTimeMillis()), roleAdmin));


            this.userRepository.save(new UserEntity("Gisel", "Zoto", "gisel-email@gmail.com", passwordEncoder.encode("12345"), "stringPath", new Timestamp(System.currentTimeMillis()), roleUser));
            this.userRepository.save(new UserEntity("Ruth", "Solis", "ruth-email@gmail.com", passwordEncoder.encode("12345"), "stringPath", new Timestamp(System.currentTimeMillis()), roleUser));
            this.userRepository.save(new UserEntity("Fabian", "vena", "fabi-email@gmail.com", passwordEncoder.encode("12345"), "stringPath", new Timestamp(System.currentTimeMillis()), roleUser));
            this.userRepository.save(new UserEntity("Mayra", "Zinko", "mayra-email@gmail.com", passwordEncoder.encode("12345"), "stringPath", new Timestamp(System.currentTimeMillis()), roleUser));
            this.userRepository.save(new UserEntity("Diana", "Di Stefano", "diana-email@gmail.com", passwordEncoder.encode("12345"), "stringPath", new Timestamp(System.currentTimeMillis()), roleUser));
            this.userRepository.save(new UserEntity("Ariel", "Alvarez", "ariel-email@gmail.com", passwordEncoder.encode("12345"), "stringPath", new Timestamp(System.currentTimeMillis()), roleUser));
            this.userRepository.save(new UserEntity("Claudio", "Matuk", "claudio-email@gmail.com", passwordEncoder.encode("12345"), "stringPath", new Timestamp(System.currentTimeMillis()), roleUser));
            this.userRepository.save(new UserEntity("Mariano", "Peralta", "mariano-email@gmail.com", passwordEncoder.encode("12345"), "stringPath", new Timestamp(System.currentTimeMillis()), roleUser));
            this.userRepository.save(new UserEntity("Carolina", "Pintos", "carolina-email@gmail.com", passwordEncoder.encode("12345"), "stringPath", new Timestamp(System.currentTimeMillis()), roleUser));
            this.userRepository.save(new UserEntity("Yoel", "Silva", "yoel-email@gmail.com", passwordEncoder.encode("12345"), "stringPath", new Timestamp(System.currentTimeMillis()), roleUser));

        }
    }
}

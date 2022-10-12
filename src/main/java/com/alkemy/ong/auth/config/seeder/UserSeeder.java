package com.alkemy.ong.auth.config.seeder;

import com.alkemy.ong.models.entity.*;
import com.alkemy.ong.repository.*;
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
    PasswordEncoder passwordEncoder;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    OrganizationRepository organizationRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    NewsRepository newsRepository;
    @Autowired
    TestimonialRepository testimonialRepository;
    @Autowired
    SlideRepository slideRepository;
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    ContactRepository contactRepository;
    @Autowired
    ActivityRepository activityRepository;

    private static final System.Logger LOGGER = System.getLogger("Mi log");

    @Override
    public void run(String... args) throws Exception {
        LOGGER.log(Level.INFO, "Inicializing seeder");
        this.loadSeedersUsers();
        this.loadSeedersOrganization();
        this.loadSeedersCategories();
        this.loadSeedersNews();
        this.loadSeedersContacts();
        this.loadSeedersActivities();
        this.loadSeedersSlides();
        this.loadSeedersTestimonials();
        this.loadSeedersComments();
    }



    private void loadSeedersUsers() {
        if (roleRepository.findAll().isEmpty() && userRepository.findAll().isEmpty()) {

            // Create Roles
            roleRepository.save(new RoleEntity(RoleEnum.USER.getFullRoleName(), null, new Timestamp(System.currentTimeMillis())));
            roleRepository.save(new RoleEntity(RoleEnum.ADMIN.getFullRoleName(), null, new Timestamp(System.currentTimeMillis())));

            Set<RoleEntity> roleAdmin = roleRepository.findByName(RoleEnum.ADMIN.getFullRoleName());
            Set<RoleEntity> roleUser = roleRepository.findByName(RoleEnum.USER.getFullRoleName());

            // Create Users
            this.userRepository.save(new UserEntity("Marta", "Sanchez", "marta-email@gmail.com", passwordEncoder.encode("12345"), "stringPath", new Timestamp(System.currentTimeMillis()), roleAdmin));
            this.userRepository.save(new UserEntity("Guillermo", "Pintos", "guillermo-email@gmail.com", passwordEncoder.encode("12345"), "stringPath", new Timestamp(System.currentTimeMillis()), roleAdmin));
            this.userRepository.save(new UserEntity("Sara", "Silva", "sara-email@gmail.com", passwordEncoder.encode("12345"), "stringPath", new Timestamp(System.currentTimeMillis()), roleAdmin));
            this.userRepository.save(new UserEntity("Fabian", "Mendoza", "fabian-email@gmail.com", passwordEncoder.encode("12345"), "stringPath", new Timestamp(System.currentTimeMillis()), roleAdmin));
            this.userRepository.save(new UserEntity("Esteban", "Pinto", "esteban-email@gmail.com", passwordEncoder.encode("12345"), "stringPath", new Timestamp(System.currentTimeMillis()), roleAdmin));
            this.userRepository.save(new UserEntity("Erica", "Strumbergert", "erica-email@gmail.com", passwordEncoder.encode("12345"), "stringPath", new Timestamp(System.currentTimeMillis()), roleAdmin));
            this.userRepository.save(new UserEntity("Florencia", "Bianqueri", "florencia-email@gmail.com", passwordEncoder.encode("12345"), "stringPath", new Timestamp(System.currentTimeMillis()), roleAdmin));
            this.userRepository.save(new UserEntity("Pablo", "Silva", "pablo-email@gmail.com", passwordEncoder.encode("12345"), "stringPath", new Timestamp(System.currentTimeMillis()), roleAdmin));
            this.userRepository.save(new UserEntity("Eddard", "Stark", "eddard-email@gmail.com", passwordEncoder.encode("12345"), "stringPath", new Timestamp(System.currentTimeMillis()), roleAdmin));
            this.userRepository.save(new UserEntity("Jon", "Nieve", "jon-email@gmail.com", passwordEncoder.encode("12345"), "stringPath", new Timestamp(System.currentTimeMillis()), roleAdmin));

            this.userRepository.save(new UserEntity("Gisel", "Zoto", "gisel-email@gmail.com", passwordEncoder.encode("12345"), "stringPath", new Timestamp(System.currentTimeMillis()), roleUser));
            this.userRepository.save(new UserEntity("Ruth", "Solis", "ruth-email@gmail.com", passwordEncoder.encode("12345"), "stringPath", new Timestamp(System.currentTimeMillis()), roleUser));
            this.userRepository.save(new UserEntity("Fabian", "vena", "fabi-email@gmail.com", passwordEncoder.encode("12345"), "stringPath", new Timestamp(System.currentTimeMillis()), roleUser));
            this.userRepository.save(new UserEntity("Mayra", "Zinko", "mayra-email@gmail.com", passwordEncoder.encode("12345"), "stringPath", new Timestamp(System.currentTimeMillis()), roleUser));
            this.userRepository.save(new UserEntity("Diana", "Di Stefano", "diana-email@gmail.com", passwordEncoder.encode("12345"), "stringPath", new Timestamp(System.currentTimeMillis()), roleUser));
            this.userRepository.save(new UserEntity("Ariel", "Alvarez", "ariel-email@gmail.com", passwordEncoder.encode("12345"), "stringPath", new Timestamp(System.currentTimeMillis()), roleUser));
            this.userRepository.save(new UserEntity("Claudio", "Matuk", "claudio-email@gmail.com", passwordEncoder.encode("12345"), "stringPath", new Timestamp(System.currentTimeMillis()), roleUser));
            this.userRepository.save(new UserEntity("Elton", "John", "elton-email@gmail.com", passwordEncoder.encode("12345"), "stringPath", new Timestamp(System.currentTimeMillis()), roleUser));
            this.userRepository.save(new UserEntity("Tyrion", "Lannister", "tyrion-email@gmail.com", passwordEncoder.encode("12345"), "stringPath", new Timestamp(System.currentTimeMillis()), roleUser));
            this.userRepository.save(new UserEntity("Daenerys", "Targaryen", "daenerys-email@gmail.com", passwordEncoder.encode("12345"), "stringPath", new Timestamp(System.currentTimeMillis()), roleUser));
        }
    }

    private void loadSeedersOrganization() {

        if (organizationRepository.findAll().isEmpty()) {
            organizationRepository.save(new OrganizationEntity("ONG Somos Mas", "/img/logo.jpg", "street A 123", "01187654321", "somos_mas@gmail.com", "Welcome Text", "About Us Text", "facebook link", "instagram link", "Linkedin link"));
            organizationRepository.save(new OrganizationEntity("ONG Por Los Pibes", "/img/logo2.jpg", "P. Sherman Calle Wallaby 42, Sidney", "01165598001", "por_los_pibes@gmail.com", "Welcome Text 2", "About Us Text 2", "facebook link", "instagram link", "Linkedin link"));

        }
    }

    private void loadSeedersCategories() {
        if (categoryRepository.findAll().isEmpty()) {
            categoryRepository.save(new CategoryEntity("Cat A", "Description AAA", "/img/a.jpg"));
            categoryRepository.save(new CategoryEntity("Cat B", "Description BBB", "/img/b.jpg"));
            categoryRepository.save(new CategoryEntity("Cat C", "Description CCC", "/img/c.jpg"));
        }
    }

    private void loadSeedersNews() {
        if (newsRepository.findAll().isEmpty()) {
            newsRepository.save(new NewEntity("Dog A", "Description One", "/img/juan.jpg", 1L));
            newsRepository.save(new NewEntity("Dog B", "Description Two", "/img/guille.jpg", 2L));
            newsRepository.save(new NewEntity("Dog C", "Description three", "/img/leo.jpg", 3L));
        }
    }

    private void loadSeedersContacts() {
        if (contactRepository.findAll().isEmpty()){
            contactRepository.save(new ContactEntity("guillermo", "11456789", "guillermo@gmail.com", "primer"));
            contactRepository.save(new ContactEntity("silvia", "11456784", "silvia@gmail.com", "segundo"));
            contactRepository.save(new ContactEntity("Ivonne", "11456724", "ivonne@gmail.com", "tercero"));
            contactRepository.save(new ContactEntity("Pepe", "11454689", "pepe@gmail.com", "cuarto"));

        }
    }
    private void loadSeedersActivities() {
        if (activityRepository.findAll().isEmpty()){
            activityRepository.save(new ActivityEntity("Futbol","Content 1","/img/leo.jpg"));
            activityRepository.save(new ActivityEntity("Tennis","Content 2","/img/leo.jpg"));
            activityRepository.save(new ActivityEntity("Natacion","Content 3","/img/leo.jpg"));
            activityRepository.save(new ActivityEntity("Basket","Content 4","/img/leo.jpg"));
            activityRepository.save(new ActivityEntity("Waterpolo","Content 5","/img/leo.jpg"));
            activityRepository.save(new ActivityEntity("Pato","Content 6","/img/leo.jpg"));
            activityRepository.save(new ActivityEntity("Golf","Content 7","/img/leo.jpg"));

        }
    }

    private void loadSeedersTestimonials() {
        if (testimonialRepository.findAll().isEmpty()) {
            testimonialRepository.save(new TestimonialEntity("Mejoras", "imagen.jpg", "Hemos mejorado nuestra página!"));
            testimonialRepository.save(new TestimonialEntity("Inclusivos", "imagen2.jpg", "Ahora hay lugar para todos!"));
        }
    }

    private void loadSeedersSlides() {
        if (slideRepository.findAll().isEmpty()) {
            slideRepository.save(new SlideEntity("image1.jpg", "text1", 1, 1L));
            slideRepository.save(new SlideEntity("image2.jpg", "text2", 2, 1L));
            slideRepository.save(new SlideEntity("image3.jpg", "text3", 1, 2L));
        }
    }

    private void loadSeedersComments() {
        if (commentRepository.findAll().isEmpty()) {
            commentRepository.save(new CommentEntity(1L, 1L, "Muy buena la ONG!"));
            commentRepository.save(new CommentEntity(2L, 2L, "Unos capos!"));
            commentRepository.save(new CommentEntity(2L, 2L, "Grosos!"));
        }
    }
}

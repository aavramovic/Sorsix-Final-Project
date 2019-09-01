package com.sorsix.finkicommunity.repository;

import com.sorsix.finkicommunity.domain.entities.Course;
import com.sorsix.finkicommunity.domain.entities.Post;
import com.sorsix.finkicommunity.domain.entities.User;
import com.sorsix.finkicommunity.domain.enums.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;

@Service
public class DbInit implements CommandLineRunner {
    private UserRepository userRepository;
    private CourseRepository courseRepository;
    private PasswordEncoder passwordEncoder;
    private PostRepository postRepository;

    public DbInit(UserRepository userRepository, CourseRepository courseRepository, PasswordEncoder passwordEncoder, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
        this.passwordEncoder = passwordEncoder;
        this.postRepository = postRepository;
    }

    @Override
    public void run(String... args) {

        // REMOVE DATA
        this.userRepository.deleteAll();
        this.courseRepository.deleteAll();
        this.postRepository.deleteAll();


        // ADD USERS    TOTAL = 15
        User user = new User();
        User fisnik = new User("fisnik", passwordEncoder.encode("fisnik123"), "fisnik_email@gmail.com", "Fisnik", "Limani", 'M', System.currentTimeMillis(), Role.USER);
        User antonio = new User("antonio", passwordEncoder.encode("antonio123"), "antonio_email@gmail.com", "Antonio", "Avramovikj", 'M',System.currentTimeMillis(),Role.USER);
        User admin = new User("admin", passwordEncoder.encode("admin123"), "admin_email@gmail.com", "AdminName", "AdminSurname", 'M',System.currentTimeMillis(),Role.ADMIN);
        User moderator = new User("moderator", passwordEncoder.encode("moderator123"), "moderator_email@gmail.com", "ModeratorName", "ModeratorSurname", 'F',System.currentTimeMillis(),Role.MODERATOR);
        User user5 = new User("user5", passwordEncoder.encode("user5123"), "user5@gmail.com", "User5Name", "User5Surname", 'F',System.currentTimeMillis(),Role.USER);
        User user6 = new User("user6", passwordEncoder.encode("user6123"), "user6@gmail.com", "User6Name", "User6Surname", 'F',System.currentTimeMillis(),Role.USER);
        User user7 = new User("user7", passwordEncoder.encode("user7123"), "user7@gmail.com", "User7Name", "User7Surname", 'M',System.currentTimeMillis(),Role.USER);
        User user8 = new User("user8", passwordEncoder.encode("user8123"), "user8@gmail.com", "User8Name", "User8Surname", 'F',System.currentTimeMillis(),Role.USER);
        User user9 = new User("user9", passwordEncoder.encode("user9123"), "user9@gmail.com", "User9Name", "User9Surname", 'F',System.currentTimeMillis(),Role.USER);
        User user10 = new User("user10", passwordEncoder.encode("user10123"), "user10@gmail.com", "User10Name", "User10Surname", 'M',System.currentTimeMillis(),Role.USER);
        User user11 = new User("user11", passwordEncoder.encode("user11123"), "user11@gmail.com", "User11Name", "User11Surname", 'M',System.currentTimeMillis(),Role.USER);
        User user12 = new User("user12", passwordEncoder.encode("user12123"), "user12@gmail.com", "User12Name", "User12Surname", 'F',System.currentTimeMillis(),Role.USER);
        User user13 = new User("user13", passwordEncoder.encode("user13123"), "user13@gmail.com", "User13Name", "User13Surname", 'F',System.currentTimeMillis(),Role.USER);
        User user14 = new User("user14", passwordEncoder.encode("user14123"), "user14@gmail.com", "User14Name", "User14Surname", 'F',System.currentTimeMillis(),Role.USER);
        User user15 = new User("user15", passwordEncoder.encode("user15123"), "user15@gmail.com", "User15Name", "User15Surname", 'M',System.currentTimeMillis(),Role.USER);

        List<User> users = Arrays.asList(fisnik, antonio, admin, moderator,user5, user6, user7, user8, user9, user10, user11, user12, user13, user14, user15);


        // ADD FOLLOWINGS   TOTAL = 5
        fisnik.addNewFollowing(antonio);
        fisnik.addNewFollowing(admin);
        admin.addNewFollowing(fisnik);
        antonio.addNewFollowing(fisnik);
        antonio.addNewFollowing(admin);
        user5.addNewFollowing(user6);
        user5.addNewFollowing(fisnik);

        userRepository.saveAll(users);


        // ADD COURSES  TOTAL = 16
        String programs = "";
        programs = Program.KNI.toString() + "," + Program.IKI.toString();
        Course course1 = new Course("code1", "course1", "courseDescription1", programs, StudyYear.FRESHMAN, Semester.SUMMER, CourseType.MANDATORY);
        Course course2 = new Course("code2", "course2", "courseDescription2", Program.KNI.toString(), StudyYear.FRESHMAN, Semester.SUMMER, CourseType.OPTIONAL);
        programs = Program.KNI.toString() + "," + Program.IKI.toString() + "," + Program.MT.toString();
        Course course3 = new Course("code3", "course3", "courseDescription3", programs, StudyYear.FRESHMAN, Semester.WINTER, CourseType.MANDATORY);
        Course course4 = new Course("code4", "course4", "courseDescription4", Program.KNI.toString(), StudyYear.FRESHMAN, Semester.WINTER, CourseType.OPTIONAL);
        programs = Program.KNI.toString() + "," + Program.IKI.toString() + "," + Program.KN.toString();
        Course course5 = new Course("code5", "course5", "courseDescription5", programs, StudyYear.SOPHOMORE, Semester.SUMMER, CourseType.MANDATORY);
        Course course6 = new Course("code6", "course6", "courseDescription6", Program.KNI.toString(), StudyYear.SOPHOMORE, Semester.SUMMER, CourseType.OPTIONAL);
        Course course7 = new Course("code7", "course7", "courseDescription7", Program.KNI.toString(), StudyYear.SOPHOMORE, Semester.WINTER, CourseType.MANDATORY);
        Course course8 = new Course("code8", "course8", "courseDescription8", Program.KNI.toString(), StudyYear.SOPHOMORE, Semester.WINTER, CourseType.OPTIONAL);

        Course course9 = new Course("code9", "course9", "courseDescription9", Program.IKI.toString(), StudyYear.FRESHMAN, Semester.SUMMER, CourseType.MANDATORY);
        Course course10 = new Course("code10", "course10", "courseDescription10", Program.IKI.toString(), StudyYear.FRESHMAN, Semester.SUMMER, CourseType.OPTIONAL);
        Course course11 = new Course("code11", "course11", "courseDescription11", Program.IKI.toString(), StudyYear.FRESHMAN, Semester.WINTER, CourseType.MANDATORY);
        Course course12 = new Course("code12", "course12", "courseDescription12", Program.IKI.toString(), StudyYear.FRESHMAN, Semester.WINTER, CourseType.OPTIONAL);
        Course course13 = new Course("code13", "course13", "courseDescription13", Program.IKI.toString(), StudyYear.SOPHOMORE, Semester.SUMMER, CourseType.MANDATORY);
        Course course14 = new Course("code14", "course14", "courseDescription14", Program.IKI.toString(), StudyYear.SOPHOMORE, Semester.SUMMER, CourseType.OPTIONAL);
        Course course15 = new Course("code15", "course15", "courseDescription15", Program.IKI.toString(), StudyYear.SOPHOMORE, Semester.WINTER, CourseType.MANDATORY);
        Course course16 = new Course("code16", "course16", "courseDescription16", Program.IKI.toString(), StudyYear.SOPHOMORE, Semester.WINTER, CourseType.OPTIONAL);


        List<Course> courses = Arrays.asList(course1, course2, course3, course4, course5, course6, course7, course8, course9, course10, course11, course12, course13, course14, course15, course16);
        courseRepository.saveAll(courses);


        // POSTS    TOTAL = 50
        String content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";

        Post post0 = new Post("Title00", "Post00 - " + content, fisnik, course10);
        Post post1 = new Post("Title01","Post01 - " + content, fisnik, course1);
        Post post2 = new Post("Title02","Post02 - " + content, fisnik, course2);
        Post post3 = new Post("Title03","Post03 -  " + content, antonio, post1);
        Post post4 = new Post("Title04","Post04 - " + content, antonio, course3);
        Post post5 = new Post("Title05","Post05 - " + content, antonio, course4);
        Post post6 = new Post("Title06","Post06 - " + content, antonio, course5);
        Post post7 = new Post("Title07","Post07 - " + content, antonio, course6);
        Post post8 = new Post("Title08","Post08 - " + content, fisnik, post5);
        Post post9 = new Post("Title09","Post09 - " + content, admin, post5);
        Post post10 = new Post("Title10","Post10 - " + content, moderator, post5);
        Post post11 = new Post("Title11","Post11 - " + content, admin, course7);
        Post post12 = new Post("Title12","Post12 - " + content, admin, post11);
        Post post13 = new Post("Title13","Post13 - " + content, moderator, course8);
        Post post14 = new Post("Title14","Post14 - " + content, moderator, course9);
        Post post15 = new Post("Title15","Post15 - " + content, moderator, post14);

        Post post16 = new Post("Title16", "Post16 - " + content, fisnik, course10);
        Post post17 = new Post("Title17","Post17 - " + content, fisnik, course1);
        Post post18 = new Post("Title18","Post18 - " + content, fisnik, course2);
        Post post19 = new Post("Title19","Post19 - " + content, antonio, post1);
        Post post20 = new Post("Title20","Post20 - " + content, antonio, post1);
        Post post21 = new Post("Title21","Post21 - " + content, antonio, post1);
        Post post22 = new Post("Title22","Post22 - " + content, antonio, post1);
        Post post23 = new Post("Title23","Post23 - " + content, antonio, post1);
        Post post24 = new Post("Title24","Post24 - " + content, fisnik, post5);
        Post post25 = new Post("Title25","Post25 - " + content, admin, post1);
        Post post26 = new Post("Title26","Post26 - " + content, moderator, post5);
        Post post27 = new Post("Title27","Post27 - " + content, admin, course7);
        Post post28 = new Post("Title28","Post28 - " + content, admin, post11);
        Post post29 = new Post("Title29","Post29 - " + content, moderator, course8);
        Post post30 = new Post("Title30","Post30 - " + content, moderator, course9);

        Post post31 = new Post("Title31","Post31 - " + content, moderator, post14);
        Post post32 = new Post("Title32", "Post32 - " + content, fisnik, course10);
        Post post33 = new Post("Title33","Post33 - " + content, fisnik, course1);
        Post post34 = new Post("Title34","Post34 - " + content, fisnik, course2);
        Post post35 = new Post("Title35","Post35 - " + content, antonio, post1);
        Post post36 = new Post("Title36","Post36 - " + content, antonio, post1);
        Post post37 = new Post("Title37","Post37 - " + content, antonio, post1);
        Post post38 = new Post("Title38","Post38 - " + content, antonio, post1);
        Post post39 = new Post("Title39","Post39 - " + content, antonio, post1);
        Post post40 = new Post("Title40","Post40 - " + content, fisnik, post5);
        Post post41 = new Post("Title41","Post41 - " + content, admin, post1);
        Post post42 = new Post("Title42","Post42 - " + content, moderator, post5);
        Post post43 = new Post("Title43","Post43 - " + content, admin, course7);
        Post post44 = new Post("Title44","Post44 - " + content, admin, post11);
        Post post45 = new Post("Title45","Post45 - " + content, moderator, course8);
        Post post46 = new Post("Title46","Post46 - " + content, moderator, course9);
        Post post47 = new Post("Title47","Post47 - " + content, moderator, post14);
        Post post48 = new Post("Title48","Post48 - " + content, moderator, course8);
        Post post49 = new Post("Title49","Post49 - " + content, moderator, course9);
        Post post50 = new Post("Title50","Post50 - " + content, moderator, post14);

        Post post51 = new Post("Title51","Post51 - " + content, moderator, course16);
        Post post52 = new Post("Title52","Post52 - " + content, moderator, course16);
        Post post53 = new Post("Title53","Post53 - " + content, moderator, course16);
        Post post54 = new Post("Title54","Post54 - " + content, moderator, course16);
        Post post55 = new Post("Title55","Post55 - " + content, moderator, course16);
        Post post56 = new Post("Title56","Post56 - " + content, moderator, course16);
        Post post57 = new Post("Title57","Post57 - " + content, moderator, course16);
        Post post58 = new Post("Title58","Post58 - " + content, moderator, course16);
        Post post59 = new Post("Title59","Post59 - " + content, moderator, course16);
        Post post60 = new Post("Title60","Post60 - " + content, moderator, course16);
        Post post61 = new Post("Title61","Post61 - " + content, fisnik, course16);

        List<Post> posts = Arrays.asList(post0, post1, post2, post3, post4, post5, post6, post7, post8, post9, post10,
                post11, post12, post13, post14, post15, post16, post17, post18, post19, post20, post21, post22, post23,
                post24, post25, post26, post27, post28, post29, post30, post31, post32, post33, post34, post35, post36,
                post37, post38, post39, post40, post41, post42, post43, post44, post45, post46, post47, post48, post49,
                post50, post51, post52, post53, post54, post55, post56, post57, post58, post59, post60, post61);

        postRepository.saveAll(posts);


        // USERS LIKES      TOTAL = 22
        fisnik.addPostLiked(post1);
        fisnik.addPostLiked(post2);
        fisnik.addPostLiked(post3);

        antonio.addPostLiked(post4);
        antonio.addPostLiked(post5);
        antonio.addPostLiked(post6);
        antonio.addPostLiked(post1);

        user5.addPostLiked(post1);
        user5.addPostLiked(post2);
        user5.addPostLiked(post3);
        user5.addPostLiked(post4);
        user5.addPostLiked(post5);
        user5.addPostLiked(post6);
        user5.addPostLiked(post7);
        user5.addPostLiked(post8);
        user5.addPostLiked(post9);
        user5.addPostLiked(post10);

        user6.addPostLiked(post1);

        user7.addPostLiked(post1);

        user8.addPostLiked(post1);

        user9.addPostLiked(post1);

        user10.addPostLiked(post1);

        List<User> addPostLikedToUsers = Arrays.asList(fisnik, antonio, user5, user6, user7, user8, user9, user10);

         userRepository.saveAll(users);
         courseRepository.saveAll(courses);
         postRepository.saveAll(posts);
    }
}

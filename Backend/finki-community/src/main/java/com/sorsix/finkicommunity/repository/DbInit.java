package com.sorsix.finkicommunity.repository;

import com.sorsix.finkicommunity.domain.entities.Course;
import com.sorsix.finkicommunity.domain.entities.Post;
import com.sorsix.finkicommunity.domain.entities.User;
import com.sorsix.finkicommunity.domain.enums.CourseType;
import com.sorsix.finkicommunity.domain.enums.Program;
import com.sorsix.finkicommunity.domain.enums.Semester;
import com.sorsix.finkicommunity.domain.enums.StudyYear;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
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
        this.userRepository.deleteAll();

        User fisnik = new User("fisnik", passwordEncoder.encode("fisnik123"), "USER", "");
        User antonio = new User("antonio", passwordEncoder.encode("antonio123"), "USER", "");
        User admin = new User("admin", passwordEncoder.encode("admin123"), "ADMIN", "");
        User moderator = new User("moderator", passwordEncoder.encode("moderator123"), "MODERATOR", "");

        List<User> users = Arrays.asList(fisnik, antonio, admin, moderator);

        fisnik.addNewFollowing(antonio);
        antonio.addNewFollowing(fisnik);

        fisnik.addNewFollowing(admin);
        admin.addNewFollowedBy(fisnik);

        antonio.addNewFollowing(fisnik);
        fisnik.addNewFollowedBy(antonio);

        antonio.addNewFollowing(admin);
        admin.addNewFollowedBy(antonio);

        userRepository.saveAll(users);

        Course course1 = new Course("code1", "course1", "courseDescription1", Program.KNI, StudyYear.FRESHMAN, Semester.SUMMER, CourseType.MANDATORY);
        Course course2 = new Course("code2", "course2", "courseDescription2", Program.KNI, StudyYear.FRESHMAN, Semester.SUMMER, CourseType.OPTIONAL);
        Course course3 = new Course("code3", "course3", "courseDescription3", Program.KNI, StudyYear.FRESHMAN, Semester.WINTER, CourseType.MANDATORY);
        Course course4 = new Course("code4", "course4", "courseDescription4", Program.KNI, StudyYear.FRESHMAN, Semester.WINTER, CourseType.OPTIONAL);
        Course course5 = new Course("code5", "course5", "courseDescription1", Program.KNI, StudyYear.SOPHOMORE, Semester.SUMMER, CourseType.MANDATORY);
        Course course6 = new Course("code6", "course6", "courseDescription2", Program.KNI, StudyYear.SOPHOMORE, Semester.SUMMER, CourseType.OPTIONAL);
        Course course7 = new Course("code7", "course7", "courseDescription3", Program.KNI, StudyYear.SOPHOMORE, Semester.WINTER, CourseType.MANDATORY);
        Course course8 = new Course("code8", "course8", "courseDescription4", Program.KNI, StudyYear.SOPHOMORE, Semester.WINTER, CourseType.OPTIONAL);

        Course course9 = new Course("code9", "course9", "courseDescription1", Program.IKI, StudyYear.FRESHMAN, Semester.SUMMER, CourseType.MANDATORY);
        Course course10 = new Course("code10", "course10", "courseDescription2", Program.IKI, StudyYear.FRESHMAN, Semester.SUMMER, CourseType.OPTIONAL);
        Course course11 = new Course("code11", "course11", "courseDescription3", Program.IKI, StudyYear.FRESHMAN, Semester.WINTER, CourseType.MANDATORY);
        Course course12 = new Course("code12", "course12", "courseDescription4", Program.IKI, StudyYear.FRESHMAN, Semester.WINTER, CourseType.OPTIONAL);
        Course course13 = new Course("code13", "course13", "courseDescription1", Program.IKI, StudyYear.SOPHOMORE, Semester.SUMMER, CourseType.MANDATORY);
        Course course14 = new Course("code14", "course14", "courseDescription2", Program.IKI, StudyYear.SOPHOMORE, Semester.SUMMER, CourseType.OPTIONAL);
        Course course15 = new Course("code15", "course15", "courseDescription3", Program.IKI, StudyYear.SOPHOMORE, Semester.WINTER, CourseType.MANDATORY);
        Course course16 = new Course("code16", "course16", "courseDescription4", Program.IKI, StudyYear.SOPHOMORE, Semester.WINTER, CourseType.OPTIONAL);


        List<Course> courses = Arrays.asList(course1, course2, course3, course4, course5, course6, course7, course8, course9, course10, course11, course12, course13, course14, course15, course16);

        Post post1 = new Post("Post1", System.currentTimeMillis(), fisnik, course1);
        Post post2 = new Post("Post2", System.currentTimeMillis(), fisnik, course2);
        Post post3 = new Post("Post3", System.currentTimeMillis(), antonio, post1);
        Post post4 = new Post("Post4", System.currentTimeMillis(), antonio, course3);
        Post post5 = new Post("Post5", System.currentTimeMillis(), antonio, course4);
        Post post6 = new Post("Post6", System.currentTimeMillis(), antonio, course5);
        Post post7 = new Post("Post7", System.currentTimeMillis(), antonio, course6);
        Post post8 = new Post("Post8", System.currentTimeMillis(), fisnik, post5);
        Post post9 = new Post("Post9", System.currentTimeMillis(), admin, post5);
        Post post10 = new Post("Post10", System.currentTimeMillis(), moderator, post5);

        List<Post> posts = Arrays.asList(post1, post2, post3, post4, post5, post6, post7, post8, post9, post10);


        Post post11 = new Post("Post11", System.currentTimeMillis(), course7);
        Post post12 = new Post("Post12", System.currentTimeMillis(), post11);
        Post post13 = new Post("Post13", System.currentTimeMillis(), course8);
        Post post14 = new Post("Post14", System.currentTimeMillis(), course9);
        Post post15 = new Post("Post15", System.currentTimeMillis(), post14);

        antonio.addNewPost(post11);
        antonio.addNewPost(post13);

        fisnik.addNewPost(post12);
        fisnik.addNewPost(post15);
        admin.addNewPost(post14);

        List<User> addPostsToUsers = Arrays.asList(antonio, fisnik, admin);


        fisnik.addPostLiked(post1);
        fisnik.addPostLiked(post2);
        fisnik.addPostLiked(post3);

        antonio.addPostLiked(post4);
        antonio.addPostLiked(post5);
        antonio.addPostLiked(post6);
        antonio.addPostLiked(post1);

        List<User> addPostLikedToUsers = Arrays.asList(fisnik, antonio);

        courseRepository.saveAll(courses);
        userRepository.saveAll(addPostLikedToUsers);
        postRepository.saveAll(posts);
    }
}

package com.example.blogAPI.services.user;

import com.example.blogAPI.dtos.postDto.PostDTO;
import com.example.blogAPI.dtos.userDto.PasswordChangeDTO;
import com.example.blogAPI.dtos.userDto.UserDetailsDTO;
import com.example.blogAPI.dtos.userDto.UserPostsDTO;
import com.example.blogAPI.exceptions.ResourceNotFoundException;
import com.example.blogAPI.models.Comment;
import com.example.blogAPI.models.Post;
import com.example.blogAPI.models.Role;
import com.example.blogAPI.models.User;
import com.example.blogAPI.repositories.RoleRepository;
import com.example.blogAPI.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import static org.springframework.util.StringUtils.capitalize;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.lang.reflect.Field;
import java.util.*;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    private UserDetailsDTO convertEntityToUsersDetailsDto(User user){
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        UserDetailsDTO userDetailsDTO = modelMapper.map(user,UserDetailsDTO.class);
        return userDetailsDTO;
    }

    private UserPostsDTO convertEntityToUserPostsDto(User user){
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        UserPostsDTO userPostsDTO = modelMapper.map(user,UserPostsDTO.class);
        return userPostsDTO;
    }



    @Override
    public void saveUser(User user) {
        boolean existsByEmail = userRepository.existsByEmail(user.getEmail());
        boolean existByUsername = userRepository.existsByUsername(user.getUsername());

        if (existsByEmail){
            throw new ResourceNotFoundException("Email Already exist");
        }
        if (existByUsername){
            throw new ResourceNotFoundException("Username Already taken");
        }
        user.setCreatedAt(new Date());
        user.setUsername(user.getUsername().toLowerCase());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setFirstname(capitalize(user.getFirstname()));
        user.setLastname(capitalize(user.getLastname()));
        userRepository.save(user);
        addRoleToUser(user.getUsername(),"USER");

    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser (String username, String roleName) {
        User user = userRepository.findByUsername(username);
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public UserDetailsImpl getUserByUsername(String username,String password) {
            User user = userRepository.findByUsername(username);
            if (user == null){
                throw new UsernameNotFoundException("User Not Found with username: " + username);
            }


        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(username, password));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return userDetails;
    }

    @Override
    public UserDetailsDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow( () ->
                new ResourceNotFoundException("User not exists"));
        return convertEntityToUsersDetailsDto(user);
    }

    @Override
    public void deleteUser(Long id) {
        boolean exists = userRepository.existsById(id);
        if (!exists){
            throw new ResourceNotFoundException("User not exists");
        }
        userRepository.deleteById(id);
    }

    @Override
    public void updateUser(Long id, Map<Object, Object> fields) {
        User user = userRepository.findById(id).orElseThrow( () ->
                new ResourceNotFoundException("User not exists"));
        fields.forEach((key,value) ->{
            Field field = ReflectionUtils.findField(User.class,(String) key);
            if (field.getName() == "email"){
                boolean existsByEmail = userRepository.existsByEmail((String) value);
                if (existsByEmail){
                    throw new RuntimeException("Email already taken");
                }
            }
            field.setAccessible(true);
            ReflectionUtils.setField(field,user,value);
        });
        userRepository.save(user);
    }

    @Override
    public UserPostsDTO getUsersPosts(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("User not exists"));
        return convertEntityToUserPostsDto(user);
    }

    @Override
    public Collection<Object> getRandomPosts() {
        Collection<Object > users = userRepository.getRandomPost();
        Collection<UserPostsDTO> userPostsDTOS = new ArrayList<>();
//        users.forEach((user) ->{
//         userPostsDTOS.add(new PostDTO(user[3],user[0],user[1],user[2],user[5],user[6],user[7]))
//        });

        return users;
    }

    public Collection<Object []> getPostsOrdered(){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Object []> cq = cb.createQuery(Object[].class);
        Root<User> user = cq.from(User.class);
        Join<User, Post> post = user.join("posts");
        Join<Post, Comment> comment = post.join("comments");
        cq.multiselect(user,post,comment);
        cq.orderBy(cb.desc(post.get("createdAt")));
        Collection<Object[]> result = em.createQuery(cq).getResultList();
        return result;
    }

    @Override
    public void changePassword(PasswordChangeDTO passwordChangeDTO) {
        User user = userRepository.findById(passwordChangeDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not exists"));
        boolean isTrue = passwordEncoder.matches(passwordChangeDTO.getPassword(), user.getPassword());
        if (isTrue){
            user.setPassword(passwordEncoder.encode(passwordChangeDTO.getNewPassword()));
        }else {
            throw new RuntimeException("Wrong password");
        }

    }
}

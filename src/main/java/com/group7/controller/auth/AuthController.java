package com.group7.controller.auth;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


import com.group7.controller.auth.payload.LoginRequest;
import com.group7.controller.auth.payload.SignupRequest;
import com.group7.controller.user.UserDetailsImpl;
import com.group7.db.jpa.*;
import com.group7.db.jpa.utils.ERole;
import com.group7.utils.common.JwtResponse;
import com.group7.utils.common.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.group7.utils.common.R;

/**
 * @Author: WangYuyang
 * @Date: 2023/2/26-18:00
 * @Project: COMP3032J_FYP_Thesis_Group_7
 * @Package: com.group7.controller
 * @Description:
 **/
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult bindingResult) {

        // check the auto validation defined in LoginRequest
        if(bindingResult.hasErrors()){
            return ResponseEntity
                    .badRequest()
                    .body(R.error().message(bindingResult.getAllErrors().get(0).getDefaultMessage()));
        }

        // check if the user exist
        User user = userRepository.findByEmail(loginRequest.getEmail()).orElse(null);
        if (user == null){
            return ResponseEntity
                    .badRequest()
                    .body(R.error().message("Login failed: User does not exist!"));
        }

        // check the password
        if(!encoder.matches(loginRequest.getPassword(), user.getPassword())){
            return ResponseEntity
                    .badRequest()
                    .body(R.error().message("Login failed: Wrong password!"));
        }

        // for authentication and token generation
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                userDetails.getAvatar(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest, BindingResult bindingResult) {

        // check the auto validation defined in SignupRequest
        if(bindingResult.hasErrors()){
            return ResponseEntity
                    .badRequest()
                    .body(R.error().message(bindingResult.getAllErrors().get(0).getDefaultMessage()));
        }

        // check username duplication
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(R.error().message("Error: Username is already taken!"));
        }

        // check email duplication
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(R.error().message("Error: Email is already in use!"));
        }

        // check two passwords
        if (!signUpRequest.getPassword().equals(signUpRequest.getRePassword())){
            return ResponseEntity
                    .badRequest()
                    .body(R.error().message("Error: Two passwords do not match!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                                signUpRequest.getEmail(),
                                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin" -> {
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                    }
                    case "mod" -> {
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);
                    }
                    default -> {
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                    }
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(R.ok().message("User registered successfully!"));
    }

    @GetMapping(value = "/getUserInfo")
    public ResponseEntity<?> getUserByToken(HttpServletRequest request){
        // find user from the token in request header
        User user = jwtUtils.getUserFromRequestByToken(request);
        if (user == null){
            return ResponseEntity
                    .badRequest()
                    .body(R.error().message("Invalid token! User not found! You should login first."));
        }

        // return the user info
        return ResponseEntity.ok(R.ok().data("user", user));
    }

}

//@RestController
//@RequestMapping(value = "/api")
//public class AuthController {
//    @Autowired
//    LoginService loginService;
//
//    @Autowired
//    RegisterService registerService;
//
//    @GetMapping(value = "/hello")
//    public R hello() {
//        return R.ok().message("hello");
//    }
//
//
//    @PostMapping(value = "/login")
//    @CrossOrigin
//    public R login(@RequestBody LoginDTO loginDTO) {
//        return loginService.login(loginDTO);
//    }
//
//    @PostMapping(value = "/logout")
//    @CrossOrigin
//    public R logout() {
//        return R.ok();
//    }
//
//
//    @PostMapping(value = "/register")
//    public R register(@RequestBody RegisterVo registerVo){
//        return registerService.register(registerVo);
//    }
//
//    @GetMapping(value = "/getUserInfo")
//    public R getUserByToken(HttpServletRequest request){
//        Long userId = JwtUtil.getUserIdByToken(request);
//        // query user from db
//        User user = registerService.getUserById(userId);
//        if (user == null){
//            return R.error().message("User not found");
//        }
//        return R.ok().data("user", user);
//    }
//
//}

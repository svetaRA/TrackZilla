package com.ms.bugzilla.web;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms.bugzilla.entity.User;
import com.ms.bugzilla.jwt.JwtTokenUtil;
import com.ms.bugzilla.payload.JwtAuthenticationResponse;
import com.ms.bugzilla.payload.LoginRequest;
//import com.ms.bugzilla.exeption.UserNameNotfoundException;
import com.ms.bugzilla.repository.UserRepository;
import com.ms.bugzilla.service.LoginService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/auth")
public class AuthController {

	Logger LOG = LoggerFactory.getLogger(AuthController.class);
	@Autowired
	UserRepository userRepository;

	@Autowired
	LoginService loginService;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	JwtTokenUtil jwtTokenUtil;

	@PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtTokenUtil.generateToken(authentication); 
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

	// =========================================== Get single user details from DB
	// ==========================================

	@GetMapping("/users/{username}")
	public ResponseEntity<User> getUserByUserName(@PathVariable("username") String name) {
		LOG.info("get user name***" + name);
		User username = userRepository.findByUsername(name).
				orElseThrow(() -> 
			//	new UsernameNotFoundException("User", "username", name));;
				new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", name)));
		return new ResponseEntity<User>(username, HttpStatus.OK);

	}

	// =========================================== Get user details from DB
	// ==========================================
	@GetMapping("/users")
	public ResponseEntity<List<User>> getUsers() {
		LOG.info("get uses details **");
		//List<User> list= userRepository.findAll();
				//orElseThrow(() -> 
			//	new UsernameNotFoundException("User", "username", name));;
				//new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", name)));
			//	return new ResponseEntity<List<User>>(list,HttpStatus.OK);
				try {
					return new ResponseEntity<List<User>>(loginService.getUsers(), HttpStatus.OK);
				//return new ResponseEntity<User>(userRepository.save(user), HttpStatus.OK);

			} catch (UsernameNotFoundException ex) {
				//System.out.println("in controller1111***");
				//return (ResponseEntity<Application>
				//return new ResponseEntity<Application>(applicationService.findApplicationbyId(id), HttpStatus.);
				throw new UsernameNotFoundException(String.format("USER_Details notfound'%s'."));
			}
				   

	}

	// =========================================== Add user details in DB
	// ==========================================
	@PostMapping("/signup")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		LOG.info("get user name***" + user.getUsername());
		try {
			return new ResponseEntity<User>(loginService.save(user), HttpStatus.OK);
			// return new ResponseEntity<User>(userRepository.save(user), HttpStatus.OK);

		} catch (UsernameNotFoundException ex) {
			// System.out.println("in controller1111***");
			// return (ResponseEntity<Application>
			// return new
			// ResponseEntity<Application>(applicationService.findApplicationbyId(id),
			// HttpStatus.);
			throw new UsernameNotFoundException(String.format("USER_CANT_BE ADDED '%s'.", user.getUsername()));
		}

		// new UsernameNotFoundException("User", "username", name));;

	}

}

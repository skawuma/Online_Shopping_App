package com.onlineshoppers.Online_Shoppers_Backend.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import com.onlineshoppers.Online_Shoppers_Backend.dao.UserDao;
import com.onlineshoppers.Online_Shoppers_Backend.entity.JwtRequest;
import com.onlineshoppers.Online_Shoppers_Backend.entity.JwtResponse;
import com.onlineshoppers.Online_Shoppers_Backend.entity.User;
import com.onlineshoppers.Online_Shoppers_Backend.service.JwtService;
import com.onlineshoppers.Online_Shoppers_Backend.util.JwtUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;

@RestController
 @CrossOrigin
 //@RequiredArgsConstructor
@RequestMapping
public class JwtController { 
    
    @Autowired
    private JwtUtil jwtUtil;
      
     @Autowired
   private  JwtService jwtService;

    @Autowired
    private  UserDao userRepo;

    // private Users users;
    @Autowired
	private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;



    public String getEncodedPassword( String password){
		return passwordEncoder.encode(password);

	}
    // @PostMapping({"/signup"})
        // public JwtResponse register(@RequestBody RegisterRequest request) throws Exception {
           
             
        // //     Users user = new Users();

        // //     user.setUserName(user.getUsername());
        // //     user.setUserPassword(passwordEncoder.encode(user.getPassword())); 
        // //     user.setUserPhone(user.getUserPhone());
        // //     user.setUserEmail(user.getUserEmail());
        // //     user.setRole(user.getRole());
        // //     user.setUserType(user.getUserType());


        // //   userRepo.save(user);
        // //     authenticate(user.getUsername(), user.getPassword());

        //     // String userName = jwtRequest.getUserName();
        //     // String userPassword = jwtRequest.getUserPassword();
        //     // BigInteger userPhone = jwtRequest.getUserPhone();
        //     // String userEmail = jwtRequest.getUserEmail();
        //     Set<Role> role = new HashSet<>();
        //     var user =Users.builder()
        //     .userName(request.getUserName())
        //     .UserPassword (passwordEncoder.encode(request.getUserPassword()))
        //     .userPhone(request.getUserPhone())
        //     .userEmail(request.getUserEmail())
        //     .role(role)
        //     .build();
        //     userRepo.save(user);

            
        //  //UserDetails userDetails = jwtService.loadUserByUsername(user.getUserName());
        //     String newGeneratedToken = jwtUtil.generateToken1(user);
    
        // return new JwtResponse(user, newGeneratedToken);
        // }
 

        @PostMapping({"/authenticate"})
        public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
            String userName = jwtRequest.getUserName();
            String userPassword = jwtRequest.getUserPassword();
            authenticate(userName, userPassword);
    
            UserDetails userDetails = jwtService.loadUserByUsername(userName);
            String newGeneratedToken = jwtUtil.generateToken2(userDetails);
    
            User user = userRepo.findByUserName(userName).get();
            return new JwtResponse(user, newGeneratedToken);
        }

    private void authenticate(String userName, String userPassword) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, userPassword));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
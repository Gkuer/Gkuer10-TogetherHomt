package com.ssafy.togetherhomt.user;

import com.ssafy.togetherhomt.config.auth.PrincipalDetails;
import com.ssafy.togetherhomt.util.Mailing.MailingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private MailingService mailingService;

    @Autowired
    public UserController(UserService userService, MailingService mailingService) {
        this.userService = userService;
        this.mailingService = mailingService;
    }


    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody SignupDto signupDto) {
        String result = userService.signup(signupDto);

        if (result.equals("success"))
            return ResponseEntity.ok("success");
        else
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping({"/signup/confirm", "/reset-password"})
    public ResponseEntity<String> confirmMail(@RequestBody String email) throws Exception {
        return ResponseEntity.ok(mailingService.sendSimpleMessage(email));
    }

//    @PostMapping("/login")
//    public ResponseEntity<?> login(@Valid @RequestBody LoginDto loginDto) {
//        System.out.println("UserController - login");
//        SignupDto signupDto = userService.login(loginDto);
//        if (signupDto != null)
//            return ResponseEntity.ok("success");
//        else
//            System.out.println("fail");
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }

    @DeleteMapping("/{email}")
    public ResponseEntity<?> withdraw(@PathVariable String email){
        PrincipalDetails principalDetails = (PrincipalDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(email == null || !principalDetails.getUsername().equals(email)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        userService.withdraw(email);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PutMapping("/{email}")
    public ResponseEntity<?> update(@PathVariable String email, @Valid @RequestBody UpdateDto updateDto){
        PrincipalDetails principalDetails = (PrincipalDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(email == null || !principalDetails.getUsername().equals(email)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        userService.update(email, updateDto);
        return ResponseEntity.ok("update success");
    }

    @PutMapping("/{email}/passwordUpdate")
    public ResponseEntity<?> passwordUpdate(@PathVariable String email, @Valid @RequestBody LoginDto loginDto){
        PrincipalDetails principalDetails = (PrincipalDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(email == null || !principalDetails.getUsername().equals(email)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        userService.passwordUpdate(email, loginDto);
        return ResponseEntity.ok("passwordUpdate success");
    }


    @GetMapping("/admin")
    public ResponseEntity<String> admin() {
        return new ResponseEntity<String>("authorized", HttpStatus.OK);
    }

    @GetMapping("/main")
    public ResponseEntity<String> main() {
        return new ResponseEntity<String>("this is /user/main", HttpStatus.OK);
    }

}

package org.yemina.Controller;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.impl.DefaultClaims;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.yemina.Entities.*;
import org.yemina.Repository.CategoryRepository;
import org.yemina.Repository.RoleRepository;


import org.yemina.Repository.AccountRepository;
import org.yemina.Security.jwt.JwtUtils;
import org.yemina.Services.AccountDetailsImpl;
import org.yemina.Services.CustomerService;
import org.yemina.Services.ShopkeeperService;
import org.yemina.Services.StorageService;
import org.yemina.payload.rabbitmq.Producer;
import org.yemina.payload.request.LoginRequest;
import org.yemina.payload.request.SignupRequestShopKeeper;
import org.yemina.payload.request.SignupRequestCustomer;
import org.yemina.payload.request.SignupRequestfacebookAndGoogle;
import org.yemina.payload.response.JwtResponse;
import org.yemina.payload.response.MessageResponse;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    CustomerService customerService;
    @Autowired
    ShopkeeperService shopkeeperService;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    Producer producer;

    @Autowired
    private StorageService storageService;

    @DeleteMapping("/suup/{id}")
    public void deleteCategory(@PathVariable String id){
        accountRepository.deleteAccountById(id);
    }

    @PostMapping("/upload")
    public ResponseEntity<MessageResponse> uploadFile(@RequestParam(value = "file") MultipartFile file, @RequestParam(value = "fileName")String fileName) {
         String imageURL="not uploaded";
         try{
             imageURL = storageService.uploadFile(file,fileName);
             return new ResponseEntity<MessageResponse>(new MessageResponse ("Done"), HttpStatus.OK);
         }catch(Exception e) {
                e.printStackTrace();
        }
        return null; 
        //new ResponseEntity<String>(storageService.uploadFile(file,fileName), HttpStatus.OK);
    }
    


    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        AccountDetailsImpl accountDetails = (AccountDetailsImpl) authentication.getPrincipal();
        List<String> roles = accountDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        System.out.println("t3ada3");
        Account U= accountRepository.findById(accountDetails.getId()).get();
        if (U.getShopKeeper() == null){
            return ResponseEntity.ok(new JwtResponse(jwt,
                    accountDetails.getId(),
                    accountDetails.getUsername(),
                    loginRequest.getEmail(),
                    roles,
                    U.getCustomer().getId()));
        }else {
            return ResponseEntity.ok(new JwtResponse(jwt,
                    accountDetails.getId(),
                    accountDetails.getUsername(),
                    loginRequest.getEmail(),
                    roles,
                    U.getShopKeeper().getId()));
        }

    }

    /*@RequestMapping(value = "/refreshtoken", method = RequestMethod.GET)
    public ResponseEntity<?> refreshtoken(@Valid @RequestBody LoginRequest loginRequest) throws Exception {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.doGenerateRefreshToken(authentication);

        AccountDetailsImpl accountDetails = (AccountDetailsImpl) authentication.getPrincipal();
        List<String> roles = accountDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                accountDetails.getId(),
                accountDetails.getUsername(),
                loginRequest.getEmail(),
                roles));
    }*/

    @Autowired
    ServletContext context;

    public void pictureupload(MultipartFile logo,MultipartFile patente,String username,ShopKeeper shop) {
        System.out.println("Ok .............");



        /*boolean isExit = new File(context.getRealPath("/WEB-INF/images/")).exists();
        if (!isExit)
        {
            new File (context.getRealPath("/WEB-INF/images/")).mkdir();
            System.out.println("mk dir.............");
        }*/
        String logoName = logo.getOriginalFilename();
        String logoURL="";
        Date date= new Date();
        if (!logoName.equals("noLogo.jpg")) {
            logoName = username + "-" +date+ "."+ FilenameUtils.getExtension(logoName);
            logoName = logoName.replaceAll("\\s", "");
            //File logoImage = new File(context.getRealPath("/WEB-INF/images/" + File.separator + logoName));
            try
            {System.out.println("add logo image");
                    //FileUtils.writeByteArrayToFile(logoImage, logo.getBytes());
                logoURL=  storageService.uploadFile(logo, logoName);
                System.out.println("url logo"+logoURL);
            }catch(Exception e) {
                e.printStackTrace();
            }

        }

        String patenteName = patente.getOriginalFilename();
        patenteName = username+"Patente-"+date+"."+FilenameUtils.getExtension(patenteName);
        //File patenteImage = new File (context.getRealPath("/WEB-INF/images/"+File.separator+patenteName));
        patenteName = patenteName.replaceAll("\\s", "");
        //String patenteURL="";
        try
        {
            System.out.println("Image");
            System.out.println("add patente image");
            //FileUtils.writeByteArrayToFile(patenteImage,patente.getBytes());
            storageService.uploadFile(patente, patenteName);
        }catch(Exception e) {
            e.printStackTrace();
        }


        shop.setLogo(logoName);
        shop.setPatente(patenteName);
        ShopKeeper shopKeeper = shopkeeperService.add(shop);


    }

    @GetMapping("/signup/customer/getAll")
    Collection<Customer> getAll() {
        return customerService.getAll();
    }

    @GetMapping("/signup/shop/getAll")
    Collection<ShopKeeper> getAllshop() {
        return shopkeeperService.getAll();

    }
    @PostMapping("/signup/customer")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequestCustomer signUpRequest) {

        if (accountRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("ERROR_USERNAME_TAKEN"));
        }

        if (accountRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("ERROR_EMAIL_TAKEN"));
        }


            // Create new user's account
            Date today = new Date();

            Account account = new Account(today, signUpRequest.getUsername(),
                    signUpRequest.getEmail(),
                    encoder.encode(signUpRequest.getPassword()));

            Set<Role> roles = new HashSet<>();

            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);

            account.setRoles(roles);

            Customer customer = new Customer();
            customer.setFirstName(signUpRequest.getFirstName());
            customer.setLastName(signUpRequest.getLastName());
            customer.setCddress(signUpRequest.getCddress());
            customer.setContry(signUpRequest.getContry());
            customer.setCity(signUpRequest.getCity());
            customer.setPhoneNumber(signUpRequest.getPhoneNumber());
            customer.setGender(signUpRequest.getGender());
            customer.setPostalCode(signUpRequest.getPostalCode());
            customer.setStatus(signUpRequest.getStatus());

            customer.setBirthDay(signUpRequest.getBirthday());

            customerService.add(customer);
            account.setCustomer(customer);
        /*
        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
            */
            Account customerAccount = accountRepository.save(account);
            customer.setAccount(customerAccount);
            Customer savedCustomer = customerService.update(customer);
            producer.produceNewCustomer(savedCustomer);
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(account.getUsername(), signUpRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);
            AccountDetailsImpl accountDetails = (AccountDetailsImpl) authentication.getPrincipal();
            List<String> roleess = accountDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
            String idAccountCustomer = accountRepository.findAccountByEmail(accountDetails.getEmail()).getId();
             return ResponseEntity.ok(new JwtResponse(jwt,
                     idAccountCustomer,
                    accountDetails.getUsername(),
                    accountDetails.getEmail(),
                    roleess,
                    savedCustomer.getId()));
        }
    @PostMapping("/signup/shpkeeper")
    public ResponseEntity<?> registerSopkeeper(@RequestParam("shopKeeper")  String shop,@RequestParam("file") MultipartFile file,@RequestParam("patente") MultipartFile patente  )throws JsonParseException, JsonMappingException, Exception
    {
        SignupRequestShopKeeper signUpRequest = new ObjectMapper().readValue(shop, SignupRequestShopKeeper.class);
        if (accountRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("ERROR_USERNAME_TAKEN"));
        }

        if (accountRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("ERROR_EMAIL_TAKEN"));
        }
        // Create new user's account
        Date today = new Date();

        Account account = new Account(today, signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        ShopKeeper shopKeeper = new ShopKeeper();

        shopKeeper.setBrandName(signUpRequest.getBrandName());
        shopKeeper.setTaxIdentificationNumber(signUpRequest.getTaxIdentificationNumber());

        shopKeeper.setCategory(signUpRequest.getCategory());
        shopKeeper.getCategory().forEach(categ -> {
            categoryRepository.save(categ);
        });
        shopKeeper.setAddress(signUpRequest.getState());
        shopKeeper.setContry(signUpRequest.getCountry());
        shopKeeper.setCity(signUpRequest.getCity());
        shopKeeper.setPhoneNumber(signUpRequest.getPhoneNumber());
        shopKeeper.setTva(signUpRequest.getTva());
        shopKeeper.setIs_Activate(signUpRequest.isIs_Activate());

        ShopKeeper saved =shopkeeperService.add(shopKeeper);
        saved.getCategory().forEach(categ -> {
            categ.getShopKeepers().add(saved);
            categoryRepository.save(categ);
        });

        account.setShopKeeper(shopKeeper);
        Set<Role> roles = new HashSet<>();

        Role userRole = roleRepository.findByName(ERole.ROLE_SHOPKEEPER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(userRole);

        account.setRoles(roles);
        Account shopkeeperAccount = accountRepository.save(account);
            saved.setAccount(shopkeeperAccount);
            shopkeeperService.update(saved);

        String filename = file.getOriginalFilename();
        pictureupload(file,patente,account.getUsername(),shopKeeper);
       // String patentename = patente.getOriginalFilename();
        //System.out.println("wsell :"+FilenameUtils.getExtension(filename));

        //pictureupload(patente,account.getUsername()+"patente",shopKeeper);
        this.producer.produceNewShopkeeper(shopKeeper);
        System.out.println(account.getPassword());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(account.getUsername(), signUpRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        AccountDetailsImpl accountDetails = (AccountDetailsImpl) authentication.getPrincipal();
        List<String> roleess = accountDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        String idShop = accountRepository.findAccountByEmail(accountDetails.getEmail()).getShopKeeper().getId();

        return ResponseEntity.ok(new JwtResponse(jwt,
                idShop,
                accountDetails.getUsername(),
                accountDetails.getEmail(),
                roleess,
                saved.getId()));
    }


    @PostMapping("/signup/google-facebook")
    public ResponseEntity<?> signUpGoogleAndFacebook(@RequestParam("signup")String signUp,@RequestParam("image") MultipartFile image ) throws JsonParseException, JsonMappingException, Exception{

        SignupRequestfacebookAndGoogle signUpRequest = new ObjectMapper().readValue(signUp, SignupRequestfacebookAndGoogle.class);
        if (accountRepository.existsByEmail(signUpRequest.getEmail())) {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(signUpRequest.getEmail(), signUpRequest.getEmail()));

            System.out.println("t3ada1");
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);
            AccountDetailsImpl accountDetails = (AccountDetailsImpl) authentication.getPrincipal();

            String idCustomer = accountRepository.findAccountByEmail(accountDetails.getEmail()).getCustomer().getId();
            List<String> roleess = accountDetails.getAuthorities().stream()
                    .map(item -> item.getAuthority())
                    .collect(Collectors.toList());

            Account U= accountRepository.findById(accountDetails.getId()).get();
            return ResponseEntity.ok(new JwtResponse(jwt,
                    accountDetails.getId(),
                    accountDetails.getUsername(),
                    accountDetails.getEmail(),
                    roleess,
                    U.getCustomer().getId()));


        } else {


            // Create new user's account
            LocalDate today = LocalDate.now();

            Account account = new Account(java.sql.Date.valueOf(today), signUpRequest.getEmail());
            account.setUsername(signUpRequest.getEmail());

            account.setPassword(encoder.encode(signUpRequest.getEmail()));
            Set<Role> roles = new HashSet<>();

            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);

            account.setRoles(roles);
            Date date= new Date();
            String nameImage = signUpRequest.getFirstName()+date+"."+FilenameUtils.getExtension(image.getOriginalFilename());
            try{

                System.out.println("Name : "+nameImage);
            storageService.uploadFile(image,nameImage);}
            catch (Exception e){
                System.out.println("error"+e);
            }
            Customer customer = new Customer();
            customer.setFirstName(signUpRequest.getFirstName());
            customer.setLastName(signUpRequest.getLastName());
            customer.setImageName(nameImage);

            customerService.add(customer);
            account.setCustomer(customer);


            Account acc =accountRepository.save(account);
            customer.setAccount(acc);
            Customer savedCustomer = customerService.update(customer);
            producer.produceNewCustomer(savedCustomer);
            System.out.println("account  : " + acc.getPassword() + " / " + acc.getUsername());
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(account.getUsername(), account.getUsername()));

            System.out.println("t3ada1");
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);
            AccountDetailsImpl accountDetails = (AccountDetailsImpl) authentication.getPrincipal();
            
            String idCustomer = accountRepository.findAccountByEmail(accountDetails.getEmail()).getCustomer().getId();
            List<String> roleess = accountDetails.getAuthorities().stream()
                    .map(item -> item.getAuthority())
                    .collect(Collectors.toList());
            Account U= accountRepository.findById(accountDetails.getId()).get();
            return ResponseEntity.ok(new JwtResponse(jwt,
                    accountDetails.getId(),
                    accountDetails.getUsername(),
                    accountDetails.getEmail(),
                    roleess,
                    U.getCustomer().getId()));

        }


    }

}

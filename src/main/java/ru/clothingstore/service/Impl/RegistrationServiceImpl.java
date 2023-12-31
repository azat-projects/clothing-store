package ru.clothingstore.service.Impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clothingstore.model.cart.Cart;
import ru.clothingstore.model.user.Reputation;
import ru.clothingstore.model.user.User;
import ru.clothingstore.repository.UserRepository;
import ru.clothingstore.service.MailService;
import ru.clothingstore.service.RegistrationService;
import ru.clothingstore.service.RoleService;

import java.util.Date;
import java.util.UUID;

@Service
public class RegistrationServiceImpl implements RegistrationService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final MailService mailService;
    private final PasswordEncoder passwordEncoder;
    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationService.class);

    @Autowired
    public RegistrationServiceImpl(UserRepository userRepository, RoleService roleService, MailServiceImpl mailService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.mailService = mailService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void register(User user) {
        user.setRole(roleService.getRoleByName("ROLE_USER"));
        user.setActive(true);
        user.setCreatedAt(new Date());
        user.setCart(new Cart());
        user.setReputation(Reputation.NORMAL);
        user.setActivationCode(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        LOGGER.info("User {} was registered successfully", user.getUsername());

        if (!user.getEmail().isEmpty()) {
            String message = String.format(
                    "Hello, %s! \n" +
                            "Welkome to ClothingStore. Please, visit next link: http://localhost:8083/auth/activate/%s",
                    user.getUsername(),
                    user.getActivationCode()
            );

            mailService.sendEmail(user.getEmail(), "Activation code", message);
        }
    }
}

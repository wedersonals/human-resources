package br.wals.hroauth.services;

import br.wals.hroauth.entities.User;
import br.wals.hroauth.feignclients.UserFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserFeignClient userFeignClient;
    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserService(UserFeignClient userFeignClient) {
        this.userFeignClient = userFeignClient;
    }

    public User findByEmail(String email) {
        var user = userFeignClient.findByEmail(email);
        if (user.getBody() == null) {
            logger.error("Email not found: " + email);
            throw new IllegalArgumentException("Email not found");
        }
        logger.error("Email found: " + email);
        return user.getBody();
    }
}

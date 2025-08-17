package com.learnerNrunnerBE.learnerNrunnerBE.global.util;

import com.learnerNrunnerBE.learnerNrunnerBE.domain.user.entity.User;
import com.learnerNrunnerBE.learnerNrunnerBE.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CheckServiceImpl implements CheckService {
    private final UserRepository userRepository;

    @Override
    public boolean checkUser(User user){
        if (userRepository.existsById(user.getId())){
            return true;
        }
        return false;
    }
}

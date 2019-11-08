package home_accounting.services;

import home_accounting.entity.CustomUser;
import home_accounting.entity.PlanGains;
import home_accounting.repository.PlanGainsRepository;
import home_accounting.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PlainGainsService {
    @Autowired
    private PlanGainsRepository planGainsRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<PlanGains> getAll() {
        return planGainsRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<PlanGains> getAllForUser(String login) {
        return planGainsRepository.findByLogin(login);
    }

    @Transactional
    public boolean add(String login, String description, Long value) {

        if (!userRepository.existsByLogin(login))
            return false;

        CustomUser customUser =  userService.findByLogin(login);

        PlanGains planGain = new PlanGains(customUser, description, value);
        planGainsRepository.save(planGain);

        return true;
    }

    @Transactional
    public void delete(long[] ids) {
        if (ids == null) return;

        for (long id : ids) {
            planGainsRepository.delete(id);
        }
    }
}

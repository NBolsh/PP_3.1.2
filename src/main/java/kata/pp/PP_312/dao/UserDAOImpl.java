package kata.pp.PP_312.dao;

import kata.pp.PP_312.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void addUser(User user) {
        em.merge(user);
        em.flush();
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        String jpql = "SELECT u From User u";
        TypedQuery<User> query = em.createQuery(jpql, User.class);
        return query.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public User findUserById(Long id) {
        return em.find(User.class, id);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        em.remove(em.find(User.class, id));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findUserByEmail(String email){
        String que = "SELECT u From User u where u.email = :email";

        return Optional.ofNullable(em.createQuery(que, User.class).setParameter("email", email)
                .getSingleResult());
    }


}

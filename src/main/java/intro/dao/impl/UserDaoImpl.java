package intro.dao.impl;

import intro.dao.UserDao;
import intro.exception.DataProcessingException;
import intro.model.User;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.save(user);
        } catch (Exception e) {
            throw new DataProcessingException("Can't save user", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<User> listUsers() {
        String hql = "FROM User";
        try (Session session = sessionFactory.openSession()) {
            Query<User> query = session.createQuery("FROM User", User.class);
            return query.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get list of users", e);
        }
    }
}

package intro.dao.impl;

import intro.dao.UserDao;
import intro.model.User;
import java.util.List;
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
        sessionFactory.openSession().save(user);
    }

    @Override
    public List<User> listUsers() {
        String hql = "FROM User";
        Query<User> query = sessionFactory.openSession().createQuery("FROM User", User.class);
        return query.getResultList();
    }
}

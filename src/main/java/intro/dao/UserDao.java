package intro.dao;

import intro.model.User;
import java.util.List;

public interface UserDao {
    public void add(User user);

    public List<User> listUsers();
}

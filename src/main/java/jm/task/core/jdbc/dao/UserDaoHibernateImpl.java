package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    Session session;

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {

    }

    @Override
    public void dropUsersTable() {

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        session = Util.getSessionFactory().getCurrentSession();
        session.beginTransaction();

    }

    @Override
    public void removeUserById(long id) {

    }

    @Override
    public List<User> getAllUsers() {
        session = Util.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<User> userList = session.createQuery("from User", User.class).list();
        session.getTransaction().commit();
        return userList;
    }

    @Override
    public void cleanUsersTable() {

    }
}

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
        session = Util.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.createNativeQuery("""
                CREATE TABLE IF NOT EXISTS Users(
                    id BIGSERIAL PRIMARY KEY,
                    name VARCHAR(50),
                    last_name VARCHAR(50),
                    age SMALLINT
                );""").executeUpdate();
        session.getTransaction().commit();


    }

    @Override
    public void dropUsersTable() {
        session = Util.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.createNativeQuery("DROP TABLE IF EXISTS Users").executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        session = Util.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(new User(name, lastName, age));
        session.getTransaction().commit();
    }

    @Override
    public void removeUserById(long id) {
        session = Util.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.delete(session.get(User.class, id));
        session.getTransaction().commit();
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
        session = Util.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.createQuery("delete from User").executeUpdate();
        session.getTransaction().commit();
    }
}

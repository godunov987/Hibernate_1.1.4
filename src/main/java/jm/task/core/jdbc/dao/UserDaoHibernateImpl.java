package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getSessionFactory;

public class UserDaoHibernateImpl implements UserDao {
    private final SessionFactory sessionFactory = getSessionFactory();

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        String sql = "CREATE TABLE IF NOT EXISTS users1 " +
                "(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(50) NOT NULL, lastName VARCHAR(50) NOT NULL, " +
                "age TINYINT NOT NULL)";

        session.createSQLQuery(sql).executeUpdate();

        transaction.commit();
        session.close();


    }

    @Override
    public void dropUsersTable() {
        Session session = getSessionFactory().openSession();
        Transaction tranz = session.beginTransaction();
        String sql = "DROP TABLE IF EXISTS users1";
        session.createSQLQuery(sql).executeUpdate();
        tranz.commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(new User(name, lastName, age));
            session.getTransaction().commit();
            System.out.println(" User с именем – " + name + " добавлен в базу данных ");
        } catch (Exception n) {
            session.getTransaction().rollback();
            System.out.println("Не удалось сохранить пользователя: " + name);
        } finally {
            if (session != null) session.close();
        }
    }


    @Override
    public void removeUserById(long id) {
        Session session = getSessionFactory().openSession();
        Transaction Tranz1 = session.beginTransaction();
        String sql = "delete from users1 where id=" + id;
        int i = session.createSQLQuery(sql).executeUpdate();
        Tranz1.commit();
        session.close();

    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = getSessionFactory().openSession()) {
            String hql = "FROM User";
            session.createQuery(hql);
            return session.createQuery(hql, User.class).list();
        } catch (NullPointerException n) {
            return new ArrayList<>(1);
        }
    }





    @Override
    public void cleanUsersTable() {
        Session session = getSessionFactory().openSession();
        Transaction tranz = session.beginTransaction();
        String sql = "TRUNCATE TABLE users1";
        session.createSQLQuery(sql).executeUpdate();
        tranz.commit();
        session.close();

    }
}
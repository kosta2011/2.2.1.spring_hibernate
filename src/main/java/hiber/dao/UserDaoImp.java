package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
      sessionFactory.getCurrentSession().save(user.getCar());
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }



   @Override
   @SuppressWarnings("unchecked")
   public User getUserToCar(String model, int series) {
      String bd = "from User user where user.userCar.model = :model and user.userCar.series = :series";
      User user = (User) sessionFactory.getCurrentSession().createQuery(bd).setParameter("model", model)
              .setParameter("series", series).setMaxResults(1).getSingleResult();
      return user;
   }


}

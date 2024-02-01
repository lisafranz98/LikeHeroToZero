import com.example.likeherotozero.entity.Co2EmissionsEntity;
import jakarta.persistence.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            Query q = entityManager.createQuery("select e from Co2EmissionsEntity e where e.amountValue != 0");
            q.setMaxResults(10);
            List<Co2EmissionsEntity> resultList = q.getResultList();
            for (Co2EmissionsEntity entity : resultList) {
                System.out.println(entity.toString());
            }

            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}

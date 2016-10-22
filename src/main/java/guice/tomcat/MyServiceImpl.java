package guice.tomcat;

import com.google.inject.Provider;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import java.util.List;

public class MyServiceImpl implements MyService {

    // Transactions doesn't start if EntityManager is directly injected via @Inject.
    // I have no idea why...

    // According to https://github.com/google/guice/wiki/Scopes,
    // "Note that if you make MyService a @Singleton, then you should inject Provider<EntityManager> instead."
    @Inject
    private Provider<EntityManager> emp;

    @Override
    // @javax.transaction.Transactional is not supported yet. https://github.com/google/guice/issues/797
    @com.google.inject.persist.Transactional
    public void save(MyEntity e) {
        EntityManager em = emp.get();
        em.persist(e);
   }

    @Override
    @com.google.inject.persist.Transactional
	public void delete(MyEntity e) {
        EntityManager em = emp.get();
//        em.remove(e);
        
        TypedQuery<MyEntity> q  = em.createNamedQuery("MyEntity.deleteById", MyEntity.class);
        q.setParameter("id", e.getId());
        int records = q.executeUpdate();
        
        System.out.println("deleted "+ records + " records");
	}

	@Override
    public List<MyEntity> findAll() {
        return emp.get().createNamedQuery("MyEntity.findAll", MyEntity.class).getResultList();
    }
}

package ejb;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entity.Property;

@Singleton
@Remote(SingletonBeanRemote.class)
public class SingletonBeanRemoteImpl implements SingletonBeanRemote {

    private List<Property> cache;

    @PersistenceContext(unitName = "default")
    private EntityManager em;

    @Override
    public void delete() {
        Query query = em.createQuery("delete from entity.Property");
        query.executeUpdate();
        this.cache.clear();
    }

    @Override
    public void put(String key, String value) {
        Property p = new Property();
        p.setKey(key);
        p.setValue(value);
        em.persist(p);
        this.cache.add(p);
    }

    @Override
    public List<Property> getCache() {
        return cache;
    }

    @Override
    public List<Property> queryCache() {
        Query query = em.createQuery("from entity.Propery");
        List<Property> list = query.getResultList();
        return list;
    }

}

package ejb;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entity.Property;

@Singleton
public class SingletonBean {
    private List<Property> cache;
    
    @PersistenceContext(unitName="default")
    private EntityManager em;
    
    @PostConstruct
    public void initCache() {
        this.cache = queryCache();
        if (cache == null)
        cache = new ArrayList<>();
    }
    
    public void delete() {
        Query query = em.createQuery("delete from entity.Property");
        query.executeUpdate();
        this.cache.clear();
    }
    
    public void put(String key, String value) {
        Property p = new Property();
        p.setKey(key);
        p.setValue(value);
        em.persist(p);
        this.cache.add(p);
    }
    
    public List<Property> getCache() {
        return cache;
    }

    public List<Property> queryCache() {
        Query query = em.createQuery("from entity.Propery");
        List <Property> list = query.getResultList();
        return list;
    }
    

}

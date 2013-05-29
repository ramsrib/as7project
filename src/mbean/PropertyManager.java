package mbean;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;

import ejb.SingletonBeanRemote;

@ManagedBean(name="manager")
public class PropertyManager {
    
    @EJB
    SingletonBeanRemote ejb;
    ArrayList cacheList = new ArrayList();
    
    private String key;
    private String value;
    
    public void save(ActionEvent e) {
        ejb.put(key, value);
    }
    
    public void clear(ActionEvent e) {
        System.out.println("Called Clear");
        ejb.delete();
    }
    
    public List getCacheList() {
        return ejb.getCache();
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}

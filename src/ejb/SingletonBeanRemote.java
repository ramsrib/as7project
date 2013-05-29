package ejb;

import java.util.List;

import entity.Property;

public interface SingletonBeanRemote {

    public void delete();

    public void put(String key, String value);

    public List<Property> getCache();
    
    public List<Property> queryCache();    

}
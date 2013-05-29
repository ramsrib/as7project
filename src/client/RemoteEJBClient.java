package client;

import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ejb.SingletonBeanRemote;
import ejb.SingletonBeanRemoteImpl;
import entity.Property;

public class RemoteEJBClient {

    static {
        // Remoting framework uses SASL for client-server authentication
        // this'll allows anonymous to the remote server where EJBs located
        // Security.addProvider(new JBossSaslProvider());
    }

    public static void main(String[] args) throws NamingException {
        testRemoteEJB();
    }

    private static void testRemoteEJB() throws NamingException {
        final SingletonBeanRemote ejb = lookupEJB();
        System.out.println("Got remote singleton ejb.");
        // Adds a entry to the cache
        ejb.put("key", "value");
        // Retrives the cache & print it
        List<Property> list = ejb.getCache();
        System.out.println(list);

    }

    private static SingletonBeanRemote lookupEJB() throws NamingException {
        final Hashtable jndiProperties = new Hashtable();
        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.ejb.client.naming");
        final Context context = new InitialContext(jndiProperties);

        final String appName = "";
        final String moduleName = "as7Project";
        final String distinctName = "";
        final String beanName = SingletonBeanRemoteImpl.class.getSimpleName();
        final String viewClassName = SingletonBeanRemote.class.getName();
        System.out.println("ejb:" + appName + "/" + moduleName + "/" + distinctName + "/"
                + beanName + "!" + viewClassName);
        return (SingletonBeanRemote) context.lookup("ejb:" + appName + "/" + moduleName + "/" + distinctName + "/"
                + beanName + "!" + viewClassName);

    }

}

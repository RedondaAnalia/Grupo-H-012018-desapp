package persistence;

import persistence.repositories.Initializable;

public class ServiceInitializer {
    public ServiceInitializer(Initializable... services){
        for (Initializable o : services) {
            o.initialize();
        }
    }
}

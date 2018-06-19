package persistence.repositories;


import java.io.Serializable;
import java.util.List;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.apache.log4j.Logger;

public abstract class HibernateGenericDAO<T>
        extends HibernateDaoSupport implements
        GenericRepository<T>, Serializable {

    public static Logger log = Logger.getLogger(HibernateGenericDAO.class);
    private Class<T> persistentClass = this.getDomainClass();

    @SuppressWarnings("unchecked")
    public int count() {
        List<Long> list = (List<Long>) this.getHibernateTemplate()
                .find("select count(*) from " + this.persistentClass.getName() + " o");
        Long count = list.get(0);
        return count.intValue();

    }

    public void delete(final T entity) {
        try {
            this.getHibernateTemplate().delete(entity);
        } catch (RuntimeException e) {
            log.error(e);
        }
    }

    public void deleteById(final Serializable id) {
        T obj = this.findById(id);
        this.getHibernateTemplate().delete(obj);
    }

    public List<T> findAll() {
        return (List<T>) this.getHibernateTemplate().
                find("from " + this.persistentClass.getName() + " o");

    }

    public List<T> findByExample(final T exampleObject) {
        return this.getHibernateTemplate().findByExample(exampleObject);

    }

    public T findById(final Serializable id) {
        return this.getHibernateTemplate().get(this.persistentClass, id);
    }

    protected abstract Class<T> getDomainClass();

    public void save(final T entity) {
        this.getHibernateTemplate().merge(entity);
        this.getHibernateTemplate().flush();
    }

    public void update(final T entity) {
        this.getHibernateTemplate().update(entity);
    }

    public void deleteAll() {
        this.getHibernateTemplate().deleteAll(this.findAll());
    }

}
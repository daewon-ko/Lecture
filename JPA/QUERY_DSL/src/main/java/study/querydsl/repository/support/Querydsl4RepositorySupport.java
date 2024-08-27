//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package study.querydsl.repository.support;

import com.querydsl.core.dml.DeleteClause;
import com.querydsl.core.dml.UpdateClause;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.PathBuilderFactory;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPADeleteClause;
import com.querydsl.jpa.impl.JPAUpdateClause;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.Querydsl;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

@Repository
public abstract class Querydsl4RepositorySupport {
    private final PathBuilder<?> builder;
    @Nullable
    private EntityManager entityManager;
    @Nullable
    private Querydsl querydsl;

    public Querydsl4RepositorySupport(Class<?> domainClass) {
        Assert.notNull(domainClass, "Domain class must not be null");
        this.builder = (new PathBuilderFactory()).create(domainClass);
    }

    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        Assert.notNull(entityManager, "EntityManager must not be null");
        this.querydsl = new Querydsl(entityManager, this.builder);
        this.entityManager = entityManager;
    }

    @PostConstruct
    public void validate() {
        Assert.notNull(this.entityManager, "EntityManager must not be null");
        Assert.notNull(this.querydsl, "Querydsl must not be null");
    }

    @Nullable
    protected EntityManager getEntityManager() {
        return this.entityManager;
    }

    protected JPQLQuery<Object> from(EntityPath<?>... paths) {
        return this.getRequiredQuerydsl().createQuery(paths);
    }

    protected <T> JPQLQuery<T> from(EntityPath<T> path) {
        return this.getRequiredQuerydsl().createQuery(new EntityPath[]{path}).select(path);
    }

    protected DeleteClause<JPADeleteClause> delete(EntityPath<?> path) {
        return new JPADeleteClause(this.getRequiredEntityManager(), path);
    }

    protected UpdateClause<JPAUpdateClause> update(EntityPath<?> path) {
        return new JPAUpdateClause(this.getRequiredEntityManager(), path);
    }

    protected <T> PathBuilder<T> getBuilder() {
        return (PathBuilder<T>) this.builder;
    }

    @Nullable
    protected Querydsl getQuerydsl() {
        return this.querydsl;
    }

    private Querydsl getRequiredQuerydsl() {
        if (this.querydsl == null) {
            throw new IllegalStateException("Querydsl is null");
        } else {
            return this.querydsl;
        }
    }

    private EntityManager getRequiredEntityManager() {
        if (this.entityManager == null) {
            throw new IllegalStateException("EntityManager is null");
        } else {
            return this.entityManager;
        }
    }
}

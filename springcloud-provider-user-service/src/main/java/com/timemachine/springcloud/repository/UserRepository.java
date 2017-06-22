package com.timemachine.springcloud.repository;

import com.timemachine.springcloud.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * User Repository
 *
 * @author Liuguanglei tonytimemachine@gmail.com
 * @create 2017-06-下午2:54
 */
@Repository
public interface UserRepository  extends JpaRepository<User,Long>{


    /**
     *  and
     * @param id
     * @param name
     * @return
     */
    User findByIdAndName(Long id, String name);


    User findByNameAndPassword(String name, String password);

    /**
     *  or
     * @param id
     * @param name
     * @return
     */
    User findByIdOrName(Long id, String name);


    /**
     * between
     * @param start
     * @param end
     * @return
     */
    List<User> findByCreateDateBetween(Date start, Date end);


    /**
     * lessThan
     * @param start
     * @return
     */
    List<User> getByCreateDateLessThan(Date start);

    /**
     * Greater Than
     * @param start
     * @return
     */
    List<User> findByCreateDateGreaterThan(Date start);


    /**
     * is null
     * @return
     */
    List<User> findByNameIsNull();


    /**
     * in
     * @param nameList
     * @return
     */
    List<User> findByNameIn(Collection<String> nameList);




}

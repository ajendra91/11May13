package com.example.Jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping("/myjd")
public class MyjdController {

    @Autowired
    public JdbcTemplate jt;

    /*@PostConstruct
    public void add() {
        Myjd my1 =new Myjd(1,"java","1");
        Myjd my2 =new Myjd(2,"php","12");
        Myjd my3 =new Myjd(3,"dot","13");
        jt.update("insert into myjd values(?,?,?)", my1.id, my1.name, my1.age);
        jt.update("insert into myjd values(?,?,?)", my2.id, my2.name, my2.age);
        jt.update("insert into myjd values(?,?,?)", my3.id, my3.name, my3.age);

    }*/

    @RequestMapping("")
    public List<Myjd> getAll2(){
        return jt.query("select * from myjd", (rs, rowNum)->
                new Myjd(rs.getInt("id"),rs.getString("name"),
                        rs.getString("age")));
    }

    @RequestMapping("/{id}")
    public Myjd getId(@PathVariable int id){
        return jt.queryForObject("select * from myjd where id='"+ id +"' ", (rs, rowNum)->
                new Myjd(rs.getInt("id"),rs.getString("name"),
                        rs.getString("age")));
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public void add(Myjd my) {
        jt.update("insert into myjd values(?,?)", my.id, my.name, my.age);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public int updateEmployee(@RequestBody Myjd my,@PathVariable int id){
        String query="update myjd set name='"+ my.id +"',age='"+ my.name +"' where id='"+ my.age+"' ";
        return jt.update(query);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public int deleteEmployee(@PathVariable int id){
        String query="delete from myjd where id='"+ id +"' ";
        return jt.update(query);
    }

}


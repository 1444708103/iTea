package top.season66.itea.repository;

import org.apache.ibatis.type.EnumOrdinalTypeHandler;
import top.season66.itea.model.MenuItem;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.EnumOrdinalTypeHandler;

import java.util.List;


//不使用Money保存金额

@Mapper
public interface MenuItemMapper {
//    @Select("select count(*) from t_menu")
    long count();

//    @Insert("insert into t_menu (name, price, size, create_time, update_time) " +
//            "values (#{name}, #{price}, #{size}, now(), now())")
//    @Options(useGeneratedKeys = true, keyProperty = "id")
    int save(MenuItem menuItem);

//    @Update("update t_menu set name = #{name}, price = #{price}, size = #{size}, update_time = now() " +
//            "where id = #{id}")
    int update(MenuItem menuItem);

//    @Select("select * from t_menu where id = #{id}")
//    @Results(id = "menuItem", value = {
//            @Result(column = "id", property = "id", id = true),
//            @Result(column = "size", property = "size", typeHandler = EnumOrdinalTypeHandler.class),
//            @Result(column = "price", property = "price", typeHandler = MoneyTypeHandler.class),
//            @Result(column = "create_time", property = "createTime"),
//            @Result(column = "update_time", property = "updateTime")
//    })
    MenuItem findById(@Param("id") Long id);

//    @Delete("delete from t_menu where id = #{id}")
    int deleteById(@Param("id") Long id);

    @Select("select * from t_menu")
    @ResultMap("menuItem")
    List<MenuItem> findAll();

//    @Select("select m.* from t_menu m, t_order_item i where m.id = i.item_id and i.order_id = #{orderId}")
//    @ResultMap("menuItem")
    List<MenuItem> findByOrderId(Long orderId);



    List<MenuItem> findByName(@Param("name") String name);

    List<MenuItem> findByIdList(List<Long> ids);

    int saveAll(List<MenuItem> items);
}

package com.taotao.manage.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.manage.pojo.BasePojo;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

public abstract class BaseService<T extends BasePojo> {
    @Autowired
    private Mapper<T> mapper;

    // 查询单条数据
    public T queryOne(T record){
        return mapper.selectOne(record);
    }

    // 根据全部数据
    public List<T> queryAll(){
        return mapper.selectAll();
    }

    // 根据id查询数据
    public T queryById(Long id){
        return mapper.selectByPrimaryKey(id);
    }

    // 根据条件查询数据
    public List<T> queryList(T record){
        return mapper.select(record);
    }

    // 分页查询数据
    public PageInfo<T> queryPageList(T record, Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<T> list = mapper.select(record);
        return new PageInfo<>(list);
    }

    // 新增数据
    public Integer save(T record){
        Date date = new Date();
        record.setCreated(date);
        record.setUpdated(date);
        return mapper.insert(record);
    }

    // 新增数据，使用非空字段
    public Integer saveSelective(T record){
        Date date = new Date();
        record.setCreated(date);
        record.setUpdated(date);
        return mapper.insertSelective(record);
    }

    // 修改数据
    public Integer updateById(T record){
        Date date = new Date();
        record.setUpdated(date);
        return mapper.updateByPrimaryKey(record);
    }

    // 修改数据，使用非空字段
    public Integer updateByIdSelective(T record){
        Date date = new Date();
        record.setUpdated(date);
        return mapper.updateByPrimaryKeySelective(record);
    }

    // 根据条件删除数据
    public Integer delete(T record){
        return mapper.delete(record);
    }

    // 根据id删除数据
    public Integer deleteById(Long id){
        return mapper.deleteByPrimaryKey(id);
    }

    // 删除多条数据
    public Integer deleteByIds(Class<T> clazz, String idColumn, List<Object> ids){
        Example example = new Example(clazz);
        example.createCriteria().andIn(idColumn, ids);
        return mapper.deleteByExample(example);
    }
}

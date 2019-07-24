package com.mysiteforme.admin.sysuser.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mysiteforme.admin.sysuser.dao.DictTypeDao;
import com.mysiteforme.admin.sysuser.entity.DictType;
import com.mysiteforme.admin.sysuser.entity.VO.TreeDict;
import com.mysiteforme.admin.sysuser.service.DictTypeService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Iwen
 * @date 2019/6/10 15:02
 * @Version 1.0
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class DictTypeServiceImpl extends ServiceImpl<DictTypeDao, DictType> implements DictTypeService {

    @Cacheable(value = "dictTypeCache",key = "#type",unless = "#result == null or #result.size() == 0")
    @Override
    public List<DictType> getDictByType(String type) {
        EntityWrapper<DictType> wrapper = new EntityWrapper<>();
        wrapper.eq("type",type);
        wrapper.orderBy("type");
        return selectList(wrapper);
    }


    @Override
    public List<TreeDict> showTreeDicts(String type, String name) {

        Map<String,Object> map = Maps.newHashMap();
        map.put("type", type);
        map.put("name", name);
        List<DictType> totalDicts = baseMapper.selectListByMap(map);

        List<TreeDict> treeDicts = Lists.newArrayList();
        return getZTree(totalDicts,treeDicts);
    }

    @Override
    public Integer getCountByType(String type) {
        EntityWrapper<DictType> wrapper = new EntityWrapper<>();
        wrapper.eq("type",type);
        return selectCount(wrapper);
    }

    @Override
    public Integer getCountByName(String name) {
        EntityWrapper<DictType> wrapper = new EntityWrapper<>();
        wrapper.eq("name",name);
        return selectCount(wrapper);
    }

    @CacheEvict(value = "dictTypeCache",key = "#dictType.type",condition = "#dictType.type ne null ")
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public void saveOrUpdateDict(DictType dictType) {
        insertOrUpdate(dictType);
    }

    @CacheEvict(value = "dictTypeCache",key = "#result",beforeInvocation = false,condition = "#result ne  null ")
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public String deleteDict(Long id) {
        DictType dictType = baseMapper.selectById(id);
        baseMapper.deleteById(id);
        return dictType.getType();
    }

    @CacheEvict(value = "dictTypeCache",key = "#type",beforeInvocation = false)
    @Override
    public List<DictType> saveDictList(String type, List<DictType> list) {
        insertBatch(list);
        return list;
    }

    @CacheEvict(value = "dictTypeCache",key = "#type",beforeInvocation = false)
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public void deleteByType(String type) {
        EntityWrapper<DictType> wrapper = new EntityWrapper<>();
        wrapper.eq("type",type);
        delete(wrapper);
    }

    @CacheEvict(value = "dictTypeCache",allEntries=true)
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public void updateByType(String oldType, String newType) {
        EntityWrapper<DictType> wrapper = new EntityWrapper<>();
        wrapper.eq("type",oldType);
        List<DictType> dicts = baseMapper.selectList(wrapper);
        for (DictType dict : dicts){
            dict.setType(newType);
        }
        updateBatchById(dicts);
    }

    /**
     * 递归拉取字典树的数据
     */
    private  List<TreeDict> getZTree(List<DictType> total, List<TreeDict> result){
        for (DictType dt : total){
            TreeDict treeDict = new TreeDict(false);
            treeDict.setId(dt.getId());
            treeDict.setType(dt.getType());
            treeDict.setName(dt.getName());
            treeDict.setChildren(new ArrayList<>(dt.getDicts()));
            result.add(treeDict);
        }
        return result;
    }
}

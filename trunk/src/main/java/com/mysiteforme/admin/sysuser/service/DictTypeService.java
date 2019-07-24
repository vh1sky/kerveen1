package com.mysiteforme.admin.sysuser.service;

import com.baomidou.mybatisplus.service.IService;
import com.mysiteforme.admin.sysuser.entity.DictType;
import com.mysiteforme.admin.sysuser.entity.VO.TreeDict;

import java.util.List;

/**
 * @author Iwen
 * @date 2019/6/10 14:58
 * @Version 1.0
 */
public interface DictTypeService extends IService<DictType> {

        List<DictType> getDictByType(String type);

        Integer getCountByName(String name);

        List<TreeDict> showTreeDicts(String type, String name);

        Integer getCountByType(String type);

        void saveOrUpdateDict(DictType dictType);

        String deleteDict(Long id);

        List<DictType> saveDictList(String type, List<DictType> list);

        void deleteByType(String s);

        void updateByType(String oldType,String newType);
}

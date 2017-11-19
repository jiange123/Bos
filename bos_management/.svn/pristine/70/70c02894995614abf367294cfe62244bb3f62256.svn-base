package com.itheima.service.Impl.base;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.dao.base.SubareaRepository;
import com.itheima.domain.base.SubArea;
import com.itheima.service.base.SubareaService;

/**  
 * ClassName:SubareaServiceImpl <br/>  
 * Function:  <br/>  
 * Date:     2017年11月4日 下午9:08:29 <br/>       
 */

@Transactional
@Service
public class SubareaServiceImpl implements SubareaService {
    @Autowired
    private SubareaRepository subareaRepository;

    @Override
    public void save(SubArea model) {
        subareaRepository.save(model);  
    }

    @Override
    public Page pageQuery(Pageable pageRequest) {
        Page<SubArea> page = subareaRepository.findAll(pageRequest);  
        return page;
    }

    @Override
    public List<SubArea> findSubareaByNull() {
        List<SubArea> list=subareaRepository.subareaRepository(); 
        return list;
    }

    @Override
    public List<SubArea> findSubareaById(Long id) {
        List<SubArea> list=subareaRepository.findSubareaById(id); 
        return list;
    }

    @Override
    public void assignSubArea2FixedArea(Long id, List<Long> subAreaIds) {
          //先解绑
        subareaRepository.updateSubareaById(id);
          //再关联
        if(subAreaIds!=null&&subAreaIds.size()>0){
            for (Long subAreaId : subAreaIds) {
                subareaRepository.assignSubArea2FixedArea(id, subAreaId);
            }
        }
        
    }

    @Override
    public Page<SubArea> findSubareaByFixedareaId(PageRequest pageRequest,
            Specification<SubArea> specification) {
        Page<SubArea> page = subareaRepository.findAll(specification, pageRequest);  
        return page;
    }
    
}
  

package com.itheima.dao.base;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.itheima.domain.base.SubArea;

/**  
 * ClassName:SubareaRepository <br/>  
 * Function:  <br/>  
 * Date:     2017年11月4日 下午9:10:42 <br/>       
 */
public interface SubareaRepository extends JpaRepository<SubArea, Long> , JpaSpecificationExecutor<SubArea>{

    @Query("from SubArea where fixedArea is null")
    List<SubArea> subareaRepository();
    
    //@Query("from SubArea where fixedArea.id=?")
    @Query(value="select * from T_SUB_AREA s inner join T_FIXED_AREA f on s.C_FIXEDAREA_ID=f.C_ID where f.C_ID=?",
            nativeQuery=true)
    List<SubArea> findSubareaById(Long id);
    
//    @Modifying
//    @Query("update SubArea set fixedArea=null where fixedArea.id=?")
//    @Modifying
//    @Query(value="update T_SUB_AREA s set s.C_FIXEDAREA_ID=null where (SELECT f.C_ID FROM T_FIXED_AREA f WHERE s.C_FIXEDAREA_ID=f.C_ID)=?",
//            nativeQuery=true)
    @Modifying
    @Query(value="update T_SUB_AREA s set s.C_FIXEDAREA_ID=null where s.C_FIXEDAREA_ID=?",
          nativeQuery=true)
    void updateSubareaById(Long id);
    
//    @Modifying
//    @Query("update SubArea set fixedArea.id=? where id=?")
//    @Modifying
    @Query(value="update T_SUB_AREA s set s.C_FIXEDAREA_ID=? where s.C_ID=?",
          nativeQuery=true)
    void assignSubArea2FixedArea(Long id, Long subAreaId);

}
  

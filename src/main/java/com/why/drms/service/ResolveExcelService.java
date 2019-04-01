package com.why.drms.service;

import com.why.drms.entity.StudentEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author <a href="zhuyichen">Zhu Yichen</a>
 * @version 1.0
 * @date 2019年04月01日 15:08
 * @desc ResolveExcelService 解析excel
 */
public interface ResolveExcelService {
    List<StudentEntity> resolveExcel(MultipartFile file);
}

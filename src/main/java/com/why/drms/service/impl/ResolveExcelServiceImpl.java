package com.why.drms.service.impl;

import com.why.drms.entity.StudentEntity;
import com.why.drms.enums.SystemErrorEnum;
import com.why.drms.exception.DrmsException;
import com.why.drms.service.ResolveExcelService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="zhuyichen">Zhu Yichen</a>
 * @version 1.0
 * @date 2019年04月01日 14:54
 * @desc ResolveExcelServiceImpl 解析Excel
 */
@Service
@Transactional
@Slf4j
public class ResolveExcelServiceImpl implements ResolveExcelService {
    private static final String SUFFIX_2003 = ".xls";
    private static final String SUFFIX_2007 = ".xlsx";

    @Override
    public List<StudentEntity> resolveExcel(MultipartFile file) throws DrmsException {
        if (file == null ){
            throw new DrmsException(SystemErrorEnum.FILE_NULL);
        }
        String fname = file.getOriginalFilename();
        Workbook workbook = null;
        List<StudentEntity> list = new ArrayList<>();
        try {
            assert fname != null;
            if(fname.endsWith(SUFFIX_2003)){
                workbook = new HSSFWorkbook(file.getInputStream());
            }else if(fname.endsWith(SUFFIX_2007)){
                workbook = new XSSFWorkbook(file.getInputStream());
            }
        }catch (Exception e){
            throw new DrmsException(SystemErrorEnum.FILE_TYPE_ERROR);
        }
        if (workbook == null){
            throw new DrmsException(SystemErrorEnum.FILE_TYPE_ERROR);
        }else{
            int workbookNum = workbook.getNumberOfSheets();
            for(int i=0;i<workbookNum;i++){
                Sheet sheet = workbook.getSheetAt(i);
                int sheetNum = sheet.getLastRowNum();
                for(int j = 1;j<=sheetNum;j++){
                    Row row = sheet.getRow(j);
                    int rowNum = row.getPhysicalNumberOfCells();
                    List<String> data = new ArrayList<String>();
                    for (int k=0;k<rowNum;k++){
                        Cell cell = row.getCell(k);
                        if (cell == null){
                            break;
                        }
                        data.add(cell.toString());
                    }
                    if (data.size()<rowNum){
                        continue;
                    }
                    try {
                        list.add(new StudentEntity(data));
                    }catch (Exception e){
                        log.info("[Excel构造数据异常]{}",e);
                        throw  new DrmsException(SystemErrorEnum.FILE_READ_ERROR);
                    }
                }
            }
        }
        if (list.size()==0){
            throw new DrmsException(SystemErrorEnum.FILE_DATA_ERROR);
        }
        return list;
    }
}
